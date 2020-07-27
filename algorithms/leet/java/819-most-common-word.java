class Solution {
  public String mostCommonWord(String paragraph, String[] banned) {
    Set<String> bannedDict = new HashSet<>();
    HashMap<String, Integer> freqDict = new HashMap<String, Integer>();
    int maxCount = 0;
    String mostFreqWord = "";

    // fill paragraph to iterate over
    String[] text = paragraph.split("\\W+");

    // fill banned list
    for (int i = 0; i < banned.length; i++) {
      String bannedWord = banned[i];
      bannedDict.add(bannedWord);
    }

    // fill frequencyDict
    for (int i = 0; i < text.length; i++) {
      // parse the next word
      String currentWord = text[i].toLowerCase();

      // check if the word exists in the freq dictionary
      if (bannedDict.contains(currentWord) == false) {
        if (freqDict.containsKey(currentWord)) {
          freqDict.put(currentWord, freqDict.get(currentWord) + 1);
        } else {
          freqDict.put(currentWord, 1);
        }
      }
    }

    // determine the most frequent word
    List<String> words = new ArrayList<String>(freqDict.keySet());
    for (String word : words) {
      int count = freqDict.get(word);
      if (count > maxCount) {
        maxCount = Math.max(count, maxCount);
        mostFreqWord = word;
      }
    }

    return mostFreqWord;
  }
}