class Solution {
  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length <= 1) return 0;

    Arrays.sort(intervals, (iA, iB) -> iA[1] - iB[1]); // sort by increasing end times

    int count = 1; // number of total non-overlapping intervals
    int previousEnd = intervals[0][1]; // we define overlapping as B(start) < A(end)
    int[] current = intervals[1][0];

    for (int i = 1; i < intervals.length; i++) {
      current = intervals[i];
      if (current[0] >= previousEnd) { // satisfies not overlapping condition
        // update end
        previousEnd = current[1];
        count++;
      }
    }

    return intervals.length - count; // calculates number of intervals that overlap
  }
}

// test examples
// [[1,11],[2,12],[11,22],[1,100]]
//
// comp [2,12] and 11
// comp [11,22] and 11 -> use 22 and num non-overlap:2
// comp [1,100] and 22
//
// 4 - 2 = 2 intervals that must be erased
//
// [[1,2],[1,3],[2,3],[3,4],[5,7]
//
// comp [1,3] and 2
// comp [2,3] and 2 -> use 3 and num non-overlap:2
// comp [3,4] and 3 -> use 3 and num non-overlap:3
// comp [5,7] and 3 -> use 7 and num non-overlap:4
//
// total length - non-overlapping = number of overlapping
// 5 - 4 = 1 intervals that must be erased
//
// [[-100,-2],[1,2],[2,3],[3,4],[5,7]]