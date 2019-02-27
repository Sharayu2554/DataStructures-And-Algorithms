/**
 * Class to Implement RankNode
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/27/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

public class RankNode {

    class Node {
        int val;
        int index;
        Node left;
        Node right;
        int leftCount;

        public Node(int x, int index, int leftCount) {
            val = x;
            this.index = index;
            this.leftCount = leftCount;
            left = null;
            right = null;
        }
    }

    int index = -1;
    Node root = null;

    void track(int x) {
        index += 1;
        if (root == null) {
            root = new Node(x, index, 0);
            return;
        }
        root = add(x, root, index);
    }

    Node add(int x, Node node, int index) {
        if (node == null) {
            return new Node(x, index, 0);
        }
        if (x == node.val) {
            node.leftCount += 1;
        }
        else if (x < node.val) {
            node.leftCount += 1;
            node.left = add(x, node.left, index);
        }
        else {
            node.right = add(x, node.right, index);
        }
        return node;
    }

    int getRank(int x) {
        return getRank(x, root, 0);
    }

    int getRank(int x, Node node, int c) {
        if (node == null)
            return -1;

        if (x == node.val) {
            return node.leftCount + c + 1 ;
        }
        else if (x < node.val) {
            return getRank(x, node.left, c);
        }
        else {
            return getRank(x, node.right, c + node.leftCount + 1);
        }
    }

    public static void main(String args[]){
        RankNode r  = new RankNode();
        int[] arr = {5, 1, 4, 4, 9, 13, 3};
        for (int i =0; i < arr.length; i++) {
            r.track(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(" Rank of " + arr[i] + " is " + r.getRank(arr[i]));
        }
    }
}
