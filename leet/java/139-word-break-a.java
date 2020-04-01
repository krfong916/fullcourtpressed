class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 0; i < s.length(); i++) {
      if (dp[i] == false) continue;

      for (String str : wordDict) {
        int end = i + str.length();
        if (end > s.length()) continue;
        if (dp[end]) continue;

        if (s.substring(i, end).equals(str)) {
          dp[end] = true;
        }
      }
    }
    return dp[s.length()];
  }
}