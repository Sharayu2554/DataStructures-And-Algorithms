/**
 * Class to Implement ASCIILCS
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/15/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

public class ASCIILCS {

    /**
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
     *
     * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
     * Input: s1 = "delete", s2 = "leet"
     * Output: 403
     * Explanation: Deleting "dee" from "delete" to turn the string into "let",
     * adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
     * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
     * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
     *
     * @param s1
     * @param s2
     * @return
     */
    public int lcs(String s1, String s2) {

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for(int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }

        for (int i =1; i < s1.length()+1; i++) {
            for (int j = 1; j < s2.length() +1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1),
                            dp[i][j-1] + s2.charAt(j-1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public int minimumDeleteSum(String s1, String s2) {
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        return lcs(s1, s2);
    }
}
