package com.prac.basics.search;

public class SearchExecutor {
    public static void main(String[] args) {
        int[] data = {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
        System.out.println(FirstRepeating.findFirstRepeatingElem(data));
        System.out.println(OddOccurrence.findOddOccurrence(data));
    }
}
