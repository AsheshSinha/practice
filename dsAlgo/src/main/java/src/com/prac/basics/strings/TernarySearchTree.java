package com.prac.basics.strings;

public class TernarySearchTree {
    char data;
    boolean isEndOfString;
    TernarySearchTree left;
    TernarySearchTree eq;
    TernarySearchTree right;

    public TernarySearchTree(char d, boolean endOfString, TernarySearchTree l, TernarySearchTree eq, TernarySearchTree r) {
        this.data = d;
        this.isEndOfString = endOfString;
        this.left = l;
        this.eq = eq;
        this.right = r;
    }
}
