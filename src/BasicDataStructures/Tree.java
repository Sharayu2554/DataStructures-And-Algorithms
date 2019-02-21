/**
 * Class to Implement Tree
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/20/19
 * Project : BasicDataStructures
 **/

package BasicDataStructures;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class Tree<T> {

    Node root;
    int size = 0;
    boolean isBST = false;

    public Tree (){
        root = null;
        size = 0;
    }

    public Node getRoot() {
        return root;
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

    private Node findBST(Node node, T x, Comparator<T> com) {
        if (!isBST)
        {
            //Ideally Throw Exception
            return null;
        }

        T y = (T)node.getElement();
        if (x.equals(y)) {
            return node;
        }
        else if (com.compare(x, y) == -1) {
            if (node.getLeft() == null) {
                return node;
            }
            else {
                return findBST(node.getLeft(), x, com);
            }
        }
        else {
            if (node.getRight() == null) {
                return node;
            }
            else {
                return findBST(node.getRight(), x, com);
            }
        }
    }

    public void createBST(T[] arr, Comparator<T> com) {
        isBST = true;
        if (arr.length == 0) {
            root = null;
            return;
        }

        root = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++ ) {
            Node node = findBST(root, arr[i], com);
            T x = (T) node.getElement();
            if (com.compare(arr[i], x) == -1) {
                node.setLeft(new Node(arr[i]));
            }
            else if (com.compare(arr[i] ,  x) == 1 ) {
                node.setRight(new Node(arr[i]));
            }
        }
        //printTree();
    }
}
