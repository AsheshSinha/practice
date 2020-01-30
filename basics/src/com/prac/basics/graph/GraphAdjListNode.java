package com.prac.basics.graph;

public class GraphAdjListNode {
    int vDest;
    int weight;
    GraphAdjListNode next;

    public GraphAdjListNode(int d, int w) {
        this.vDest = d;
        this.weight = w;
    }

    public static GraphAdjListNode newAdjListNode(int vDest, int w){
        return new GraphAdjListNode(vDest,w);
    }
}
