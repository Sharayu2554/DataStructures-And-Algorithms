/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 12/29/2018
 * Project : DAGProblems
 */

package DAGProblems;

import BasicDataStructures.Graph;
import BasicDataStructures.Graph.Factory;
import BasicDataStructures.Graph.Vertex;
import BasicDataStructures.Graph.GraphAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * TODO :
 * 0. Detect Cycle in DAG - Done  (In Topological Order )
 * 1. Find Topological Order in DAG -Done
 * 2. Count Number of Paths in DAG
 * 3. Print All Paths in DAG
 * 4. Find all Shortest Path in unWeighted DAG
 * 5. Find all Shortest Path in Weighted DAG
 * 6. Find Longest Path in DAG
 * 7. Find total Number of components - Done
 * 8. Find all Strongly Connected Components
 * 9. Implement PERT/CPM
 * 10. Euler Circuit in DAG : Eulerian Path is a path in graph that visits every edge exactly once. Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 * 11. Shortest Path with Exactly K Edges in  DAG and Weighted Graph
 * 12. MultiSource Shortets Path in UnWeighted Graph
 * 13. Find if there is path between two vertices in a DAG
 * 14. Johnson’s algorithm for All-pairs shortest paths
 */
public class DAG extends GraphAlgorithm<DAGVertex> {

    /**
     * @param g
     * @param: a graph g and a sample node vf used to create additional nodes using the Factory interface
     */
    public DAG(Graph g) {
        super(g, new DAGVertex( null ));
    }

    public DAG(Graph g, Factory vf) {
        super(g, vf);
    }


    public void DFS_VISIT(DAGVertex dv, int cno, LinkedList<DAGVertex>  tologyOrder) {
        dv.seen  = true;
        dv.color = "GREY";
        dv.cno = cno;

        for (Graph.Edge e : g.incident(dv.u)) {
            DAGVertex v  = get(e.otherEnd(dv.u));
            if (v.color.equals("WHITE")) {
                v.parent = dv;
                DFS_VISIT(v, cno, tologyOrder);
            }
            else if (v.color.equals("GREY")) {
                System.out.println(dv + "Cycle Exists in DAG " + v);
            }

        }
        tologyOrder.addFirst(dv);
        dv.color = "BLACK";
    }

    public void topologicalOrderInDAG() {

        LinkedList<DAGVertex> tologyOrder = new LinkedList();
        int cno = 0;
        for (Vertex v : g) {
            DAGVertex dv = get(v);
            if (dv.color.equals("WHITE") && dv.u.inDegree() == 0) {
                DFS_VISIT(dv, ++cno, tologyOrder);
            }
        }

        if (tologyOrder.isEmpty() || tologyOrder.size() < g.size()) {
            System.out.println("Cycle Exisits");
        }
        System.out.println(" Topological Order :  " + Arrays.toString(tologyOrder.toArray()));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        int choice = 2;
        if (args.length == 0 || args[0].equals("-")) {
            in = new Scanner(System.in);
        } else {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        }

        if (args.length > 1) {
            choice = Integer.parseInt(args[1]);
        }

        Graph g = Graph.readGraph(in, true);
        Vertex s = g.getVertex(1);

        DAG m = new DAG(g);
        m.topologicalOrderInDAG();
    }
}
