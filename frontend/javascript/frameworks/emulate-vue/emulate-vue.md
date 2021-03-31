## Goal: Implement the Event Emitter

Emulate Vue.js functionality

```html
<div id="app">
  <button v-on:click="counter += 1"></button>
  <p>Button has been clicked {{counter}} times</p>
</div>
```

```javascript
var app = new Vue({
  el: "#app",
  data: {
    counter: 0,
  },
});
```

Search dom nodes for the htmlelement that contains the id "app"
create a new dom text node based on the values of the data property

## Further

- Implement a pub/sub model (vanilla js): for cart handling

## Discussion

- How do you handle state in your application?
- Redux discussion

## Learned

- Event Emitter from scratch
- Publish Subscriber: abstraction of event handling/listening implement an event bus
- DOM manipulation
- Proxies are great for handling interaction between javascript and DOM nodes

## Sources

- https://vuejs.org/v2/guide/reactivity.html
  - model of reactivity
  - uses getters and setters
  - when setter is triggered, model is updated and view expresses model
- https://vuejs.org/v2/guide/events.html
  - It was once a commonly-held view to keep javascript and HTML separate.
  - Vue makes the decision to have event handlers in HTML because it keeps files pure-logic and DOM-free, it makes it easier for the developer to find function handlers with a simple read of the HTML, and it simplifies tests. Lastly, when a viewmodel is removed, the event listeners are destroyed so we don't have to worry about cleanup.
- https://betterprogramming.pub/observer-vs-pub-sub-pattern-50d3b27f838c#:~:text=In%20the%20observer%20pattern%2C%20the,message%20queues%20or%20a%20broker.
  - a good refresher of terminology of pub-sub and observer

## Reactive Programming

- Discussing a non-JS env for a moment - suppose, a multi-threaded environment. Traditionally, we'd reserve a thread pool and assign each request a thread (request per thread). In this model, one bottleneck is being limited by the number of threads, and to patch, we'd require a larger thread pool i.e. reserve more memory. Higher thread pool == more memory consumption. Costly. We wouldn't be able to handle many concurrent requests.
  - In an imperative programming model: and assuming a microservices arch. we'd depend on request/response time from other services.
  - For example: our service receives and makes a call to a service. We're required to wait for the service's response. T/f we're I/O blocked
    - exposed to network latency, processing times, GC pause, network partitions etc.
  - Instead, we'd like to asynchronously fire an event, continue servicing requests, and when the response comes back we'll react to it.
  - Reactive programming is about creating non-blocking applications that are async and event-driven. Buzzwords: Separation of concerns, failure management, and making opinions about events/messages.
  - Actors in this programming model: Publisher (data producer/data source, allows subscriber to register) -> Processor (transform incoming events and pass on to another subscriber) -> Subscriber (onSubscribe, onError, onNext, onComplete)
