package org.naarad.muni.dynamic.prog;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/minimum-cost-to-split-an-array/">problem statement link</a>
 * <p>
 * 1 <= nums.length <= 1000 <br>
 * 0 <= nums[i] < nums.length <br>
 * 1 <= k <= 10<sup>9</sup> <br>
 */
public class MinCostToSplitArray {
    public static void main(String[] args) {
        MinCostToSplitArray a = new MinCostToSplitArray();
        System.out.println(a.minCost(new int[]{1}, 3));
    }

    public int minCost(int[] nums, int k) {
        if (nums.length <= k) {
            return k;
        }
        int[] dp = new int[nums.length];
        Map<Integer, Integer> numberCount = new HashMap<>();

        dp[0] = k;
        numberCount.put(nums[0], 1);
        for (int i = 1; i < nums.length; i++) {
            if (numberCount.containsKey(nums[i])) {

            } else {
                dp[i] = dp[i - 1];
            }
        }
        return 14;
    }

    public int minCostOfSubarray(int[] numbers, int j, int k, final int dp[]) {
        int minCost = k;
        final Map<Integer, Integer> numberCount = new HashMap<>();

        for (int i = 1; i <= j; i++) {

        }
        return 0;
    }
}
