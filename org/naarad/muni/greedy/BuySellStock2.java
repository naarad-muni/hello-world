package org.naarad.muni.greedy;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">best time to buy sell stock</a>
 */
public class BuySellStock2 {
    public static void main(String[] args) {
        BuySellStock2 stock = new BuySellStock2();
        System.out.println(stock.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    public int maxProfitDynamicProgramming(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        if (prices.length == 2) {
            return prices[0] < prices[1] ? prices[1] - prices[0] : 0;
        }

        int[] dp = new int[prices.length];

        for (int i = prices.length - 2; i >= 0; i--) {
            int localMaxProfit = 0;
            if (prices[i] < prices[i + 1]) {
                for (int j = i + 1; j < prices.length; j++) {
                    if (prices[i] < prices[j]) {
                        int dpValueOfJNext = j + 1 < prices.length ? dp[j + 1] : 0;
                        localMaxProfit = Math.max(localMaxProfit, prices[j] - prices[i] + dpValueOfJNext);
                    }
                }
                dp[i] = Math.max(localMaxProfit, dp[i + 1]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        return dp[0];
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        if (prices.length == 2) {
            return prices[0] < prices[1] ? prices[1] - prices[0] : 0;
        }

        int maxProfit = 0;
        int i = 0;
        int j = 1;

        while (j < prices.length && i < prices.length - 1) {
            while (i + 1 < prices.length && prices[i] >= prices[i + 1]) {
                i++;
            }

            j = i + 1;

            if (j < prices.length) {
                while (j + 1 < prices.length && prices[j] <= prices[j + 1]) {
                    j++;
                }
            }

            if (j < prices.length) {
                maxProfit += prices[j] - prices[i];
                i = j + 1;
            }
        }

        return maxProfit;
    }
}
