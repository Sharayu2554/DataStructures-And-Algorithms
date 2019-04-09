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


    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String args[]) {
        String in = "bananas";
        System.out.println("Longest Palindromic Substring in  " + in +  " is " + longestPalindrome(in));
    }

}



