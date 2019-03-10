/**
 * Class to Implement PairSum
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/10/19
 * Project : Moderate
 **/

package Moderate;

import java.util.HashMap;

public class PairSum {

    void pairSum(int[] a, int v) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++){
            int c = v - a[i];
            if (m.containsKey(a[i])) {
                int val  = m.get(a[i]);
                val = val -1;
                if (val != 0) {
                    m.put(c, val);
                }
                else {
                    m.remove(c);
                }
                System.out.println("Pair : " + a[i] + " " + c);
            }
            else {
                m.put(c, 1);
            }
        }
    }

    public static void main(String args[]) {
        PairSum p = new PairSum();
        int[] data = {14, 5, 13, 6, -2, -1, 7, 9, 0, 3};
        p.pairSum(data, 12);
    }
}
