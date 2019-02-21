/**
 * Class to Implement LongestCommonSubString
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/13/19
 * Project : StringProblems
 **/

package StringProblems;

public class LongestCommonSubString {

    public static String longestCommonSubstring(String x, String y) {
        int n = x.length();
        int m = y.length();
        int dp[][] = new int[n+1][m+1];
        int index = 0;
        int res = 0;

        for (int i =1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] +1;
                    if (res < dp[i][j]) {
                        index = i;
                        res = dp[i][j];
                    }
                }
            }
        }

        String resString = "";
        if (res != 0) {
            for (int i = index - res; i < index; i++) {
                resString += x.charAt(i);
            }
        }
        return resString;
    }

    public static void main(String args[]) {
        String x = "geeksforgeeks";
        String y = "geeksQuiz";
        System.out.println("Longest Common  Substring between x : " + x + " y : " + y + " is " + longestCommonSubstring(x, y));
    }
}


