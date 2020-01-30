package com.prac.basics.tree.BST;

import java.util.*;


class LCA {

    public static boolean checkBST(TNode root) {
        if (isBST(root, null)) {
            System.out.println("Yes");
            return true;
        } else {
            System.out.println("No");
            return false;
        }

    }

    public static TNode lcaRev(TNode root, int v1, int v2) {
        if (root == null) {
            return null;
        }
        if (v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        if (v1 < root.data && v2 > root.data) {
            return root;
        }
        if (v1 < root.data && v2 < root.data) {
            lcaRev(root.left, v1, v2);
        }
        if (v1 > root.data && v2 > root.data) {
            lcaRev(root.right, v1, v2);
        }
        return root;
    }

    public static boolean isBST(TNode root, TNode prev) {
        boolean tester = true;
        if (root == null) {
            return tester;
        } else {
            if (root.left != null) {
                tester = tester && isBST(root.left, prev);
            }
            if (prev != null && prev.data > root.data)
                return false;
            prev = root;
            if (root.right != null) {
                tester = tester && isBST(root.right, prev);
            }
            return tester;
        }
    }

    public static TNode lca(TNode root, int v1, int v2) {
        if (v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }

        while (root != null) {
            if (v1 <= root.data && root.data <= v2) {
                return root;
            } else if (v1 > root.data && v2 > root.data) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    public static TNode insert(TNode root, int data) {
        if (root == null) {
            return new TNode(data);
        } else {
            TNode cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        TNode root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        checkBST(root);
        // Node ans = lca(root, v1, v2);

    }
}