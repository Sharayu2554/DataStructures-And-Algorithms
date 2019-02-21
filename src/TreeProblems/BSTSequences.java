/**
 * Class to Implement BSTSequences
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/20/19
 * Project : TreeProblems
 **/

package TreeProblems;

import BasicDataStructures.Node;
import BasicDataStructures.Tree;

import java.util.*;

/**
 * BST Sequences: A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree.
 * EXAMPLE : input {2, 1, 3} form bst of this
 * output :  {2, 1, 3}, {2, 3, 1}
 *
 */
public class BSTSequences<T> {

    Tree tr;

    public BSTSequences (){
        tr = new Tree();
    }

    public void createBST(T[] arr, Comparator<T> com) {
        tr.createBST(arr, com);
    }

    public void printTree() {
        tr.printTree();
    }

    public void weaved(LinkedList<T> first, LinkedList<T> second, ArrayList<LinkedList<T>> results, LinkedList<T> prefix) {

        if ((first != null && first.size() == 0) || (second != null && second.size() == 0)) {
            LinkedList<T> res = (LinkedList<T>) prefix.clone();
            res.addAll(first);
            res.addAll(second);
            results.add(res);
            return;
        }

        T data = (T)first.removeFirst();
        prefix.addLast(data);
        weaved(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(data);

        data = (T) second.removeFirst();
        prefix.addLast(data);
        weaved(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(data);
    }

    public ArrayList<LinkedList<T>> allSequences() {
        return allSequences(tr.getRoot());
    }

    public ArrayList<LinkedList<T>> allSequences(Node node) {
        ArrayList<LinkedList<T>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<T>());
            return result;
        }

        LinkedList<T> prefix = new LinkedList<>();
        prefix.add((T)node.getElement());

        ArrayList<LinkedList<T>> left = allSequences(node.getLeft());
        ArrayList<LinkedList<T>> right = allSequences(node.getRight());

        for (LinkedList<T> l : left) {
            for (LinkedList<T> r : right) {
                ArrayList<LinkedList<T>> weaved = new ArrayList<>();
                weaved(l, r, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    public static void main(String args[]) {

        BSTSequences tr = new BSTSequences<Integer>();
        Integer[] data = {20, 10, 5, 15, 23};
        Comparator<Integer> com = Comparator.comparing(integer -> { return integer; });
        tr.createBST(data, com);
        tr.printTree();
        ArrayList<LinkedList<Integer>> res = tr.allSequences();
        for (LinkedList<Integer> r : res) {
            System.out.println(Arrays.toString(r.toArray()));
        }
    }
}