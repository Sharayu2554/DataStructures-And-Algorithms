/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/11/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

public class LevenshteinDistance {

    public static int minimum(int a, int b, int c) {
        int m = Math.min(a,b);
        return Math.min(m, c);
    }

    public static int LDR(String X, int xl, String Y, int yl) {

        if (xl == 0) {
            return yl;
        }

        if (yl == 0) {
            return xl;
        }

        if (X.charAt(xl-1) == Y.charAt(yl-1)) {
            return LDR(X, xl-1, Y, yl-1);
        }
        return (minimum(
                LDR(X, xl-1, Y, yl-1) + 1,
                LDR(X, xl-1, Y, yl) + 1,
                LDR(X, xl , Y, yl-1) +1
        ));
    }

    public static int LD(String X, int xl, String Y, int yl) {

        if (xl == 0) {
            return yl;
        }

        if (yl == 0) {
            return xl;
        }

        int[][] dp = new int[xl +1][yl +1];
        for (int i = 0; i <= xl; i++) {
            dp[i][0] = i;
        }


        for (int i = 0; i <= yl; i++) {
            dp[0][i] = i;
        }

        int cost = 0;
        for (int i = 1; i <= xl; i++) {
            for (int j = 1; j <= yl; j++) {
                if (X.charAt(i-1) == Y.charAt(j-1)) {
                    cost = 0;
                }
                else {
                    cost = 1;
                }

                dp[i][j] = minimum(
                        dp[i-1][j-1] + cost,
                        dp[i-1][j] +1,
                        dp[i][j-1] +1
                );
            }
        }
        return dp[xl][yl];
    }

    public static void main(String args[]) {
        String X = "kitten", Y = "sitting";

        System.out.println(" Distance is " + LDR(X, X.length(), Y, Y.length()));
    }
}
