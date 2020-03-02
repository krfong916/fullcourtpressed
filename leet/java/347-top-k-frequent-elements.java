class Solution {
  public List<Integer> topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int n : nums) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }
    PriorityQueue<Map.Entry<Integer, Integer>> frequency = new PriorityQueue<>((a, b)->b.getValue() - a.getValue());
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      frequency.add(e);
    }

    List<Integer> topK = new ArrayList<Integer>();
    for (int i = 0; i < k; i++) {
      Map.Entry<Integer, Integer> elem = frequency.poll();
      topK.add(elem.getKey());
    }
    return topK;
  }
}