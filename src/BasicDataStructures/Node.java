/**
 *
 * Class to represent a Node of Binary Tree Data Structure
 * @author Sharayu Sharad Mantri
 *
 */
package BasicDataStructures;

import java.util.Comparator;

/**
 * Create Entry of Tree Data Structure which has left, right pointers to Entry element and element of that node
 * @param <T>
 */
public class Node<T>{
    T element;
    Node<T> left;
    Node<T> right;

    public Node(T x) {
        this.element = x;
        this.left = this.right = null;
    }

    public Node(T x, Node l, Node r) {
        this.element = x;
        this.left = l;
        this.right = r;
    }

//    public int compare(T x, T y) {
//        return x.compareTo(y);
//    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public String toString() {
        return "TreeEntry{element=" + this.element + ", left=" + this.left + ", right=" + this.right + '}';
    }
}
