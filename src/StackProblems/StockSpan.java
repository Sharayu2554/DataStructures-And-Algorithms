/**
 * Class to Implement StockSpan
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/24/19
 * Project : StackProblems
 **/

package StackProblems;

import java.util.Arrays;
import java.util.Stack;

/**
 * The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and
 * we need to calculate span of stock’s price for all n days.
 * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before
 * the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for
 * corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 *
 */
public class StockSpan {

    //Running Time Complexity: O(N)
    public static void calculateSpan(int[] price, int n, int[] S) {
        Stack<Integer> st = new Stack<>();
        Stack<Integer> span = new Stack<>();

        for (int i = 0; i< n; i++) {
            if (st.isEmpty()) {
                st.push(price[i]);
                span.push(1);
                S[i] = 1;
            }
            else {
                if (price[i] < st.peek()) {
                    st.push(price[i]);
                    span.push(1);
                    S[i] = 1;
                }
                else {
                    int val = 1;
                    while (!st.isEmpty() && price[i] > st.peek()) {
                        val = val + span.pop();
                        st.pop();
                    }
                    st.push(price[i]);
                    span.push(val);
                    S[i] = val;
                }
            }
        }
        System.out.println("Result Span : " + Arrays.toString(S));
    }

    public static void main(String[] args)
    {
        int price[] = {10, 4, 5, 90, 120, 80};
        int n = price.length;
        int S[]=new int[n];

        // Fill the span values in array S[]
        calculateSpan(price, n, S);
    }
}
