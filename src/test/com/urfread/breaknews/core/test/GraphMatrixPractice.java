package com.urfread.breaknews.core.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrixPractice {
    private int[][]adjMatrix;
    private String[] nodeNames;
    private int nodeNum;
    private boolean[] visited;
    public GraphMatrixPractice(int maxNodeNum){
        adjMatrix=new int[maxNodeNum][maxNodeNum];
        nodeNum=0;
        nodeNames=new String[maxNodeNum];
        visited=new boolean[maxNodeNum];
    }
    public void addNode(String nodeName){
        if(nodeNum<nodeNames.length){
            nodeNames[nodeNum]=nodeName;
            nodeNum++;
        }else{
            System.out.println("Graph is full");
        }
    }
    public void addEdge(String node1,String node2){
        int index1=getNodeIndex(node1);
        int index2=getNodeIndex(node2);
        if(index1==-1) addNode(node1);
        if(index2==-1) addNode(node2);
        adjMatrix[index1][index2]=1;
        adjMatrix[index2][index1]=1;
    }
    private int getNodeIndex(String node) {
        for (int i = 0; i < nodeNum; i++) {
            if(nodeNames[i].equals(node)){
                return i;
            }
        }
        return -1;
    }
    private void printAdjMatrix() {
        System.out.print("  ");
        for(String node:nodeNames)System.out.print(node+" ");
        System.out.println();
        for(String node:nodeNames){
            System.out.print(node+" ");
            int row = getNodeIndex(node);
            for(int i:adjMatrix[row]) System.out.print(i+" ");
            System.out.println();
        }
    }
    private void dfs(String nodeName){
        Arrays.fill(visited,false);
        int startNodeIndex=getNodeIndex(nodeName);
        if(startNodeIndex==-1){
            System.out.println("Node not found");
            return;
        }
        dfsRecursively(startNodeIndex);
        System.out.println();
    }
    private void dfsRecursively(int startNodeIndex) {
        System.out.print(nodeNames[startNodeIndex]+"->");
        visited[startNodeIndex]=true;
        for(int i=0;i<nodeNum;i++){
            if(adjMatrix[startNodeIndex][i]==1&&!visited[i]){
                dfsRecursively(i);
            }
        }
    }
    private void bfs(String nodeName){
        Arrays.fill(visited,false);
        int startNodeIndex=getNodeIndex(nodeName);
        if(startNodeIndex==-1){
            System.out.println("Node not found");
            return;
        }
        Queue<Integer> queue=new LinkedList<>();
        queue.add(startNodeIndex);
        visited[startNodeIndex]=true;
        while(!queue.isEmpty()){
            int currentNodeIndex=queue.poll();
            System.out.print(nodeNames[currentNodeIndex]+"->");
            for(int i=0;i<nodeNum;i++){
                if(adjMatrix[currentNodeIndex][i]==1&&!visited[i]){
                    queue.add(i);
                    visited[i]=true;
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        GraphMatrixPractice graph=new GraphMatrixPractice(5);
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A","B");
        graph.addEdge("A","C");
        graph.addEdge("B","D");
        graph.addEdge("C","E");
        graph.addEdge("D","E");
        graph.printAdjMatrix();
        graph.bfs("A");
    }

}
