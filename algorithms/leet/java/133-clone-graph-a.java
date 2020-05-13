/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
  private Set<Node> visited = new HashSet<>();

  public Node cloneGraph(Node node) {
    if (node == null) return null;
    return clone(node);
  }

  private Node clone(Node node, Set visited) {
    if (visited.contains(node)) return visited.get(node.val);

    Node nodeCopy = new Node(node.val);
    visited.put(nodeCopy.val, nodeCopy);

    for (Node neighbor : node.neighbors) {
      Node copy = clone(neighbor, visited);
      nodeCopy.neighbors.add(copy);
    }

    return nodeCopy;
  }
}