package com.prac.basics.sort;

public class CountingSort {
    public static void main(String[] args) {
        int[] input = {10, 70, 120, 200, 11, 34, 56, 22, 15, 19,10 ,10 ,1};
        input = sort(input,201);
        System.out.println();
        for (int i = 0; i < input.length; i++) {
            System.out.print(" " + input[i]);
        }
    }

    public static int[] sort(int[] input, int k) {
        int[] countingArr = new int[k];
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            countingArr[input[i]] = countingArr[input[i]] + 1;
        }
        int j =0;
        for (int i = 0; i < countingArr.length; i++) {
            while(countingArr[i] > 0){
                result[j] = i;
                countingArr[i] = countingArr[i] - 1;
                j = j + 1;
            }
        }
        return result;
    }
}
