class Solution {
  // left, right, up, down
  private int[][] direction = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};
    
  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (matrix.length == 0) return result;
    boolean[][] reachesPacific = new boolean[matrix.length][matrix[0].length];
    boolean[][] reachesAtlantic = new boolean[matrix.length][matrix[0].length];
    
    for (int i = 0; i < matrix.length; i++) {
      explore(matrix, reachesPacific, i, 0);
      explore(matrix, reachesAtlantic, i, matrix[0].length-1);
    }
    for (int j = 0; j < matrix[0].length; j++) {
      explore(matrix, reachesPacific, 0, j);
      explore(matrix, reachesAtlantic, matrix.length-1, j);
    }    
    
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (reachesPacific[i][j] && reachesAtlantic[i][j]) {
          List<Integer> validCoordinates = new ArrayList<Integer>(Arrays.asList(i, j));
          result.add(validCoordinates);
        }
      }
    }
    return result;
  }
  
  private void explore(int[][] matrix, boolean [][] visitedOcean, int row, int column) {  
    visitedOcean[row][column] = true;
    for (int[] nextDirection : direction) {
      int i = row + nextDirection[0];
      int j = column + nextDirection[1];
      if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || visitedOcean[i][j] == true || matrix[i][j] < matrix[row][column]) {
        continue;
      }
      // the direction is within the matrix bounds and the direction has not been visited yet
      explore(matrix, visitedOcean, i, j);
    }
  }
}