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

public class DAGVertex<T> implements Comparable<DAGVertex>, Factory {

    Vertex u;
    String color;
    boolean seen;
    DAGVertex parent;
    T distance;
    Comparator<T> com;
    int cno;

    public DAGVertex(Vertex u) {
        this.u = u;
        this.color = "WHITE";
        this.seen = false;
        this.parent = null;
        this.distance = null;
        this.com = null;
        cno = 0;
    }

    public DAGVertex(Vertex u, Comparator com) {
        this.u = u;
        this.seen = false;
        this.color = "WHITE";
        this.parent = null;
        this.distance = null;
        this.com = com;
        this.cno = 0;
    }

    public DAGVertex(Vertex u, boolean seen, DAGVertex parent, T distance) {
        this.u = u;
        this.seen = seen;
        this.parent = parent;
        this.distance = distance;
    }

    @Override
    public Factory make(Vertex u) {
        return new DAGVertex<T>(u);
    }

    public Factory make(Vertex u, Comparator<T> com) {
        return new DAGVertex<T>(u, com);
    }

    @Override
    public int compareTo(DAGVertex o) {
        return com.compare(this.distance, (T) o.distance);
    }

    @Override
    public String toString() {
        return "DAGVertex{" +
                "u=" + u +
                ", cno=" + cno +
                '}';
    }
}
