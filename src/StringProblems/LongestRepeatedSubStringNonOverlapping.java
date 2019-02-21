/**
 * Class to Implement LongestReapetedSubString
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/12/19
 * Project : StringProblems
 **/

package StringProblems;

public class LongestRepeatedSubStringNonOverlapping {

    public static String LRNOSS(String str) {

        System.out.println("\nInput : " + str);

        int n = str.length();
        int dp[][] = new int[n+1][n+1];
        int index = 0, res = 0;

        for (int i =1; i < n; i++) {
            for (int j = i+1; j<= n; j++) {
                if (str.charAt(i-1) == str.charAt(j-1) &&
                        dp[i-1][j-1] < (j-i)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }

                if (dp[i][j] > res) {
                    res = dp[i][j];
                    index = Math.max(i, index);
                }
            }
        }

        String resString = "";
        if (res > 0) {
            for (int i = index - res + 1; i <= index; i++) {
                resString += str.charAt(i-1);
            }
        }
        System.out.println("Result " + resString);
        return resString;
    }

    public static void main(String args[]) {

        //Longest Repaetd non overlapping substring
        LRNOSS("bananas"); //3 : ban
        LRNOSS("geeksforgeeks"); //7 eksforg
        LRNOSS("gegoogeoks"); //5geoks
        LRNOSS("bbbbbbbbb"); //1 b
        LRNOSS("1234567"); //7 1234567
    }
}
