class Solution {
  public int lastStoneWeight(int[] stones) {
    // create a maximum heap and a capacity, the initial number of stones
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stones.length, Collections.reverseOrder());

    // fill the max-heap
    for (int stone : stones) {
      maxHeap.add(stone);
    }

    // calculate final stone weight
    while (maxHeap.size() > 1) {
      int y = maxHeap.poll();
      int x = maxHeap.poll();
      if (y != x) {
        y -= x;
        maxHeap.add(y);
      }
    }
    return maxHeap.size() == 1 ? maxHeap.poll() : 0;
  }
}