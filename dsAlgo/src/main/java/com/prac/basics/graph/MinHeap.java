package com.prac.basics.graph;

class MinHeap extends Heap {
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.arr = new HeapNode[capacity];
        this.posInHeap = new int[capacity];
        /*for (int i = 0; i < arr.length; i++) {
            arr[i] = new HeapNode(i, Integer.MAX_VALUE);
        }*/
    }

    @Override
    public void percolateDown(int i) {
        int min = i;
        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        if (left != -1 && arr[min].value > arr[left].value) {
            min = left;
        }
        if (right != -1 && arr[min].value > arr[right].value) {
            min = right;
        }
        if (min != i) {
            HeapNode temp = arr[i];
            arr[i] = arr[min];
            posInHeap[arr[min].sourceIndex] = i;
            arr[min] = temp;
            posInHeap[temp.sourceIndex] = min;
            percolateDown(min);
        }
    }

    @Override
    public void percolateUp(int i) {
        int min = i;
        int parentIndex = parentIndex(i);
        if (parentIndex != -1 && arr[min].value < arr[parentIndex].value) {
            HeapNode temp = arr[parentIndex];
            arr[parentIndex] = arr[min];
            posInHeap[arr[min].sourceIndex] = parentIndex;
            arr[min] = temp;
            posInHeap[temp.sourceIndex] = min;
            percolateUp(parentIndex);
        }
    }
}

