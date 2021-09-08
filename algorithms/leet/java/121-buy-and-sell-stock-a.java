class Solution {
  public int maxProfit(int[] a) {
    if (a.length < 2) return 0;
    int bought = a[0];
    int[] dp = new int[a.length];
    for (int i = 1; i < a.length; i++) {
      bought = Math.min(bought, a[i]);
      dp[i] = Math.max(a[i] - bought, dp[i - 1]);
    }
    return dp[dp.length - 1];
  }
}