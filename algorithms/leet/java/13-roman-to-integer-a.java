class Solution {
  public int romanToInt(String romanNumeral) {
    // empty
    if (romanNumeral == "") return 0;

    HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
    int intRepresentation = 0;

    createRomanDict(dict);

    // consider edge case where romanNumberal is a single character
    if (romanNumeral.length() < 2) {
      return dict.get(romanNumeral.charAt(0));
    }

    char[] arr = romanNumeral.toCharArray();
    int nextChar = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i < arr.length - 1) nextChar = i + 1;

      if (arr[i] == 'I' && arr[nextChar] == 'V') {
        intRepresentation += 4;
        i++;
      } else if (arr[i] == 'I' && arr[nextChar] == 'X') {
        intRepresentation += 9;
        i++;
      } else if (arr[i] == 'X' && arr[nextChar] == 'L') {
        intRepresentation += 40;
        i++;
      } else if (arr[i] == 'X' && arr[nextChar] == 'C') {
        intRepresentation += 90;
        i++;
      } else if (arr[i] == 'C' && arr[nextChar] == 'D') {
        intRepresentation += 400;
        i++;
      } else if (arr[i] == 'C' && arr[nextChar] == 'M') {
        intRepresentation += 900;
        i++;
      } else {
        intRepresentation += dict.get(arr[i]);
      }
    }

    return intRepresentation;
  }

  private void createRomanDict(HashMap<Character, Integer> dict) {
    dict.put('I', 1);
    dict.put('V', 5);
    dict.put('X', 10);
    dict.put('L', 50);
    dict.put('C', 100);
    dict.put('D', 500);
    dict.put('M', 1000);
  }
}