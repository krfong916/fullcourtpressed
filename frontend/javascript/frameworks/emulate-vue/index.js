const TEXT_NODE = 3;
const CHILD_NODE = 1;

// const app = document.getElementById("app");
// let subscribers = getSubscribers(app);

function getSubscribers(parent, data) {
  let child = parent.firstChild;
  let text = [];
  let subscriberRef = [];
  while (child) {
    // we keep reference of the next sibling
    // because we will transform text content to text nodes
    // the new test node will become the next sibling of the current child
    // i.e. "this is text" <next sibling> ... becomes
    // <node>this</node> <node>is</node> <node>text</node> <next sibling>
    let nextSibling = child.nextSibling;
    let list = [];
    if (child.nodeType == TEXT_NODE) {
      list = getTextNodes(child.textContent);
      if (list.length) {
        // clear the text content
        // insert the text nodes
        child.textContent = "";
        while (list.length > 0) {
          let node = list.pop();
          child.after(node);
          let str = node.textContent;

          if (data.hasOwnProperty(str)) {
            subscriberRef.push(node);
          }
        }
      }
    } else {
      // recursive DFS
      let subscribers = getSubscribers(child, data);
      // post-process will refactor for local subscriber object on function object
      if (subscribers.length > 0) {
        subscribers.forEach((subscriber) => {
          subscriberRef.push(subscriber);
        });
      }
    }
    child = nextSibling;
  }
  return subscriberRef;
}

function getTextNodes(str) {
  let list = [];
  let identifiers = ["other", "counter"];
  identifiers.forEach((id) => {
    if (str.includes(id)) {
      list = stringToTextList(str);
    }
  });
  return list;
}

function stringToTextList(str) {
  return str
    .split(/({{.*}})/g)
    .filter((item) => item.length > 0)
    .map((item) => {
      if (item.match(/({{.*}})/g)) {
        item = item.replace(/({{)|(}})/g, "");
      }
      return document.createTextNode(item);
    });
}

class Vue {
  constructor(vueModel) {
    this.observableCollection = [];
    this.subscribers = [];
    this.publishers = [];
    this.observers = [];
    this.observerables = [];
    this.context = {};
    this.parent = document.getElementById("app");
    this.eventMap = { "v-on:click": "click" };

    // create context object
    this.context.data = {};

    // get subscribers
    this.subscribers = getSubscribers(this.parent, vueModel.data);

    // bind the this context to the object, in order to maintain the reference to subscribers etc.
    // when the function is invoked by a DOM event
    // this.context.notify.bind(this.context);

    // create observable for each data property
    for (let key in vueModel.data) {
      this.context.data[key] = vueModel.data[key];
      this.observableCollection.push(new Observable(this.context.data, key));
    }

    // this.observableCollection[0].name == this.subscribers[0].textContent
    this.subscribers[0].__update__ = function (newVal) {
      this.textContent = newVal;
    };

    // get publishers
    // this.publishers = getPublishers(parent, eventMap)
  }
}

// function defineReactive(parent) {
//   for (let child of parent.childNodes) {
//     if (child.attribute.contains(DIRECTIVE_SIGNATURE)) {
//       let functionBody = `return + ${directiveValue}`
//       const emit = new Function(functionBody)

//       child.addEventListener(eventMapAction, emit.bind(data))
//     }
//   }
// }

class Observable {
  constructor(data, key) {
    this.value = data[key];
    this.name = key;
    Object.defineProperty(data, key, {
      get: function reactiveGetter() {
        return this.value;
      },
      set: function reactiveSetter(newVal) {
        this.value = newVal;
        // need to map subscriber to key before defining a new Observable
        // this.subscribers[0].__update__();
      },
    });
  }
}

new Vue({
  el: "#app",
  data: {
    counter: 0,
    other: 10,
  },
});

// function getPublishers(parent, eventMap) {
//   // assign emit events and event listeners
//   let publishers = [];

