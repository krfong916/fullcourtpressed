const state = Object.freeze({
  PENDING: "pending",
  FULFILLED: "fulfilled", // settled
  REJECTED: "rejected", // settled
});

const isThenable = (x) => x && isFunction(x.then);
const isFunction = (x) => typeof x == "function";

/**
 * @desc Implementation of the Promises/A+ specification.
 *       A promise represents an asynchronous operation.
 *       The user can expect a set of defined, deterministic behaviors for non-deterministic actions
 *       The user interacts with the value returned from a promise using the then() method
 *       The user defines and methods to handle the result of the callback:
 *       either the promise's eventual value, or the reason why the promise could not be fulfilled
 *
 * @param {[type]}   args     [description]
 * @param {Function} callback [description]
 */
function Promised(executor) {
  this.state = state.PENDING;
  this.value = undefined;
  this.exception = undefined;
  this.reason = undefined;

  // maintains the order of thenables to execute
  // promises queue microtasks when callbacks are added to a *settled* promise
  // they are also added when a promise settles for any callbacks that were added before that
  this._thenableQueue = [];
  this._finallyQueue = [];

  if (isFunction(executor)) {
    // setTimeout is a macrotask
    // we call setTimeout to ensure that we have a clean execution context before executing our promise
    setTimeout(() => {
      try {
        executor(this._onFulfilled.bind(this), this._onRejected.bind(this));
      } catch (err) {
        this._onRejected(err);
      }
    });
  }
}

/**
 * @desc A promise provides a "thenable",
 *       a way to interact with the result of the promise.
 *       This function allows us to chain promises
 *
 * @param  {any} onFulfilled transforms value if the promise is fulfilled
 *                           only values considered to be a function will be considered
 *                           called *after* the promise fulfills, and not before, and not more than once
 *
 * @param  {any} onRejected  transforms the reason if the promise is rejected
 *                           only values considered to be a function will be considered
 *                           called *after* the promise fulfills, and not before, and not more than once
 *
 * @return {[type]}             [description]
 */
Promised.prototype.then = function (fulfilledFn, catchFn) {
  const controlledPromise = new Promised();
  this._thenableQueue.push([controlledPromise, fulfilledFn, catchFn]);
  // if we are calling .then() on a promise that has already settled
  // notify all thenables of the new value
  if (this.state == state.FULFILLED) {
    this._propagateFulfilled();
  } else if (this.state == state.REJECTED) {
    this._propagateRejected();
  }

  // if the promise hasn't settled yet, the promise has to wait, return it
  return controlledPromise;
};

/**
 * @desc Like a special case of then, catch is a handler for when our promise is rejected
 * @param  {function || any} handles rejected promises, it will be called with the rejected value from the promise
 * @return {Promise}    returns a promise
 */
Promised.prototype.catch = function (catchFn) {
  return this.then(undefined, catchFn);
};

/**
 * @desc Runs only after the parent promise resolves or rejects (the parent promise settles)
 *       finally is a cleanup call
 * @param {function} sideEffectFn
 */
Promised.prototype.finally = function (sideEffectFn) {
  if (this.state !== state.PENDING) {
    sideEffectFn();

    return this.state == state.FULFILLED
      ? Promise.resolve(this.value)
      : Promise.reject(this.value);
  }

  const controlledPromise = new Promised();
  this._finallyQueue.push([controlledPromise, sideEffectFn]);
};

Promised.prototype._propagateRejected = function () {
  this._thenableQueue.forEach(([controlledPromise, _, catchFn]) => {
    // the catchFn is used as a handler for any errors
    if (isFunction(catchFn)) {
      const valueOrPromise = catchFn(this.reason);

      if (isThenable(valueOrPromise)) {
        valueOrPromise.then(
          (value) => controlledPromise._onFulfilled(value),
          (reason) => controlledPromise._onRejected(reason)
        );
      } else {
        // if a regular value
        // catch is used to recover from errors
        // catch is returning errors back into normal promises
        controlledPromise._onFulfilled(valueOrPromise);
      }
    } else {
      return controlledPromise._onRejected(this.reason);
    }
  });

  this._finallyQueue.forEach(([controlledPromise, sideEffectFn]) => {
    sideEffectFn();
    controlledPromise._onFulfilled(this.value);
  });

  this._thenableQueue = [];
  this._finallyQueue = [];
};

