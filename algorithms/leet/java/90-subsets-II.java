class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    int currentIndex = 0;
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    ArrayList<Integer> subset = new ArrayList<Integer>();
    Set<ArrayList<Integer>> dict = new HashSet<>();

    computePowerSet(nums, currentIndex, dict, subset, result);
    return result;
  }

  private void computePowerSet(int[] nums, int currentIndex, Set<ArrayList<Integer>> dict, ArrayList<Integer> subset, List<List<Integer>> result) {

    if (dict.contains(subset)) return;
    if (currentIndex == nums.length) {
      ArrayList<Integer> validSubset = new ArrayList<>(subset);
      dict.add(validSubset);
      result.add(validSubset);
    } else {
      for (int i = currentIndex; i < nums.length; i++) {
        subset.add(nums[i]);
        computePowerSet(nums, currentIndex + 1, dict, subset, result);
        subset.remove(subset.size() - 1);
        computePowerSet(nums, currentIndex + 1, dict, subset, result);
      }
    }
  }
}
