class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> candidateList = new ArrayList<Integer>();
    List<List<Integer>> finalList = new ArrayList<>();

    combSumHelper(0, target, candidates, candidateList, finalList);

    return finalList;
  }
  private void combSumHelper(int currentIndex, int target, int[] candidates, List<Integer> candidateList, List<List<Integer>> finalList) {
    if (target == 0) finalList.add(new ArrayList<Integer>(candidateList));
    if (target > 0) {
      for (int i = currentIndex; i < candidates.length; i++) {
        // choose
        candidateList.add(candidates[i]);

        // recusively call and explore all options
        combSumHelper(i, target - candidates[i], candidates, candidateList, finalList);

        // unchoose: the number is not included in the list that sums up to the target
        candidateList.remove(candidateList.size() - 1);
      }
    }
  }
}