//   for (let child of parent.children) {
//     child.getAttributeNames().forEach(attribute => {
//       if (eventMap.hasOwnProperty(attribute)) {
//         publishers.push(child);
//       }
//     })
//   }
// }

// // still need to parse all other text in dom node
// function getSubscribers(parent, properties) {
//   let subscribers = []
//   let identifier = ""
//   // given the parent
//   // determine if text is a symbol in the data properties
//   if (Object.hasOwnProperty(properties, text)) {
//     // create a new subscriber text node from the current
//     subscribers.push(new Subscriber(text))
//   }
// }

// class Subscriber {
//   constructor(prop) {
//     this.identifier = prop
//     this.update = this.update.bind(this)
//   }

//   update() {
//     // create a new text node and insert in the correct position in DOM
//   }
// }

// let Observable = function Observable(value) {}

// /* If we define the getter and setter, does that change the data properties? */
// /* If so, do we need to return an obsrvable object? Or can we just assign to the data object? */

// const parent = document.getElementById("app")
// let subscribers = getSubscribers(parent)

// let observers = subscribers.map(subscriber => {
//   return new Observer(subscriber)
// })

// let Observer = function Observer() {
//   update: function(newValue) {
//     if (newValue == oldValue) return;
//     document.createTextNode(newValue);
//   }
// }

// function getSubscribers(DOMNode) {}

// observableCollection.forEach(observable => {
//   observers.forEach(observer => {
//     if (observer.key == observable.name) {
//       observable.register(observer)
//     }
//   })
// })

/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

// const Vue = function Vue(obj) {
//   let data = {}
//   for (let key in obj.data) {
//     data[key] = obj.data[key]
//     modProp(data, key)
//   }
//   const app = document.getElementById("app")
//   app.addEventListener('click', emit.bind(data))
// }

// new Vue({
//   el: "#app",
//   data: {
//     counter: 0,
//     other: 0,
//   },
// });

// function emit() {
//   this.counter += this.other + 1
//   console.log(this.counter)
// }

// function modProp(data, key) {
//     let val = data[key]
//     Object.defineProperty(data, key, {
//       get: function() {
//         return val
//       },
//       set: function(newVal) {
//         if (newVal <= 10) {
//           val = newVal
//         }
//       }
//     })

// }

// const parent = document.getElementById("app")
// const eventMap = {
//   'v-on:click': 'click'
// }
// let publishers = getPublishers(parent, eventMap);
// publishers.map(publisher => {
//   Object.keys(eventMap).forEach((key) => {
//     if (publisher.hasAttribute(key)) {
//       let directive = publisher.getAttribute(key)
//       let event = createEvent(directive)

//     }
//   })
// })

// function getPublishers(parent, eventMap) {
//   let publishers = [];

//   for (let child of parent.children) {
//     child.getAttributeNames().forEach(attribute => {
//       if (eventMap.hasOwnProperty(attribute)) {
//         publishers.push(child))
//       }
//     })
//   }
// }

// let eventMap = {
//   "v-on:click": "click",
// };

// const Vue = function Vue(props) {
//   let data = props.data;
//   let publishers = [];
//   let subscribers = [];
//   let eventMap = { "v-on:click": "click" };

//   const parent = document.querySelector(props.el);

//   // use the parent container element
//   // to get the list of publishers (event emitters)
//   publishers = getPublishers(parent, eventMap);

//   // get the list of subscribers
//   // i.e. elements that need to know if an event is fired
//   subscribers = getSubscribers(parent, data);

//   // associate subscribers to publishers
//   assignPublisherEventHandlers(publishers, subscribers);
// };

// function assignPublisherEventHandlers(publishers, subscribers) {
//   publishers = publishers.map((pub) => {
//     let e = getEvent(pub);
//     let args = getPublisherArgs(pub);
//     let command = getPublisherCmd(pub);
//     let eventEmitter = new Function(...args, `return ${command}`);
//     pub.addEventListener(e, function (event) {
//       newValue = eventEmitter(...args, command);
//       notifySubscribers(e, newValue, subscribers);
//     });
//   });
//   // return publishers
// }

