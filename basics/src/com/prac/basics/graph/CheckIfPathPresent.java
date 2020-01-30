package com.prac.basics.graph;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class CheckIfPathPresent {

    public static void main(String[] args) {
        AdjMatrixGraph graph = new AdjMatrixGraph(10, 10);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 4, 1);
        boolean[] visited = new boolean[graph.V];
        boolean hasSimplePath = checkIfSimplePathExists(graph, 1, 3, visited);
        System.out.println(hasSimplePath);
    }

    public static boolean checkIfSimplePathExists(AdjMatrixGraph g, int s, int d, boolean visited[]) {
        visited[s] = true;
        if (s == d)
            return true;
        else {
            for (int i = 0; i < g.edges[s].length; i++) {
                if (!visited[i] && g.edges[s][i] > 0)
                    return checkIfSimplePathExists(g, i, d, visited);
            }
        }
        return false;
    }
}
