/**
 * Class to Implement RankedDisjoint
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/3/19
 * Project : BasicDataStructures
 **/

package BasicDataStructures;

public class RankedNode {
    int node;
    RankedNode parent;
    int rank;
    boolean visited;
    int count;

    public RankedNode() {
        node = -1;
        parent = null;
        rank = 0;
        visited = false;
        count = 1;
    }

    public RankedNode(int node) {
        this.node = node;
        parent = this;
        rank = 0;
        visited = false;
        count = 1;
    }

    public RankedNode find() {
        if (this.parent.node == this.node) {
            return this;
        }
        return this.parent.find();
    }

    public int union(RankedNode v) {
        this.visited = true;
        v.visited = true;

        if (this.rank > v.rank) {
            v.parent = this;
            this.count += v.count;
            return this.count;
        }
        else if (this.rank < v.rank) {
            this.parent = v;
            v.count += this.count;
            return v.count;
        }
        else {
            this.rank++;
            v.parent = this;
            this.count += v.count;
            return this.count;
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }
}


