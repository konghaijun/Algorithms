package com.my.experiment.demo04;

import java.util.*;


public class dd {
    private static final int INF = Integer.MAX_VALUE;
    private static int[][] graph;

    public static class Dijkstra {
        private int[] dist;
        private boolean[] vis;
        private PriorityQueue<int[]> pq;

        public Dijkstra(int[][] graph, int start) {
            int n = graph.length - 1; // 顶点数
            this.dist = new int[n + 1];
            this.vis = new boolean[n + 1];
            this.pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            for (int i = 1; i <= n; i++) {
                dist[i] = INF;
                vis[i] = false;
            }

            dist[start] = 0;
            pq.offer(new int[]{dist[start], start});

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int u = curr[1];

                if (vis[u]) {
                    continue;
                }

                vis[u] = true;

                for (int v = 1; v <= n; v++) { // 遍历与点u相邻的所有点及其权值
                    if (graph[u][v] != 0) {
                        int w = graph[u][v];

                        if (dist[v] > dist[u] + w) { // 更新dist[v]的值
                            dist[v] = dist[u] + w;
                            pq.offer(new int[]{dist[v], v});
                        }
                    }
                }
            }
        }

        public int getDistance(int dest) {
            return dist[dest];
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 顶点数
        int m = scanner.nextInt(); // 边数
        graph = new int[n + 1][n + 1]; // 邻接矩阵表示的图

        // 构建邻接矩阵
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph[u][v] = w;
            graph[v][u] = w; // 无向图，需要对称存储
        }

        int start = scanner.nextInt();
        Dijkstra dijkstra = new Dijkstra(graph, start);

        for (int i = 1; i <= n; i++) {
            if (dijkstra.getDistance(i) == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dijkstra.getDistance(i));
            }
        }
    }
}
