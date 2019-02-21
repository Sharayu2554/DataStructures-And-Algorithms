/**
 * Class to Implement LongestCommonSubstring
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/13/19
 * Project : StringProblems
 **/

package StringProblems;

import java.util.Arrays;

class LongestCommonSubsequence {

    public static String printSubsequence(String x, int[][] dp, int n, int m) {
        int i = n, j = m;
        int k = dp[n][m];
        char[] res = new char[k];
        k = k-1;
        while (i > 0&& j > 0 ) {
            while (i > 0 && dp[i][j] == dp[i-1][j]) {
                i = i - 1;
            }
            while (j > 0 && dp[i][j] == dp[i][j-1]) {
                j = j - 1;
            }
            res[k] = x.charAt(i-1);
            k--;
            i--;
            j--;
        }
        return Arrays.toString(res);
    }

    public static String longestCommonSubsequence(String x, String y) {

        int n = x.length();
        int m = y.length();
        int res = 0, index = 0;
        int dp[][] = new int[n+1][m+1];

        for (int i = 1; i <= n; i++ ) {
            for (int j = 1; j <= m; j++ ) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println("length of longest common subsequence " + dp[n][m] );

        return printSubsequence(x, dp, n, m);
    }

    public static void main(String args[]) {
        String x = "GeeksForGeeks";
        String y = "GeeksQuiz";

        System.out.println("Input X : " + x + " Y : " + y );
        System.out.println("Common Substring : " + longestCommonSubsequence(x, y));

    }
}

