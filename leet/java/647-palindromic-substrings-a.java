class Solution {
  public int countSubstrings(String s) {
    int totalPalindromes = 0;
    int j = s.length() - 1;
    for (int start = 0; start < s.length(); start++) {
      for (int end = j; end >= start; end--) {
        if (s.charAt(start) == s.charAt(end)) {
          if (isPalindrome(s, start, end)) {
            totalPalindromes++;
          }
        }
      }
    }
    return totalPalindromes;
  }

  private boolean isPalindrome(String s, int start, int end) {
    boolean isPalindrome = true;
    while (start <= end) {
      if (s.charAt(start) != s.charAt(end)) {
        isPalindrome = false;
        break;
      }
      start++;
      end--;
    }
    return isPalindrome;
  }
}
