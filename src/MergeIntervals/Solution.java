/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/2/2019
 * Project : MergeIntervals
 */

package MergeIntervals;

import java.util.*;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

class Solution {


     public static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }

         @Override
         public String toString() {
             return "start=" + start +
                     ", end=" + end;
         }
     }


    public List<Interval> merge(List<Interval> intervals) {

        Collections.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
        LinkedList<Integer> d = new LinkedList<>();

        System.out.println(Arrays.toString(intervals.toArray()));

        List<Interval> out = new ArrayList<>();
        Interval prev = null;
        for (Interval in: intervals) {
            if (prev == null) {
                prev = in;
            }
            else {
                if (prev.end < in.start) {
                    out.add(prev);
                    prev = in;
                }
                else {
                    prev.end = in.end;
                }
            }
        }
        if (prev != null) {
            out.add(prev);
        }
        return out;
    }

    public static void main(String args[]) {
        List<Interval>  in = new LinkedList<>();
        in.add(new Interval(1, 4));
        in.add(new Interval(0, 4));

        Solution s = new Solution();
        List<Interval> out = s.merge(in);
        System.out.println(Arrays.toString(out.toArray()));
    }
}