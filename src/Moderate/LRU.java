/**
 * Class to Implement LRU
 *
 * @author Sharayu Sharad Mantri
 * Date : 6/3/19
 * Project : Moderate
 **/

package Moderate;

import java.util.HashMap;

public class LRU {

    class DLLNode {
        int key;
        int value;
        DLLNode prev;
        DLLNode next;

        public DLLNode() {
            prev = null;
            next = null;
        }

        public DLLNode(int k, int v) {
            key = k;
            value = v;
        }
    }

    class DLL {

        DLLNode head;
        DLLNode tail;
        int size = 0;

        DLL() {
            head = new DLLNode();
            tail = new DLLNode();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addFirst(DLLNode d) {
            DLLNode next = head.next;
            head.next = d;
            d.next = next;
            next.prev= d;
            d.prev = head;
            size++;
        }
        public void addLast(DLLNode d) {
            DLLNode prev = tail.prev;
            prev.next = d;
            d.next = tail;
            tail.prev = d;
            d.prev = prev;
            size++;
        }

        DLLNode removeLast() {

            if (size == 0) {
                return null;
            }

            DLLNode d = tail.prev;
            DLLNode prev = d.prev;
            prev.next = tail;
            tail.prev = prev;
            size--;
            return d;
        }

        public void remove(DLLNode d) {

            DLLNode prev = d.prev;
            DLLNode next = d.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }
    }

    HashMap<Integer, DLLNode> map;
    DLL dll;
    int CAPACITY;
    int size;

    public LRU(int cap) {
        CAPACITY = cap;
        size = 0;
        map  = new HashMap<>();
        dll = new DLL();
    }

    //put
    public void put(int key, int value) {



        if (!map.containsKey(key)) {
            DLLNode d = new DLLNode(key, value);
            dll.addFirst(d);
            map.put(key, d);
            size++;
        }
        else {
            DLLNode d = map.get(key);
            d.value = value;
            dll.remove(d);
            dll.addFirst(d);
            map.put(key, d);
        }
        if (size == CAPACITY + 1) {
            DLLNode d = dll.removeLast();
            map.remove(d.key);
            size--;
        }
    }

    //get
    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        DLLNode d = map.get(key);
        dll.remove(d);
        dll.addFirst(d);
        return d.value;
    }

    public static void main(String args[]) {

        LRU lru = new LRU(3);

    }
}