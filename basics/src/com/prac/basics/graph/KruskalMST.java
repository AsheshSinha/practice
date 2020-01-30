package com.prac.basics.graph;

import java.util.Arrays;

class KEdge implements Comparable<KEdge> {
    int source;
    int dest;
    int weight;

    @Override
    public int compareTo(KEdge o) {
        return this.weight - o.weight;
    }
}

class KGraph {
    int V;
    int E;
    KEdge edge[];

    KGraph(int v, int e) {
        this.V = v;
        this.E = e;
        this.edge = new KEdge[E];
        for (int i = 0; i < e; i++) {
            edge[i] = new KEdge();
        }
    }

    void addEdge(int u, int v, int w, int index) {
        edge[index].source = u;
        edge[index].dest = v;
        edge[index].weight = w;
    }
}

class SubSet {
    int parent;
    int rank;

    SubSet(int p, int r) {
        this.parent = p;
        this.rank = r;
    }
}

class DisjointSetADT {
    static int find(SubSet[] subsets, int f) {
        if (subsets[f].parent != f) {
            subsets[f].parent = find(subsets, subsets[f].parent);
        }
        return subsets[f].parent;
    }

    static void union(SubSet[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }

    }
}

public class KruskalMST {
    public static void main(String[] args) {
        KGraph g = new KGraph(4, 5);
        g.addEdge(0, 1, 10, 0);
        g.addEdge(0, 2, 6, 1);
        g.addEdge(0, 3, 5, 2);
        g.addEdge(1, 3, 15, 3);
        g.addEdge(2, 3, 4, 4);
        findKruskalMST(g);
    }

    private static void findKruskalMST(KGraph g) {
        KEdge result[] = new KEdge[g.V];
        int e = 0;
        Arrays.sort(g.edge);
        SubSet[] subsets = new SubSet[g.V];
        for (int i = 0; i < g.V; i++) {
            subsets[i] = new SubSet(i, i);
        }

        for (int i = 0; i < g.edge.length; i++) {
            int xRoot = DisjointSetADT.find(subsets, g.edge[i].source);
            int yRoot = DisjointSetADT.find(subsets, g.edge[i].dest);

            if (xRoot != yRoot) {
                result[e++] = g.edge[i];
                DisjointSetADT.union(subsets, xRoot, yRoot);
            } else {
                //Contains cycle thus rejecting edge
            }
        }
        System.out.println("Following are the edges in " +
                "the constructed MST");
        for (int i = 0; i < e; ++i)
            System.out.println(result[i].source + " -- " +
                    result[i].dest + " == " + result[i].weight);
    }
}
