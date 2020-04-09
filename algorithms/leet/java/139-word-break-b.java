class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>();
    for (String word: wordDict) {
      dict.add(word);
    }

    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 0; i < s.length(); i++) {
      if (dp[i] == false) continue;

      for (int j = i+1; j <= s.length(); j++) {
        if (dict.contains(s.substring(i, j))) {
          dp[j] = true;
        }
      }
    }
    return dp[s.length()];
  }
}

// "aaaaa"
// i = 0 -> [t,f,f,f,f,f]
// j = 1 -> a
// j = 2 -> aa -> [t,f,t,f,f,f]
// j = 3 -> aaa -> [t,f,t,t,f,f]
// j = 4 -> aaaa -> not contained in dictionary
// j = 5 -> aaaaa -> not contained in dictionary
// 
// i = 1
// i = 2 -> dp[2] == true -> [t,f,t,t,f,f]
// j = 3 -> dict.contains(s.subtr(2,3)) -> a
// j = 4 -> aa -> [t,f,t,t,t,f]
// j = 5 -> aaa -> [t,f,t,t,t,t]
// 
// i = 3 -> dp[3] == true -> [t,f,t,t,t,f]
// j = 4 -> dict.contains(s.substr(3,4)) -> a
// j = 5 -> aa -> [t,f,t,t,t,t]
// 
// continue repeating the process...