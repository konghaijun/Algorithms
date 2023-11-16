package com.my.experiment.demo04;


import java.util.Arrays;
import java.util.Scanner;

public class rr {
    private static final int MAXN = 1000;  // 最大顶点数
    private static final int INF = 100;    // 表示无穷大的距离

    private static int n, m;  // 顶点数和边数

    private static int[][] graph = new int[MAXN][MAXN];  // 图的邻接矩阵
    private static int[] dist = new int[MAXN];  // 起始顶点到各顶点的最短距离
    private static boolean[] vis = new boolean[MAXN];  // 标记顶点是否已访问

    // Dijkstra算法，计算从start出发的最短路径
    private static void dijkstra(int start) {
        Arrays.fill(dist, INF);  // 初始化距离数组为无穷大
        Arrays.fill(vis, false);  // 初始化访问标记数组为false

        dist[start] = 0;  // 起始顶点到自身的距离为0
        vis[start] = true;  // 标记起始顶点已访问

        for (int v = 1; v <= n; v++) {
            if (!vis[v]) {
                dist[v] = Math.min(dist[v], dist[start] + graph[start][v]);  // 更新起始顶点到其他顶点的距离
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");  // 输出起始顶点到各顶点的最短距离
            } else {
                System.out.println(dist[i]);
            }
        }

        System.out.println("下面是解");

        for (int i = 1; i < n; i++) {
            int minDist = INF;
            int u = -1;

            for (int j = 1; j <= n; j++) {
                if (!vis[j] && dist[j] < minDist) {
                    u = j;
                    minDist = dist[j];
                }
            }

            vis[u] = true;  // 标记顶点u已访问

            for (int v = 1; v <= n; v++) {
                if (!vis[v]) {
                    dist[v] = Math.min(dist[v], dist[u] + graph[u][v]);  // 更新起始顶点到其他未访问顶点的最短距离
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = 6;  // 顶点数
        m = 10;  // 边数

        graph = new int[MAXN][MAXN];  // 初始化图的邻接矩阵
        dist = new int[MAXN];  // 初始化最短距离数组
        vis = new boolean[MAXN];  // 初始化访问标记数组

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);  // 初始化邻接矩阵，将所有边的权值设为无穷大
        }

        // 初始化图的边权值
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

        int start = scanner.nextInt();  // 输入起始顶点

        dijkstra(start);  // 使用Dijkstra算法计算最短路径

        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");  // 输出起始顶点到各顶点的最短距离
            } else {
                System.out.println(dist[i]);
            }
        }

        scanner.close();
    }

}
