package com.prac.basics.search;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeating {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 2, 2, 3};
        System.out.println(findFirstRepeatingElem(data));
    }

    public static int findFirstRepeatingElem(int[] data) {
        int min = 0;
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = data.length - 1; i >= 0; i--) {
            if (hash.contains(data[i]))
                min = i;
            else
                hash.add(data[i]);
        }
        return data[min];
    }
}
