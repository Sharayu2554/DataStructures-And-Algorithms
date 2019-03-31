/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/12/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

import java.util.ArrayList;

public class DiffUtility {

    public static int LCS(String X, String Y, int[][] dp) {

        for (int i = 1; i <= X.length(); i++) {
            for (int j = 1; j <= Y.length(); j++) {
                if (X.charAt(i-1) == Y.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] +1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[X.length()][Y.length()];
    }

    public static void Diff(String X, int m, String Y, int n, int[][] dp) {

        //Character Present in both X and Y, Write it
        if (m > 0 && n > 0 && X.charAt(m-1) == Y.charAt(n-1)) {
            Diff(X, m-1, Y, n-1, dp);
            System.out.print(" " + X.charAt(m-1));
        }

        //Extra Character present in Y, Add it
        else if (n > 0 && (m == 0 || dp[m][n-1] >= dp[m-1][n])) {
            Diff(X, m, Y, n-1, dp);
            System.out.print(" +" + Y.charAt(n-1));
        }

        //Extra Character Present in X, Remove it
        else if (m > 0 && (n ==0 || dp[m][n-1] < dp[m-1][n])) {
            Diff(X, m-1, Y, n, dp);
            System.out.print(" -" + X.charAt(m-1));
        }
    }

    public static void main(String args[]) {
        String x = "ABCDFGHJQZ";
        String y = "ABCDEFGIJKRXYZ";

        int[][] dp = new int[x.length()+1][y.length() +1];
        int len = LCS(x, y, dp);
        System.out.println(" length of lcs " + len);
        Diff(x, x.length(), y, y.length(), dp);

        System.out.println(" ");
        x = "CXABBB";
        y = "DCBABYX";
        dp = new int[x.length() +1][y.length() +1];
        len = LCS(x, y, dp);
        Diff(x, x.length(), y, y.length(), dp);
        ArrayList<Integer> d = new ArrayList<>();

    }
}
