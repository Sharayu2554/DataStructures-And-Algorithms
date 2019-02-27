/**
 * Class to Implement Queens
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/25/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class Queens {

    public static final int GRID = 8;

    void placeQueens(int row, Integer[] col, ArrayList<Integer[]> res) {

        if (row == GRID) {
            res.add((Integer[]) col.clone());
            return;
        }

        for (int i = 0; i < GRID; i++) {
            if (checkValid(row, i, col)) {
                col[row] = i;
                placeQueens(row + 1, col, res);
            }
        }
    }

    boolean checkValid(int row, int col, Integer[] cols) {

        for (int r = 0; r < row; r++) {
            int c = cols[r];

            if (c == col)
                return false;

            if (Math.abs(c-col) == Math.abs(r-row))
                return false;
        }
        return true;
    }

    ArrayList<Integer[]> NQueens() {
        ArrayList<Integer[]> res = new ArrayList<>();
        Integer[] col = new Integer[GRID];
        placeQueens(0, col, res);
        return res;
    }

    public static void main(String args[]) {
        Queens q = new Queens();
        ArrayList<Integer[]> res =  q.NQueens();
        int count = 0;
        for(Integer[] cols : res) {
            count++;
            System.out.println(count + " : " + Arrays.toString(cols));
        }
    }
}
