/**
 * Class to Implement MaxSubsequenceSumAdjacent
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/29/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

/**
 * Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in
 * the sequence should be adjacent in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should
 * return 15 (sum of 3, 5 and 7).Answer the question in most efficient way.
 *
 * Input : arr[] = {5, 5, 10, 100, 10, 5}
 * Output : 110
 *
 * Input : arr[] = {1, 2, 3}
 * Output : 4
 *
 * Input : arr[] = {1, 20, 3}
 * Output : 20
 */

public class MaxSubsequenceSumAdjacent {

    public static int subSequenceSum(int[] arr) {

        if (arr.length  <= 2) {
            if (arr.length == 0) {
                return 0;
            }
            else if (arr.length == 1) {
                return arr[0];
            }
            else {
                return arr[0] > arr[1] ? arr[0] : arr[1];
            }
        }
        int inc = arr[1];
        int ex = arr[0];
        int ex_1 = 0;
        int inc_n;

        for (int i = 2; i< arr.length; i++) {
            inc_n = arr[i] + Math.max(ex, ex_1);
            ex_1 = ex;
            ex = inc;
            inc = inc_n;
        }
        return Math.max(Math.max(inc, ex), ex_1);
    }

    public static int maxSumOptimized(int[] arr) {
        int exc_n;
        int inc  = arr[0];
        int ex = 0;

        for (int i = 1; i< arr.length; i++) {
            exc_n = inc > ex ? inc : ex;
            inc = arr[i] + ex;
            ex = exc_n;
        }
        return Math.max(inc, ex);
    }

    public static void main(String args[]) {
        int arr[] = {5, 5, 10, 100, 10, 5};
        System.out.println(" Result  " + subSequenceSum(arr));
        System.out.println(" Result  " + maxSumOptimized(arr));

        int arr1[] = {1, 2, 3};
        System.out.println(" Result  " + subSequenceSum(arr1));
        System.out.println(" Result  " + maxSumOptimized(arr1));

        int arr2[] = {1, 20, 3};
        System.out.println(" Result  " + subSequenceSum(arr2));
        System.out.println(" Result  " + maxSumOptimized(arr2));

        int arr3[] = {3, 2, 7, 10 };
        System.out.println(" Result  " + subSequenceSum(arr3));
        System.out.println(" Result  " + maxSumOptimized(arr3));

        int arr4[]= {3, 2, 5, 10, 7};
        System.out.println(" Result  " + subSequenceSum(arr4));
        System.out.println(" Result  " + maxSumOptimized(arr4));

        int arr5[] = {1, 2};
        System.out.println(" Result  " + subSequenceSum(arr5));
        System.out.println(" Result  " + maxSumOptimized(arr5));
    }
}
