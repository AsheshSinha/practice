package com.prac.basics.heap;

public class FindInHeap {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(20);
        int arr[] = {20, 10, 30, 50, 35, 45, 90, 1, 56, 3};
        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }
        int arr2[] = heap.arr;
        int a = heap.deleteMax();
        int arr3[] = heap.arr;
        printLesserThanK(30, heap, 0);
    }

    private static void printLesserThanK(int k, Heap heap, int root) {
        if (root < 0)
            return;
        if (heap.arr[root] < k) {
            System.out.println(heap.arr[root]);
        }
        int left = heap.leftChildIndex(root);
        printLesserThanK(k, heap, left);
        int right = heap.rightChildIndex(root);
        printLesserThanK(k, heap, right);
    }
}
