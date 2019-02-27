/**
 * Class to Implement RouteBtwNodes
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/19/19
 * Project : GraphProblems
 **/

package GraphProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RouteBtwNodes {

    enum State { Unvisited, Visited, Visiting ;}

    class Node {
        int ele;
        LinkedList<Node> adjList;
        State state;

        public Node(int ele) {
            this.ele = ele;
            adjList = new LinkedList<>();
            state = State.Unvisited;
        }
    }

    class Graph {
        ArrayList<Node> nodes;
        int V;
        boolean isDirected;


        public Graph(int N) {
            this.V = N;
            nodes = new ArrayList<>(N);
            for (int i =0; i < N; i++) {
                nodes.add(i , new Node(i));
            }
        }

        public void addEdge(int from, int to) {

            if (from < 0 && from >= V && to < 0 && to >= V) {
                System.out.println("Vertex doesnt exists");
                return;
            }

            Node node = nodes.get(from);
            node.adjList.add(nodes.get(to));

            if (!isDirected) {
                node = nodes.get(to);
                node.adjList.add(nodes.get(from));
            }
        }

        public List<Node> getAdjacentNodes(int u) {
            return nodes.get(u).adjList;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void initializeNodes() {
            for (Node node : nodes) {
                node.state = State.Unvisited;
            }
        }

    }

    public void search(Graph g, Node start, Node end) {
        g.initializeNodes();


    }

    public static void main(String args[]) {

    }
}
