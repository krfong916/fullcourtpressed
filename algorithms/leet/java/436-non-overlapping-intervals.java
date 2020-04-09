class Solution {
  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length <= 1) return 0;

    int numIntervalsErased = 0;
    Arrays.sort(intervals, (iA, iB) -> iA[0] - iB[0]);

    int[] prev = intervals[0];
    int[] current = intervals[1];

    for (int i = 1; i < intervals.length; i++) {
      current = intervals[i];

      if (current[0] == prev[0] || current[0] < prev[1]) {

        current[0] = prev[0];
        current[1] = Math.min(current[1], prev[1]); // erase overlapping interval using greedy-choice property
        numIntervalsErased++;
      }

      prev = current;
    }
    return numIntervalsErased;
  }
}

// [[-100,-2],[1,2],[2,3],[3,4],[5,7]]
//
// comp [-100, -2] and [1,2] -> [1,2]
// comp [1,2] and [2,3] ->

// [[1,100],[1,11],[2,12],[11,22]]
//
// comp [1,100] and [1,11] -> [1,11]
// comp [1,11] and [2,12] -> [1,11]
// comp [1,11] and [11,22] ->
//
// [[1,2],[1,3],[2,3],[3,4]]
//
// comp [1,2] and [1,3] -> [1,2]
// comp [1,2] and [2,3] -> [2,3]
// comp [2,3] and [3,4] -> [3,4]