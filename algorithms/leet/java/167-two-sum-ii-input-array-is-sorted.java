class Solution {
  public int[] twoSum(int[] nums, int target) {
    if (nums.length == 0) return new int[0];
    int[] result = new int[2];
    int end = nums.length - 1;

    for (int start = 0; start < nums.length; start++) {
      // find the range where we guarantee index2 is contained in
      // if the combined value > target
      // decrement until we have a small number
      while (nums[start] + nums[end] > target) {
        end--;
      }

      int targetIndex = target - nums[start];

      // nums[start] is large enough, then nums[end] will exist in the range of [start, end]
      if (targetIndex <= nums[end]) {
        int index1 = start;
        int index2 = Arrays.binarySearch(nums, target - nums[start]);
        // the problem specifies that indices are unique, therefore, we must check for this property
        // if the values are the same and the indices are the same
        if (nums[index1] == nums[index2] && index1 == index2) {
          result[0] = index1 + 1;
          result[1] = index2 + 2;
        } else {
          result[0] = index1 + 1;
          result[1] = index2 + 1;
        }
        break;
      }
    }
    return result;
  }
}