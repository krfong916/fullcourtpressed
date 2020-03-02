class Solution {
  public int findKthLargest(int[] nums, int k) {
    int start = 0;
    int end = nums.length - 1;
    int kthLargestIndex = nums.length - k;
    Random random = new Random();
    while (start <= end) {
      /* choose a random index between start and end */
      int randomlyChosenPivotIndex = random.nextInt(end - start + 1) + start;
      int index = partition(nums, start, end, randomlyChosenPivotIndex);
      if (index == kthLargestIndex) {
        return nums[kthLargestIndex];
      } else if (index < kthLargestIndex) {
        start = index + 1;
      } else {
        end = index - 1;
      }
    }
    throw new Error("no kth largest found");
  }

  private int partition(int[] nums, int start, int end, int pivot) {
    int i = start;
    int pivotValue = nums[pivot];
    swap(nums, pivot, end);
    for (int j = start; j < end; j++) {
      if (nums[j] < pivotValue) {
        swap(nums, j, i);
        i++;
      }
    }
    swap(nums, i, end);
    return i;
  }

  private void swap(int[] nums, int x, int y) {
    int temp = nums[x];
    nums[x] = nums[y];
    nums[y] = temp;
  }
}