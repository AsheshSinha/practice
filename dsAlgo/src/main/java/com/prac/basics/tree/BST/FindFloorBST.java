package com.prac.basics.tree.BST;

public class FindFloorBST {
    public static void main(String[] args) {
        int a[] = {3, 5, 8, 10, 12, 13};
        TNode root = buildBST(a, 0, 5);
        System.out.println(findFloor(root, 11, 0));
        System.out.println(findCeiling(root, 11, 0));
    }

    private static int findFloor(TNode root, int value, int prev) {
        if (root == null) {
            return 0;
        } else {
            if (root.left != null) {
                prev = findFloor(root.left, value, prev);
            }
            //System.out.println(root.data);
            if (value >= root.data) {
                prev = root.data;
            }
            if (root.right != null) {
                prev = findFloor(root.right, value, prev);
            }
            return prev;
        }
    }

    private static int findCeiling(TNode root, int value, int prev) {
        if (root == null) {
            return 0;
        } else {
            if (root.right != null) {
                prev = findCeiling(root.right, value, prev);
            }
            //System.out.println(root.data);
            if (value <= root.data) {
                prev = root.data;
            }
            if (root.left != null) {
                prev = findCeiling(root.left, value, prev);
            }
            return prev;
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
