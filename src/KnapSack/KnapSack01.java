/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/6/2019
 * Project : KnapSack
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
        int val[] = new int[]{120, 100, 60};
        int wt[] = new int[]{30, 20, 10};
        int  W = 50;
        int n = val.length;
        System.out.println(knapSackOptimized(W, wt, val, n));
    }
}