/**
 * Class to implement Vertex for DAG
 *
 * @author Sharayu Sharad Mantri
 * Date : 12/29/2018
 * Project : DAGProblems
 */

package DAGProblems;

import BasicDataStructures.Graph.Factory;
import BasicDataStructures.Graph.Vertex;

import java.util.Comparator;

public class DAGVertex implements Comparable<DAGVertex>, Factory {

    Vertex u;
    String color = "WHITE"; //color while getting order
    boolean seen  = false;
    DAGVertex parent = null; //parent of the vertex in DAG
    int distance = 0; //distance from root or source
    Comparator com;
    int cno = 0; //component number
    int cPath = 0; //count nuber of paths from source

    public DAGVertex(Vertex u) {
        this.u = u;
        this.color = "WHITE";
        this.seen = false;
        this.parent = null;
        this.distance = 0;
        this.com = null;
        cno = 0;
        cPath = 0;
    }

    public DAGVertex(Vertex u, Comparator com) {
        this.u = u;
        this.seen = false;
        this.color = "WHITE";
        this.parent = null;
        this.distance = 0;
        this.com = com;
        this.cno = 0;
        this.cPath = 0;
    }

    public DAGVertex(Vertex u, boolean seen, DAGVertex parent, int distance) {
        this.u = u;
        this.seen = seen;
        this.parent = parent;
        this.distance = distance;
    }

    @Override
    public Factory make(Vertex u) {
        return new DAGVertex(u);
    }

    public Factory make(Vertex u, Comparator com) {
        return new DAGVertex(u, com);
    }

    @Override
    public int compareTo(DAGVertex o) {
        return com.compare(this.distance, o.distance);
    }

    @Override
    public String toString() {
        return "DAGVertex{" +
                "u=" + u +
                ", parent=" +( parent == null ? null : parent.u )+
                ", distance=" + distance +
                ", cno=" + cno +
                ", cPath=" + cPath +
                '}';
    }

    public String toStringPath() {
        return "DAGVertex{" +
                "u=" + u +
                ", parent=" + parent +
                ", distance=" + distance +
                '}';
    }

    public String toStringU() {
        return u.toString();
    }
}
