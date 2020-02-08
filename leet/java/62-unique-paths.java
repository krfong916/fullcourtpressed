class Solution {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        for (int[] row: grid) {
            Arrays.fill(row, 1);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }
        return grid[m-1][n-1];
    }
}