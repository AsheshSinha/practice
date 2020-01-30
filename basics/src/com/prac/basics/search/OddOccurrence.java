package com.prac.basics.search;

public class OddOccurrence {
    public static int findOddOccurrence(int[] data) {
        int res = 0;
        for (int i = 0; i < data.length; i++) {
            res = res ^ data[i];
        }
        return res;
    }
}
