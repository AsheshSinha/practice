package com.prac.basics.tree.BST.AVL;

public class TNode {
    public TNode left;
    public TNode right;
    public int data;
    public int noOfChild;

    public TNode(int data,int noOfChild) {
        this.data = data;
        left = null;
        right = null;
        this.noOfChild = noOfChild;
    }
}