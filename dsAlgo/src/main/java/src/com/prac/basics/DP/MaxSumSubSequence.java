package com.prac.basics.DP;


import java.util.Arrays;
import java.util.Collections;

/**
 * Task is to find the subsequence for which the sum is maximum without selcting two consecutive numbers
 */
public class MaxSumSubSequence {
    public static void main(String[] args) {
        findMaxSum(new int[]{11, -2, -4, -5, 13, 2});
    }

    /**
     * Let s[i] represent the sum of ith position
     *
     * @param input
     */
    public static void findMaxSum(int[] input) {
        int[] s = new int[input.length];
        //if (input.length > 2){
        s[0] = input[0];
        s[1] = Math.max(input[0], input[1]);
        for (int i = 2; i < input.length; i++) {
            s[i] = Math.max(input[i] + s[i - 2], s[i - 1]);
        }
        int max = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] > max)
                max = s[i];
        }
        System.out.println(max);
        //}
    }
}
