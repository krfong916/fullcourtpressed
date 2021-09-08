class FenwickTree {
	public FenwickTree() {}
	public FenwickTree(long[] values) {
    // get length of the values array, n
    // our fenwick tree will be 1-based, instead of being 0-based
    // make a deep copy of the values array so that we don't modify the values array while we construct the fenwick tree, call this tree
    // for the length of the tree, starting at 1 to tree.length
      // add the current value of i to the parent of i
      // parent = i + lsb(i)
      // if parent <= tree.length
        // tree[parent] += tree[i]
    // return tree
  }

	public int rangeQuery(int begin, int end) {
		// if begin >= 1 and end >= 1, if begin >= getSize() || end >= getSize(), throw an exception
		// if begin > end, throw an exception
		// the range sum = prefixSum(end) - prefixSum(begin-1)
	}

  // cascade down operation
  private int prefixSum(int i) {
    // declare sum variable, prefixSum = 0
    // while i > 0
      // add r[i] to prefixSum
      // get least significant bit of i -> lsb(i)
      // i -= lsb(i)
    // return prefixSum
  }

	public void rangeUpdate(int i, int x) {
		// if i >= getSize() && i >= 1, throw an exception
		// while i <= getSize()
			// add x to r[i]
			// get least significant bit of i -> lsb(i)
			// i += lsb(i)
	}

	public String serialize() {}
	public void deserialize() {}
	private int getSize() {}
}