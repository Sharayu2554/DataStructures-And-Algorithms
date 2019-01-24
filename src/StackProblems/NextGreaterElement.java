/**
 * Class to Implement NextGreaterElement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/24/19
 * Project : StackProblems
 **/

package StackProblems;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem Description :
 * Given an array, print the Next Greater Element (NGE) for every element.
 * The Next greater Element for an element x is the first greater element on the right side of x in array.
 * Elements for which no greater element exist, consider next greater element as -1.
 * For given array :  [4, 5, 2, 25}
 *  Element       NGE
 *    4      -->   5
 *    5      -->   25
 *    2      -->   25
 *    25     -->   -1
 */
public class NextGreaterElement {

    //Naive Approach with two nested loops take O(N ^2) Runtime.
    //Approach with stack uses

    public static void printNGE(int[] arr, int n) {
        Deque<Integer> st = new LinkedList<>();

        for (int i =0; i < n; i++) {
            if (st.isEmpty()) {
                st.push(arr[i]);
            }
            else {
                if (arr[i] < st.peek()) {
                    st.push(arr[i]);
                }
                else {
                    while (!st.isEmpty()  &&  arr[i] > st.peek()) {
                        System.out.println(st.pop() + " --> " + arr[i]);
                    }
                    st.push(arr[i]);
                }
            }
        }
        while (!st.isEmpty()) {
            System.out.println(st.pop() + " --> " + (-1));
        }

    }

    public static void main(String[] args)
    {
        int arr[] = { 11, 13, 21, 3 };
        int n = arr.length;
        printNGE(arr, n);
    }
}
