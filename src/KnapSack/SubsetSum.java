/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/7/2019
 * Project : KnapSack
 */

package KnapSack;

/**
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
 * Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 *
 * This problem exactly like 01 knapSack
 */
public class SubsetSum {

    static int max(int a, int b) { return (a > b)? a : b; }

    public static boolean isSubsetSum(int[] arr, int n, int maxSum) {

        int[]  dp = new int[maxSum +1];
        for (int i = 0; i < n; i++ ) {
            for (int j = maxSum; j >= arr[i]; j--) {
                dp[j] = max(dp[j], arr[i] + dp[j - arr[i]]);
            }
        }
        return dp[maxSum] == maxSum;
    }

    public static void main(String args[]) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 11;
        int n = set.length;
        if (isSubsetSum(set, n, sum) == true)
            System.out.println("Found a subset"
                    + " with given sum");
        else
            System.out.println("No subset with"
                    + " given sum");
    }
}
