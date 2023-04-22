import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/out-of-boundary-paths/">Problem statement link</a>
 */
public class OutOfBoundaryPaths {
    public static void main(String[] args) {
        OutOfBoundaryPaths p = new OutOfBoundaryPaths();
        System.out.println(p.findPaths(16, 35, 47, 12, 31)); // expected answer in leetcode is 914783380
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }

        /* This map is used for memoization, where key is "row,column,moveLeft" and value is maxMovePossible */
        Map<String, Long> moveToMaxPossibleWay = new HashMap<>();

        return (int) ((runDFS(m, n, maxMove, startRow, startColumn, moveToMaxPossibleWay)) % (long) 1000000007);
    }

    public long runDFS(int m, int n, int moveLeft, int currentRow, int currentColumn, Map<String, Long> memoization) {
        if (currentRow == m || currentRow == -1 || currentColumn == n || currentColumn == -1) {
            // This scenario indicates that we are out of the grid, thus returning 1
            return 1;
        }

        if (moveLeft == 0) {
            // this scenario indicates that we have no moves left, and we are still inside the grid
            return 0;
        }

        long currentNodeMoveCount = 0;
        // Calculate For left
        final String leftKey = constructKey(currentRow, currentColumn - 1, moveLeft - 1);

        if (!memoization.containsKey(leftKey)) {
            memoization.put(leftKey,
                    runDFS(m, n, moveLeft - 1, currentRow, currentColumn - 1, memoization) % (long) 1000000007);
        }
        currentNodeMoveCount = (currentNodeMoveCount + memoization.get(leftKey)) % (long) 1000000007;

        // calculate for up
        final String upKey = constructKey(currentRow - 1, currentColumn, moveLeft - 1);

        if (!memoization.containsKey(upKey)) {
            memoization.put(upKey, runDFS(m, n, moveLeft - 1, currentRow - 1, currentColumn, memoization));
        }

        currentNodeMoveCount = (currentNodeMoveCount + memoization.get(upKey)) % (long) 1000000007;
        //calculate for right
        final String rightKey = constructKey(currentRow, currentColumn + 1, moveLeft - 1);

        if (!memoization.containsKey(rightKey)) {
            memoization.put(rightKey, runDFS(m, n, moveLeft - 1, currentRow, currentColumn + 1, memoization));
        }

        currentNodeMoveCount = (currentNodeMoveCount + memoization.get(rightKey)) % (long) 1000000007;

        //calculate for down
        final String downKey = constructKey(currentRow + 1, currentColumn, moveLeft - 1);

        if (!memoization.containsKey(downKey)) {
            memoization.put(downKey, runDFS(m, n, moveLeft - 1, currentRow + 1, currentColumn, memoization));
        }

        currentNodeMoveCount = (long) (currentNodeMoveCount + memoization.get(downKey)) % (long) 1000000007;

        return currentNodeMoveCount;
    }

    public String constructKey(int currentRow, int currentColumn, int moveLeft) {
        return currentRow + "," + currentColumn + "," + moveLeft;
    }
}
