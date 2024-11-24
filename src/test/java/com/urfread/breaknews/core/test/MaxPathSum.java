package com.urfread.breaknews.core.test;

import java.util.Scanner;

public class MaxPathSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取三角形的行数
        int N = scanner.nextInt();
        int[][] triangle = new int[N + 1][N + 1]; // 创建一个 N+1 x N+1 的矩阵

        // 读取三角形的数字
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = scanner.nextInt();
            }
        }

        // 使用两个一维数组来存储上一行和当前行的最大路径和
        int[] prev = new int[N + 1];
        int[] curr = new int[N + 1];

        // 初始化第一行
        prev[1] = triangle[1][1]; // 起始点

        // 从第二行开始计算
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                // 处理左右走的次数差为 0
                if (j <= i - 1) {
                    curr[j] = Math.max(curr[j], prev[j] + triangle[i][j]);
                }
                if (j > 1) {
                    curr[j] = Math.max(curr[j], prev[j - 1] + triangle[i][j]);
                }
            }
            // 将当前行的结果复制到上一行
            System.arraycopy(curr, 0, prev, 0, N + 1);
            // 清空当前行
            for (int j = 1; j <= i; j++) {
                curr[j] = 0;
            }
        }

        // 找到最后一行的最大值
        int maxSum = 0;
        for (int j = 1; j <= N; j++) {
            maxSum = Math.max(maxSum, prev[j]);
        }

        // 输出结果
        System.out.println(maxSum);

        scanner.close();
    }
}
