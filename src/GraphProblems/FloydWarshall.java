/**
 * Class to Implement FloydWarshall
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/5/19
 * Project : GraphProblems
 **/

package GraphProblems;

/**
 * This is very similar to Dijktras and Bellman Ford
 * But
 * 1. For a graph with no negative weights, we can do better and calculate single source shortest distances in O(E + VLogV)
 * time using Dijkstra’s algorithm
 * 2. For a general weighted graph, we can calculate single source shortest distances in O(VE) time using Bellman–Ford Algorithm.
 * 3. The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem.
 * The problem is to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
 * 4.Can we do even better for Directed Acyclic Graph (DAG)?
 * We can calculate single source shortest distances in O(V+E) time for DAGs. The idea is to use Topological Sorting.
 */
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
