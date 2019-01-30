/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/10/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

/**
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 *
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {

    /**
     * Recursion : cutRod[n] = max (price[i] + cutRod(n - i - 1)) for all i .. 1..10
     * @param arr
     * @param n
     * @return
     */
    public static int cutRod(int[] arr, int n) {
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                max_val = Math.max(max_val, arr[j] + dp[i-j-1]);
            }
            dp[i] = max_val;
        }

        return dp[n];
    }

    public static void main(String args[])
    {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                cutRod(arr, size));
    }
}
