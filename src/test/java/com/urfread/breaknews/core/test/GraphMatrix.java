package com.urfread.breaknews.core.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix {
    private int[][] adjMatrix; // 用于存储邻接矩阵
    private int numNodes;      // 当前图中的节点数量
    private String[] nodes;    // 存储节点名称
    private boolean[] visited; // 记录节点是否被访问过

    // 构造函数，指定最大节点数量
    public GraphMatrix(int maxNodes) {
        adjMatrix = new int[maxNodes][maxNodes]; // 初始化矩阵，默认全为 0
        nodes = new String[maxNodes];            // 存储节点名称
        numNodes = 0;                            // 当前节点数量从 0 开始
        visited = new boolean[maxNodes];
    }

    // 添加节点
    public void addNode(String node) {
        if (numNodes < nodes.length) {
            nodes[numNodes++] = node; // 将节点添加到节点列表中
        } else {
            System.out.println("无法添加更多节点，达到最大节点数量限制。");
        }
    }

    // 添加边 (有向图)
    public void addEdge(String node1, String node2, int weight) {
        int index1 = getNodeIndex(node1);
        int index2 = getNodeIndex(node2);

        if (index1 != -1 && index2 != -1) {
            adjMatrix[index1][index2] = weight; // 设置从 node1 到 node2 的边的权重
        } else {
            System.out.println("其中一个节点不存在。");
        }
    }


    // 查找节点的索引
    private int getNodeIndex(String node) {
        for (int i = 0; i < numNodes; i++) {
            if (nodes[i].equals(node)) {
                return i;
            }
        }
        return -1; // 如果未找到节点，返回 -1
    }


    // 打印邻接矩阵
    public void printAdjMatrix() {
        System.out.println("邻接矩阵：");
        System.out.print("  ");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(nodes[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < numNodes; i++) {
            System.out.print(nodes[i] + " ");
            for (int j = 0; j < numNodes; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void dijkstra(String startNode) {
        int startIndex = getNodeIndex(startNode);
        if (startIndex == -1) {
            System.out.println("起始节点不存在。");
            return;
        }

        int[] distances = new int[numNodes]; // 存储到各节点的最短距离
        boolean[] visited = new boolean[numNodes]; // 标记节点是否已确定最短路径
        Arrays.fill(distances, Integer.MAX_VALUE); // 初始化距离为无穷大
        distances[startIndex] = 0; // 起始节点的距离为 0

        for (int i = 0; i < numNodes - 1; i++) {
            int nearestNode = -1;
            int shortestDistance = Integer.MAX_VALUE;

            // 找到未访问节点中距离最小的节点
            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && distances[j] < shortestDistance) {
                    nearestNode = j;
                    shortestDistance = distances[j];
                }
            }

            if (nearestNode == -1) break; // 无法再找到更近的节点，结束算法
            visited[nearestNode] = true; // 将该节点标记为已访问

            // 更新邻居的距离
            for (int j = 0; j < numNodes; j++) {
                if (adjMatrix[nearestNode][j] > 0 && !visited[j] && distances[nearestNode] + adjMatrix[nearestNode][j] < distances[j]) {
                    distances[j] = distances[nearestNode] + adjMatrix[nearestNode][j];
                }
            }
        }

        // 输出起始节点到所有其他节点的最短路径
        System.out.println("Dijkstra 算法的最短路径结果：");
        for (int i = 0; i < numNodes; i++) {
            System.out.println(startNode + " 到 " + nodes[i] + " 的最短距离是: " + (distances[i] == Integer.MAX_VALUE ? "不可达" : distances[i]));
        }
    }


    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix(5);

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B", 4); // A-B 之间添加边，权重为 4
        graph.addEdge("A", "C", 2); // A-C 之间添加边，权重为 2
        graph.addEdge("B", "C", 5); // B-C 之间添加边，权重为 5
        graph.addEdge("B", "D", 10); // B-D 之间添加边，权重为 10
        graph.addEdge("C", "E", 3); // C-E 之间添加边，权重为 3
        graph.addEdge("D", "E", 1); // D-E 之间添加边，权重为 1

        graph.printAdjMatrix(); // 输出邻接矩阵
        graph.dijkstra("A"); // 调用 Dijkstra 算法，从节点 A 开始
    }
}
