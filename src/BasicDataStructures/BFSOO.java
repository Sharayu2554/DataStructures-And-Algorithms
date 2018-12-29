/**
 * This class is taken from Professor Ragavchari balaji
 * Breadth-first search: Object-oriented version
 *
 * @author rbk
 * Version 1.0: 2018/10/16
 */

package BasicDataStructures;

import BasicDataStructures.Graph.Edge;
import BasicDataStructures.Graph.Factory;
import BasicDataStructures.Graph.Vertex;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSOO extends Graph.GraphAlgorithm<BFSOO.BFSVertex> {
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

        public BFSVertex make(Vertex u) {
            return new BFSVertex(u);
        }
    }

    // code to initialize storage for vertex properties is in GraphAlgorithm class
    public BFSOO(Graph g) {
        super(g, new BFSVertex(null));
    }


    // getter and setter methods to retrieve and update vertex properties
    public boolean getSeen(Vertex u) {
        return get(u).seen;
    }

    public void setSeen(Vertex u, boolean value) {
        get(u).seen = value;
    }

    public Vertex getParent(Vertex u) {
        return get(u).parent;
    }

    public void setParent(Vertex u, Vertex p) {
        get(u).parent = p;
    }

    public int getDistance(Vertex u) {
        return get(u).distance;
    }

    public void setDistance(Vertex u, int d) {
        get(u).distance = d;
    }

    public void initialize(Vertex src) {
        for (Vertex u : g) {
            setSeen(u, false);
            setParent(u, null);
            setDistance(u, INFINITY);
        }
        setDistance(src, 0);
    }

    public void setSource(Vertex src) {
        this.src = src;
    }

    public Vertex getSource() {
        return this.src;
    }

    // Visit a node v from u
    void visit(Vertex u, Vertex v) {
        setSeen(v, true);
        setParent(v, u);
        setDistance(v, getDistance(u) + 1);
    }

    public void bfs(Vertex src) {
        setSource(src);
        initialize(src);

        Queue<Vertex> q = new LinkedList<>();
        q.add(src);
        setSeen(src, true);

        while (!q.isEmpty()) {
            Vertex u = q.remove();
            for (Edge e : g.incident(u)) {
                Vertex v = e.otherEnd(u);
                if (!getSeen(v)) {
                    visit(u, v);
                    q.add(v);
                }
            }
        }
    }

    // Run breadth-first search algorithm on g from source src
    public static BFSOO breadthFirstSearch(Graph g, Vertex src) {
        BFSOO b = new BFSOO(g);
        b.bfs(src);
        return b;
    }

    public static BFSOO breadthFirstSearch(Graph g, int s) {
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

        // Call breadth-first search
        BFSOO b = breadthFirstSearch(g, s);

        g.printGraph(false);

        System.out.println("Output of BFS:\nNode\tDist\tParent\n----------------------");
        for (Vertex u : g) {
            if (b.getDistance(u) == INFINITY) {
                System.out.println(u + "\tInf\t--");
            } else {
                System.out.println(u + "\t" + b.getDistance(u) + "\t" + b.getParent(u));
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