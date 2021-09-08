class NumArray {

  private int[] tree;
  private int length;

  public NumArray(int[] nums) {
    this.length = nums.length;
    this.tree = new int[length + 1];

    // we change the tree from being 0-indexed to 1-indexed
    for (int i = 0; i < length; i++) {
      updateTree(i, nums[i]);
    }
  }

  private void updateTree(int i, int val) {
    i = i + 1;
    while (i <= this.length) {
      this.tree[i] += val;
      i += getLSB(i);
    }
  }

  private static int getLSB(int i) {
    return (i & -i);
  }

  public int sumRange(int i, int j) {
    return getPointSum(j) - getPointSum(i - 1);
  }

  private int getPointSum(int i) {
    i = i + 1;
    int sum = 0;
    while (i > 0) {
      sum += this.tree[i];
      i -= getLSB(i);
    }
    return sum;
  }

  // update is a set operation, not an add operation
  public void update(int i, int val) {
    updateTree(i, val - sumRange(i, i));
  }
}