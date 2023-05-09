package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">Problem statement link</a>
 * Runtime Complexity = O(n)
 * Spacetime complexity = O(1)
 */
public class StockProblem {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 2, 5, 3, 6, 4, 1, 4}));
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0, j = 1; j < prices.length; j++) {
            if (prices[i] > prices[j]) {
                i = j;
            } else if (prices[i] < prices[j]) {
                maxProfit = Math.max(prices[j] - prices[i], maxProfit);
            }
        }

        return maxProfit;
    }
}
