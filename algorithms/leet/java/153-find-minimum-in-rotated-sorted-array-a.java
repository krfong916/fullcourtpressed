class Solution {
  public int findMin(int[] nums) {
    return findMinHelper(nums, 0, nums.length - 1);
  }

  // given our array, a start index, and end index
  private int findMinHelper(int[] nums, int start, int end) {

    // REVISIT: must clarify why we check for these conditions
    if (start == 0 && end == 1 || start == end) return Math.min(nums[start], nums[end]);
    if (start > end) return Integer.MAX_VALUE;

    // calculate the midpoint
    int mid = (start + end) / 2;

    // if the array is rotated by a pivot
    // then we have only two conditions to meet
    // the minimum is in the right half from the pivot
    if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
      return nums[mid + 1];
    }

    // the minimum is the pivot
    if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
      return nums[mid];
    }


    // leftMin = recursively call findMinHelper on the left of the mid
    // rightMin = recursively call findMinHelper on the right of the mid
    // compare left and right min
    // return the minimum of the comparison
    int leftMin = findMinHelper(nums, start, mid - 1);
    int rightMin = findMinHelper(nums, mid + 1, end);
    return Math.min(leftMin, rightMin);
  }
}
























class Solution {
  public int findMin(int[] nums) {
    return findMinHelper(nums, 0, nums.length - 1);
  }


  private int findMinHelper(int[] nums, int start, int end) {

    if (start > end) return Integer.MAX_VALUE;
    int mid = (start + end) / 2;


    if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
      return nums[mid + 1];
    }
    // if a[index] < a[index-1] = return a[index]
    if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
      return nums[mid];
    }

    int leftMin = findMinHelper(nums, start, mid - 1);
    int rightMin = findMinHelper(nums, mid + 1, end);
    return Math.min(leftMin, rightMin);

  }
}