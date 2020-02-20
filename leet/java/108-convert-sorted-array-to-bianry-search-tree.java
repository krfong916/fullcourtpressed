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
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;
    if (nums.length == 1) return new TreeNode(nums[0]);

    TreeNode root = new TreeNode(nums[(nums.length - 1 + 0) / 2]);
    return convertTreeHelper(nums, root, 0, nums.length - 1);
  }

  private TreeNode convertTreeHelper(int[] arr, TreeNode parent, int start, int end) {
    if (start > end) {
      return null;
    }

    int median = (start + end) / 2;
    TreeNode root = new TreeNode(arr[median]);
    root.left = convertTreeHelper(arr, root, start, median - 1);
    root.right = convertTreeHelper(arr, root, median + 1, end);
    return root;
  }
}
