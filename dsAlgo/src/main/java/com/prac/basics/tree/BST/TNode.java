package com.prac.basics.tree.BST;

public class TNode {
    public TNode left;
    public TNode right;
    public int data;

    public TNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}