class Solution {
  public int maxProfit(int[] a) {
    if (a.length < 2) return 0;
    int bought = a[0];
    int maxProfit = 0;
    for (int i = 1; i < a.length; i++) {
      bought = Math.min(bought, a[i]);
      maxProfit = Math.max(a[i] - bought, maxProfit);
    }
    return maxProfit;
  }
}