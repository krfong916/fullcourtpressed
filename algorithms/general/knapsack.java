class Solution {
  public static int knapsack(List<Item> items, int capacity) {
    // V[i][j] holds optimum value when we choose items[0:i] and have a capacity of j
    int[][] V = new int[items.size()][capacity + 1];
    for (int[] v : V) {
      Arrays.fill(v, -1);
    }
    return optimalValueAndWeight(items, items.size() - 1, capacity, V);
  }

// returns optimum value when we choose from items[0:k] and have a capacity of availableCapacity
  private static int optimalValueAndWeight(List<Integer> items, int k, int availableCapacity, int[][] V) {
    if (k < 0) return 0;

    // if no optimum capacity has been chosen for this size of capacity
    if (V[k][availableCapacity] == -1) {
      // either don't choose the current item
      int withoutCurrentItem = optimalValueAndWeight(items, k - 1, availableCapacity, V);
      int withCurrentItem = 0;

      // or choose it with respect to its weight
      // if there's room in the knapsack given the item's weight, put it in
      // else withCurrentItem = 0 <- meaning, it's too heavy
      if (availableCapacity > items.get(k).weight) {
        withCurrentItem = items.get(k).value + optimalValueAndWeight(items, k - 1, availableCapacity - items.get(k).weight, V);
      }

      //
      V[k][availableCapacity] = Math.max(withoutCurrentItem, withCurrentItem);
    }
    return V[k][availableCapacity];
  }
}