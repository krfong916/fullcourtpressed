const TEXT_NODE = 3;
const CHILD_NODE = 1;

class Watcher {
  constructor(props) {
    this.node = props.node;
    this.value = props.value;
    this.key = props.key;
    this.id = props.id;
  }

  update(newVal) {
    /* add validation and diffing here */
    this.value = newVal;
    this.node.textContent = newVal;
  }
}

function getSubscribers(parent, data) {
  let subscribers = dfs(parent, data);

  let watchers = [];
  subscribers.forEach((subscriber, index) => {
    let props = {
      id: index,
      key: subscriber.textContent,
      node: subscriber,
      value: data[subscriber.textContent],
    };

    watchers.push(new Watcher(props));
  });
  return watchers;
}

function dfs(parent, data) {
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
      let subscribers = dfs(child, data);
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
    this.observableDict = new Map();
    this.subscribers = [];
    this.publishers = [];
    this.context = {};
    this.context.data = {};
    this.parent = document.getElementById("app");
    this.eventMap = { "v-on:click": "click" };

    this.subscribers = getSubscribers(this.parent, vueModel.data);

    // create observable for each data property
    for (let key in vueModel.data) {
      this.observableDict.set(
        key,
        new Observable(this.context, key, vueModel.data[key])
      );
    }

    for (let i = 0; i < this.subscribers.length; i++) {
      this.observableDict.get(this.subscribers[i].key).register(this.subscribers[i]);
    }

    this.publishers = getPublishers(this.parent, this.eventMap, this.context);
  }
}

function defineReactive(node, action, directive, context) {
  for (let key in context.data) {
    let exp = new RegExp(key);
    directive = directive.replace(exp, `this.${key}`);
  }
  const emit = new Function("return " + directive);
  node.addEventListener(action, emit.bind(context.data));
}

function getPublishers(parent, eventMap, context) {
  let publishers = [];
  for (let child of parent.children) {
    let list = getPublishers(child, eventMap, context);
    if (list.length > 0) {
      publishers.push(list);
    }
    child.getAttributeNames().forEach((attribute) => {
      if (eventMap.hasOwnProperty(attribute)) {
        defineReactive(
          child,
          eventMap[attribute],
          child.getAttribute(attribute),
          context
        );
        publishers.push(child);
      }
    });
  }
  return publishers.flat();
}

class Observable {
  constructor(context, key, value) {
    this.value = value;
    this.name = key;
    this.dependencies = [];
    this.notify = this.notify.bind(this);
    let notify = this.notify;

    Object.defineProperty(context.data, key, {
      get() {
        return value;
      },
      set(newVal) {
        this.value = newVal;
        notify(newVal);
      },
      enumerable: true,
      configurable: true,
    });
  }

  register(watcher) {
    this.dependencies.push(watcher);
  }

  notify(newVal) {
    console.log(this.dependencies);
    for (let dependency of this.dependencies) {
      dependency.update(newVal);
    }
  }
}

new Vue({
  el: "#app",
  data: {
    counter: 0,
    other: 10,
  },
});
