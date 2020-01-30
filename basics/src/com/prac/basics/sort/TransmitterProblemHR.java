package com.prac.basics.sort;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TransmitterProblemHR {

    // Complete the hackerlandRadioTransmitters function below.

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

    static int hackerlandRadioTransmitters(int[] x, int k) {
        sort(x);
        int count = 0;
        int j = 1;
        int n = 0;
        for (int i = 0; i < x.length; i++) {
            if (i + 1 < x.length) {
                if (j > k) {
                    count = count + 1;
                    j = -(k - 1);
                }
                if (x[i+ 1] - x[i] > k){
                    j = x[i + 1] - x[i];
                }else
                    j = j + x[i + 1] - x[i];
            } else {
                if (x[i] - x[i - 1] > k) {
                    count = count + 1;
                } else if (j >= k) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
        }

        int result = hackerlandRadioTransmitters(x, k);
        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.close();*/
        System.out.println(result);
        scanner.close();
    }
}
