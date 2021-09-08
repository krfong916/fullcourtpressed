## Goals

- Learn about the DOM and how the `document` API implements particular methods

## Design Decisions

- We iterate over `children` and not `childNodes`. Why? We chose not to support text or comments.
- For the sake of exercise, we choose to support single string matchers. We omit matching on regex expressions, custom ids, and multiple tags. Though support can easily added for a fully functional solution

## Pseudocode

We perform a DFS traversal, `querySelector()`, and match on nodes that meet the criteria

- Given the arg: `id` as `string`
- if `id` is `null` or `undefined`
  - return an exception
- declare collection: `nodeCollection = []`

- Get DOM as an object via `document.body`

  - the DOM will be represented as a tree
  - given the `body` tag, for every child node
    - call `findNode()` with args: `id` and `child`

- `findNode()` args: `id: string`, `current: DOMNode`
- performs a dfs traversal on children nodes, we match on nodes that contain the `id`
- call `isMatch()` that returns a `boolean`
  - if `isMatch()` is `true` insert the child into `nodeCollection`
  * recursive dfs on `children` of `DOMNode`

## Lessons Learned

- In order to iterate over an `HTMLCollection`
  - We should use `for of` and not `for in`
    - `for in`: iterates over all keys of an object and serves as a way to inspect the properties of an object. An `HTMLCollection` has a few properties on the object that are not desirable for DOM traversal
    - `for of`: iterates over an iterable collection (array) and returns the collections' values
      - alternatives would be a generic for loop
- DOM nodes do not have a unique identifier.
- Perhaps self-apparent, but still worth saying, given that the DOM is an OO representation of a n-ary tree, the DOM has no cycles t/f a `visited` structure when performing DFS is not needed.
