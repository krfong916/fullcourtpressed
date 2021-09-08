class Solution {
  public int maxProduct(int[] nums) {
    // we could compute every possible contigious combination
    // this would be O(n^2)
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int cp = nums[0];
    int gp = nums[0];
    for (int i = 0; i < nums.length; i++) {
      int continuous = nums[i];
      for (int j = i; j < nums.length; j++) {
        if (i == j) {
          continuous = nums[j];
        } else {
          continuous *= nums[j];
        }
        int current = nums[j];
        cp = Math.max(continuous, current);
        gp = Math.max(cp, gp);
      }
    }
    return gp;
  }
}