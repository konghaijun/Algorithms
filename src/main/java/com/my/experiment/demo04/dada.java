package com.my.experiment.demo04;

import java.util.Arrays;
import java.util.Scanner;

public class dada {
    private static final int MAXN = 1000;
    private static final int INF = 100;

    private static int n, m;

    private static int[][] graph = new int[MAXN][MAXN];
    private static int[] dist = new int[MAXN];
    private static boolean[] vis = new boolean[MAXN];

    private static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        Arrays.fill(vis, false);

        dist[start] = 0;
        vis[start] = true;

        for (int v = 1; v <= n; v++) {
            if (!vis[v]) {
                dist[v] = Math.min(dist[v], dist[start] + graph[start][v]);
            }
        }

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

            for (int j = 1; j <= n; j++) {
                if (!vis[j] && dist[j] < minDist) {
                    u = j;
                    minDist = dist[j];
                }
            }

            vis[u] = true;

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
