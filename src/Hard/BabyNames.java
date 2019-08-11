/**
 * Class to Implement BabyNames
 *
 * @author Sharayu Sharad Mantri
 * Date : 6/3/19
 * Project : Hard
 **/

package Hard;

import java.util.HashMap;
import java.util.HashSet;

public class BabyNames {

    HashMap<String, Node> graph;

    class Node {
        String name;
        int rank;
        int fre;
        Node parent;
    }

    public Node find(String u) {

        Node n = graph.get(u);
        if (n.parent == null) {
            return n;
        }
        return find(n.parent.name);
    }

    public void union(Node u, Node v) {

        if (u.rank < v.rank) {
            u.parent = v;
            v.fre += u.fre;
        }
        else if (u.rank > v.rank) {
            v.parent = u;
            u.fre += v.fre;
        }
        else {
            u.rank++;
            v.parent = u;
            u.fre += v.fre;
        }
    }

    public void process(HashMap<String, Integer> names, String[][] syn) {

        graph = new HashMap<>();
        HashSet<Node> res = new HashSet<>();
        for (String name : names.keySet()) {
            Node n = new Node();
            n.name = name;
            n.fre = names.get(name);
            n.parent = null;
            graph.put(name, n);
        }

        for (int i =0; i < syn.length; i++) {
            if (!names.containsKey(syn[i][0]) || !names.containsKey(syn[i][1])) {
                continue;
            }

            Node u = find(syn[i][0]);
            Node v = find(syn[i][1]);
            union(u, v);
        }

        for (String name: names.keySet()) {
            Node u = find(name);
            res.add(u);
        }

        for (Node u : res) {
            System.out.println(u.name + " " + u.fre);
        }
    }

    public static void main(String args[]) {
        HashMap<String, Integer> names = new HashMap<>();
        String[][] syn = new String[4][2];
        names.put("John", 15);
        names.put("Jon", 12);
        names.put("Chris", 13);
        names.put("Kris", 4);
        names.put("Christopher", 19);

        syn[0][0] = "John";
        syn[0][1] = "Jon";

        syn[1][0] = "John";
        syn[1][1] = "Johnny";

        syn[2][0] = "Chris";
        syn[2][1] = "Kris";

        syn[3][0] = "Kris";
        syn[3][1] = "Christopher";

        BabyNames b = new BabyNames();
        b.process(names, syn);
    }
}
