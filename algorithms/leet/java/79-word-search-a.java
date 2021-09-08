class Solution {
  private int[][] direction = new int[][] {{ -1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public boolean exist(char[][] board, String word) {
    if (word.length() == 0) return false;
    boolean exists = false;
    boolean[][] visited = new boolean[board.length][board[0].length];
    char firstChar = word.charAt(0);

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (board[row][col] == firstChar) {
          if (findWord(board, visited, 0, word, row, col)) return true;
        }
      }
    }
    return false;
  }

  private boolean findWord(char[][] board, boolean[][] visited, int currentIndex, String word, int row, int col) {
    currentIndex += 1;
    if (currentIndex >= word.length()) return true;

    visited[row][col] = true;

    char nextChar = word.charAt(currentIndex);
    for (int[] nextDirection : direction) {
      int x = row + nextDirection[0];
      int y = col + nextDirection[1];
      if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y] == true) continue;
      if (board[x][y] == nextChar) {
        if (findWord(board, visited, currentIndex, word, x, y)) {
          return true;
        }
      }
    }
    visited[row][col] = false;
    return false;
  }
}