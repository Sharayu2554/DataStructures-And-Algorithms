package HuffmanCoding;

import BasicDataStructures.TreeEntry;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    public HuffmanCoding() {
    }

    public static void printHuffmanCodes(TreeEntry<HuffmanNode> root, String code) {
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.println("Element " + root.getElement() + " Huffman Code : " + code);
        } else {
            printHuffmanCodes(root.getLeft(), code + "0");
            printHuffmanCodes(root.getRight(), code + "1");
        }
    }

    public static void main(String[] args) {
        int n = 6;
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charfreq = new int[]{5, 9, 12, 13, 16, 45};
        PriorityQueue<TreeEntry<HuffmanNode>> pq = new PriorityQueue(n, new Comparator<TreeEntry<HuffmanNode>>() {
            public int compare(TreeEntry<HuffmanNode> o1, TreeEntry<HuffmanNode> o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (((HuffmanNode) o1.getElement()).count < ((HuffmanNode) o2.getElement()).count) {
                    return -1;
                } else {
                    return ((HuffmanNode) o1.getElement()).count > ((HuffmanNode) o2.getElement()).count ? 1 : -1;
                }
            }
        });

        for (int i = 0; i < charfreq.length; ++i) {
            HuffmanNode node = new HuffmanNode(charArray[i], charfreq[i]);
            TreeEntry entrynode = new TreeEntry(node);
            pq.add(entrynode);
        }

        while (!pq.isEmpty() && pq.size() > 1) {
            TreeEntry<HuffmanNode> x = (TreeEntry) pq.remove();
            TreeEntry<HuffmanNode> y = (TreeEntry) pq.remove();
            TreeEntry entrynode = new TreeEntry(
                    new HuffmanNode('x', ((HuffmanNode) x.getElement()).count + ((HuffmanNode) y.getElement()).count ),
                    x, y
            );
            pq.add(entrynode);
        }

        printHuffmanCodes((TreeEntry) pq.remove(), "");
    }

    static class HuffmanNode {
        int count;
        char data;

        public HuffmanNode(char in, int c) {
            this.data = in;
            this.count = c;
        }

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
