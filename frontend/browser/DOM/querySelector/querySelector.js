/**
 * Return a list of HTMLElements that match our query
 *
 * @param  {String} matcher
 * @return {Array<HTMLElement>}
 */
const querySelector = function querySelector(matcher) {
  if (typeof matcher != "string" && typeof matcher != "number") {
    throw new TypeError(`${matcher} must be a string or number`);
  }

  // Pre-process
  let id = matcher;
  let nodeCollection = [];
  let visited = {};
  let body = document.body;

  // DFS
  for (let child of body.children) {
    if (!visited[child]) {
      findMatchingNode(child, id);
    }
  }

  return nodeCollection;

  /**
   * DFS-Visit
   *
   * @param  {HTMLElement}
   * @param  {String}
   * @return {void}
   */
  function findMatchingNode(current, id) {
    if (isMatch(current, id)) {
      nodeCollection.push(current);
    }
    for (let child of current.children) {
      if (!visited[child]) {
        // console.log(child);
        findMatchingNode(child, id);
      }
    }
  }

  /**
   * Determine if the node matches our query
   *
   * @param  {HTMLElement}
   * @param  {String}
   * @return {Boolean}
   */
  function isMatch(node, id) {
    if (node.className == id || node.id == id) {
      return true;
    } else {
      return false;
    }
  }
};

export default querySelector;
