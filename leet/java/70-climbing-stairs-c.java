class Solution {
  public int climbStairs(int n) {
    int[] memo = new int[n + 1];
    return climbStairsHelper(n, memo);
  }
  private int climbStairsHelper(int i, int[] memo) {
    if (memo[i] != 0) return memo[i];
    if (i == 1) return 1;
    if (i == 2) return 2;
    memo[i] = climbStairsHelper(i - 1, memo) + climbStairsHelper(i - 2, memo);
    return memo[i];
  }
}
