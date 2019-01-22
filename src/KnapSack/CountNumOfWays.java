/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/7/2019
 * Project : KnapSack
 */

package KnapSack;

/**
 * Given a set of m distinct positive integers and a value ‘N’.
 * The problem is to count the total number of ways we can form ‘N’ by doing sum of the array elements.
 * Repetitions and different arrangements are allowed.
 *
 * Input : arr = {1, 5, 6}, N = 7
 * Output : 6
 *
 * Explanation:- The different ways are:
 * 1+1+1+1+1+1+1
 * 1+1+5
 * 1+5+1
 * 5+1+1
 * 1+6
 * 6+1
 */
public class CountNumOfWays {

    public static int countWays(int N, int arr[], int m) {

        int[] count = new int[N+1];
        count[0] = 1;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[j] <= i ) {
                    count[i] += count[i - arr[j]];
                }
            }
        }
        return count[N];
    }

    public static void main(String[] args)
    {
        int N = 3;
        int arr[] = {1, 5, 6};
        System.out.println("Total number of ways = "
                + countWays(N, arr, arr.length));
    }
}
