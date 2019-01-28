/**
 * Class to Implement JobSchedulingLossMinimization
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/28/19
 * Project : JobSchedulingProblem
 **/

package JobSchedulingProblem;

import java.util.Arrays;
import java.util.Comparator;

class LossMinJob{
    int id;
    int loss;
    int time;

    public LossMinJob(int id, int loss, int time) {
        this.id = id;
        this.loss = loss;
        this.time = time;
    }
}

/**
 * We are given N jobs numbered 1 to N. For each activity, let Ti denotes the number of days required to complete the job.
 * For each day of delay before starting to work for job i, a loss of Li is incurred.
 * We are required to find a sequence to complete the jobs so that overall loss is minimized. We can only work on one job at a time.
 *
 * If multiple such solutions are possible, then we are required to give the lexicographically least permutation (i.e earliest in dictionary order).
 */
public class JobSchedulingLossMinimization {

    public static int lossIncurred(int[] L, int[] T) {

        //We select all the jobs first which takes less time to finish and have high Losses
        //so we select jobs which have HIgh Li/Ti values (descending order)
        //To get most accurate result avoid dividing Li by Ti.
        //Instead, compare the two ratios like fractions. To compare a/b and c/d, compare ad and bc.
        LossMinJob[] jobs = new LossMinJob[L.length];
        for (int i =0; i < L.length; i++) {
            jobs[i] =  new LossMinJob(i+1, L[i], T[i]);
        }

        Arrays.sort(jobs, new Comparator<LossMinJob>(){

            @Override
            public int compare(LossMinJob j1, LossMinJob j2) {
                int val = j1.loss * j2.time - j2.loss * j1.time;
                if (val > 0) {
                    return -1;
                }
                else if (val == 0) {
                    return 0;
                }
                return 1;
            }
        });

        System.out.print("Jobs : ");
        for (int i = 0; i < jobs.length; i++) {
            System.out.print(" " + jobs[i].id + " , ");
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] L = { 3, 1, 2, 4}; //incurred loss (For each day of delay before starting to work for job i, a loss of Li is incurred.)
        int[] T = { 4, 1000, 2, 5}; //Number of Days Required to complete the job
        lossIncurred(L, T);

    }
}
