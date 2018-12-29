/**
 *
 *  This class is taken from Professor Ragavchari balaji
 *  Breadth-first search
 *  @author rbk
 *  Version 1.0: 2018/10/16
 */

package BasicDataStructures;

import BasicDataStructures.Graph.Edge;
import BasicDataStructures.Graph.Factory;
import BasicDataStructures.Graph.Vertex;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS extends Graph.GraphAlgorithm<BFS.BFSVertex> {
    public static final int INFINITY = Integer.MAX_VALUE;
    Vertex src;

    // Class to store information about vertices during BFS
    public static class BFSVertex implements Factory {
        boolean seen;
        Vertex parent;
        int distance;  // distance of vertex from source
        public BFSVertex(Vertex u) {
            seen = false;
            parent = null;
            distance = INFINITY;
        }
        public BFSVertex make(Vertex u) { return new BFSVertex(u); }
    }

    // code to initialize storage for vertex properties is in GraphAlgorithm class
    public BFS(Graph g) {
        super(g, new BFSVertex(null));
    }

    public void bfs(Vertex src) {
        this.src = src;
        for(Vertex u: g) {
            get(u).seen = false;
            get(u).parent = null;
            get(u).distance = INFINITY;
        }
        get(src).distance = 0;

        Queue<Vertex> q = new LinkedList<>();
        q.add(src);
        get(src).seen = true;

        while(!q.isEmpty()) {
            Vertex u = q.remove();
            for(Edge e: g.incident(u)) {
                Vertex v = e.otherEnd(u);
                if(!get(v).seen) {
                    get(v).seen = true;
                    get(v).parent = u;
                    get(v).distance = get(u).distance + 1;
                    q.add(v);
                }
            }
        }
    }

    // Run breadth-first search algorithm on g from source src
    public static BFS breadthFirstSearch(Graph g, Vertex src) {
        BFS b = new BFS(g);
        b.bfs(src);
        return b;
    }

    public static BFS breadthFirstSearch(Graph g, int s) {
        return breadthFirstSearch(g, g.getVertex(s));
    }

    public static void main(String[] args) throws Exception {
        String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 -7   6 7 -1   7 6 -1 1";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
        // Read graph from input
        Graph g = Graph.readDirectedGraph(in);
        int s = in.nextInt();

        // Create an instance of BFS and run bfs from source s
        BFS b = breadthFirstSearch(g, s);

        g.printGraph(false);

        System.out.println("Output of BFS:\nNode\tDist\tParent\n----------------------");
        for(Vertex u: g) {
            if(b.get(u).distance == INFINITY) {
                System.out.println(u + "\tInf\t--");
            } else {
                System.out.println(u + "\t" + b.get(u).distance + "\t" + b.get(u).parent);
            }
        }
    }
}

/* Sample run:
______________________________________________
Graph: n: 7, m: 8, directed: true, Edge weights: false
1 :  (1,2) (1,3)
2 :  (2,4)
3 :  (3,4)
4 :  (4,5)
5 :  (5,1)
6 :  (6,7)
7 :  (7,6)
______________________________________________
Output of BFS:
Node	Dist	Parent
----------------------
1	0	null
2	1	1
3	1	1
4	2	2
5	3	4
6	Inf	--
7	Inf	--
*/