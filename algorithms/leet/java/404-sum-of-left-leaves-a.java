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
    if (root == null) return 0;
    return sumHelper(root.left, true, 0) + sumHelper(root.right, false, 0);
  }

  private int sumHelper(TreeNode root, boolean leftSubtree, int sum) {
    if (root == null) return 0;
    if (leftSubtree && isLeaf(root)) {
      return root.val;
    } else {
      return sum += sumHelper(root.left, true, sum) + sumHelper(root.right, false, sum);
    }
  }

  private boolean isLeaf(TreeNode root) {
    return root.left == null && root.right == null ? true : false;
  }
}