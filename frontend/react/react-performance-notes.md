## Lazy Load to pre-load chunks only when needed

We can use the chrome dev tool to analyze unused code This information lets us
see what code is not on the critical path for loading

For our react application, we can use React.lazy to dynamically import
javascript only when needed To speed up interaction of a loaded component - we
can trigger a pre-load of a component on user events like onMouseOver or onFocus

We can also use

```jsx
const loadGlobe = import(/* webpackPrefetch: true */ '../globe')
```

to insert a link tag that contains the chunk of js to pre-load in the browser's
cache

## useMemo for expensive calculations

We can use useMemo or useCallback hooks to optimize the CPU required to perform
expensive operations, or prevent un-necesary re-renders

- How does an un-necessary re-render happen?
- possibly many sibling components
  - one sibling component updates state which triggers all siblings to be
    re-rendered
  * note: we should only spend time preventing un-necessary re-renders for use
    cases such as rendering charts, images, and animations
  * `React.useMemo()` is an escape hatch that prevents unnecessary re-render of
    a component
    - The component declared in useMemo will change only when props or state
      changes

## React.memo

- One of the main reasons that React was created was to optimize performance of
  updating the DOM. The virtual DOM allows fast, in-memory diff between the
  current DOM and the new DOM based on events.
  - There are three steps to React updating the virtual DOM
    - Render: react DOM elements are created
    - Reconciliation: React engine diffs the current tree structure with the
      newly generated DOM
    - Commit: React updates the virtual DOM with the new changes
  - When state and props change in components, if we're not careful, some of our
    components may unnecessarily re-render
  - If the reconciliation and re-render of a component is expensive, it may be
    worthwhile to prevent it
  - We can use React.memo in order to prevent an unnecessary re-render
  - How? React relies on the same props each call to prevent unnecessary
    re-renders
  - Sometimes, when a parent component updates, children components
    unnecessarily update. This is because children components don't know if they
    need to re-rendered or not based on the parent's state update. We can wrap
    those expensive children components in React.memo.
  - React.memo - we won't re-render simply because the parent re-renders
- React.memo accepts a second argument of prevProps and nextProps and allows us
  to make updates based on comparing props
- This can be wildly helpful for preventing list items from re-rendering when
  hovering
- Additionally, we can pass pre-computed primitive values as props to children
  list item components, and take advantage of reacts built-in comparator
  function.

## Windowing for lazy 'just-in time' rendering

- for rendering large lists we can 'window' or use just-in time rendering
- suppose we have a list of thousands of rows
  - we don't need to render every row on the page for the user, just enough rows
    in the viewable window and maybe a few outside the viewport.
  - This allows React reduce the amount of code to run when we need to render
    components with lots of data. This can helpful for components like graphs,
    charts, visualizations, and lists

# Performance death by a thousand cuts and context

- Co-locate state - place state only where it's strictly necessary to avoid
  re-rendering.
- Separate contexts for logically separate pieces of state. Instead of one large
  context, separate multiple values of state into multiple contexts
- Limit the amount of work our consuming components need to do:
  - If many components of our app uses context in multiple places, when context
    updates, perhaps some of the components don't need to re-render
    - We can use a HOC that accepts a component, memoizes the component, and
      returns a wrapper functional component that wraps the memoized component
      with the state slice that it needs - the memoized component will onyl
      update when it's props change

# Profiler

- We can profile our app in production using React profiler
- We wrap our components with the profiler API and send a network request every
  n-seconds to profile our components
- Data Aggregation services like Grafana can produce insights about the
  performance of our application
