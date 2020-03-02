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
  public int kthSmallest(TreeNode root, int k) {
    List<Integer> arr = new ArrayList<Integer>();
    inorderTraversal(root, arr);
    return arr.get(k - 1);
  }

  private void inorderTraversal(TreeNode root, List<Integer> arr) {
    if (root.left != null) {
      inorderTraversal(root.left, arr);
    }
    arr.add(root.val);
    if (root.right != null) {
      inorderTraversal(root.right, arr);
    }
  }
}