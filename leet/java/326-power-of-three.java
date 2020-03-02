class Solution {
  public boolean isPowerOfThree(int n) {
    if (n == 2) return false;
    if (n <= 0 || n >= Integer.MAX_VALUE) return false;
    int current = 3;
    int exponent = 0;
    while (current <= n) {
      int num = (int)Math.pow(current, exponent);
      if (num > n) return false;
      if (num == n) return true;
      exponent++;
    }
    return true;
  }
}