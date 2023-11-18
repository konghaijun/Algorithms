package com.my.experiment.demo04;
import java.util.Scanner;
public class prim {
    private static final int MAXN = 1000;
    private static final int INF = 100;

    private static int n, m; // n: 顶点数, m: 边数
    private static int[][] graph = new int[MAXN][MAXN]; // 存储图的邻接矩阵
    private static int[] dist = new int[MAXN]; // 存储解集合中的点到各个点的最短距离
    private static boolean[] vis = new boolean[MAXN]; // 记录是否已经确定了最短路径

    private static void prim() {
        // 初始化
        for (int i = 1; i <= n; i++) {
            dist[i] = 1000;
            vis[i] = false;
        }
        dist[1] = 0; // 从顶点1开始生成最小生成树
        for (int i = 1; i < n; i++) {
            // 选择距离最小的顶点
            int u = -1;

            for (int j = 1; j <= n; j++) {
                if (!vis[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }
            vis[u] = true; // 标记选择的顶点为已访问


            // 更新与选择的顶点相邻的点的距离
            for (int v = 1; v <= n; v++) {
                if (graph[u][v] != 0 && !vis[v] && graph[u][v] < dist[v]) {
                    dist[v] = graph[u][v];
                }
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph[u][v] = w;
            graph[v][u] = w;
        }
        prim();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            }
            else {
                System.out.println(dist[i]);
            }
        }
    }
}
