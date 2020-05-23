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
  private int maxPathSum = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    helper(root);
    return maxPathSum;
  }

  private int helper(TreeNode root) {
    if (root == null) return 0;
    int left = helper(root.left);
    int right = helper(root.right);

    // if the result of the subtree is negative, do not consider the path
    int leftMax = Math.max(left, 0);
    int rightMax = Math.max(right, 0);

    maxPathSum = Math.max(maxPathSum, root.val + leftMax + rightMax);

    return root.val + Math.max(leftMax, rightMax);
  }
}