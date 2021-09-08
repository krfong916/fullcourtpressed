class Solution {
  public int longestCommonSubsequence(String text1, String text2) {
    return lcs(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1, new Integer[text1.length()][text2.length()]);
  }

  private int lcs(char[] text1, char[] text2, int i, int j, Integer[][] dp) {
    if (i < 0 || j < 0) return 0;
    if (dp[i][j] != null) return dp[i][j];

    if (text1[i] == text2[j]) {
      return 1 + lcs(text1, text2, i - 1, j - 1, dp);
    } else {
      return dp[i][j] = Math.max(lcs(text1, text2, i - 1, j, dp), lcs(text1, text2, i, j - 1, dp));
    }
  }
}