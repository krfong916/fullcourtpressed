class Solution {
  public int longestPalindromeSubseq(String s) {
    return lps(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
  }

  private int lps(String s, int i, int j, Integer[][] memo) {
    if (i > j) return 0;
    if (s.charAt(i) == s.charAt(j) && i == j) return 1;
    if (memo[i][j] != null) return memo[i][j];

    int length = 0;
    if (s.charAt(i) == s.charAt(j)) {
      length = lps(s, i + 1, j - 1, memo) + 2;
    } else {
      length = Math.max(lps(s, i + 1, j, memo), lps(s, i, j - 1, memo));
    }
    memo[i][j] = length;
    return length;
  }
}