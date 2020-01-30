package com.prac.basics.heap;

public abstract class Heap {
    int arr[];
    int count;
    int capacity;

    public int parentIndex(int i) {
        if (i <= 0 || i >= this.count)
            return -1;
        return (i - 1) / 2;
    }

    public int leftChildIndex(int i) {
        int left = 2 * i  + 1;
        if (left >= this.count)
            return -1;
        return left;
    }

    public int rightChildIndex(int i) {
        int right = 2 * i + 2;
        if (right >= this.count)
            return -1;
        return right;
    }

    public int getTop() {
        if (this.count <= 0)
            return -1;
        return arr[0];
    }

    public abstract void percolateDown(int i);

    public int deleteMax() {
        if (this.count <= 0) {
            return -1;
        }
        int value = arr[0];
        arr[0] = arr[count - 1];
        this.count--;
        percolateDown(0);
        return value;
    }

    public abstract void percolateUp(int i);

    public void insert(int elem) {
        if (this.count == this.capacity) {
            reSizeHeap();
        }
        this.count++;
        arr[this.count - 1] = elem;
        percolateUp(this.count - 1);
    }

    public void reSizeHeap() {
        int[] tempArr = new int[this.count * 2];
        for (int i = 0; i < this.count; i++) {
            tempArr[i] = this.arr[i];
        }
        this.arr = tempArr;
        this.capacity = this.count * 2;
    }
}
