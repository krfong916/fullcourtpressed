class Solution {
  public String[] reorderLogFiles(String[] logs) {
    Arrays.sort(logs, (s1, s2) -> {
      // we partition the log entry into a word
      // word = identifier + log type
      // log[] = log[0] = identifier, log[1] = log type
      String[] log1 = s1.split(" ", 2);
      String[] log2 = s2.split(" ", 2);

      // the log type is either digit or letter
      boolean isDigit1 = Character.isDigit(log1[1].charAt(0));
      boolean isDigit2 = Character.isDigit(log2[1].charAt(0));

      // if both logs are letter logs
      if (!isDigit1 && !isDigit2) {
        int comp = log1[1].compareTo(log2[1]);
        // if the log entries are equivalent then we must use the identifier to sort by alphanumeric
        if (comp == 0) return log1[0].compareTo(log2[0]);
        // else return the result of the comparison
        else return comp;
      } else if (isDigit1 && isDigit2) { // if both logs are digits, do nothing
        return 0;
      } else if (isDigit1 && !isDigit2) { // if the first log is a digit and the second is a letter
        // then swap the digit and letter log
        return 1;
      } else { // the letter log precedes the digit log, keep the letter log in place
        return -1;
      }
    });
    return logs;
  }
}