package com.prac.basics.sort;

public class QuickSort {
    public static void main(String[] args) {
        //int a[] = {24, 8, 42, 75, 21, 77, 38, 57};
        int a[] = {6,4,8,1,5};
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivot = partition(a, left, right);
            quickSort(a, left, pivot-1);
            quickSort(a, pivot+ 1, right);
        }
    }

    private static void swap(int[] a, int s, int d) {
        int t = a[s];
        a[s] = a[d];
        a[d] = t;
    }

    private static int partition(int[] a, int left, int right) {
        int low = left;
        int pivotItem = a[low];
        while (left < right) {
            while (a[left] <= pivotItem)
                left++;
            while (a[right] > pivotItem)
                right--;
            if (left < right) {
                swap(a, left, right);
            }
        }
        a[low] = a[right];
        a[right] = pivotItem;
        return right;
    }
}
