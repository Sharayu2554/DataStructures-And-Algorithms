/**
 * Class to Implement ApproximateSubsetSum
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/30/19
 * Project : KnapSack
 **/

package KnapSack;

import java.util.Arrays;

public class ApproximateSubsetSum {

    public static int subset(int[] arr, int k) {

        int [] subset = new int[k+1];

        int[] res = new int[k+1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = k; j >= arr[i]; j--) {
                if (res[j] < (arr[i] + res[j - arr[i]])) {
                    res[j] =  arr[i] + res[j - arr[i]];
                    subset[j] = arr[i];
                }
            }
        }

        int  i = res[k];
        System.out.print("Subset is : ");
        while (i > 0) {
            System.out.print(subset[i] + " ");
            i = i - subset[i];

        }
        System.out.println("");
        return Arrays.stream(res).max().getAsInt();
    }

    public static void main(String args[]) {
        int[] arr =  {4, 34, 4, 14, 6, 2};
        int k = 11;
        System.out.println("Result is " + subset(arr, k));
    }
}
