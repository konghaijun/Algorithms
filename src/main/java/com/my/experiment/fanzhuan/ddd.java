package com.my.experiment.fanzhuan;

import java.util.Arrays;

public class ddd {
    public static void main(String[] args) {
        int[] distances = {1, 3, 5, 2, 6, 4, 8, 9, 10};
        int[] dp = new int[distances.length];
        int[] path = new int[distances.length];
        dp[0] = distances[0];
        path[0] = -1; // 使用-1表示起点
        for (int i = 1; i < distances.length; i++) {
            if (dp[i-1] + distances[i] < distances[i]) {
                dp[i] = dp[i-1] + distances[i];
                path[i] = i-1;
            } else {
                dp[i] = distances[i];
                path[i] = i;
            }
        }
        System.out.println("最短总距离为：" + dp[distances.length-1]);
        System.out.println("最短路线为：" + getShortestPath(path, distances.length-1));
    }

    private static String getShortestPath(int[] path, int endIndex) {
        StringBuilder sb = new StringBuilder();
        int index = endIndex;


        while (index >= 0) {
            sb.insert(0, index);
            if (path[index] >= 0) {
                sb.insert(0, " ");
            }
            index = path[index];
        }
        return sb.toString();
    }
}
