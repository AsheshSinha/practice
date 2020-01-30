package com.prac.basics.tree.BST;

public class BuildBSTFromSortedLinkList {
    private static int a[] = {10, 12, 15, 16, 20, 49, 67};
    private static LNode linkedList = new LNode(a[0]);

    public static void main(String[] args) {
        for (int i = 1; i < a.length; i++) {
            linkedList.next = new LNode(a[i]);
        }
        TNode root = buildBST(7);
        printBST(root);
    }

    private static void printBST(TNode root) {
        if (root == null) {
            return;
        } else {
            if (root.left != null) {
                printBST(root.left);
            }
            System.out.println(root.data);
            if (root.right != null) {
                printBST(root.right);
            }
            return;
        }
    }

    private static TNode buildBST(int size) {
        if (size <=0)
            return null;
        //int mid = start + (end - start) / 2;
        if (null == linkedList)
            return null;
        TNode left = buildBST(size/2);
        TNode root = new TNode(linkedList.data);
        root.left = left;
        linkedList = linkedList.next;
        root.right = buildBST(size - size/2 - 1);
        return root;
    }
}

