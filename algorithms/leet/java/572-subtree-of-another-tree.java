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
  public boolean isSubtree(TreeNode p, TreeNode q) {
    if (p == null) return false;
    return isSame(p, q) || isSubtree(p.left, q) || isSubtree(p.right, q);
  }
  
  private boolean isSame(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true; 
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;
    return isSame(p.left, q.left) && isSame(p.right, q.right);
  }
}