package com.prac.basics.strings;

public class RemovingAdjacentChars {
    public static void main(String[] args) {
        String s = "ABCCBCBA";
        char[] res = new char[s.length()];
        //removeAdjacent(s, 0, s.length() - 1, res, 'z');
        System.out.println(new String(res));

    }
}
