class Solution {
  public int romanToInt(String romanNumeral) {
    // empty
    if (romanNumeral == "") return 0;
    int intRepresentation = 0;

    char[] arr = romanNumeral.toCharArray();
    int nextChar = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i < arr.length - 1) nextChar = i + 1;
      char curr = arr[i];
      // I can be placed before V (5) and X (10) to make 4 and 9.
      if (curr == 'I' && arr[nextChar] == 'V') {
        intRepresentation += 4;
        i++;
      } else if (curr == 'I' && arr[nextChar] == 'X') {
        intRepresentation += 9;
        i++;
      } else if (curr == 'X' && arr[nextChar] == 'L') {
        intRepresentation += 40;
        i++;
      } else if (curr == 'X' && arr[nextChar] == 'C') {
        intRepresentation += 90;
        i++;
      } else if (curr == 'C' && arr[nextChar] == 'D') {
        intRepresentation += 400;
        i++;
      } else if (curr == 'C' && arr[nextChar] == 'M') {
        intRepresentation += 900;
        i++;
      } else {
        switch (curr) {
        case 'I':
          intRepresentation += 1;
        case 'V':
          intRepresentation += 5;
        case 'X':
          intRepresentation += 10;
        case 'L':
          intRepresentation += 50;
        case 'C':
          intRepresentation += 100;
        case 'D':
          intRepresentation += 500;
        case 'M':
          intRepresentation += 1000;
        }
      }
    }
    return intRepresentation;
  }
}