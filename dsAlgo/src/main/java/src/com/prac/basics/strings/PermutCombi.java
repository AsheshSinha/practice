package com.prac.basics.strings;

import java.util.List;

public class PermutCombi {
    public static void main(String[] args) {
        String s = "abc";
        permutations(s.toCharArray(), 0);
    }

    public static void permutations(char[] s, int depth) {
        if (depth == s.length - 1)
            System.out.println(new String(s));
        else {
            for (int i = depth; i < s.length; i++) {
                swap(s, depth, i);
                permutations(s, depth + 1);
                swap(s, depth, i);
            }
        }
    }

    private static void swap(char[] c, int x, int y) {
        if (x != y) {
            char t = c[x];
            c[x] = c[y];
            c[y] = t;
        }
    }

    public static void combinations(String s, int depth, char[] combi, List<String> res, int start) {
        for (int i = start; i < s.length(); i++) {
            combi[depth] = s.charAt(i);
            res.add(new String(combi));
            combi[depth + 1] = '\0';
            if (i < s.length() - 1) {
                combinations(s, depth + 1, combi, res, start + 1);
            }
        }
    }
}
