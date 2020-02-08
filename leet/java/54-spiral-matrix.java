class Solution {
    enum Compass {
        RIGHT, LEFT, UP, DOWN;
    }
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix.length == 0 || matrix[0].length == 0) return list;
        boolean[][] grid = new boolean[matrix.length][matrix[0].length];
        Compass direction = Compass.RIGHT;
        getSpiral(matrix, grid, list, 0, 0, direction);
        return list;
    }
    
    private void getSpiral(int[][] matrix, boolean[][] grid, List<Integer>list, int x, int y, Compass direction) {
        while (hasValidMove(x, y, grid)) {
            addToList(matrix, grid, list, x, y);
            direction = getNextDirection(x, y, grid, direction);
            switch (direction) {
                case RIGHT: y++; break;
                case DOWN: x++; break;
                case LEFT: y--; break;
                case UP: x--; break;
            }
        }
    }

    private Compass getNextDirection(int x, int y, boolean[][] grid, Compass currentDirection) {
        Compass nextDirection = currentDirection;
        switch (currentDirection) {
            case RIGHT:
                nextDirection = (y+1 < grid[0].length && grid[x][y+1] == false) ? Compass.RIGHT : Compass.DOWN;
                break;
            case DOWN:
                nextDirection = (x+1 < grid.length && grid[x+1][y] == false) ? Compass.DOWN : Compass.LEFT;
                break;
            case LEFT:
                nextDirection = (y-1 >= 0 && grid[x][y-1] == false) ? Compass.LEFT : Compass.UP;
                break;
            case UP:
                nextDirection = (x-1 >= 0 && grid[x-1][y] == false) ? Compass.UP : Compass.RIGHT;
                break;
        }
        return nextDirection;
    }
    
    private boolean hasValidMove(int i, int j, boolean[][] grid) {
        if (i < 0 || j < 0) return false; // edge cases
        if (i == grid.length || j == grid[0].length) return false; // edge cases
        return grid[i][j] == false;
    }
    
    private void addToList(int[][] matrix, boolean[][] grid, List<Integer>list, int i, int j) {
        grid[i][j] = true;
        list.add(list.size(), matrix[i][j]);
    }
    
}