// function notifySubscribers(event, newValue, subscribers) {
//   subscribers.forEach((subscriber) => {
//     // that needs to know about the event
//     if (event.key == subscriber.key) {
//       subsciber.innerText = newValue;
//     }
//   });
// }

// function getPublishers(parent, eventMap) {
//   let list = [];

//   for (let child of parent.children) {
//     // if the current child has a vue-event handler
//     // add it to the list of publishers
//     if (child.attributes) {
//       for (let attribute of child.attributes) {
//         if (eventMap.hasOwnProperty(attribute.name)) {
//           list.push(child);
//         }
//       }
//     }

//     // dfs-visit
//     // if the child is iterable (meaning has children)
//     if (Symbol.iterator in Object(child)) {
//       let result = getPublishers(child, eventMap);
//       if (result.length != 0) list.push(result);
//     }
//   }

//   return list;
// }

//     new Vue({
//       el: "#app",
//       data: {
//         counter: 0,
//         other: 0,
//       },
//     });

/*
  We'll create a text node
  the text node will have special properties
  an id
  an attribute identifier that corresponds to the data key
  and an update function

  when an event happens - get all subscribers
  the subscribers will be the text nodes
  the text nodes will have the function that they should run
  with the updated value

  we have directives = a special token in the markup to do something to the DOM element
  the directive instructs "Vue" (our Vue implementation) to update the textContent whenever the counter property on the ViewModel changes

  bind the text node to some function
    - bind: function(value)
    - do prep-work and stuff that only needs to run only once
     + computationally expensive stuff
    - value is the initial value
    - the bind method could be proxy?
  update: called when the bound value changes
    - update: function(value)
    - do something based on the initial value
 */

// const TEXT_NODE = 3;
// const CHILD_NODE = 1;

// const app = document.getElementById("app");
// let subscribers = getSubscribers(app);

// function hasTextContent(node) {
//   if (node.nodeType == TEXT_NODE) {
//     return node.textContent.trim().length > 0;
//   }
//   return false;
// }

// function getSubscribers(srcVertex) {
//   let queue = [];
//   queue.push(srcVertex);
//   let nextLevel = 1;
//   while (queue.length > 0) {
//     let numNodesInLevel = nextLevel;
//     while (numNodesInLevel != 0) {
//       let node = queue.shift();
//       // console.log(node);
//       const children = node.childNodes;
//       // bfs nodes in current level
//       for (let i = 0; i < children.length; i++) {
//         let v = children[i];
//         if (hasTextContent(v)) {
//           console.log("this is the text", v);
//           // let list = getTextNodes();
//         } else {
//           queue.push(v);
//           nextLevel++;
//         }
//       }
//       numNodesInLevel--;
//     }
//   }
//   //   let nextSibling = child.nextSibling;
//   //   let list = [];
//   //   list = getTextNodes(child.textContent);
//   //   if (list.length) {
//   //     console.log(list);
//   //     if (child.nodeType == TEXT_NODE) {
//   //       // clear the text content
//   //       // insert the text nodes
//   //       child.textContent = "";
//   //       while (list.length > 0) {
//   //         let node = list.pop();
//   //         child.after(node);
//   //       }
//   //     } else {
//   //     }
//   //   }
//   //   child = nextSibling;
//   // }

//   function getTextNodes(str) {
//     let list = [];
//     let identifiers = ["other", "counter"];
//     identifiers.forEach((id) => {
//       if (str.includes(id)) {
//         list = stringToTextList(str);
//       }
//     });
//     return list;
//   }

//   function stringToTextList(str) {
//     return str
//       .split(/({{.*}})/g)
//       .filter((item) => item.length > 0)
//       .map((item) => {
//         return document.createTextNode(item + "yo");
//       });
//   }
// }
