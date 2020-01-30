package com.prac.basics.graph;

class AdjList {
    GraphAdjListNode head;
}

public class AdjListGraph {
    int V;
    int E;
    AdjList[] array;

    AdjListGraph(int v) {
        this.V = v;
        this.array = new AdjList[v];
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = new AdjList();
        }
    }

    public void addEdge(int u, int v, int w) {
        GraphAdjListNode newListNode = GraphAdjListNode.newAdjListNode(v, w);
        newListNode.next = this.array[u].head;
        this.array[u].head = newListNode;

        GraphAdjListNode newListNodeRev = GraphAdjListNode.newAdjListNode(u, w);
        newListNodeRev.next = this.array[v].head;
        this.array[v].head = newListNodeRev;
    }
}
