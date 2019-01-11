/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/10/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

public class LongestCommonSubsequence {

    public int max(int i, int j) {
        return i > j ? i : j;
    }

    public String lcs(char[] X, char[] Y, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i-1] == Y[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        //return string
        String res = "";
        int i = m, j = n;
        while (i > 0 && j > 0) {
            while(i > 0 && j >0 && dp[i][j] == dp[i-1][j]) {
                i = i-1;
            }
            while(i > 0 && j >0 && dp[i][j] == dp[i][j-1]) {
                j = j-1;
            }
            res = X[i-1] + res;
            i = i-1;
            j = j-1;
        }
        System.out.println(" subsequence " + res);
        return res;
    }

    public static void main(String[] args)
    {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "AAABBBCCC";
        String s2 = "AABBDCC";

        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " +
                lcs.lcs( X, Y, m, n ) );
    }
}
