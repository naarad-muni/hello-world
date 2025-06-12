package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/min-cost-climbing-stairs/">Minimum Cost Climbing Stairs</a>
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        final MinCostClimbingStairs stairs = new MinCostClimbingStairs();
        System.out.println(stairs.minCostClimbingStairs(new int[]{10, 15, 20}));
    }

    public int minCostClimbingStairs(int[] cost) {
        final int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
