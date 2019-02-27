/**
 * Class to Implement PathWithSum
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/21/19
 * Project : TreeProblems
 **/

package TreeProblems;

import BasicDataStructures.Node;
import BasicDataStructures.Tree;

import java.util.Comparator;

/**
 * Paths with Sum: You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 *
 *
 */
public class PathWithSum<T> {

    Tree tr;

    public PathWithSum (){
        tr = new Tree();
    }

    public void createBST(T[] arr, Comparator<T> com) {
        tr.createBST(arr, com);
    }

    public void createTree(T[] arr) {
        tr.createTree(arr);
    }

    public void printTree() {
        tr.printTree();
    }

    //recusrsive solution
    public int countPathSum(int target, Node<Integer> node, int TARGET_SUM) {
        int count = 0;
        if (node == null) {
            return 0;
        }


        int newTarget = target - node.getElement();
        /**
         * Actual code will not have this, but current tree formed has zero instead of null hence case handling
         */
        if (target == 0 && node.getElement() == 0) {
            count--;
        }
        if (newTarget == 0) {
            count = count + 1;
        }

        if (node.getLeft() != null) {
            count += countPathSum(newTarget, node.getLeft(), TARGET_SUM);
            count += countPathSum(TARGET_SUM, node.getLeft(), TARGET_SUM);
        }

        if (node.getRight() != null) {
            count += countPathSum(newTarget, node.getRight(), TARGET_SUM);
            count += countPathSum(TARGET_SUM, node.getRight(), TARGET_SUM);
        }

        return count;
    }

    //optimized solution : Using HashMap
    //TODO:
//    public int countPathsSumDFS() {
//
//    }

    public static void main(String args[]) {

        PathWithSum<Integer> tr = new PathWithSum<Integer>();
        Integer[] data = {10, 5, -2, 3, 2, 0, 11, 3, -2, 1, 0, 0, 0, 0, -1, 0, 0, 2};
        Comparator<Integer> com = Comparator.comparing(integer -> { return integer; });
        tr.createTree(data);
        tr.printTree();
        System.out.println(" Count of paths whose sum is 8  : " + tr.countPathSum(8, tr.tr.getRoot(), 8));
    }
}
