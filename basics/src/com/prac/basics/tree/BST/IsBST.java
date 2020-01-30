package com.prac.basics.tree.BST;


import java.util.LinkedList;

public class IsBST {
    public static void main(String[] args) {
        //System.out.println(Integer.MIN_VALUE);
        IsBST chcker = new IsBST();
    }

    boolean checkBST(TNode root) {
        return isBST(root, null);
    }

    public static TNode insert(TNode root, int data) {
        if (root == null) {
            root = new TNode(data);
        }
        if (data > root.data) {
            root.right = insert(root.right, data);
        } else if (data < root.data) {
            root.left = insert(root.left, data);
        }
        return root;
    }
    class Data{
        int horiDist;
        TNode node;
    }
    public static void levelOrder(TNode root) {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TNode node = queue.remove();
            System.out.print(node.data + " ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    boolean isBST(TNode root, TNode prev) {
        boolean tester = true;
        if (root == null) {
            return tester;
        } else {
            if (root.left != null) {
                tester = tester && isBST(root.left, prev);
                prev = root.left;
            }
            if (prev != null && root.data <= prev.data) {
                tester = tester && false;
            }
            prev = root;
            if (root.right != null) {
                tester = tester && isBST(root.right, prev);
            }
            return tester;
        }
    }

    static int prev = Integer.MIN_VALUE;

    boolean isBST2(TNode root) {
        if (root == null) {
            return true;
        } else {
            if (!isBST2(root.left)) {
                return false;
            }
            if (root.data <= prev) {
                return false;
            }
            prev = root.data;
            return isBST2(root.right);
        }
    }

    boolean isBSTUtil(TNode node, int min, int max) {
        /* an empty tree is BST */
        if (node == null)
            return true;

        /* false if this node violates the min/max constraints */
        if (node.data < min || node.data > max)
            return false;

        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        // Allow only distinct values
        return (isBSTUtil(node.left, min, node.data - 1) &&
                isBSTUtil(node.right, node.data + 1, max));
    }
}

