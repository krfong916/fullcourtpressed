Array.prototype.reduce = function(callback) {
  if (this === null) {
    throw new TypeError('Cannot call Array.reduce on an undefined array');
  }

  if (typeof callback !== 'function') {
    throw new TypeError('Callback must be a function');
  }

  let t = Object(this),
    accum,
    i = 0;
  if (arguments.length == 2) {
    accum = arguments[1];
  } else {
    while (k < t.length) {
      k++;
    }
    accum = t[k];
  }
  for (; i < t.length; i++) {
    accum = callback(accum, t[i], i, t);
  }
  return accum;
}[(1, 2, 3, 4, 5)]
  .reduce((accumulator, num, index, t) => {}, 0)

  [(1, 2, 3, 4, 5)].reduce((accumulator, num, index, t) => {}, [])

  [(1, 2, 3, 4, 5)].reduce((accumulator, num, index, t) => {}, {});

Array.prototype.filter = function(callback) {
  if (this === null) {
    throw new TypeError(
      'Cannot call Array.filter on an array that is null or undefined'
    );
  }
  if (typeof callback !== 'function') {
    throw new TypeError('The callback function for Array.filter must be a function');
  }

  let t = Object(this),
    arr = [],
    i = 0;
  for (; i < t.length; i++) {
    if (callback(t[i], arr, i)) {
      arr.push(t[i]);
    }
  }
  return arr;
}[(1, 2, 3, 4, 56)].filter((val, arr, i) => {
  return val % 2 == 0;
});
