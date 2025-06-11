package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">[LC] Best Time To Sell Stock</a>
 */
public class BestTimeToSellStock {
    public static void main(String[] args) {
        final BestTimeToSellStock stock = new BestTimeToSellStock();
        System.out.println(stock.maxProfit(new int[]{7,1,5,3,6,4}));
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;
        int smallestNumberSoFar = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - smallestNumberSoFar;
            if (profit > 0) {
                maxProfit = Math.max(maxProfit, profit);
            }
            smallestNumberSoFar = Math.min(smallestNumberSoFar, prices[i]);
        }

        return maxProfit;
    }
}
