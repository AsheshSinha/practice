package com.prac.basics.tree.BST;

public class BuildBSTFromSortedArray {
    public static void main(String[] args) {
        int a[] = {3, 5, 8, 10, 12, 13};
        TNode root = buildBST(a, 0, 5);
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

    private static TNode buildBST(int a[], int l, int r) {
        if (l > r) {
            return null;
        } else if (l == r) {
            return new TNode(a[l]);

        } else {
            int mid = l + (r - l) / 2;
            TNode bst = new TNode(a[mid]);
            bst.left = buildBST(a, l, mid - 1);
            bst.right = buildBST(a, mid + 1, r);
            return bst;
        }
    }
}
