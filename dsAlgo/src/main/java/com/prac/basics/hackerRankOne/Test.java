package com.prac.basics.bnyM;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        //System.out.println(getTotal(6,2,2));

        //System.out.println(minNum(4, 5, 1));
        System.out.println(isValidString("abcbe", 3));
    }

    private static int getTotal(int amount, int coc, int residue) {
        int initc = amount / coc;
        return count(initc, residue);
    }

    private static int count(int initC, int residue) {
        if (initC < residue)
            return initC;
        int rem = initC - residue;
        return residue + count(rem + 1, residue);
    }

    private static int minNum(int aPerday, int kPerday, int aAhead) {
        if (aPerday >= kPerday)
            return -1;
        int diff = kPerday - aPerday;
        return countDays(diff, aAhead);

    }

    private static int countDays(int diff, int ahead) {
        if (diff > ahead) {
            return 1;
        }
        return 1 + countDays(diff, ahead - diff);
    }

    private static boolean isValidString(String s, int maxUnique) {
        char[] cArr = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < cArr.length; i++) {
            set.add(cArr[i]);
        }
        if (set.size() > maxUnique)
            return false;
        else
            return true;
    }


}
