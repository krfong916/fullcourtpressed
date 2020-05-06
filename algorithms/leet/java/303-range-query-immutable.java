class NumArray {
  private int[] prefixSumTable;

  public NumArray(int[] values) {
    prefixSumTable = new int[values.length + 1];

    prefixSumTable[0] = 0;
    for (int i = 1; i < prefixSumTable.length; i++) {
      prefixSumTable[i] = values[i - 1] + prefixSumTable[i - 1]
    }
  }

  public int sumRange(int i, int j) {
    if (i < 0 || j < 0 || i > j || i >= values.length || j >= values.length) return 0;
    return prefixSumTable[j + 1] - prefixSumTable[i];
  }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */


/* Pseudocode */

/*
class NumArray {

    private int[] prefixSumTable;

    // construction is O(n) time and O(n) space
    public NumArray(int[] values) {
      // prefixSumTable = new int[values.length];
      // initialize a prefix sum table
      // for every entry in the values array
        // we want to calculate the prefix sum of every entry
        // i.e. v[3] = v[2] + v[1]
        // more generally, prefix sum table of i = prefix sum table i - 1
      // we have base cases, if the values table is == 0, do nothing
      // if the values table == 1, prefixSumTable[0] = 0
      // if the values table == 2, prefixSumTable[1] == prefixSumTable[0]
      // if the values table == 3, prefixSumTable[2] == prefixSumTable[1] + prefixSumTable[0]
    }

    // sum range is calculated in O(2*n) time -> O(n)
    // further, we use 0 to j inclusive, 0 to i exclusive
    public int sumRange(int i, int j) {
      // check if valid range i.e. i > 0, j >= 0, i < valuesTable.length, j < valuesTable.length && i <= j
      // returns getPointSum(j) - getPointSum(i-1)
    }

    // getPointSum(index)
    // gets the sum up to the point
    // declare a variable called sum
    // while the index >= 0
      // add the prefixSumTable[index] to sum
      // decrement index
    // return sum
}