// DP Top-Down: Memoization with O(n) space and time
class Solution {
  public int fib(int n) {
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    return fibHelper(n, cache);
  }

  private int fibHelper(int n, HashMap<Integer, Integer> cache) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    if (n == 2) return 1;

    if (cache.containsKey(n)) return cache.get(n);
    int num = fibHelper(n - 1, cache) + fibHelper(n - 2, cache);
    cache.put(n, num);
    return cache.get(n);
  }
}