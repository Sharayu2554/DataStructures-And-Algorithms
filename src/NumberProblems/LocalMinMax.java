/**
 * Class to Implement LocalMinMax
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/12/19
 * Project : NumberProblems
 **/

package NumberProblems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LocalMinMax {

    public static void localMaxMin(int[] arr) {

        System.out.println("\nInput " + Arrays.toString(arr));
        int flag = 0; //1 = max, -1 = min, 0 = none
        int i =0;
        int n = arr.length;
        List<Integer> res = new LinkedList<>();

        while (i < n) {

            if (flag == 0) {

                if (i+2 >= n) {
                    break;
                }
                else if (arr[i] == arr[i+2]) {
                    if (arr[i+1] < arr[i])
                        flag = -1;
                    else
                        flag = 1;

                    res.add(i+1);
                    i = i +1;
                }
                else {
                    flag = 0;
                    i = i + 1;
                }
            }
            else if(flag  == 1) {
                if (i+3 >= n) {
                    flag = 0;
                    continue;
                }
                else if (arr[i+3] == (arr[i] -1)) {
                    if (arr[i] == arr[i+2])
                        res.add(i+1);

                    flag = -1;
                    res.add(i+2);
                    i = i + 2;
                }
                else {
                    flag = 0;
                    i = i + 2;
                }

            }
            else if (flag == -1) {
                if (i+3 >= n) {
                    flag = 0;
                    continue;
                }
                else if (arr[i+3] == (arr[i] +1)) {
                    if (arr[i] == arr[i+2])
                        res.add(i+1);

                    flag = 1;
                    res.add(i+2);
                    i = i + 2;
                }
                else {
                    flag = 0;
                    i = i + 2;
                }
            }
        }
        System.out.println(Arrays.toString(res.toArray()));
    }

    public static void main(String args[]) {
        int[] input = {9, 10, 9, 8, 9, 10, 11, 12, 11, 10, 9, 8, 7, 8, 9, 8, 7, 8, 9};
        localMaxMin(input);

        int[] input1 = { 1 };
        localMaxMin(input1);

        int[] input2 = {1, 2};
        localMaxMin(input2);

        int[] input3 = {1, 2, 3};
        localMaxMin(input3);

        int[] input4 = {1, 2, 3, 4, 5, 6};
        localMaxMin(input4);

        int[] input5 = {1, 2, 1};
        localMaxMin(input5);

        int[] input6 = {1, 2, 1, 0};
        localMaxMin(input6);

    }
}
