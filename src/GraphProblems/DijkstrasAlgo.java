/**
 * Class to Implement DijkstrasAlgo
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/5/19
 * Project : GraphProblems
 **/

package GraphProblems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Class to represent a node in the graph
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}

public class DijkstrasAlgo {

    private int dist[];
    private int V;

    public DijkstrasAlgo(int V) {
        this.V = V;
        dist = new int[V];
        for (int i =0; i< V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
    }

    /**
     * Assumption : Graph does not have negative cycle
     * Works in Weighted Directed OR UnDirected Graph
     * @param adj
     * @param src
     */
    public void dijkstra(List<List<Node>> adj , int src) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());
        pq.add(new Node(src, 0));
        dist[src] = 0;

        int count = 0;
        while(!pq.isEmpty() && count < V) {
            Node node = pq.poll();
            visited[node.node] = true;
            count++;
            for (Node v: adj.get(node.node)) {
                if (!visited[v.node]) {
                    if (dist[node.node] != Integer.MAX_VALUE && dist[v.node] > dist[node.node] + v.cost) {
                        dist[v.node] = dist[node.node] + v.cost;
                        pq.add(v);
                    }
                }
            }
        }
    }

    public static void main(String arg[])
    {
        int V = 9;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 4));
        adj.get(0).add(new Node(7, 8));

        adj.get(1).add(new Node(0, 4));
        adj.get(1).add(new Node(7, 8));
        adj.get(1).add(new Node(2, 8));

        adj.get(2).add(new Node(1, 8));
        adj.get(2).add(new Node(3, 7));
        adj.get(2).add(new Node(5, 4));
        adj.get(2).add(new Node(8, 2));

        adj.get(3).add(new Node(2, 7 ));
        adj.get(3).add(new Node(4, 9));
        adj.get(3).add(new Node(5, 14));

        adj.get(4).add(new Node(3, 9 ));
        adj.get(4).add(new Node(5, 10 ));

        adj.get(5).add(new Node(4,10));
        adj.get(5).add(new Node(3, 14));
        adj.get(5).add(new Node(2, 4 ));
        adj.get(5).add(new Node(6, 2 ));

        adj.get(6).add(new Node(5, 2));
        adj.get(6).add(new Node(7, 1));
        adj.get(6).add(new Node(8, 6));

        adj.get(7).add(new Node(0, 8));
        adj.get(7).add(new Node(1, 11));
        adj.get(7).add(new Node(6, 1));
        adj.get(7).add(new Node(8, 7));

        adj.get(8).add(new Node(2, 2));
        adj.get(8).add(new Node(6, 6));
        adj.get(8).add(new Node(7, 7));

        // Calculate the single source shortest path
        DijkstrasAlgo dpq = new DijkstrasAlgo(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}
