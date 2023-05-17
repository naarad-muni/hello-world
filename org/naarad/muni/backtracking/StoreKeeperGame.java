package org.naarad.muni.backtracking;

/**
 * TODO: pending solution, many test cases are failing.
 * <a href="https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/">Storekeeper game</a>
 *
 * <h3>Constraints: </h3>
 * <p>
 * m == grid.length <br />
 * n == grid[i].length <br />
 * 1 <= m, n <= 20 <br />
 * grid contains only characters '.', '#', 'S', 'T', or 'B'. <br />
 * There is only one character 'S', 'B', and 'T' in the grid. <br />
 */
public class StoreKeeperGame {
    public static void main(String[] args) {

        StoreKeeperGame game = new StoreKeeperGame();
        System.out.println(game.minPushBox(new char[][]{
                        {'#', '.', '.', '#', 'T', '#', '#', '#', '#'},
                        {'#', '.', '.', '#', '.', '#', '.', '.', '#'},
                        {'#', '.', '.', '#', '.', '#', 'B', '.', '#'},
                        {'#', '.', '.', '.', '.', '.', '.', '.', '#'},
                        {'#', '.', '.', '.', '.', '#', '.', 'S', '#'},
                        {'#', '.', '.', '#', '.', '#', '#', '#', '#'}
                }
        ));
    }

    static final char UNDER_PROCESSING = 'u';
    static final char FLOOR = '.';
    static final char TARGET = 'T';
    static final char BOX = 'B';
    static final char WALL = '#';
    static final char PLAYER = 'S';
    static final char CANNOT_REACH_BOX = 'X';
    static final int CANNOT_REACH_TARGET = -1;
    static final int UNDER_EVALUATION = -2;

