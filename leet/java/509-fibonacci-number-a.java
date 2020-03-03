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

// 5 -> 8
// (2)
// fn1 = 1, b = 1, a = 0
// b = 2, a = 1
// (3)
// fn1 = 2, b = 2, a = 1
// b = 3, a = 2
// (4)
// fn1 = 3, b = 3, a = 2
// b = 5, a = 3
// (5)
// fn1 = 5, b = 5, b = 3
// b = 8, a = 5