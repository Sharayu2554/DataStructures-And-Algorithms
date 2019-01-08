/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/7/2019
 * Project : KnapSack
 */

package KnapSack;

import java.util.Arrays;

/**
 * Unbounded Knapsack (Repetition of items allowed)
 * Given a knapsack weight W and a set of n items with certain value vali and weight wti,
 * we need to calculate minimum amount that could make up this quantity exactly.
 * This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
 *
 * Input : W = 100
 *        val[]  = {1, 30}
 *        wt[] = {1, 50}
 * Output : 100
 * There are many ways to fill knapsack.
 * 1) 2 instances of 50 unit weight item.
 * 2) 100 instances of 1 unit weight item.
 * 3) 1 instance of 50 unit weight item and 50
 *    instances of 1 unit weight items.
 * We get maximum value with option 2.
 */
public class UnboundedKnapSack {

    private static int max(int i, int j) {
        return (i > j) ? i : j;
    }

    /**
     * Logic: in Bounded KnapSack we iterated from
     * i 1..n
     * j W..wt[i]
     *
     * For Unbounded its reverse :
     * i 1..W
     * j 1..n
     *
     * @param W
     * @param n
     * @param val
     * @param wt
     * @return
     */
    public static int unboundedKnapsack(int W, int n, int val[], int[] wt) {
        int[] dp = new int[W+1];
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= i) {
                    dp[i] = max(dp[i], val[j] + dp[i - wt[j]]);
                }
            }
        }
        System.out.println(" ");
        return dp[W];
    }

    public static void main(String[] args) {
        int W = 100;
        int val[] = {1, 30};
        int wt[] = {1, 50};
        int n = val.length;
        System.out.println(unboundedKnapsack(W, n, val, wt));
    }
}
