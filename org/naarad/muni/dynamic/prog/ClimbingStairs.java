package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/climbing-stairs/">[LC] Climbing Stairs</a>
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        final ClimbingStairs cs = new ClimbingStairs();
        System.out.println(cs.climbStairs(4));
    }

    public int climbStairs(int n) {
        int[] dp = new int[n+2];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            dp[i+1] += dp[i];
            dp[i+2] += dp[i];
        }
        return dp[n];
    }
}
