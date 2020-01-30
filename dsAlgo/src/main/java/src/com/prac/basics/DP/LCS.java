package com.prac.basics.DP;

/**
 * Longest Common Subsequence
 */
public class LCS {
    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        System.out.println(longestCommonSubSequenceLength(X, Y, X.length(), Y.length()));
    }

    public static int longestCommonSubSequenceLength(String x, String y, int xLen, int yLen) {
        int lcs[][] = new int[xLen + 1][yLen + 1];

        for (int i = 1; i <= xLen; i++) {
            for (int j = 1; j <= yLen; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j - 1]);
                }
            }
        }
        return lcs[xLen][yLen];
    }

    /**
     * This method does not use memoization and the time complexity is 2^n
     *
     * @param x
     * @param y
     * @param xLen
     * @param yLen
     */
    public static int findLongestCommonSubsequence(String x, String y, int xLen, int yLen) {
        if (xLen == x.length() - 1 || yLen == y.length() - 1)
            return 0;
        if (x.charAt(xLen) == y.charAt(yLen)) {
            return 1 + findLongestCommonSubsequence(x, y, xLen + 1, yLen + 1);
        } else {
            return Math.max(findLongestCommonSubsequence(x, y, xLen + 1, yLen),
                    findLongestCommonSubsequence(x, y, xLen, yLen + 1));
        }
    }
}