    public int minPushBox(char[][] grid) {

        int[][] memoization = new int[grid.length][grid[0].length];

        int boxI = 0;
        int boxJ = 0;
        int playerI = 0;
        int playerJ = 0;
        int targetI = 0;
        int targetJ = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == PLAYER) {
                    playerI = i;
                    playerJ = j;
                    grid[i][j] = FLOOR;
                }
                if (grid[i][j] == BOX) {
                    boxI = i;
                    boxJ = j;
                    grid[i][j] = FLOOR;
                }
                if (grid[i][j] == TARGET) {
                    targetI = i;
                    targetJ = j;
                    grid[i][j] = FLOOR;
                }
            }
        }

        return recursion(grid, memoization, boxI, boxJ, playerI, playerJ, targetI, targetJ);
    }

    private int recursion(final char[][] grid, final int[][] memoization, final int boxI, final int boxJ,
                          final int playerI, final int playerJ, final int targetI, final int targetJ) {
        if (memoization[boxI][boxJ] > 0) {
            // This condition means that we have earlier calculated for this position
            return memoization[boxI][boxJ];
        }

        int minimumMoves = CANNOT_REACH_TARGET;
        int calculateResult;

        // now we will be evaluating this position
        memoization[boxI][boxJ] = UNDER_EVALUATION;

        // Move UP from present position
        calculateResult = moveBoxAndReturnMin(grid, memoization, boxI, boxJ, boxI - 1, boxJ, playerI, playerJ, boxI + 1,
                boxJ, targetI, targetJ);

        minimumMoves = getMinimumMoves(minimumMoves, calculateResult);

        // Move RIGHT from present position
        calculateResult = moveBoxAndReturnMin(grid, memoization, boxI, boxJ, boxI, boxJ + 1, playerI, playerJ, boxI,
                boxJ - 1, targetI, targetJ);

        minimumMoves = getMinimumMoves(minimumMoves, calculateResult);

        // Move DOWN from present position
        calculateResult = moveBoxAndReturnMin(grid, memoization, boxI, boxJ, boxI + 1, boxJ, playerI, playerJ, boxI - 1,
                boxJ, targetI, targetJ);

        minimumMoves = getMinimumMoves(minimumMoves, calculateResult);

        // Move LEFT from present position

        calculateResult =
                moveBoxAndReturnMin(grid, memoization, boxI, boxJ, boxI, boxJ - 1, playerI, playerJ, boxI, boxJ + 1,
                        targetI, targetJ);

        minimumMoves = getMinimumMoves(minimumMoves, calculateResult);

        memoization[boxI][boxJ] = minimumMoves;
        return minimumMoves;
    }

    private static int getMinimumMoves(int minimumMoves, int currentResult) {
        if (currentResult > 0) {
            if (minimumMoves > 0) {
                return Math.min(currentResult, minimumMoves);
            } else {
                return currentResult;
            }
        }

        return minimumMoves;
    }

    private int moveBoxAndReturnMin(char[][] grid, int[][] memoization, int currentBoxI, int currentBoxJ,
                                    int desiredBoxI, int desiredBoxJ, int currentPlayerPositionI,
                                    int currentPlayerPositionJ, int desiredPlayerPositionI,
                                    int desiredPlayerPositionJ, final int targetI, final int targetJ) {
        if (canMoveBox(grid, memoization, currentBoxI, currentBoxJ, desiredBoxI, desiredBoxJ, currentPlayerPositionI,
                currentPlayerPositionJ, desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ)) {
            if (desiredBoxI == targetI && desiredBoxJ == targetJ) {
                memoization[currentBoxI][currentBoxJ] = 1;
                return 1;
            } else {
                int result = recursion(grid, memoization, desiredBoxI, desiredBoxJ, desiredPlayerPositionI,
                        desiredPlayerPositionJ, targetI, targetJ);
                if (result > 0) {
                    return result + 1;
                }
            }
        }

        return CANNOT_REACH_TARGET;
    }

    private boolean canMoveBox(final char[][] grid, final int[][] memoization, final int currentBoxI,
                               final int currentBoxJ, final int desiredBoxI, final int desiredBoxJ,
                               final int currentPlayerPositionI, final int currentPlayerPositionJ,
                               final int desiredPlayerPositionI, final int desiredPlayerPositionJ,
                               final int targetI, final int targetJ) {
        boolean moveNext =
                desiredBoxI >= 0 && desiredBoxI < grid.length && desiredBoxJ >= 0 && desiredBoxJ < grid[0].length &&
                        grid[desiredBoxI][desiredBoxJ] != WALL &&
                        memoization[desiredBoxI][desiredBoxJ] != UNDER_EVALUATION &&
                        memoization[desiredBoxI][desiredBoxJ] != CANNOT_REACH_TARGET;

        if (moveNext) {
            char[][] playerMemo = new char[grid.length][grid[0].length];

            moveNext = canPlayerTraverse(grid, playerMemo, currentBoxI, currentBoxJ, currentPlayerPositionI,
                    currentPlayerPositionJ, desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ);
        }

        return moveNext;
    }

    private boolean canPlayerTraverse(final char[][] grid, final char[][] playerMemo, final int boxI, final int boxJ,
                                      final int currentPlayerPositionI, final int currentPlayerPositionJ,
                                      final int desiredPlayerPositionI, final int desiredPlayerPositionJ,
                                      final int targetI, final int targetJ) {
        if (currentPlayerPositionI == desiredPlayerPositionI && currentPlayerPositionJ == desiredPlayerPositionJ) {
            return true;
        }

        playerMemo[currentPlayerPositionI][currentPlayerPositionJ] = UNDER_PROCESSING;

        // if we can move UP
        if (extracted(grid, playerMemo, boxI, boxJ, currentPlayerPositionI - 1, currentPlayerPositionJ,
                desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ)) {
            return true;
        }

        // if we can move RIGHT
        if (extracted(grid, playerMemo, boxI, boxJ, currentPlayerPositionI, currentPlayerPositionJ + 1,
                desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ)) {
            return true;
        }

        // if we can move DOWN
        if (extracted(grid, playerMemo, boxI, boxJ, currentPlayerPositionI + 1, currentPlayerPositionJ,
                desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ)) {
            return true;
        }

        // if we can move LEFT
        if (extracted(grid, playerMemo, boxI, boxJ, currentPlayerPositionI, currentPlayerPositionJ - 1,
                desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ)) {
            return true;
        }

        playerMemo[currentPlayerPositionI][currentPlayerPositionJ] = CANNOT_REACH_BOX;
        return false;
    }

    private boolean extracted(char[][] grid, char[][] playerMemo, final int boxI, final int boxJ,
                              final int currentPlayerPositionI, final int currentPlayerPositionJ,
                              final int desiredPlayerPositionI, final int desiredPlayerPositionJ, final int targetI,
                              int targetJ) {
        if (canPlayerMove(grid, playerMemo, boxI, boxJ, currentPlayerPositionI, currentPlayerPositionJ, targetI,
                targetJ)) {
            return canPlayerTraverse(grid, playerMemo, boxI, boxJ, currentPlayerPositionI, currentPlayerPositionJ,
                    desiredPlayerPositionI, desiredPlayerPositionJ, targetI, targetJ);
        }
        return false;
    }

    private boolean canPlayerMove(final char[][] grid, final char[][] playerMemo, final int boxI, final int boxJ,
                                  final int i, final int j, final int targetI, final int targetJ) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && playerMemo[i][j] != UNDER_PROCESSING &&
                playerMemo[i][j] != CANNOT_REACH_BOX && !(i == boxI && j == boxJ) &&
                (grid[i][j] == FLOOR || (i == targetI && j == targetJ) || grid[i][j] == PLAYER);
    }

}
