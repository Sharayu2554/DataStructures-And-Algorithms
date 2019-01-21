/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/8/2019
 * Project : BasicDataStructures
 */

package BasicDataStructures;

public class DisjointSet {

    int[] parent;


    /**
     * Intialize parent array with parent[i] = i
     * @param size
     */
    public DisjointSet(int size) {
        parent  = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }
    }

    /**
     * if parent[i] == i then return i
     * else recursively call find function to find parent of parent.
     * This merge and find operation is naive approach, since worst case complexity is O(N)
     *
     * If disjoint set implemented with rank, it can be optimized to O(log N)
     *
     * @param s
     * @return
     */
    public int find(int s) {
        if (parent[s] == s) {
            return s;
        }
        return find(parent[s]);
    }

    public void merge(int u, int v) {
        parent[v] = u;
    }

    public static void main(String args[]) {

        int n =3;
        DisjointSet dsu = new DisjointSet(n);
        System.out.println(" find 3" + dsu.find(3));
        System.out.println(" merge 1. 3 ");
        dsu.merge(1, 3);
        System.out.println(" find 3 " + dsu.find(3));
    }
}
