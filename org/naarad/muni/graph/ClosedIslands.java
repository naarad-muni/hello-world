package org.naarad.muni.graph;

/**
 * <a href="https://leetcode.com/problems/number-of-closed-islands/?envType=study-plan&id=graph-i">
 * Calculate closed islands
 * </a>
 * <p>
 * Constraints:
 * <p>
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class ClosedIslands {
    public static void main(String[] args) {
        ClosedIslands closedIslands = new ClosedIslands();

        System.out.println(closedIslands.closedIsland(
                new int[][]{
                        {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                        {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                        {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}}
        ));
    }

    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int totalIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 0 && isValidIsland(grid, i, j, rows, columns)) {
                    totalIslands++;
                }
            }
        }
        return totalIslands;
    }

    private boolean isValidIsland(int[][] grid, int i, int j, int rows, int columns) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            return false;
        }

        if (grid[i][j] == 1 || grid[i][j] == 2) {
            return true;
        }

        grid[i][j] = 2;

        boolean validIslandUp = isValidIsland(grid, i - 1, j, rows, columns);
        boolean validIslandRight = isValidIsland(grid, i, j + 1, rows, columns);
        boolean validIslandDown = isValidIsland(grid, i + 1, j, rows, columns);
        boolean validIslandLeft = isValidIsland(grid, i, j - 1, rows, columns);

        return validIslandUp && validIslandRight && validIslandDown && validIslandLeft;
    }
}
