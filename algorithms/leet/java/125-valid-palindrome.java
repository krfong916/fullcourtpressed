class Solution {
  public boolean isPalindrome(String s) {
    // using a two-pointer solution
    // one pointer starting at the beginning of the string
    // the second pointer starting at the end of the string
    // increment and decrement each pointer
    int start = 0;
    int end = s.length() - 1;
    s = s.toUpperCase();
    char[] str = s.toCharArray();

    // we terminate this search when pointer at start is greater than pointer at end
    while (start <= end) {
      // evaluate the character found at each pointer
      // if either pointer is a non-alphanumeric character
      // increment or decrement that pointer
      if (isAlphaNum(str[start]) == false) {
        start++;
        continue;
      }
      if (isAlphaNum(str[end]) == false) {
        end--;
        continue;
      }

      // else, if both characters are alpha-numeric
      // then compare - if the characters do not equal each other
      if (str[start] != str[end]) {
        return false;
      }
      // increment and decrement the pointers
      start++;
      end--;
    }
    return true;
  }

  private boolean isAlphaNum(char c) {
    if (Character.isLetter(c) || Character.isDigit(c)) {
      return true;
    } else {
      return false;
    }
  }

}