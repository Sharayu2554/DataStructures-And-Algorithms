package BasicDataStructures;

public class TreeEntry<T> {
    T element;
    TreeEntry<T> left;
    TreeEntry<T> right;

    public TreeEntry(T x) {
        this.element = x;
        this.left = this.right = null;
    }

    public TreeEntry(T x, TreeEntry l, TreeEntry r) {
        this.element = x;
        this.left = l;
        this.right = r;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public TreeEntry<T> getLeft() {
        return this.left;
    }

    public void setLeft(TreeEntry<T> left) {
        this.left = left;
    }

    public TreeEntry<T> getRight() {
        return this.right;
    }

    public void setRight(TreeEntry<T> right) {
        this.right = right;
    }

    public String toString() {
        return "TreeEntry{element=" + this.element + ", left=" + this.left + ", right=" + this.right + '}';
    }
}
