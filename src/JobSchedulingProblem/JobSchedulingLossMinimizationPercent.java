/**
 * Class to Implement JobSchedulingLossMinimizationPercent
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/28/19
 * Project : JobSchedulingProblem
 **/

package JobSchedulingProblem;

import java.lang.invoke.VolatileCallSite;
import java.util.Arrays;
import java.util.Collections;

class LossMinJobPer {
    int id;
    int vol; //volume vi
}

/**
 * We are given a sequence of N goods of production numbered from 1 to N. Each good has a volume denoted by (Vi).
 * The constraint is that once a good has been completed its volume starts decaying at a fixed percentage (P) per day.
 * All goods decay at the same rate and further each good take one day to complete.
 *
 * Input: 4, 2, 151, 15, 1, 52, 12 and P = 10%
 * Output: 222.503
 *
 */
public class JobSchedulingLossMinimizationPercent {

    public static double lossMinimization(Integer[] volumes, double percent) {
        //Take Higher values last and smaller values first in production
        //Good Produuction Day i : ((1-p) ^ (N -i)) * Vi
        double result = 0;

        Arrays.sort(volumes);
        System.out.println("  " + Arrays.toString(volumes));
        for (int i = volumes.length; i >=1; i--) {
            result += Math.pow(1 - percent, volumes.length - i) * volumes[i -1];
        }
        return result;
    }

    public static void main(String args[]) {
        Integer[] vols = {4, 2, 151, 15, 1, 52, 12};
        System.out.println("Minimized Loss : " + lossMinimization(vols, 0.10));
    }
}
