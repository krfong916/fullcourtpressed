class Solution {
  public int countSubstrings(String s) {
    int totalPalindromes = 0;

    for (int start = 0; start < s.length(); start++) {
      totalPalindromes += countPalindromes(s, start, start);
      totalPalindromes += countPalindromes(s, start, start + 1);
    }
    return totalPalindromes;
  }

  private int countPalindromes(String s, int start, int end) {
    int count = 0;
    while (start >= 0 && end < s.length() && start <= end && s.charAt(start) == s.charAt(end)) {
      count++;
      start--;
      end++;
    }
    return count;
  }
}