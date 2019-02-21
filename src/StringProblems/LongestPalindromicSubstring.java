/**
 * Class to Implement LongestPalindromicSubstring
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/13/19
 * Project : StringProblems
 **/

package StringProblems;

public class LongestPalindromicSubstring {

    public static String longestPalindromicSubstring(String s) {

        int index = 0, len = 0, n = s.length();
        boolean dp[][] = new boolean[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i-1) == s.charAt(i))
                dp[i-1][i] = true;
        }

        for (int j = 2; j < n; j++) {
            for (int i = 0; i < (n-j); i++) {
                if (s.charAt(i) == s.charAt(i+j) && dp[i+1][i+j-1]){
                    dp[i][i+j] = true;
                    index = i;
                    len = j + 1;
                }
                else {
                    dp[i][i+j] = false;
                }
            }
        }
        System.out.println("index " + index);
        System.out.println("lenght " + len);
        return s.substring(index, index + len);
    }

    public static void main(String args[]) {
        String in = "bananas";
        System.out.println("Longest Palindromic Substring in  " + in +  " is " + longestPalindromicSubstring(in));
    }

}



