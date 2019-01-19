/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/11/2019
 * Project : DynamicProgramming
 */

package DynamicProgramming;

import java.util.Arrays;

public class LongestIncreasingSubSequence {
    public static int NonRecuLIS(int[] arr, int n, int max) {
        int[] L = new int[n];
        L[0] =1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && L[j] > L[i]) {
                    L[i] = L[j];
                }
            }
            L[i]++;
        }
        return Arrays.stream(L).max().getAsInt();
    }

    /**
     * LIS recursive :
     * @param arr
     * @return
     */
    public static int LIS(int[] arr, int i, int n, int max) {

        int res =0;
        if (i == n) {
            return 0;
        }
        if (arr[i] > max) {
            res = 1 + LIS(arr, i+1, n, arr[i]);
        }
        int excl = LIS(arr, i +1, n, max);
        return Math.max(res, excl);
    }

    public static void main(String[] args)
    {
        int[] A = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };

        System.out.print("Length of LIS is " + LIS(A, 0, A.length, Integer.MIN_VALUE));
    }

}
