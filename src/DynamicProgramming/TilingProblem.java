package DynamicProgramming;

/**
 * Given a “2 x n” board and tiles of size “2 x 1”, count the number of ways to tile the given board using the 2 x 1 tiles.
 * A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
 *
 * Input n = 3
 * Output: 3
 * Explanation:
 * We need 3 tiles to tile the board of size  2 x 3.
 * We can tile the board using following ways
 * 1) Place all 3 tiles vertically.
 * 2) Place first tile vertically and remaining 2 tiles horizontally.
 * 3) Place first 2 tiles horizontally and remaining tiles vertically
 *
 * Input n = 4
 * Output: 5
 * Explanation:
 * For a 2 x 4 board, there are 5 ways
 * 1) All 4 vertical
 * 2) All 4 horizontal
 * 3) First 2 vertical, remaining 2 horizontal
 * 4) First 2 horizontal, remaining 2 vertical
 * 5) Corner 2 vertical, middle 2 horizontal
 *
 * This can be solved by fibonnaci series
 * but if
 *
 * Given a 3 x n board, find the number of ways to fill it with 2 x 1 dominoes.
 */
public class TilingProblem {

    public static int countWays(int n) {
        int []A = new int[n+1];
        int []B = new int[n+1];
        A[0] = 1; A[1] = 0;
        B[0] = 0; B[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            A[i] = A[i - 2] + 2 * B[i - 1];
            B[i] = A[i - 1] + B[i - 2];
        }

        return A[n];
    }

    public static void main (String[] args)
    {
        int n = 4;
        System.out.println(countWays(n));
    }
}
