package com.my.experiment.demo05;

public class dd {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 1,2};
        int[] result = findBestTime(prices);
        System.out.println("Buy at day " + ++result[0] + ", sell at day " + ++result[1]);
    }

    public static int[] findBestTime(int[] prices) {
        int[] dp = new int[prices.length];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;
        int newBuyDay=0;
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i-1] + prices[i] - prices[i-1], 0);
            if (dp[i] > maxProfit) {
                maxProfit = dp[i];
                buyDay=newBuyDay;
                sellDay = i;
            }
            if (dp[i] == 0) {
                newBuyDay = i;
            }
        }

        return new int[]{buyDay, sellDay};
    }
}