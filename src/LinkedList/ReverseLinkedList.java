/**
 * Class to Implement ReverseLinkedList
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/13/19
 * Project : LinkedList
 **/

package LinkedList;

import BasicDataStructures.ListNode;

import java.util.Arrays;

public class ReverseLinkedList {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    class ResultNode {
        ListNode rev;
        ListNode next;
        ListNode node;

        public ResultNode(ListNode _rev, ListNode _next, ListNode _node) {
            rev = _rev;
            next = _next;
            node = _node;
        }
    }

    public ResultNode reverse(ListNode node, int m, int n) {

        if (node != null && m == n) {
            return new ResultNode(node, node.next, node);
        }

        ResultNode r = reverse(node.next, m+1, n);
        if (r != null) {
            r.rev.next = node;
            r.rev= node;
        }
        return r;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {


        if (n < m) {
            return null;
        }

        ListNode node = head;
        ListNode prev= null;
        int i=1;
        while (i < m && node != null) {
            prev = node;
            node = node.next;
            i++;
        }

        ResultNode r = reverse(node, m, n);
        if (prev != null) {
            prev.next = r.node;
            r.rev.next = r.next;
        }
        else {
            head = r.node;
            r.rev.next = r.next;
        }
        return head;
    }

    public static void main(String args[]) {

        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i : arr) {
            node.next = new ListNode(i);
            node = node.next;
        }
        head = head.next;
        System.out.println(Arrays.toString(arr));
        ReverseLinkedList r = new ReverseLinkedList();
        r.reverseBetween(head, 2, 4).print();
    }
}
