/**
 * Class to Implement RoseProblem
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/3/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import BasicDataStructures.RankedNode;

import java.util.HashMap;

/**
 *
 * Reference of question : https://stackoverflow.com/questions/49743078/consecutive-group-with-size-k-in-an-array
 * Question: There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
 * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 *
 * Given an array named flowers consist of numbers from 1 to N. Each number in the array represents the place where the
 * flower will open in that day.
 *
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x,
 * where i and x will be in the range from 1 to N.
 *
 * Given K, find the last day on which there is at least one group of bloomed flowers of size K. Return -1 if no such day is found
 *
 * Example :
 * array : [3,1,5,4,2] ; k = 1
 * day1 ：0 0 1 0 0
 * day2 ：1 0 1 0 0
 * day3 ：1 0 1 0 1
 * day4 ：1 0 1 1 1 >>> Last day on which you can see a group of size k=1
 * day5 ：1 1 1 1 1
 *
 * The answer is day 4 (flower: 1).
 *
 * If k = 2 or 4
 * The answer is -1.
 *
 * If k = 3
 * The answer is day 4 (flowers : 3,4,5).
 *
 * If k = 5
 * The answer is day 5 (flowers : 1,2,3,4,5).
 */
public class RoseProblem {

    public static int findDay(int[] arr, int k){
        RankedNode[] obj = new RankedNode[arr.length +1];
        HashMap<Integer, Integer> kmap = new HashMap<>();
        int ansDay = 0;
        int curDay = 1;

        for (int i = 0; i< arr.length; i++) {
            obj[arr[i]] = new RankedNode(arr[i]);
        }

        RankedNode ru, rv;
        int count = 0;
        for (int i = 0; i< arr.length; i++) {
            ru = obj[arr[i]].find();
            ru.setVisited(true);
            kmap.put(1, kmap.getOrDefault(1, 0)+ 1);
            if (arr[i]-1 > 0 && obj[arr[i]-1].isVisited()) {
                rv = obj[arr[i] -1].find();
                if (!ru.equals(rv)) {
                    kmap.put(ru.getCount(), kmap.getOrDefault(ru.getCount(), 0) -1);
                    kmap.put(rv.getCount(), kmap.getOrDefault(rv.getCount(), 0) -1);
                    count = ru.union(rv);
                    kmap.put(count, kmap.getOrDefault(count, 0) +1);
                }            }
            if (arr[i] +1 < arr.length + 1 && obj[arr[i] +1].isVisited()) {
                rv = obj[arr[i] +1].find();
                if (!ru.equals(rv)) {
                    kmap.put(ru.getCount(), kmap.getOrDefault(ru.getCount(), 0) -1);
                    kmap.put(rv.getCount(), kmap.getOrDefault(rv.getCount(), 0) -1);
                    count = ru.union(rv);
                    kmap.put(count, kmap.getOrDefault(count, 0) +1);
                }
            }
            if (kmap.getOrDefault(k, 0) > 0) {
                ansDay = curDay;
            }

            curDay += 1;
        }
        return ansDay;
    }

    public static void main(String args[]) {
        int[] arr = {3, 1, 5, 4, 2};
        int k = 5;
        System.out.println(" "  + findDay(arr, k));
    }

}
