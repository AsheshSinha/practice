package com.prac.basics.graph;

import java.util.LinkedList;
import java.util.Queue;

public class DfsBfs {
    public static void main(String[] args) {
        AdjListGraph graph = createGraph();
        boolean visited[] = new boolean[graph.V];
        breadthFirstSearch(0, graph, visited);
        visited = new boolean[graph.V];
        System.out.println();
        depthFirstSearch(0, graph, visited);
    }

    private static void breadthFirstSearch(int u, AdjListGraph graph, boolean visited[]) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            System.out.print(curr);
            System.out.print(" ");
            visited[curr] = true;
            GraphAdjListNode node = graph.array[curr].head;
            while (node != null) {
                int v = node.vDest;
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                }
                node = node.next;
            }
        }
    }

    private static void depthFirstSearch(int u, AdjListGraph graph, boolean visited[]) {
            visited[u] = true;
            System.out.print(u);
            System.out.print(" ");
            GraphAdjListNode node = graph.array[u].head;
            while (node != null) {
                int v = node.vDest;
                if (!visited[v]) {
                    depthFirstSearch(v, graph, visited);
                }
                node = node.next;
            }
    }

    private static AdjListGraph createGraph() {
        AdjListGraph g = new AdjListGraph(10);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 5, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 4, 1);
        g.addEdge(2, 9, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(9, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(3, 8, 1);
        g.addEdge(4, 6, 1);
        g.addEdge(6, 8, 1);
        g.addEdge(6, 7, 1);
        g.addEdge(7, 5, 1);
        return g;

    }
}
