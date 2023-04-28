/**
 * <a href="https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/">problem statement link</a>
 * <br />
 * 1 <= n <= 2 * 10<sup>9</sup>
 */
public class MinimumDayToEatOranges {
    public static void main(String[] args) {
        MinimumDayToEatOranges a = new MinimumDayToEatOranges();
        System.out.println(a.minDays(4));
    }

    public int minDays(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2 || n == 3) {
            return 2;
        }
        int[] oranges = new int[n + 1];

        oranges[0] = 0;
        oranges[1] = 1;
        oranges[2] = 2;
        oranges[3] = 2;

        for (int i = 4; i <= n; i++) {
            int minCost = 1 + oranges[i - 1];

            if (i % 2 == 0) {
                minCost = Math.min(minCost, 1 + oranges[i - (i / 2)]);
            }

            if (i % 3 == 0) {
                minCost = Math.min(minCost, 1 + oranges[i - (2 * (i / 3))]);
            }

            oranges[i] = minCost;
        }

        return oranges[n];
    }
}
