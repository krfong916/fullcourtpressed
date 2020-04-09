// DP Bottom-Up: Memoization with O(n) space and time
class Solution {
  public int fib(int n) {
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    cache.put(0, 0);
    cache.put(1, 1);
    cache.put(2, 1);
    int num = 0;
    for (int i = 2; i < n; i++) {
      num = cache.get(i - 1) + cache.get(i - 2);
      cache.put(i, num);
    }
    return cache.get(n - 1);
  }
}