/**
 * Class to Implement FinishJobInTime
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/29/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given an array of jobs with different time requirements. There are K identical assignees available and we are also
 * given how much time an assignee takes to do one unit of the job. Find the minimum time to finish all jobs with following
 * constraints.
 *
 * An assignee can be assigned only contiguous jobs. For example, an assignee cannot be assigned jobs 1 and 3, but not 2.
 * Two assignees cannot share (or co-assigned) a job, i.e., a job cannot be partially assigned to one assignee and
 * partially to other.
 *
 * K:     Number of assignees available.
 * T:     Time taken by an assignee to finish one unit
 *        of job
 * job[]: An array that represents time requirements of
 *        different jobs.
 *
 * Input:  k = 2, T = 5, job[] = {4, 5, 10}
 * Output: 50
 * The minimum time required to finish all the jobs is 50.
 * There are 2 assignees available. We get this time by
 * assigning {4, 5} to first assignee and {10} to second
 * assignee.
 *
 * Input:  k = 4, T = 5, job[] = {10, 7, 8, 12, 6, 8}
 * Output: 75
 * We get this time by assigning {10} {7, 8} {12} and {6, 8}
 */
public class FinishJobInTime {

    public static int getMax(int[] jobs, int N) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < jobs[i]) {
                max= jobs[i];
            }
        }
        return max;
    }

    public static boolean check(int min, int[] jobs, int N, int K) {

        int res = 0;
        int cnt = 0;
        for (int i = 0; i< N;) {
            if (res + jobs[i] > min) {
                res = 0;
                cnt++;
            }
            else {
               res += jobs[i];
               i++;
            }
        }
        System.out.print(cnt);
        return cnt < K;
    }

    public static int findMinTime(int[] jobs, int K, int T, int N) {

        int start = 0, end = 0;
        int max = getMax(jobs, N);
        for (int i =0; i < N; i++)
            end += jobs[i];

        int mid = 0, ans = end;
        while (start <= end) {

            mid = (start + end)/2;
            System.out.println(start + " " + mid + " " + end + " ");
            if (mid >= max && check(mid, jobs, N, K)) {
                ans = Math.min(ans, mid);
                end = mid -1;
            }
            else {
                start = mid +1;
            }
            System.out.print( " "+ ans + "\n");
        }
        return ans * T;
    }

    public static void main(String args[]) {
        int[] jobs ={10, 7, 8, 12, 6, 8};
        System.out.println(" Result " + findMinTime(jobs, 4, 5, jobs.length)); //jobs, k, t
    }
}
