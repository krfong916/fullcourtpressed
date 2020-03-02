class Solution {
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        dp[i][j] = calcMinPathSum(i, j, grid, dp);
      }
    }

    return dp[m - 1][n - 1];
  }

  private int calcMinPathSum(int i, int j, int[][] grid, int[][] dp) {
    // base case
    if (i == 0 && j == 0) {
      return grid[i][j];

      // case: when j = 0, dp[i][j-1] does not exist
      // therefore dp[i][j] = previous + current
    } else if (j == 0 && i > 0) {
      return dp[i - 1][j] + grid[i][j];

      // case: when i = 0, dp[i-1][j] does not exist
      // therefore dp[i][j] = previous + current
    } else if (i == 0 && j > 0) {
      return dp[i][j - 1] + grid[i][j];

      // case: i > 0 and j > 0
    } else {
      return Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
    }
  }
}