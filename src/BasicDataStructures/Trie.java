package BasicDataStructures;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Trie<T> {

    private static class Entry<T> {
        T ele;
        HashMap<Character, Entry<T>> child;
        int depth;
        boolean isEndOfWord;

        public Entry(T ele, int depth, boolean isEndOfWord) {
            this.ele = ele;
            child = new HashMap<>();
            this.depth = depth;
            this.isEndOfWord = isEndOfWord;
        }
    }

    Entry<T> root;
    int size;  //number of words in trie

    public Trie() {
        root = new Entry<>(null, 0, false);
    }

    /**
     *
     * @param iter
     * @param value
     * @return
     */
    private T put(CharacterIterator iter, T value) {
        Entry current = root;
        for (char ch = iter.first(); ch != CharacterIterator.DONE; ch = iter.next()) {

            //ch is already child of the trie then make it current anc move on for next ch
            if (current.child.containsKey(ch)) {
                current = (Entry) current.child.get(ch);
            }
            else { //if ch is not child of trie then add it as child of trie
                current.child.put(ch, new Entry<T>(value, current.depth + 1, false));
                current  = (Entry) current.child.get(ch);
            }
        }
        //mark the last character is end of the word and increase size of trie by 1
        current.isEndOfWord = true;
        size++;
        return value;
    }

    private boolean get(CharacterIterator iter) {
        Entry current = root;
        for (char ch = iter.first(); ch != CharacterIterator.DONE; ch = iter.next()) {

            if (current.child.containsKey(ch)) {
                current = (Entry) current.child.get(ch);
            }
            else {
                return false;
            }
        }
        return current.isEndOfWord;
    }

    private boolean remove(CharacterIterator iter) {

        Entry prev, current;
        Character removeChar = null;
        current = root;
        prev = root;

        for (char ch = iter.first(); ch != CharacterIterator.DONE; ch = iter.next()) {

            if (current.child.containsKey(ch)) {
                if (current.isEndOfWord || current.child.size() > 1) {
                    prev = current;
                    removeChar = ch;
                }
                current = (Entry) current.child.get(ch);
            }
            else {
                return false;
            }
        }

        if (current.child.size() > 0) {
            current.isEndOfWord = false;
            size--;
            return true;
        }
        else if (removeChar != null){
            prev.child.remove(removeChar);
            size--;
            return true;
        }
        return false;
    }


    // public methods
    public T put(String s, T value) {
        return put(new StringCharacterIterator(s), value);
    }

    public boolean get(String s) {
        return get(new StringCharacterIterator(s));
    }

    public boolean remove(String s) {
        return remove(new StringCharacterIterator(s));
    }

    // How many words in the dictionary start with this prefix?
    public int prefixCount(String s) {
        return 0;
    }

    public int size() {
        return size;
    }

    public static class StringIterator implements Iterator<Character> {
        char[] arr;  int index;
        public StringIterator(String s) { arr = s.toCharArray(); index = 0; }
        public boolean hasNext() { return index < arr.length; }
        public Character next() { return arr[index++]; }
        public void remove() { throw new java.lang.UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        int wordno = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter to put words : ");
        while(in.hasNext()) {
            String s = in.next();
            if(s.equals("End")) { break; }
            wordno++;
            trie.put(s, wordno);
        }

        System.out.println("Enter to get words : ");
        while(in.hasNext()) {
            String s = in.next();
            if(s.equals("End")) { break; }
            boolean val = trie.get(s);
            System.out.println(s + "\t" + val);
        }

        System.out.println("Enter Remove words : ");
        while(in.hasNext()) {
            String s = in.next();
            if(s.equals("End")) { break; }
            boolean val = trie.remove(s);
            System.out.println(s + "\t" + val);
        }

        System.out.println("Enter to get words : ");
        while(in.hasNext()) {
            String s = in.next();
            if(s.equals("End")) { break; }
            boolean val = trie.get(s);
            System.out.println(s + "\t" + val);
        }

        System.out.println("Number of words in Trie : " + trie.size );
    }
}
