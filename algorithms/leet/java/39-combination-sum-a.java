class Solution {
  private List<List<Integer>> finalList = new ArrayList<>();
  private HashMap<String, Integer> dict = new HashMap<String, Integer>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> candidateList = new ArrayList<Integer>();
    combSumHelper(0, target, candidates, candidateList);
    return finalList;
  }
  private void combSumHelper(int sumSoFar, int target, int[] candidates, List<Integer> candidateList) {
    if (sumSoFar >= target) {
      if (sumSoFar == target) {
        Collections.sort(candidateList);
        String uniqueCombination = candidateList.toString();
        if (dict.containsKey(uniqueCombination) == false) {
          dict.put(uniqueCombination, 1);
          finalList.add(candidateList);
        }
      }
    } else {
      for (int i = 0; i < candidates.length; i++) {
        // choose
        sumSoFar += candidates[i];
        candidateList.add(candidates[i]);
        List<Integer> newList = new ArrayList<Integer>(candidateList);
        // recusively call
        combSumHelper(sumSoFar, target, candidates, newList);

        // unchoose: the number is not included in the list that sums up to the target
        sumSoFar -= candidates[i];
        candidateList.remove(candidateList.size() - 1);
      }
    }
  }
}