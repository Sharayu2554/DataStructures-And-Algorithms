/**
 * Class to Implement FloydWarshall
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/5/19
 * Project : GraphProblems
 **/

package GraphProblems;

public class FloydWarshall {

    static final int INF = 99999;

    static boolean negCyclefloydWarshall(int graph[][]) {
        int[][] dist = new int[graph.length][graph.length];
        int V = graph.length;

        for (int i =0; i < V; i++ ) {
            for (int j =0; j< V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k =0; k< V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j =0; j < V; j++) {
                    if (dist[i][j] > dist[i][k] + graph[k][j]) {
                        dist[i][j] = dist[i][k] + graph[k][j];
                    }
                }
            }
        }

        // If distance of any verex from itself
        // becomes negative, then there is a negative
        // weight cycle.
        for (int i=0; i< V; i++ ) {
            if (dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
    // Driver code
    public static void main (String[] args)
    {

    /* Let us create the following weighted graph
                1
        (0)----------->(1)
        /|\               |
         |               |
      -1 |               | -1
         |                \|/
        (3)<-----------(2)
            -1     */

        int graph[][] = {
                {0, 1, INF, INF},
                {INF, 0, -1, INF},
                {INF, INF, 0, -1},
                {-1, INF, INF, 0}
        };

        if (negCyclefloydWarshall(graph))
            System.out.print("Yes");
        else
            System.out.print("No");
    }

}
