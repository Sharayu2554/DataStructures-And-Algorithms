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

    public DisjointSet(int size) {
        parent  = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }
    }

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
