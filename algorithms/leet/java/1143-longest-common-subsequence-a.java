class Solution {
  public int longestCommonSubsequence(String text1, String text2) {
    return lcs(text1, text2, text1.length() - 1, text2.length() - 1, new Integer[text1.length()][text2.length()]);
  }

  private int lcs(String text1, String text2, int i, int j, Integer[][] dp) {
    if (i < 0 || j < 0) return 0;
    if (dp[i][j] != null) return dp[i][j];

    int result = 0;

    if (text1.charAt(i) == text2.charAt(j)) {
      result = 1 + lcs(text1, text2, i - 1, j - 1, dp);
    } else {
      result = Math.max(lcs(text1, text2, i - 1, j, dp), lcs(text1, text2, i, j - 1, dp));
    }
    dp[i][j] = result;
    return result;
  }
}