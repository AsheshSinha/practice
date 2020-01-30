package com.prac.basics.graph;

public class ArticulationPoint {
    public static void main(String[] args) {
        System.out.println("Articulation points in first graph ");
        AdjListGraph g1 = new AdjListGraph(5);
        g1.addEdge(1, 0, 1);
        g1.addEdge(0, 2, 1);
        g1.addEdge(2, 1, 1);
        g1.addEdge(0, 3, 1);
        g1.addEdge(3, 4, 1);
        int[] parent = new int[g1.V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        findArticulationPoint(0, g1, new boolean[g1.V], new int[g1.V], new int[g1.V], parent);
        System.out.println();

        System.out.println("Articulation points in Second graph");
        AdjListGraph g2 = new AdjListGraph(4);
        g2.addEdge(0, 1, 1);
        g2.addEdge(1, 2, 1);
        g2.addEdge(2, 3, 1);
        parent = new int[g2.V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        findArticulationPoint(0, g2, new boolean[g2.V], new int[g2.V], new int[g2.V], parent);
        System.out.println();

        System.out.println("Articulation points in Third graph ");
        AdjListGraph g3 = new AdjListGraph(7);
        g3.addEdge(0, 1, 1);
        g3.addEdge(1, 2, 1);
        g3.addEdge(2, 0, 1);
        g3.addEdge(1, 3, 1);
        g3.addEdge(1, 4, 1);
        g3.addEdge(1, 6, 1);
        g3.addEdge(3, 5, 1);
        g3.addEdge(4, 5, 1);
        parent = new int[g3.V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        findArticulationPoint(0, g3, new boolean[g3.V], new int[g3.V], new int[g3.V], parent);
    }

    static int dfsOrder = 0;

    private static void findArticulationPoint(int u, AdjListGraph graph, boolean visited[], int[] dfsnum, int[] low,
                                              int[] parent) {
        visited[u] = true;
        dfsnum[u] = low[u] = ++dfsOrder;
        GraphAdjListNode node = graph.array[u].head;

        int children = 0;
        while (node != null) {
            int v = node.vDest;
            children++;
            if (!visited[v]) {
                parent[v] = u;
                findArticulationPoint(v, graph, visited, dfsnum, low, parent);

                if (parent[u] == -1 && children > 1) {
                    System.out.println("Cut Vertex found : " + u);
                }

                if (parent[u] > -1 && low[v] >= dfsnum[u]) {
                    System.out.println("Cut Vertex found : " + u);
                }
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], dfsnum[v]);
            }
            node = node.next;
        }
    }
}
