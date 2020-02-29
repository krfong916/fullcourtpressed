class Solution {
  public int findMin(int[] nums) {
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = (start + end) / 2;
      if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) { // if mid == minimum
        return nums[mid];
      }
      // if min is contained in the right partition
      if (nums[start] > nums[end] && nums[mid] > nums[end]) {
        start = mid + 1;
      } else { // if min is contained in left partition or is a fully rotated cycle
        end = mid - 1;
      }
    }
    return nums[start];
  }
}
