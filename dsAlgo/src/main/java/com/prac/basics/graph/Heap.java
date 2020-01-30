package com.prac.basics.graph;

public abstract class Heap {
    HeapNode arr[];
    int size;
    int capacity;
    // Map<Integer, Integer> map = new HashMap<>();
    int[] posInHeap;

    public int parentIndex(int i) {
        if (i <= 0 || i >= this.size)
            return -1;
        return (i - 1) / 2;
    }

    public int leftChildIndex(int i) {
        int left = 2 * i + 1;
        if (left >= this.size)
            return -1;
        return left;
    }

    public int rightChildIndex(int i) {
        int right = 2 * i + 2;
        if (right >= this.size)
            return -1;
        return right;
    }

    public HeapNode getTop() {
        if (this.size <= 0)
            return new HeapNode(-1, 0);
        return arr[0];
    }

    public abstract void percolateDown(int i);

    public HeapNode deleteTop() {
        if (this.size <= 0) {
            return new HeapNode(-1, 0);
        }
        HeapNode value = arr[0];
         arr[0]= arr[size - 1];
        posInHeap[arr[0].sourceIndex] = 0;
        this.size--;
        percolateDown(0);
        return value;
    }

    public HeapNode deleteAt(int i) {
        if (this.size <= 0) {
            return new HeapNode(-1, 0);
        }
        HeapNode value = arr[i];
        arr[i] = arr[size - 1];
        posInHeap[size - 1] = i;
        this.size--;
        percolateDown(i);
        return value;
    }

    public abstract void percolateUp(int i);

    public void insert(HeapNode elem) {
        if (this.size == this.capacity) {
            reSizeHeap();
        }
        this.size++;
        arr[this.size - 1] = elem;
        posInHeap[elem.sourceIndex] = this.size -1;
        percolateUp(this.size - 1);
    }

    public void reSizeHeap() {
        HeapNode[] tempArr = new HeapNode[this.size * 2];
        for (int i = 0; i < this.size; i++) {
            tempArr[i] = this.arr[i];
        }
        this.arr = tempArr;
        this.capacity = this.size * 2;
    }
}
