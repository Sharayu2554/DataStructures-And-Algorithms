/**
 * Class to Implement HighestPoints
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/7/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * if there is a point x1 > x2 and y1 > y2 then remove x1,y1 from array
 */
public class HighestPoints {

    public static int[][] getPoints(int in[][]) {

        Arrays.sort(in, (a, b) -> a[0]-b[0]);

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[1]-b[1]));
        q.add(in[0]);
        for (int i =1; i < in.length; i++) {
            while (!q.isEmpty() && in[i][1] > q.peek()[1]) {
                q.poll();
            }
            q.add(in[i]);
        }

        int n = q.size();
        int[][] res = new int[n][2];
        for (int i =0; i < res.length; i++) {
            res[i] = q.poll();
        }
        return res;
    }

    public static void main(String args[]) {
        int[][] in = {
                {1, 1},
                {2, 2},
                {3, 5},
                {4, 3},
                {5, 3},
                {5, 4},
                {6, 4},
                {7, 2}
        };

        int[][] res = getPoints(in);
        for (int i =0; i < res.length; i++) {
            System.out.println(res[i][0] + " , " + res[i][1]);
        }


    }
}
