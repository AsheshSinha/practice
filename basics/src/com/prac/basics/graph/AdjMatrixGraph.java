package com.prac.basics.graph;

public class AdjMatrixGraph {
    int E;
    int V;
    int[][] edges;

    AdjMatrixGraph(int e, int v) {
        this.E = e;
        this.V = v;
        this.edges = new int[V][V];
    }

    public void addEdge(int source, int dest, int weight) {
        this.edges[source][dest] = weight;
        this.edges[dest][source] = weight;
    }
}
