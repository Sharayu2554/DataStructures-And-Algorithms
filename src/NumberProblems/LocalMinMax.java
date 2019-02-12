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

    class Node {
        int l;
        int r;

        public Node(int i, int j) {
            l = i;
            r = j;
        }
    }

    public void localMaxMin(int[] arr) {

        System.out.println("\nInput " + Arrays.toString(arr));
        List<Integer> res = new LinkedList<>();
        if (arr.length > 2) {
            if ((arr[0] + arr.length - 1 != arr[arr.length -1]) ||
                 arr[0] - arr.length + 1 != arr[arr.length -1]) {
                localRecursion(arr, 0, arr.length-1, res);
            }
        }

        System.out.println(Arrays.toString(res.toArray()));
    }

    public Node localRecursion(int[] arr, int p, int q, List<Integer> res) {

        if (p > q)
            return null;
        if (p == q)
            return new Node(0, 0);

        if (q -p == 1) {
            if  (arr[p] < arr[q])
                return new Node(1, 1);
            else if (arr[p] > arr[q])
                return new Node(-1, -1);
            else
                return new Node(0, 0);
        }

        int m = (p + q)/2;
        Node L = localRecursion(arr, p, m, res);
        Node R = localRecursion(arr, m, q, res);

        if (L.r == -1 && R.l == 1) {
            res.add(m);
        }
        else if (L.r == 1 && R.l == -1) {
            res.add(m);
        }
        return new Node(L.l, R.r);
    }

    public static void main(String args[]) {

        LocalMinMax l = new LocalMinMax();
        int[] input0 = {1, 2, 1, 2, 3, 2, 1, 2, 3, 4};
        l.localMaxMin(input0);

        int[] input = {9, 10, 9, 8, 9, 10, 11, 12, 11, 10, 9, 8, 7, 8, 9, 8, 7, 8, 9};
        l.localMaxMin(input);

        int[] input1 = { 1 };
        l.localMaxMin(input1);

        int[] input2 = {1, 2};
        l.localMaxMin(input2);

        int[] input3 = {1, 2, 3};
        l.localMaxMin(input3);

        int[] input4 = {1, 2, 3, 4, 5, 6};
        l.localMaxMin(input4);

        int[] input5 = {1, 2, 1};
        l.localMaxMin(input5);

        int[] input6 = {1, 2, 1, 0};
        l.localMaxMin(input6);

    }
}
