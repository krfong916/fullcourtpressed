class Solution {
  public String minWindow(String s, String t) {
    if (t.length() == 0) return "";
    HashMap<Character, Integer> dict = new HashMap<>();
    for (int i = 0; i < t.length(); i++) dict.put(t.charAt(i), dict.getOrDefault(t.charAt(i), 0) + 1);
    
    int characterCount = t.length(); int leftPointer = 0;
    int minLeftIndex = 0; int len = s.length()+1;
    
    for (int rightPointer = 0; rightPointer < s.length(); rightPointer++) {
      Character rightChar = s.charAt(rightPointer);
      if (dict.containsKey(rightChar)) {
        dict.put(rightChar, dict.get(rightChar) -1);
        if (dict.get(rightChar) >= 0) {
          characterCount--;
          while (characterCount == 0) {
            if (rightPointer - leftPointer + 1 < len) {
              len = rightPointer - leftPointer + 1;
              minLeftIndex = leftPointer;
            }
            Character leftChar = s.charAt(leftPointer);
            if (dict.containsKey(leftChar)) {
              dict.put(leftChar, dict.get(leftChar) + 1);
              if (dict.get(leftChar) > 0) characterCount++;
            }
            leftPointer++;
          }
        }
      }
    }
    
    if (len > s.length()) return "";
    return s.substring(minLeftIndex, minLeftIndex+len);
  }
}