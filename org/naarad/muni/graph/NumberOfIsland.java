package org.naarad.muni.graph;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/?envType=study-plan&id=graph-i">Find number of island</a>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length <br />
 * n == grid[i].length  <br />
 * 1 <= m, n <= 300   <br />
 * grid[i][j] is '0' or '1'.   <br />
 */
public class NumberOfIsland {
    /*
    numberOfIsland's initial value is 1, signifying that there are no island discovered yet
    once an island is discovered we will update the value fill all 1's with whatever the
    numberOfIsland's value will be.
     */ int numberOfIsland = 1 + 100;

    public static void main(String[] args) {
        NumberOfIsland islands = new NumberOfIsland();
        System.out.println(islands.numIslands(new char[][]{
                {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
                {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0'},
                {'0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '1'},
                {'1', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'},
                {'0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '0', '1'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '1'},
                {'0', '0', '0', '0', '1', '0', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '1', '0', '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '1', '0', '0', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
                {'1', '0', '0', '0', '0', '1', '0', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0'}}

        ));
    }

    public int numIslands(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    traceIsland(grid, i, j);
                }
            }
        }

        return numberOfIsland - 1 - 100;
    }

    public void traceIsland(char[][] grid, int row, int column) {
        numberOfIsland++; // increment the number of island

        int totalRow = grid.length;
        int totalColumn = grid[0].length;

        final Stack<Node> stackTrace = new Stack<>();
        stackTrace.add(new Node(row, column));

        while (!stackTrace.isEmpty()) {
            int i = stackTrace.peek().row;
            int j = stackTrace.peek().column;
            stackTrace.pop();

            //up
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                stackTrace.add(new Node(i - 1, j));
            }
            //right
            if (j + 1 < totalColumn && grid[i][j + 1] == '1') {
                stackTrace.add(new Node(i, j + 1));
            }
            //down
            if (i + 1 < totalRow && grid[i + 1][j] == '1') {
                stackTrace.add(new Node(i + 1, j));
            }
            //left
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                stackTrace.add(new Node(i, j - 1));
            }

            grid[i][j] = (char) numberOfIsland;
        }
    }

    private int calculateRelatedIndex(int totalColumn, int row, int column) {
        return (totalColumn * row) + column;
    }

    class Node {
        int row;
        int column;

        Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
