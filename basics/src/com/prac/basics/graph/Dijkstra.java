package com.prac.basics.graph;

public class Dijkstra {
    public static void main(String[] args) {
        AdjListGraph g = new AdjListGraph(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);
        dijkstra(g, 0);
    }

    private static void dijkstra(AdjListGraph g, int source) {
        MinHeap queue = new MinHeap(g.V);
        queue.insert(new HeapNode(source, 4));
        int[] distArr = new int[g.V];
        int[] path = new int[g.V];
        for (int i = 0; i < distArr.length; i++) {
            distArr[i] = -1;
        }
        distArr[source] = 0;
        while (queue.size > 0) {
            HeapNode node = queue.deleteTop();
            GraphAdjListNode graphNode = g.array[node.sourceIndex].head;
            while (graphNode != null) {
                int distance = distArr[node.sourceIndex] + graphNode.weight;
                if (distArr[graphNode.vDest] == -1) {
                    distArr[graphNode.vDest] = distance;
                    queue.insert(new HeapNode(graphNode.vDest, graphNode.weight));
                    path[graphNode.vDest] = node.sourceIndex;
                }
                if (distArr[graphNode.vDest] > distance) {
                    int posn = queue.posInHeap[graphNode.vDest];
                    HeapNode existingNode = queue.deleteAt(posn);
                    existingNode.value = distance;
                    queue.insert(existingNode);
                    path[graphNode.vDest] = node.sourceIndex;
                    distArr[graphNode.vDest] = distance;
                }
                graphNode = graphNode.next;
            }
        }
        System.out.println("Vertex  dist-from-source prev-vertex");
        for (int i = 0; i < distArr.length; i++) {
            System.out.print(i);
            System.out.print("          ");
            System.out.print(distArr[i]);
            System.out.print("                   ");
            System.out.println(path[i]);
        }

    }
}
