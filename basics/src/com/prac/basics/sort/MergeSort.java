package com.prac.basics.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] input = {10, 70, 120, 200, 11, 34, 56, 22, 15, 19};
        sort(input);
        System.out.println();
        for (int i = 0; i < input.length; i++) {
            System.out.print(" " + input[i]);
        }
    }

    public static void sort(int[] input) {
        int[] temp = new int[input.length];
        mergeSort(input, temp, 0, input.length - 1);
    }

    private static void mergeSort(int[] input, int[] temp, int left, int right) {
        if (right > left) {
            int mid = (right + left) / 2;
            mergeSort(input, temp, left, mid);
            mergeSort(input, temp, mid + 1, right);
            merge(input, temp, left, right, mid + 1);
        }
    }

    private static void merge(int[] input, int[] temp, int left, int right, int mid) {
        int tempPos = left;
        int leftEnd = mid - 1;
        int size = right - left + 1;
        while (left <= leftEnd && mid <= right) {
            if (input[left] <= input[mid]) {
                temp[tempPos] = input[left];
                left = left + 1;
                tempPos = tempPos + 1;
            } else {
                temp[tempPos] = input[mid];
                tempPos = tempPos + 1;
                mid = mid + 1;
            }
        }

        while (left <= leftEnd) {
            temp[tempPos] = input[left];
            left = left + 1;
            tempPos = tempPos + 1;
        }

        while (mid <= right) {
            temp[tempPos] = input[mid];
            mid = mid + 1;
            tempPos = tempPos + 1;
        }
        for (int i = 0; i < size; i++) {
            input[right] = temp[right];
            right = right - 1;
        }
    }
}
