/**
 * Class to Implement ConstraintSubSet
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/30/19
 * Project : KnapSack
 **/

package KnapSack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Is there a subset of R items whose sum is K
 */
public class ConstraintSubSet {

    public static boolean subset(int[] arr, int r, int k, int n) {

        if (n < r)
            return false;

        if (Arrays.stream(arr).sum() < k)
            return false;

        int[] res = new int[k+1];
        LinkedList<Integer>[] countW = new LinkedList[k+1];
        for (int i = 0; i < k+1; i++) {
            countW[i] = new LinkedList<>(); //array of linkedList
        }
        int count[] = new int[k+1];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= arr[i]; j--) {
                if (res[j] <= (arr[i] + res[j - arr[i]])) {
                    res[j] = arr[i] + res[j - arr[i]];
                    count[j] = 1 + count[j-arr[i]];
                    if (j == res[j]) {
                        countW[j].add(count[j]);
                    }
                }
            }
        }

        if (res[k] == k) {

            if (count[k] == r)
                return true;

            for (Integer c : countW[k]) {
                if (c == r) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String arsg[]) {
        int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
        int r  = 5;
        int k = 48;
        System.out.println("There is subset of " + r + " items whose sum is " + k + " " + subset(arr, r, k, arr.length));


        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int r1  = 2;
        int k1 = 9;
        System.out.println("There is subset of " + r1 + " items whose sum is " + k1 + " " + subset(arr1, r1, k1, arr1.length));
    }
}
