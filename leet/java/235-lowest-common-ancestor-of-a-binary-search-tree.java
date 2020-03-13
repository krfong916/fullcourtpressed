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
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // LCA is in the left subtree
    if (root.val > p.val && root.val > q.val) {
      return lowestCommonAncestor(root.left, p, q);

    // LCA is in the right subtree
    } else if (root.val < p.val && root.val < q.val) {
      return lowestCommonAncestor(root.right, p, q);

    // LCA is the current node
    } else {
      return root;
    }
  }
}