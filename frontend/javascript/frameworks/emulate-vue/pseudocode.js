// get values from data object
// create an observable for each value (getter and setter [calls notify function when setter is invoked])
let observable = new Observable(value);

// add the observable to the list of observables
let observableCollection = [];
observableCollection.push(observable);

// get placeholders from the DOM {{placeholder}}
// create observers for each placeholder...
let observer = new observer();

// define mechanism for notification from observer
observer.update = function () {
  // replace text with the new value and insert in text in the correct order
};

// create text nodes from the placeholder -> call these nodes
// w/each node create a new observer
let placeholder_init_value = data[placeholder];

// Subscribe to the correct observable
// iterate over the list of observables and find the correct placeholder
// given the placeholder
for (let observable in observableCollection) {
  if (placeholder == observable.name) {
    observable.subscribe(node);
  }
}

// get all publishers
// assign event listeners to publishers and call their directives
el.addEventListener(emit);
function emit() {
  // execute directive body
}

class Vue {
  evtAttrMap = { "v-on:click": "click", "v-on:mouseover": "mouseover" };
  list = [];
  vData = {};
  static component({ el, data }) {
    vData = data;

    const parent = document.querySelector(el);

    getEvtElements(parent, list);
    const evtElements = list;

    assignEvtListeners(evtElements);
    getSubscribers();
  }

  static assignEvtListeners(evtElements) {
    evtElements.forEach((el) => {
      const vEvent = el.attributes().filter((attrib) => isEventAttribute(attrib));
      el.addEventListener(evtAttrMap[vEvent], function (event) {
        let command = el.getAttribute(vEvent);
        let keys = command.split(" ");
        let key = keys.find((key) => vData[key]);
        vData[key];
        // data["counter"]: (___ do something)
        notifySubscribers(newData);
      });
    });
  }

  static getEvtElements(parent, list) {
    // for every child of the parent
    for (let child of parent.children) {
      // dfs
      getEvtElements(child, list);
      // if the current child has an event specified add it to the list
      if (child.attributes) {
        if (child.attributes.some(hasEvent)) {
          list.push(child);
        }
      }
    }
  }
}

// View Model Update

// Model = data and getter/setter?
// View is the VDOM and DOM

// parse the data object

// get DOM
// parse through the body
// find the HTMLElement node that matches - could do querySelector
// get the text string from the node
// parse the text string
// for the variable

// trigger event

// notify all subscribers
// create new html text node

// get all observers
// traverse DOM
// look for those with key in data
// parse {{}}: - is data[key]?
// if so the element is a subscriber/observer, they need to know of the event

const app = Vue.component({
  el: "#app",
  data: {
    counter: 0,
    multip: 1,
  },
});

// I suppose this is an event-emitter/pub-sub exercise
// Declare an event handler
// When the handler fires
// update all subscribers of the event

// managing state <- in-memory, ephemeral state
// subsribers are all those whose are identified in {{}} brackets and their reference id
// contents of {{}} specify the variable state and describe the event to subscribe to
// a ref: we have to assign each HTMLElement a specific reference to avoid traversing DOM each time
// the contents of {{}} reflect the variable state

// getEventElements()
// returns a collection of events who want to have an onclick
// Option A: document.querySelector()
// Option B: DFS traverse the DOM
// look for an HTMLElement that has a unique identifier using a map of identifiers
// call internal function (isMatch)
// Option A || B: put into the collection

// assignEventListeners(collectionOfHTMLElements)
// assigns event listeners based on their event
// internally, we use a map to map attribute to event listener
// i.e. v-on:click: element.addEventListener('onclick')
// i.e. v-on:onmouseover: element.addEventListener('onmouseover')

// event handling - what fires when event comes in
// data structures

// definition and problem clarification
// methods required
// pseudocode
// explicit data structures
// cover any areas left out: method definition etc.
// write code

// given the list of identifiers
// register subscribers...
//

// when an event comes in
// look in the map of
//
