/**
 * Class to Implement FinishTaskWithSkipping
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/28/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

/**
 * Given time taken by n tasks. Find the minimum time needed to finish the tasks such that skipping of tasks is allowed,
 * but can not skip two consecutive tasks.
 */
public class FinishTaskWithSkipping {


    //TODO: Optimized, memoized solution
    //This is recursive algorithm
    public static int skip(int[] arr, int i, int inc, int excl, int res) {
        if (i == arr.length) {
            return res;
        }

        if (excl == i-1) {
            return skip(arr, i+1, i, excl, res + arr[i]);
        }
        else {
            int incl_r = skip(arr,  i+1,  i, excl, res + arr[i]);
            int excl_r = skip(arr, i+1, inc, i, res);
            return Math.min(incl_r, excl_r);
        }

    }

    public static int skip(int[] arr) {
        return skip(arr, 0, -2, -2, 0);
    }

    public static void main(String args[]) {

        int[] arr = {10, 5, 2, 4, 8, 6, 7, 10};
        System.out.println(" Result " + skip(arr));
        //Expected 22

        int[] arr1 = { 10, 5, 7, 10};
        System.out.println(" Result " + skip(arr1));
        //Expected Output 12

        int[] arr2 = { 10, 5, 2, 7, 10};
        System.out.println(" Result " + skip(arr2));
        //Expected output 12

        int[] arr3 = {10, 10, 5, 2, 4, 8, 6, 10, 7 ,10 };
        System.out.println(" Result " + skip(arr3));
        //Expected Output 29
    }
}