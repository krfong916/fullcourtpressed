/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
    // model the call stack
    Stack<TreeNode> stack = new Stack<TreeNode>();
    // a structure to store 'visited' nodes
    List<Integer> inorderCollection = new ArrayList<Integer>();

    TreeNode current = root;
    while (stack.empty() == false || current != null) {
      if (current != null) {
        // traverse left subtree until left child is null
        stack.push(current);
        current = current.left;
      } else {
        // a left subtree DNE, visit the node on the stack
        current = stack.pop();
        inorderCollection.add(current.val);
        // traverse the right subtree
        current = current.right;
      }
    }
    return inorderCollection;
  }
}
