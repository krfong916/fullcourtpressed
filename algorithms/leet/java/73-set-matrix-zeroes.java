class Solution {
  public void setZeroes(int[][] matrix) {
    
    // we will be using the first row/column as a marker
    // and therefore changing it's values
    // we need reference flags to remember whether or not
    // the first row or first column contains a zero
    boolean firstRowHasZero = false;
    boolean firstColumnHasZero = false;

    // if matrix[i][j] is a 0 value
    // mark the first index of row, and first index of column for change
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (i == 0 && j == 0 && matrix[i][j] == 0) {
          firstRowHasZero = true;
          firstColumnHasZero = true;
        } else if (i == 0 && matrix[i][j] == 0) {
          firstRowHasZero = true;
        } else if (j == 0 && matrix[i][j] == 0) {
          firstColumnHasZero = true;
        } else if (matrix[i][j] == 0 && i != 0 && j != 0) {
          markCells(matrix, i, j);
        }
      }
    }
    
    setMarkedCellsToZero(matrix, firstRowHasZero, firstColumnHasZero);
  }

  private void setMarkedCellsToZero(int[][] matrix, boolean firstRowHasZero, boolean firstColumnHasZero) {
    // fill row
    for (int i = 0; i < matrix.length; i++) {
      if (firstRowHasZero == true && i == 0) { 
        continue;
      } else if (matrix[i][0] == 0 && i != 0) {
        Arrays.fill(matrix[i], 0);
      }
    }

    // fill column
    for (int j = 0; j < matrix[0].length; j++) {
      if (firstColumnHasZero == true && j == 0) { 
        continue;
      } else if (matrix[0][j] == 0 && j != 0) {
        for (int i = 0; i < matrix.length; i++) {
          matrix[i][j] = 0;
        }
      }
    }
    
    // fill first row
    if (firstRowHasZero == true) {
      for (int j = 0; j < matrix[0].length; j++) {
        matrix[0][j] = 0;
      }
    }
    
    // fill first column
    if (firstColumnHasZero == true) {
      for (int i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
      }
    }
    
  }
  
  private void markCells(int[][] matrix, int i, int j) {
    matrix[i][0] = 0;
    matrix[0][j] = 0;
  }
}