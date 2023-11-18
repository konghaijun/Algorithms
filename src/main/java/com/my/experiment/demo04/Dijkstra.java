package com.my.experiment.demo04;

import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {
    private static final int MAXN = 1000;  // 最大顶点数
    private static final int INF = 100;    // 表示无穷大的距离

    private static int n, m;  // 顶点数和边数

    private static int[][] graph = new int[MAXN][MAXN];  // 图的邻接矩阵
    private static int[] dist = new int[MAXN];  // 起始顶点到各顶点的最短距离
    private static boolean[] vis = new boolean[MAXN];  // 标记顶点是否已访问

    private static void dijkstra(int start) {

        //初始化 标记起点
        Arrays.fill(dist, INF);
        Arrays.fill(vis, false);

        dist[start] = 0;
        vis[start] = true;

        //  更新起始顶点到其他顶点的距离
        for (int v = 1; v <= n; v++) {
            if (!vis[v]) {
                dist[v] = Math.min(dist[v], dist[start] + graph[start][v]);
            }
        }

        //输出与起点的距离
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

        System.out.println("下面是解");


        for (int i = 1; i < n; i++) {
            int minDist = INF;
            int u = -1;
             //在没被标记的点里面找出值最小的那个
            for (int j = 1; j <= n; j++) {
                if (!vis[j] && dist[j] < minDist) {
                    u = j;
                    minDist = dist[j];
                }
            }

            //把那个最小点标记
            vis[u] = true;

            //更新起始顶点到其他未访问顶点的最短距离
            for (int v = 1; v <= n; v++) {
                if (!vis[v]) {
                    dist[v] = Math.min(dist[v], dist[u] + graph[u][v]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = 6;
        m = 10;

        graph = new int[MAXN][MAXN];
        dist = new int[MAXN];
        vis = new boolean[MAXN];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        graph[1][2] = 10;
        graph[1][6] = 3;
        graph[2][3] = 7;
        graph[2][4] = 5;
        graph[4][1] = 3;
        graph[4][3] = 4;
        graph[4][5] = 7;
        graph[6][2] = 2;
        graph[6][4] = 6;
        graph[6][5] = 1;

        int start = scanner.nextInt();

        dijkstra(start);
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

        scanner.close();
    }
}
