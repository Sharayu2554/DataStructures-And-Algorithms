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
 * s = { 3, 1, 1, 2, 1, 2}
 *  {3, 1, 1}
 *  {2, 2, 1}
 */
public class PartitionProblem {

    public static boolean PartitionRecur(int[] arr, int sum, int i) {
        if (i >= arr.length)
            return false;

        if (sum == 0 && i < arr.length) {
            return true;
        }

        return PartitionRecur(arr, sum - arr[i], i +1) || PartitionRecur(arr, sum, i+1);
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
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
                //optimized if value becomes true before it reached end,
                //stop there, not need to process any further
                if (j == sum && dp[i][j]) {
                    return dp[i][sum];
                }
            }
        }
        return dp[arr.length][sum];
    }

    public static void main(String args[]) {

        int[] arr = {3, 1, 8, 2, 1, 1};
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 == 0) {
//            System.out.println("parition is possible " + PartitionRecur(arr, sum/2, 0));
            System.out.println("parition is possible " + PartitionDP(arr, sum/2));
        }
    }
}
