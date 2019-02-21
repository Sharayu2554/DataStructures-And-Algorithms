/**
 * Class to Implement LongestPalindromicSubsequence
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/13/19
 * Project : StringProblems
 **/

package StringProblems;


public class LongestPalindromicSubsequence {

    public static int longestPalindromicSubsequence(String s) {

        int n = s.length();
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i =1; i < n; i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                dp[i-1][i] = 2;
            }
            else {
                dp[i-1][i] = 1;
            }
        }

        for(int j = 2; j <n; j++ ) {
            for(int i = 0; i < (n-j); i++){
                if (s.charAt(i) == s.charAt(i+j)) {
                    dp[i][i+j] = 2 + dp[i+1][i+j-1];
                }
                else {
                    dp[i][i+j] = Math.max(dp[i][i+j-1], dp[i+1][i+j]);
                }
            }
        }
        return dp[0][n-1];

    }

    public static void main(String args[]) {
//        String in = "agbdefdzba";
        String in = "agbdefda";
        System.out.println("Longest Palindromic Subsequence of " + in  + " is " + longestPalindromicSubsequence(in));
    }

}


