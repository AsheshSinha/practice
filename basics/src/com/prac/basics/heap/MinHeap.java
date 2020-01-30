package com.prac.basics.heap;

public class MinHeap extends Heap {
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.arr = new int[capacity];
    }

    @Override
    public void percolateDown(int i) {
        int min = i;
        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        if (left != -1 && arr[min] > arr[left]) {
            min = left;
        }
        if (right != -1 && arr[min] > arr[right]) {
            min = right;
        }
        if (min != i) {
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            percolateDown(min);
        }
    }

    @Override
    public void percolateUp(int i) {
        int min = i;
        int parentIndex = parentIndex(i);
        if (parentIndex != -1 && arr[min] < arr[parentIndex]) {
            int temp = arr[parentIndex];
            arr[parentIndex] = arr[min];
            arr[min] = temp;
            percolateUp(parentIndex);
        }
    }
}
