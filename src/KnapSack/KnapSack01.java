/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/6/2019
 * Project : KnapSack
 */

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 * in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and
 * weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out
 * the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 *   val[] = {60, 100, 120};
 *   wt[] = {10, 20, 30};
 *   W = 50;
 *
 *   Output : 220
 */
package KnapSack;

public class KnapSack01 {

    // A utility function that returns maximum of two integers
    static int max(int a, int b) { return (a > b)? a : b; }

    public static int knapSackOptimized(int W, int wt[], int val[], int n) {
        int[] dp = new int[W + 1];

        for (int i =0; i < n ; i++) {
            for (int j = W; j >= wt[i];  j--) {
                dp[j] = max(dp[j] , val[i] + dp[j - wt[i]]);
            }
        }
        return dp[W];
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, int wt[], int val[], int n)
    {
        int i, w;
        int K[][] = new int[n+1][W+1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if (wt[i-1] <= w)
                    K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }

        return K[n][W];
    }


    public static void main(String args[])
    {
        int val[] = new int[]{100, 120, 60};
        int wt[] = new int[]{2, 3, 1};
        int  W = 5;
        int n = val.length;
        System.out.println(knapSack(W, wt, val, n));
    }
}
