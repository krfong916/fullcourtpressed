/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null || root.left == null && root.right == null) return 0;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    int sum = 0;
    while (!q.isEmpty()) {
      TreeNode current = q.poll();

      if (current.left != null && (current.left.right == null && current.left.left == null)) sum += current.left.val;
      if (current.left != null) q.add(current.left);
      if (current.right != null) q.add(current.right);
    }

    return sum;
  }
}