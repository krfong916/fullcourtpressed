class Solution {
  public int climbStairs(int n) {
    if (n == 0 || n == 1 || n == 2) return n;
    int oneBehind = 2;
    int twoBehind = 1;
    int total = oneBehind + twoBehind;
    for (int i = 3; i <= n; i++) {
      total = oneBehind + twoBehind;
      twoBehind = oneBehind;
      oneBehind = total;
    }

    return total;
  }
}