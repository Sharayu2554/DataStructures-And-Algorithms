import BasicDataStructures.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/**
 * Class to Implement TreeProblems
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/31/19
 * Project : PACKAGE_NAME
 **/

public class TreeProblems {

    Node root;
    int size = 0;

    public TreeProblems() {
        root = null;
        size = 0;
    }

    public void createTree(int[] data) {
        Node cur, nodel = null, noder = null;
        if (data.length <= 0)
            return;

        root = new Node(data[0]);

        Deque<Node> q = new ArrayDeque<>();
        q.addLast(root);
        for (int i = 1; i < data.length; ) {

            cur = q.removeFirst();
            nodel = new Node(data[i++]);
            cur.setLeft(nodel);
            q.addLast(nodel);

            if (i < data.length) {
                noder = new Node(data[i++]);
                cur.setRight(noder);
                q.addLast(noder);
            }
        }
    }

    public void printTree() {

        Deque<Node> q = new ArrayDeque<>();
        if (root == null)
            return;

        Node cur;
        q.add(root);
        System.out.println(" Start ");
        System.out.println(root.getElement());
        while(!q.isEmpty()) {
            cur = q.removeFirst();
            if (cur.getLeft() != null) {
                System.out.println( cur.getElement() + " : Left is " + cur.getLeft().getElement());
                q.addLast(cur.getLeft());
            }
            if (cur.getRight() != null) {
                System.out.println( cur.getElement() + " : Right is " + cur.getRight().getElement());
                q.addLast(cur.getRight());
            }
        }
    }

    public void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.getElement() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void preOrder() {
        System.out.println("\nPrinting Pre-Order using Recursion :  ");
        preOrder(root);
    }

    public void preOrderItr(){

        if (root == null)
            return;

        System.out.println("\nPrinting Pre-Order using Iteration :  ");
        Deque<Node> st = new ArrayDeque<>();
        Node cur = root;
        st.push(cur);
        System.out.print(cur.getElement() + " ");
        cur = cur.getLeft();
        
        while (!st.isEmpty()) {
            if (cur == null) {
                cur = st.pop();
                cur = cur.getRight();
            }
            if (cur != null) {
                System.out.print(cur.getElement() + " ");
                st.push(cur);
                cur = cur.getLeft();
            }
        }
    }

    public static void main(String args[]) {

        TreeProblems tr = new TreeProblems();
        int[] data = {9, 3, 2, 5, 6, 7, 4, 1, 0, 8, 10, 14, 12, 13};
        tr.createTree(data);
        tr.printTree();

        tr.preOrder();
        tr.preOrderItr();
    }
}
