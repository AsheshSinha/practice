package com.prac.basics.DP;

public class BooleanKnapSack {
    public static void main(String[] args) {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(computeMaxValue(wt, val, W, n));
    }

    public static int computeMaxValue(int[] w, int[] v, int C, int n) {
        int[][] mValue = new int[n + 1][C + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= C; j++) {
                if (i == 0 || j == 0) {
                    mValue[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    mValue[i][j] = Math.max(mValue[i - 1][j], mValue[i - 1][j - w[i - 1]] + v[i - 1]);
                } else {
                    mValue[i][j] = mValue[i - 1][j];
                }
            }
        }
        return mValue[n][C];
    }
}
