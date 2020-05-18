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
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    return pathSumHelper(root, sum);
  }

  private boolean pathSumHelper(TreeNode root, int sum) {
    int sumRemaining = sum - root.val;
    if (sumRemaining == 0 && isLeaf(root)) return true;
    return hasPathSum(root.left, sumRemaining) || hasPathSum(root.right, sumRemaining);
  }

  private boolean isLeaf(TreeNode root) {
    return root.left == null && root.right == null ? true : false;
  }
}