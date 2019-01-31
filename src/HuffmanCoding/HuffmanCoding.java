/**
 *
 * Class to implement Huffman Coding
 * @author Sharayu Sharad Mantri
 *
 */
package HuffmanCoding;

import BasicDataStructures.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    public HuffmanCoding() {
    }

    /*
     * print all the code by PreOrder bype traversal of the tree passed
     * String code : is the code of the tree read so far
     * root : current node of the tree with huffman code value in code
     */
     private static void printHuffmanCodes(Node<HuffmanNode> root, String code) {

        //if node doesn't have left and right, means its leaf node and hence, print its huffman code of the element in leaf location
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.println("Element " + root.getElement() + " Huffman Code : " + code);
        } else {

            //travel left subtree, and append '0' to code since, its travelled left subtree
            printHuffmanCodes(root.getLeft(), code + "0");

            //travel right subtree, and append '1' to code since, its travelled right subtree
            printHuffmanCodes(root.getRight(), code + "1");
        }
    }

    public static void main(String[] args) {

        //Input
        int n = 6;
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charfreq = new int[]{5, 9, 12, 13, 16, 45};

        //Creating Priority Queue of Type TreeEntry where TreeEntry is made of HuffmanNode Object
        //Passing comparator to create min priority queue on the count of root node of tree
        PriorityQueue<Node<HuffmanNode>> pq = new PriorityQueue(n, new Comparator<Node<HuffmanNode>>() {
            public int compare(Node<HuffmanNode> o1, Node<HuffmanNode> o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (( o1.getElement()).count < (o2.getElement()).count) {
                    return -1;
                } else {
                    return ( o1.getElement()).count > ( o2.getElement()).count ? 1 : -1;
                }
            }
        });

        //Iterate over input array to create object of HuffmanNode which is put in TreeEntry and then added to Queue
        for (int i = 0; i < charfreq.length; ++i) {
            HuffmanNode node = new HuffmanNode(charArray[i], charfreq[i]);
            Node entrynode = new Node(node);
            pq.add(entrynode);
        }

        //iterate until there is only one node in the heap
        while (!pq.isEmpty() && pq.size() > 1) {
            //remove top 2 (min nodes) from the heap
            Node<HuffmanNode> x = pq.remove();
            Node<HuffmanNode> y = pq.remove();

            //add their counts and create a new node with new count and x as left of that node and  y as right of the node
            Node entrynode = new Node(
                    new HuffmanNode('x', x.getElement().count +  y.getElement().count ),
                    x, y
            );

            //add the updated node in heap
            pq.add(entrynode);
        }

        //remove the final tree node from the heap and print huffman codes of all elements.
        printHuffmanCodes( pq.remove(), "");
    }

    static class HuffmanNode {
        int count;  //stores frequency of the data
        char data;  //input char element

        //constructor
        HuffmanNode(char in, int c) {
            this.data = in;
            this.count = c;
        }

        //getter , setters and toString
        public int getCount() {
            return this.count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public char getData() {
            return this.data;
        }

        public void setData(char data) {
            this.data = data;
        }

        public String toString() {
            return "HuffmanNode{count=" + this.count + ", data=" + this.data + '}';
        }
    }
}
