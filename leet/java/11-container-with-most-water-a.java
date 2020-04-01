class Solution {
  public int maxArea(int[] height) {
    int x = 0; int y = 0;
    int localHeight = 0; int maxHeight = 0;

    for (int left  = 0; left < height.length; left++) {
      for (int right = height.length - 1; right > left; right--) {
        // calculate x-axis: x
        // given x2, subtract x1 = x
        x = right - left;

        // 2 - calculate y-axis: y
        // given y2 and y1, calculate the minimum
        y = Math.min(height[right], height[left]);

        // calculate the local height: lh
        // x * y = lh
        localHeight = x * y;

        // calculate the maximum height: mh
        // Math.max(lh, mh)
        maxHeight = Math.max(localHeight, maxHeight);
      }
    }

    return maxHeight;
  }
}