package com.prac.basics.tree.BST.AVL;

public class TreeCompressor {
    public static void main(String[] args) {
        int a[] = {3, 5, 8, 10, 15, 20 };
        TNode root = buildBST(a, 0, 5);
        printBST(root);
        //compressTree(root, null);
        //printBSTCompressed(root);
        System.out.println(findClosest(root,18).data);
    }
    private static int abs(int elem){
        if(elem < 0){
            elem = elem * -1;
        }
        return elem;
    }

    private static TNode findClosest(TNode root, int elem) {
        if (root == null || root.data == elem) {
            return root;
        } else {
            TNode temp = null;
            if (elem > root.data) {
                if(root.right == null){
                    return root;
                }
                temp = findClosest(root.right, elem);
                if(abs(elem - temp.data) > abs(elem - root.data))
                    return root;
                else
                    return temp;
            } else {
                if(root.left == null){
                    return root;
                }
                temp = findClosest(root.left, elem);
                if(abs(elem - temp.data) > abs(elem - root.data))
                    return root;
                else
                    return temp;
            }
        }
    }

    /**
     * This method replaces the no of children data in the nodes inorder predecessors data
     * And also reduces the tree size by merging the inorder predecessor
     *
     * @param root
     * @param prev
     * @return NOT COMPLETED
     */
    private static TNode compressTree(TNode root, TNode prev) {
        if (root == null) {
            return prev;
        } else {
            if (root.left != null) {
                prev = compressTree(root.left, prev);
            }
            if (prev.noOfChild == 0) {
                root.left = null;
            }
            if (prev != null) {
                if (prev.noOfChild != 0) {
                    root.noOfChild = prev.data;
                }
            }
            prev = root;
            if (root.right != null) {
                prev = compressTree(root.right, prev);
            }
            return prev;
        }
    }

    private static void printBST(TNode root) {
        if (root == null) {
            return;
        } else {
            if (root.left != null) {
                printBST(root.left);
            }
            System.out.println(root.data + " ," + root.noOfChild);
            if (root.right != null) {
                printBST(root.right);
            }
            return;
        }
    }

    private static void printBSTCompressed(TNode root) {
        if (root == null) {
            return;
        } else {
            if (root.left != null) {
                printBSTCompressed(root.left);
            }
            System.out.println(root.data + " ," + root.noOfChild);
            if (root.right != null) {
                printBSTCompressed(root.right);
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
            int mid = l + (r - l) / 2;
            TNode bst = new TNode(a[mid], mid);
            bst.left = buildBST(a, l, mid - 1);
            bst.right = buildBST(a, mid + 1, r);
            return bst;
        }
    }
}
