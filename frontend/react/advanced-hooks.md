## Hooks

- useReducer can be used for complex state updates in favor of useState
- we declare an initial state object
- destructure the state properties that we need
- and dispatch a state update when it occurs
- we can spearate the logic to handle the state update - the callback fn - into its own separate function
  - `{...state, count: state.count+1}`

## useCallback and memo

- value stability and make sure we don't do a side-effect or call a function on every render
- useCallback only changes when one of the inputs changes
- it may be the case that we unmount our component before an async request returns - in that case, we want to prevent dispatch from being called if the component use unmounted
  - we need useCallback, a ref to determine if we have the component mounted, and useEffect to run the mount and unmount boolean on the ref

## Context

- we can use the context API to provide multiple components with state
- The context API is a singleton that allows us to store state
  - the API can very well be used as a cache to save previous data

## useLayoutEffect

- for observable changes to the DOM, useLayoutEffect
