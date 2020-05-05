class FenwickTree {
	public FenwickTree() {}
	public FenwickTree(long[] values) {}

	public int rangeSum(int begin, int end) {
		// if begin >= 1 and end >= 1, if begin >= getSize() || end >= getSize(), throw an exception
		// if begin > end, throw an exception
		// declare range sum variable, sum
		// while begin <= end
			// add r[begin] to sum
			// get least significant bit of begin -> lsb(begin)
			// begin += lsb(i)
	}
	public void rangeUpdate(int i, int x) {
		// if i >= getSize() && i >= 1, throw an exception
		// while i < getSize()
			// add x to r[i]
			// get least significant bit of i -> lsb(i)
			// i += lsb(i)
	}
	public String serialize() {}
	public void deserialize() {}
	private int getSize() {}
}