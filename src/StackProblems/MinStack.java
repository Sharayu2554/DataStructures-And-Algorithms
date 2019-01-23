/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/21/2019
 * Project : StackProblems
 */

package StackProblems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a stack that supports getMin() in O(1) time and O(1) extra space
 *
 * Approach one : two stacks but extra space  of O(N)
 *
 * Approach two : . We define a variable minEle that stores the current minimum element in the stack.
 * Now the interesting part is, how to handle the case when minimum element is removed.
 * To handle this, we push “2x – minEle” into the stack instead of x so that previous minimum element can be retrieved
 * using current minEle and its value stored in stack. Below are detailed steps and explanation of working.
 *
 * Push(x) : Inserts x at the top of stack.
 *
 * If stack is empty, insert x into the stack and make minEle equal to x.
 * If stack is not empty, compare x with minEle. Two cases arise:
 * If x is greater than or equal to minEle, simply insert x.
 * If x is less than minEle, insert (2*x – minEle) into the stack and make minEle equal to x.
 * For example, let previous minEle was 3. Now we want to insert 2. We update minEle as 2 and insert 2*2 – 3 = 1 into the stack.
 *
 * Pop() : Removes an element from top of stack.
 *
 * Remove element from top. Let the removed element be y. Two cases arise:
 * If y is greater than or equal to minEle, the minimum element in the stack is still minEle.
 * If y is less than minEle, the minimum element now becomes (2*minEle – y), so update (minEle = 2*minEle – y).
 * This is where we retrieve previous minimum from current minimum and its value in stack.
 * For example, let the element to be removed be 1 and minEle be 2. We remove 1 and update minEle as 2*2 – 1 = 3.
 *
 * Stack doesn’t hold actual value of an element if it is minimum so far.
 * Actual minimum element is always stored in minEle
 *
 * Proof :
 * How 2*x - minEle is less than x in push()?
 * x < minEle which means x - minEle < 0
 *
 * // Adding x on both sides
 * x - minEle + x < 0 + x
 *
 * 2*x - minEle < x
 *
 * We can conclude 2*x - minEle < new minEle
 *
 * While Popping :
 *
 * How previous minimum element, prevMinEle is, 2*minEle - y
 * in pop() is y the popped element?
 *
 *  // We pushed y as 2x - prevMinEle. Here
 *  // prevMinEle is minEle before y was inserted
 *  y = 2*x - prevMinEle
 *
 *  // Value of minEle was made equal to x
 *  minEle = x .
 *
 *  new minEle = 2 * minEle - y
 *             = 2*x - (2*x - prevMinEle)
 *             = prevMinEle // This is what we wanted
 *
 * Logic Referred from (Reference) :  https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
 */
public class MinStack {

    Deque<Integer> st;
    Integer minEle;

    public MinStack() {
        st = new LinkedList<>();
        minEle = null;
    }

    public void push(Integer e) {
        if (minEle == null) {
            minEle = e;
            st.push(e);
        }
        //e is smaller than or equal to minEle
        else if( e < minEle ) {
            st.push(2 * e - minEle);
            minEle = e;
        }
        //e is greater than minEle
        else{
            st.push(e);
        }
    }

    public Integer pop() {
        if (minEle == null || st.isEmpty()) {
            return null;
        }
        Integer val = st.pop();
        System.out.println(val);
        if (val >= minEle) {
            return val;
        }
        else {
            int temp = minEle;
            minEle = 2 * minEle - val;
            return temp;
        }
    }

    public Integer getMin() {
        return minEle;
    }

    public Integer peek() {
        if (minEle == null || st.isEmpty()) {
            return null;
        }
        Integer val = st.peek();
        if (val >= minEle) {
            return val;
        }
        else {
            int temp = minEle;
            minEle = 2 * minEle - val;
            return temp;
        }
    }

    public static void main(String args[]) {
        MinStack s = new MinStack();
        s.push(3);
        s.push(5);
        System.out.println(" Data : " + Arrays.toString(s.st.toArray()));
        Integer t = s.getMin();
        System.out.println(" Min : " + t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
        s.push(2);
        s.push(1);
        System.out.println(" Data :  " + Arrays.toString(s.st.toArray()));
        t = s.getMin();
        System.out.println(" Min : " +t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
        t = s.pop();
        System.out.println(" Pop : " +  t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
        t = s.getMin();
        System.out.println(" Min : " +t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
        t = s.pop();
        System.out.println(" Pop : " + t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
        t =  s.peek();
        System.out.println(" Peek  : " +t);
        System.out.println(" " + Arrays.toString(s.st.toArray()));
    }
}