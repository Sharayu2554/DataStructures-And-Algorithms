/**
 * Class to Implement PeakAndValley
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/27/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.Arrays;

/**
 * : In an array of integers, a "peak" is an element which is greater than or equal
 * to the adjacent integers and a "valley" is an element which is less than or equal to the adjacent
 * integers. For example, in the array {S, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an
 * array of integers, sort the array into an alternating sequence of peaks and valleys.
 * EXAMPLE
 * Input: {S, 3, 1,2, 3}
 * Output: {S, 1,3,2, 3}
 */
public class PeakAndValley {

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void peaksAndValley(int[] a) {
        if (a.length == 0 || a.length == 1) {
            return;
        }

        if (a[0] < a[1]) {
            swap(a, 0, 1);
        }

        int i = 2;
        boolean flag = true; //1: high 0 : low
        while (i < a.length) {
            if (flag) {
                if (a[i] < a[i-1])
                    swap(a, i, i-1);
            }
            else {
                if (a[i] > a[i-1])
                    swap(a, i, i-1);
            }
            i = i +1;
            flag = !flag;

        }
    }

    public static void main(String args[]) {
        int[] arr = {10, 9, 8, 7, 6, 3, 1, 4, 2, 5, 6, 3, 8, 9, 10};
        PeakAndValley p = new PeakAndValley();
        p.peaksAndValley(arr);
        System.out.println(" solution " + Arrays.toString(arr));

        int[] arr1 = {1, 2, 3, 4, 5, 6,7 , 8, 9, 10};
        p.peaksAndValley(arr1);
        System.out.println(" " + Arrays.toString(arr1));

        int[] arr2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        p.peaksAndValley(arr2);
        System.out.println(" " + Arrays.toString(arr2));

        int[] arr3 = {1, 1, 1, 1, 1, 1, 1};
        p.peaksAndValley(arr3);
        System.out.println(" " + Arrays.toString(arr3));
    }
}
