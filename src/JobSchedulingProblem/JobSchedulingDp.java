/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/8/2019
 * Project : JobSchedulingProblem
 */

package JobSchedulingProblem;

/**
 * Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline.
 * It is also given that every job takes single unit of time, so the minimum possible deadline for any job is 1.
 * How to maximize total profit if only one job can be scheduled at a time.
 */

import BasicDataStructures.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Job implements Comparator<Job> {

    char id;
    Integer profit, deadline;

    public Job() {}

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    public int compare(Job j1, Job j2) {
        return -(j1.profit.compareTo(j2.profit));
    }
}

public class JobSchedulingDp {

    public static int getMaxDeadline(List<Job> arr) {
        int maxDeadline = arr.get(0).deadline;
        int index = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (maxDeadline < arr.get(i).deadline) {
                maxDeadline = arr.get(i).deadline;
                index = i;
            }
        }
        return index;
    }

    /**
     *  first we sort all the jobs in descending order of their profit
     *  then we iterate in that order
     *      for each job , we try to place at its last deadline,
     *      if that is full we substract ant put it in next best free and valid location using
     *      parent concept of disjoint set
     * @param arr
     * @return
     *
     * Running Time is O(N LogN)
     */
    public static int jobScheduling(List<Job> arr) {
        //any of the two ways to pass comparator works
        Collections.sort(arr, (o1, o2) -> { return o2.profit.compareTo(o1.profit); });
        //Collections.sort(arr, new Job());
        DisjointSet dsu = new DisjointSet(getMaxDeadline(arr));

        //find acts as function to find next best free position it can be stored in
        //merge acts as function to update current position with next free position value
        for (Job job : arr) {
            int free = dsu.find(job.deadline);
            if (free > 0) {
                dsu.merge(dsu.find(free -1), free);
                System.out.println("Job: " + job.id + " profit: " + job.profit + " deadline: " + job.deadline);
            }
        }

        return -1;
    }

    public static void main(String args[])
    {
        ArrayList<Job> arr = new ArrayList<Job>();
        arr.add(new Job('a',2,100));
        arr.add(new Job('b',1,19));
        arr.add(new Job('c',2,27));
        arr.add(new Job('d',1,25));
        arr.add(new Job('e',3,15));
        System.out.println("Following jobs need to be "+
                "executed for maximum profit");
        jobScheduling(arr);
    }
}
