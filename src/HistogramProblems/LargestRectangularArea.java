/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/21/2019
 * Project : HistogramProblems
 */

package HistogramProblems;

import java.util.Stack;

/**
 * Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a number
 * of contiguous bars. For simplicity, assume that all bars have same width and the width is 1 unit.
 * For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 1, 6}.
 * The largest possible rectangle possible is 12 (see the below figure, the max area rectangle is highlighted in red)
 *
 */
public class LargestRectangularArea {

    /**
     * logic :
     * Elements are added in increasing order in stack.
     * smallest element area will be calculated at last, when no smaller element is found.
     * if current value is i, areas is calculated for all the bars above i
     * and until max area is found, i is not incremented
     *
     * for example :  { 6, 2, 5, 5,  3, 5, 1, 6, 2, 6, 1, 6, 1 }
     * Here, whe 2 was found, 6 area was calculated and stack is emptied.
     * then 2, 5, 5, is added in stack
     * then 3 smaller than 5 is found,
     * 5 is popped and first area of 5 as one bar is calculated
     * next 5 is popped again and then area of 5 as 2 bars is calculated
     * Now since 2 is smaller than 3 we stop and add 3 in stack
     * then add 5 in stack
     * now we find 1 which is smaller than 5, so
     * we pop 5 and calculate area of 5 as single bar,
     * then we pop 3 and calculate area with 3
     *
     * But problem is HOW IS AREA OF 3 with possible values Calculated :
     * 3 is popped and stack has 2 on top, so since current i position value is smaller than 3 and 2 is also smaller than 3,
     * Hence, from i to stack.top -1 is number of 3 bars we can take for area calculation.
     *
     * area of 2 is taken only when 1 is found.
     *
     * area of 1 is taken only when i is done. (i = n)
     *
     * Run Time of Algo : O(N)
     * @param hist
     * @param n
     * @return
     */
    public static int getMaxArea(int[] hist, int n) {

        Stack<Integer> st = new Stack<>();
        int i = 0, tp = 0;
        int maxArea = 0;
        int area = 0;
        while (i < n) {

            if (st.isEmpty() || hist[st.peek()] <= hist[i]) {
                st.push(i++);
            }
            else {

                tp = st.pop();
                area = hist[tp] * (st.isEmpty() ? i : (i - st.peek() -1));
                if (maxArea < area) {
                    maxArea = area;
                }
            }
        }

        while (!st.isEmpty()) {
            tp = st.pop();
            area = hist[tp] * (st.isEmpty() ? i : (i - st.peek() -1));
            if (maxArea < area) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String[] args)
    {
        int hist[] = { 6, 2, 5, 5,  3, 5, 1, 6, 2, 6, 1, 6, 1 };
        System.out.println("Maximum area is " + getMaxArea(hist, hist.length));
    }
}
