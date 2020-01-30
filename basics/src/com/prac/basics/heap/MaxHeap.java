package com.prac.basics.heap;

public class MaxHeap extends Heap {
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.arr = new int[capacity];
    }

    @Override
    public void percolateDown(int i) {
        int max = i;
        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        if (left != -1 && arr[max] < arr[left]) {
            max = left;
        }
        if (right != -1 && arr[max] < arr[right]) {
            max = right;
        }

        if (max != i) {
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            percolateDown(max);
        }
    }

    @Override
    public void percolateUp(int i) {
        int max = i;
        int parent = parentIndex(i);
        if (parent < 0)
            return;
        if (arr[max] > arr[parent]) {
            int temp = arr[parent];
            arr[parent] = arr[max];
            arr[max] = temp;
            percolateUp(parent);
        }
    }
}
