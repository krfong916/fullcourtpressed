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
  public Node cloneGraph(Node node) {
    if (node == null) return null;
    HashMap<Integer, Node> map = new HashMap<>();
    Queue<Node> q = new LinkedList<>();
    Node copy = new Node(node.val);

    map.put(copy.val, copy);
    q.add(node);

    while (q.isEmpty() == false) {
      Node current = q.remove();
      for (Node neighbor : current.neighbors) {
        if (map.containsKey(neighbor.val) == false) {
          map.put(neighbor.val, new Node(neighbor.val));
          q.add(neighbor);
        }
        Node currentCopy = map.get(current.val);
        currentCopy.neighbors.add(map.get(neighbor.val));
        map.put(currentCopy.val, currentCopy);
      }
    }
    return copy;
  }
}

class Solution {
  public Node cloneGraph(Node node) {
    // Given a node s
    // If s is null return null
    // Initialize a hashmap structure (M)
    // This structure will contain reference to the cloned nodes and will be used to build their adjacent vertices
    // Initialize a queue structure (Q)
    // This structure will contain the nodes we need to create copies of
    // Initialze a node called copy
    // Copy will be the node that we'll return
    // Add copy to the hashmap M
    // Enqueue the node in Q
    // While Q is not empty, we must continue creating the clone of the graph
    // Dequeue the next node from Q, call this vertex v
    // Get the copy of V from M, call this copy-v
    // For every neighbor vertex u in the adjacency list of v i.e. neighbors of v
    // If M already contains vertex u
    // Then place u in the adjacency list of copy-v
    // Else, M does not contain vertex u, therefore, we have not enqueued u, nor have we created a copy of it
    // Create a copy of u, call this copy-u
    // Add copy-u into M
    // Add copy-u into the adjacency list of copy-v
    // At the time of termination of the while loop, we will have created a clone of the graph
    // Copy will have reference to the other cloned nodes
    // Return copy
  }
}

// We are guaranteed to only enqueue and process a vertex once
// The cost of Queue operations enqueue, dequeue, and Map operations get() and put() is O(1),
// for all v ∈ V, that takes O(V) time
// We examine all edges of an adjacency list of v, |adj[v]|,
// for all v ∈ V, that cost is O(E) time
// We also have an add operation where we add all neighbors of v into the copy-v neighbors list
// for all v ∈ V, that cost is O(E) time because we only add to the length of the copy-adj[v]
// Therefore, our algorithm takes O(V+E^2) time and O(V+E) space