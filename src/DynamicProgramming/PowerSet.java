/**
 * Class to Implement PowerSet
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/22/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class PowerSet {

    public ArrayList<ArrayList<Integer>> powerSet(int[] arr) {
        ArrayList<ArrayList<Integer>> sol = new ArrayList<>(2 ^ arr.length);
        sol.add(new ArrayList<>());
        for (int i =0; i < arr.length; i++) {
            ps(sol, arr[i]);
        }
        return sol;
    }

    public void ps(ArrayList<ArrayList<Integer>> in, int a) {
        ArrayList<ArrayList<Integer>> sol = new ArrayList<>();
        for (ArrayList cur : in) {
            ArrayList<Integer> p = (ArrayList<Integer>) cur.clone();
            p.add(a);
            sol.add(p);
        }
        in.addAll(sol);
        return;
    }

    public static void main(String args[]) {

        PowerSet p = new PowerSet();
        int[] arr =  {1, 2, 3, 4};
        ArrayList<ArrayList<Integer>> sol = p.powerSet(arr);
        for (ArrayList<Integer>  c : sol) {
            System.out.println(Arrays.toString(c.toArray()));
        }
    }
}
