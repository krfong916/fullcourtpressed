// complexity of O(nlogn) upper-bounded by the sort
// space complexity is O(n) because we allocate structure that contains n-many intervals
class Solution {
  public int[][] merge(int[][] intervals) {
    if (intervals.length <= 1) return intervals;
    // sort intervals by start time
    Arrays.sort(intervals, (intervalA, intervalB) -> Integer.compare(intervalA[0], intervalB[0]));

    // create a list that will contain all merged intervals
    List<int[]> merged = new ArrayList<>();
    int[] prev = intervals[0];

    // for each interval in the list of intervals
    for (int[] currentInterval : intervals) {
      // if the intervals overlap by current(start) and prev(end)
      if (currentInterval[0] <= prev[1]) {
        prev[1] = Math.max(currentInterval[1], prev[1]);

      } else { // the intervals are disjoint
        merged.add(prev);
        prev = currentInterval;
      }
    }

    // add the last interval
    merged.add(prev);

    // convert the list into an int of intervals and return
    return merged.toArray(new int[merged.size()][]);
  }
}
