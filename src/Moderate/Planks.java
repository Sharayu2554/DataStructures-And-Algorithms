/**
 * Class to Implement Planks
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/9/19
 * Project : Moderate
 **/

package Moderate;

import java.util.ArrayList;
import java.util.Arrays;

public class Planks {

    ArrayList<Integer> countPlanks(int shorter, int longer, int k) {

        ArrayList<Integer> res = new ArrayList();
        if (shorter == longer) {
            res.add(shorter * k);
            return res;
        }
        int sc = k, lc = 0;
        for (int i = 0; i <= k; i++) {
            res.add(shorter * sc + longer * lc);
            sc--;
            lc++;
        }
        return res;
    }

    public static void main(String  args[]){
        Planks p = new Planks();
        System.out.println(Arrays.toString(p.countPlanks(1, 2, 4).toArray()));
    }
}
