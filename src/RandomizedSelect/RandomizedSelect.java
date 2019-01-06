/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/5/2019
 * Project : RandomizedSelect
 */

package RandomizedSelect;

import java.util.Arrays;

public class RandomizedSelect {

    public static int k = 0;

    public static void swap(int[] arr, int i, int j) {
        int temp  = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int p, int r) {
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (arr[j] < arr[r]) {
                i = i + 1;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, r);
        return i + 1;
    }

    public static int randomizedPartition(int[] arr, int p, int r) {
        int ran = (int)(Math.random() * 4); //casting Math.random in (int) will always return in zero, so always multiply with constant
        int q = ( ran % (r - p) )+ p;

        swap(arr, q, r);
        return partition(arr, p, r);
    }

    /**
     *
     * @param arr
     * @param p : start index
     * @param r  : end index
     * @param i : ith smallest element in array
     * @return     * Running Time : Average O(N) and Worst Case : O(N ^ 2)
     */
    public static int randomizedSelect(int[] arr, int p, int r, int i) {

        if (p == r) {
            return arr[r];
        }
        int pivot = randomizedPartition(arr, p, r);
        int k = pivot - p + 1;

        if (i == k) {
            return arr[pivot];
        }

        if (i < k)
            return randomizedSelect(arr, p, pivot, i);
        else
            return randomizedSelect(arr, pivot +1,  r, i - k);
    }


    public static void main(String args[]) {
        k = 4;
        int[] arr = {1, 3, 6, 11, 14, 12, 2, 4, 13, 10, 9, 5, 8, 7};
        int pivot = randomizedSelect(arr, 0, arr.length -1, 1 );
        System.out.println(Arrays.toString(arr) + " result is " + pivot);
    }
}
