package com.urfread.breaknews.core.test;

public class SevenSegmentDisplay {
    static final int N = 7; // 段的数量
    static int ans = 0;
    static int[] parent = new int[N + 1];
    static boolean[] used = new boolean[N + 1];
    static int[][] graph = new int[N + 1][N + 1];

    public static void main(String[] args) {
        // 初始化邻接矩阵
        graph[1][2] = graph[1][6] = 1;
        graph[2][1] = graph[2][3] = graph[2][7] = 1;
        graph[3][2] = graph[3][4] = graph[3][7] = 1;
        graph[4][3] = graph[4][5] = 1;
        graph[5][4] = graph[5][6] = graph[5][7] = 1;
        graph[6][1] = graph[6][5] = graph[6][7] = 1;
        graph[7][2] = graph[7][3] = graph[7][5] = graph[7][6] = 1;

        dfs(1);
        System.out.println(ans);
    }

    // 查找并查集
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 深度优先搜索
    static void dfs(int u) {
        if (u == N + 1) {
            // 初始化并查集
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            // 建立连通性
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] == 1 && used[i] && used[j]) {
                        parent[find(i)] = find(j);
                    }
                }
            }

            // 计算根节点数量
            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (used[i] && parent[i] == i) {
                    count++;
                }
            }

            if (count == 1) {
                ans++; // 如果所有亮灯都属于同一个集合
            }
            return;
        }

        // 打开当前段
        used[u] = true;
        dfs(u + 1);

        // 关闭当前段
        used[u] = false;
        dfs(u + 1);
    }
}
