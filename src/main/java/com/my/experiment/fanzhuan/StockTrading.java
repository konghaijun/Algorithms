package com.my.experiment.fanzhuan;

public class StockTrading {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] result = findBestTime(prices);
        System.out.println("Buy at day " + ++result[0] + ", sell at day " + ++result[1]);
    }

    public static int[] findBestTime(int[] prices) {
        // 创建一个与股票价格数组相同长度的一维数组，用于存储截止到每一天的最大收益
        int[] dp = new int[prices.length];
        // 初始化最大收益、最佳买入时机和卖出时机的天数
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        // 遍历股票价格数组，计算并更新dp数组的值
        for (int i = 1; i < prices.length; i++) {
            // dp[i]可以被分为两种情况：
            // 1. 在第i天卖出所能获得的最大收益：dp[i-1] + prices[i] - prices[i-1]
            // 2. 第i天不交易，即保持前一天的最大收益：0
            dp[i] = Math.max(dp[i-1] + prices[i] - prices[i-1], 0);

            // 如果当前的dp[i]大于之前记录的最大收益，则更新最大收益和卖出时机的天数
            if (dp[i] > maxProfit) {
                maxProfit = dp[i];
                sellDay = i;
            }

            // 如果dp[i]等于0，表示新的买入时机出现，将购买股票的天数buyDay更新为当前天数i
            if (dp[i] == 0) {
                buyDay = i;
            }
        }

        // 返回一个长度为2的数组，第一个元素为buyDay，第二个元素为sellDay
        return new int[]{buyDay, sellDay};
    }
}
