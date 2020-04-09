// DP Bottom-Up: Constant Space
class Solution {
  public int fib(int N) {
    if (N == 0) return 0;
    int a = 0; // f(0)
    int b = 1; // f(1)
    int fn1 = b;
    for (int i = 2; i <= N; i++) {
      fn1 = b;
      b = b + a;
      a = fn1;
    }
    return b;
  }
}
