package org.naarad.muni.graph;

/**
 * <a href="https://leetcode.com/problems/max-area-of-island/?envType=study-plan&id=graph-i">max area of island</a>
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        MaxAreaOfIsland maxArea = new MaxAreaOfIsland();
        System.out.println(maxArea.maxAreaOfIsland(
                new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int totalRow = grid.length;
        int totalColumn = grid[0].length;
        int maxIslandArea = 0;

        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                if (grid[i][j] == 1) {
                    maxIslandArea = Math.max(maxIslandArea, calculateArea(grid, i, j, totalRow, totalColumn));
                }
            }
        }
        return maxIslandArea;
    }

    private int calculateArea(int[][] grid, int i, int j, int rows, int columns) {
        int totalArea = 1;

        grid[i][j] = 0;

        // go up
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            totalArea += calculateArea(grid, i - 1, j, rows, columns);
        }

        // go right
        if (j + 1 < columns && grid[i][j + 1] == 1) {
            totalArea += calculateArea(grid, i, j + 1, rows, columns);
        }

        // go down
        if (i + 1 < rows && grid[i + 1][j] == 1) {
            totalArea += calculateArea(grid, i + 1, j, rows, columns);
        }

        // go left
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            totalArea += calculateArea(grid, i, j - 1, rows, columns);
        }

        return totalArea;
    }
}
