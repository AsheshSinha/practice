package com.prac.basics.tree.BST.AVL;

public class PruneBST {
    public static void main(String[] args) {
        int a[] = {7, 13, 19, 25, 37, 41, 49, 53, 60, 71, 82, 89};
        TNode root = buildBST(a, 0, 11);
        printBST(root);
        pruneBST(root, 25, 60);
        System.out.println();
        printBST(root);
    }

    private static TNode pruneBST(TNode root, int a, int b) {
        if (root == null)
            return null;
        else {
            if (root.left != null) {
                root.left = pruneBST(root.left, a, b);
            }
            if (root.right != null) {
                root.right = pruneBST(root.right, a, b);
            }
            if (a<= root.data && root.data <= b) {
                return root;
            }else if(root.data < a){
                return root.right;
            }else {
                return root.left;
            }
        }
    }

    private static void printBST(TNode root) {
        if (root == null) {
            return;
        } else {

            if (root.left != null) {
                printBST(root.left);
            }
            System.out.print(root.data + " ");
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
            return new TNode(a[l], l);
        } else {
            int mid = l + (r - l) / 2 + 1;
            TNode bst = new TNode(a[mid], mid);
            bst.left = buildBST(a, l, mid - 1);
            bst.right = buildBST(a, mid + 1, r);
            return bst;
        }
    }
}
