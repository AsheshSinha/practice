package com.prac.basics.graph;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//BFSSortedReach
public class Solution {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        AdjLstGraph g = new AdjLstGraph(n+1);
        int u, v;
        for (int i = 0; i < m; i++) {
            u = edges[i][0];
            v = edges[i][1];
            g.addEdge(u, v, 6);
        }
        boolean[] visited = new boolean[g.V];
        int[] weights = findWeightAtLevels(g, s, visited);
        for (int i = 0; i < g.V; i++) {
            if (!visited[i]) {
                weights[i] = -1;
            }
        }
        return weights;
    }

    static int[] findWeightAtLevels(AdjLstGraph g, int s, boolean visited[]) {
        int[] weight = new int[g.V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int counter = 0;
        queue.add(-1);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            if (u == -1) {
                counter++;
            } else {
                if (counter > 0) {
                    weight[u] = counter * 6;
                }
                GraphAdjLstNode node = g.array[u].head;
                while (node != null && !visited[node.vDest]) {
                    queue.add(node.vDest);
                    visited[node.vDest] = true;
                    node = node.next;
                }
                queue.add(-1);
            }
        }
        return weight;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            /*for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();*/
            System.out.println();
            for (int i = 0; i < result.length; i++) {
                System.out.print(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    System.out.println(" ");
                }
            }
        }

        //bufferedWriter.close();

        scanner.close();
    }
}

class GraphAdjLstNode {
    int vDest;
    int weight;
    GraphAdjLstNode next;

    public GraphAdjLstNode(int d, int w) {
        this.vDest = d;
        this.weight = w;
    }

    public static GraphAdjLstNode newAdjListNode(int vDest, int w) {
        return new GraphAdjLstNode(vDest, w);
    }
}

class AdjLst {
    GraphAdjLstNode head;
}

class AdjLstGraph {
    int V;
    int E;
    AdjLst[] array;

    AdjLstGraph(int v) {
        this.V = v;
        this.array = new AdjLst[v];
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = new AdjLst();
        }
    }

    public void addEdge(int u, int v, int w) {
        GraphAdjLstNode newListNode = GraphAdjLstNode.newAdjListNode(v, w);
        newListNode.next = this.array[u].head;
        this.array[u].head = newListNode;

       /* GraphAdjLstNode newListNodeRev = GraphAdjLstNode.newAdjListNode(u, w);
        newListNodeRev.next = this.array[v].head;
        this.array[v].head = newListNodeRev;*/
    }
}