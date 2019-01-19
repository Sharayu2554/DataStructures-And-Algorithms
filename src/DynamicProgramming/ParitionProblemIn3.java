/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/11/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

import java.util.Arrays;

/**
 * Given a set of positive integers, find if uit can be divided into two subsets with equal sums
 * s = { 7, 3, 2, 1, 5, 4, 8}
 *  {7, 3}
 *  {5, 4, 1}
 *  {8, 2}
 */
public class ParitionProblemIn3 {

    public static boolean PartitionRecur(int[] arr, int i, int SA, int SB, int SC) {
        if (i >= arr.length && (SA != 0 || SB != 0 || SC != 0) )
            return false;

        if (SA == 0 && SB == 0 && SC == 0) {
            return true;
        }

        boolean A = PartitionRecur(arr, i+1, SA - arr[i], SB, SC);
        boolean B = PartitionRecur(arr, i+1, SA, SB - arr[i], SC);
        boolean C = PartitionRecur(arr, i+1, SA, SB, SC - arr[i]);
        return A || B || C;
    }

    public static boolean PartitionDP(int[] arr, int sum) {
        boolean[][] dp = new boolean[arr.length +1][sum+1];

        for (int i = 0; i <= arr.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if (j - arr[i-1] < 0) {
                    dp[i][j] =  dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i - i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[arr.length][sum];
    }

    public static void main(String args[]) {

        int[] arr = { 7, 3, 2, 1, 5, 4, 14};
        int sum = Arrays.stream(arr).sum();
        if (sum % 3 == 0) {
//            System.out.println("parition is possible " + PartitionRecur(arr, sum/2, 0));
            System.out.println("parition is possible " + PartitionRecur(arr, 0, sum/3, sum/3, sum/3));
        }
    }
}