/**
 * @desc Communicate with the promises in the queue, inform them of new values
 * @return {[type]} [description]
 */
Promised.prototype._propagateFulfilled = function () {
  this._thenableQueue.forEach(([controlledPromise, fulfilledFn]) => {
    if (isFunction(fulfilledFn)) {
      // this.value refers to the value of the parent promise
      // wait until the promise settles, then use its value
      const valueOrPromise = fulfilledFn(this.value);

      if (isThenable(valueOrPromise)) {
        valueOrPromise.then(
          (value) => controlledPromise._onFulfilled(value),
          (reason) => controlledPromise._onRejected(reason)
        );
      } else {
        // if the promise has already settled and is a value, then call onFulfilled with the new value
        controlledPromise._onFulfilled(valueOrPromise);
      }
    } else {
      return controlledPromise._onFulfilled(this.value);
    }
  });

  this._finallyQueue.forEach(([controlledPromise, sideEffectFn]) => {
    sideEffectFn();
    controlledPromise._onFulfilled(this.value);
  });

  this._thenableQueue = [];
  this._finallyQueue = [];
};

// when called, they contain the value that we expect
Promised.prototype._onFulfilled = function (value) {
  // once a promise settles with a value/reason - the state cannot be changed
  // we can only act on a promise when it's in a pending state
  if (this.state == state.PENDING) {
    this.state = state.FULFILLED;
    this.value = value;

    // inform the promises in the queue of the new value
    this._propagateFulfilled();
  }
};

Promised.prototype._onRejected = function (reason) {
  if (this.state == state.PENDING) {
    this.state = state.REJECTED;
    this.reason = reason;
    this._propagateRejected();
  }
};

// For testing purposes: what happens if we return a promise?
// These promises are in a particular state
Promised.resolve = function (value) {
  return new Promised((resolve) => resolve(value));
};
Promised.reject = function (value) {
  return new Promised((_, reject) => reject(value));
};

const fs = require("fs");
const path = require("path");

const delay = (value) => {
  setTimeout(1000);
  return Promised.reject(value);
};

const readFile = (filename) => {
  return new Promised((resolve, reject) => {
    fs.readFile(path.resolve(__dirname, filename), (err, data) => {
      if (err) {
        return reject(err);
      }
      return resolve(data);
    });
  });
};

readFile("./promisify.js")
  .then((value) => {
    let count = 0;
    for (char in value) {
      count++;
    }
    console.log("Total number of characters: " + count);
  })
  .catch((err) => {
    console.log(err);
  })
  .finally((message) => {
    console.log("---- All done! ----");
  });

// const promise = new Promised((resolve, reject) => {
//   setTimeout(() => resolve(1), 1000);
//   throw new Error("error!");
// }).catch((err) => {
//   console.log(err);
//   return Promised.reject("recovered");
// });

// const firstThen = promise.then((val) => {
//   console.log("value = " + val);
//   return val + 1;
// });

// const secondThen = firstThen
//   .then((val) => {
//     console.log("value = " + val);
//     return val + 1;
//   })
//   .catch((err) => {
//     console.log("err", err);
//     return "finally";
//   });

// console.log(firstThen);

// export { Promised };

/*
  Q: How can we accept multiple optional callbacks? i.e. executor == (resolve, reject)
  A: we inject the methods as arguments to executor, bind the Promises's function object's execution context 

  Q: How can we make sure the execution context stack contains only platform code?
     I think that means that we need to schedule the code as a microtask/macrotask - a clean execution context
  A: The Promise/A+ specification allows tasks and microtasks such as setTimeout, setInterval, MutationObserver, and process.nextTick
     to schedule the promise to run when the execution context stack only contains platform code (engine, environment, and promise implementation code)

  Q: How can we get the resolve and reject in the executor fn?
     to refer to THIS promise's resolve and reject?
  A: 

  Notes:
    Finally does not return a promise, so there's no need to call a controlled promise.

  Sources:
    Low-Level Javascript
    "Promises From Scratch In A Post-Apocalyptic Future"
    https://www.youtube.com/watch?v=4GpwM8FmVgQ&list=PLP29wDx6QmW7IaD762Rf_Awfr1Wxz0Amq&index=3&t=176s
 */
