/**
 * Class to Implement Bipartite
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/3/19
 * Project : GraphProblems
 **/

package GraphProblems;

import java.util.ArrayDeque;
import java.util.Deque;

public class Bipartite {

    public static boolean isBipartite(int[][] G, int src) {
        int[] color = new int[G.length];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(src);
        color[src] = -1;

        int curColor = 1;
        while(!q.isEmpty()) {
            int u = q.removeFirst();
            if (G[u][u] == 1 )
                return false;

            for (int v =0; v < G.length; v++) {

                if (G[u][v] == 1 && color[v] == 0) {
                    color[v] = curColor;
                    q.add(v);
                }

                else if (G[u][v] == 1 && color[v] == color[u])
                    return false;
            }
            curColor = -curColor;
        }
        return true;
    }

    public static void main(String args[]) {
        int[][] G = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        System.out.println(" IsBipartite " + isBipartite(G, 0));
    }
}
