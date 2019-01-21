/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/8/2019
 * Project : JobSchedulingProblem
 */

package JobSchedulingProblem;

import java.util.Arrays;
/**
 * Given set of jobs with start and end interval and profit, how to maximize profit such that
 * jobs in subset do not overlap.
 */

class WeightJob{

    Integer start;
    Integer end;
    Integer profit;

    WeightJob(int start,int end,int profit){
        this.start= start;
        this.end = end;
        this.profit= profit;
    }
}

public class WeightedJobScheduling {

    public WeightedJobScheduling() {}
    public boolean doesntOverlap(WeightJob i, WeightJob j) {
        if (i.end > j.start) {
            return false;
        }
        return true;
    }

    public int getMaxProdut(WeightJob jobs[]) {

        Arrays.sort(jobs, (o1, o2) -> { return o1.end.compareTo(o2.end); });
        int[] profits = new int[jobs.length];
        for (int  i = 0; i < jobs.length; i++) {
            profits[i] =  jobs[i].profit;
        }

        for (int j = 1; j < jobs.length; j++) {
            for (int  i = 0; i < j; i++) {
                if (doesntOverlap(jobs[i], jobs[j]) && profits[j] < jobs[j].profit + profits[i]) {
                    profits[j] = jobs[j].profit + profits[i];
                }
            }
        }

        //find max profit
        int max = profits[0];
        int index  = 0;
        for (int i = 1; i< profits.length; i++)
            if (max < profits[i]) {
                index = i;
                max = profits[i];
            }
        return max;
    }

    public static void main(String args[]){
        WeightJob jobs[] = new WeightJob[6];
        jobs[0] = new WeightJob(1,3,5);
        jobs[1] = new WeightJob(2,5,6);
        jobs[2] = new WeightJob(4,6,5);
        jobs[3] = new WeightJob(6,7,4);
        jobs[4] = new WeightJob(5,8,11);
        jobs[5] = new WeightJob(7,9,2);
        WeightedJobScheduling wj = new WeightedJobScheduling();
        System.out.println(" MAX PROFIT MADE IS " + wj.getMaxProdut(jobs));
    }
}
