package org.naarad.muni.greedy;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/">buy-sell-stock</a>
 * <p>
 * TODO: Incomplete
 */
public class BuySellStock3 {
    public static void main(String[] args) {
        BuySellStock3 stock = new BuySellStock3();
        System.out.println(stock.maxProfit(new int[]{1000, 1001, 10, 100, 90, 1000}));
    }

    public int maxProfit(int[] prices) {
        int[] profit = new int[2];

        if (prices.length == 1) {
            return 0;
        }

        if (prices.length == 2) {
            return prices[0] < prices[1] ? prices[1] - prices[0] : 0;
        }

        int previousMin = 0;
        int i = 0;
        int j = 1;

        while (j < prices.length && i < prices.length - 1) {
            while (i + 1 < prices.length && prices[i] >= prices[i + 1]) {
                i++;
            }

            //previousMin = Math.min();
            j = i + 1;

            if (j < prices.length) {
                while (j + 1 < prices.length && prices[j] <= prices[j + 1]) {
                    j++;
                }
            }

            if (j < prices.length) {
                int localProfit = prices[j] - prices[i];
                if (localProfit > profit[0]) {
                    profit[1] = profit[0];
                    profit[0] = localProfit;
                } else if (localProfit > profit[1]) {
                    profit[1] = localProfit;
                }

                i = j + 1;
            }
        }

        return profit[0] + profit[1];
    }
}
