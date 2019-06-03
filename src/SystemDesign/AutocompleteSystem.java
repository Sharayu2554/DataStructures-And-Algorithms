/**
 * Class to Implement AutocompleteSystem
 *
 * @author Sharayu Sharad Mantri
 * Date : 5/2/19
 * Project : SystemDesign
 **/

package SystemDesign;

import java.util.*;

class AutocompleteSystem {

    Trie dic;
    String prefix;
    Node cur;

    public  AutocompleteSystem() {
        dic = new Trie();
    }

    //min heap on pair
    class Pair implements Comparable<Pair> {
        int r;
        String s;

        public Pair(int _r, String _s) {
            r = _r;
            s = _s;
        }

        @Override
        public int compareTo(Pair q) {
            if (this.r != q.r) {
                return this.r - q.r;
            }

            return this.s.compareTo(q.s);
        }
    }

    class Node {
        Character c;
        PriorityQueue<Pair> rate;
        HashMap<Character, Node> child;

        public Node(Character _c, Pair p) {
            c = _c;
            if (rate.size() < 3) {
                rate.add(p);
            }
            else if (rate.peek().compareTo(p) < 0) {
                rate.poll();
                rate.add(p);
            }
        }

        public void add(Pair p) {

            if (rate.size() < 3) {
                rate.add(p);
            }
            else if (rate.peek().compareTo(p) < 0) {
                rate.poll();
                rate.add(p);
            }
        }
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node('#', new Pair(0, "#"));
        }

        void add(String s, int r, Pair p) {

            Node node = root;
            for (int i =0; i < s.length(); i++) {
                if (node.child == null) {
                    node.child = new HashMap<>();
                }
                if (node.child.containsKey(s.charAt(i))) {
                    Node child = node.child.get(s.charAt(i));
                    child.add(p);
                }
                else {
                    node.child.put(s.charAt(i), new Node(s.charAt(i), p));
                }
                node = node.child.get(s.charAt(i));
            }
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {

        dic = new Trie();
        for (int i =0; i < sentences.length; i++) {
            dic.add(sentences[i], times[i], new Pair(times[i], sentences[i]));
        }

        prefix = "";
        cur = dic.root;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            prefix = "";
            cur = dic.root;
            return res;
        }

        if (cur == null) {
            return res;
        }

        if (cur.child.containsKey(c)) {
            Node node = cur.child.get(c);
            PriorityQueue<Pair> rate = node.rate;
            Iterator<AutocompleteSystem.Pair> it = (Iterator<Pair>) rate.iterator();
            while (it.hasNext()) {
                Pair p = it.next();
                res.add(0, p.s);
            }
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
