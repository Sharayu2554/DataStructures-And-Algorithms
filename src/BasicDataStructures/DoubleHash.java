/**
 * Class to Implement DoubleHash
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/10/19
 * Project : BasicDataStructures
 **/

package BasicDataStructures;

import java.util.*;

public class DoubleHash<T extends Comparable<? super T>>  {
    private final int DEFAULT_CAPACITY = 16;
    private int CAPACITY = DEFAULT_CAPACITY;
    private int size = 0;
    private Entry[] set;

    public DoubleHash() {
        size = 0;
        CAPACITY = DEFAULT_CAPACITY;
        set = new Entry[CAPACITY];
    }

    public DoubleHash(T[] arr) {
        buildHash(arr);
    }

    /**
     * Build Hash from given input array
     * @param input
     */
    private void buildHash(T[] input) {
        while (input.length > CAPACITY) {
            CAPACITY = CAPACITY * 2;
        }
        set = new Entry[CAPACITY];
        size = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != null) {
                add(input[i]);
            }
        }
    }


    /**
     * Allocate memory with double the capacity and return old hashSet
     * @return
     */
    private Entry[] reallocateMemory() {
        CAPACITY = 2 * CAPACITY;
        Entry[] oldSet = set;
        set = new Entry[CAPACITY];
        size = 0;
        return oldSet;
    }

    /**
     * Using oldhashset and newly allocated memory create new HashSet
     * @param input
     */
    private void reHash(Entry<T>[] input) {
        while (input.length > CAPACITY) {
            CAPACITY = CAPACITY * 2;
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i] != null) {
                add(input[i].element);
            }
        }
    }

    /**
     * hash function gets hash of input hashcode
     * @param h
     * @return
     */
    private static int hash(int h) {
        if (h < 0) {
            h = h * -1;
        }
        h ^= (h >>> 20) ^ (h >>> 12);
        return (h ^ (h >>> 7) ^ (h >>> 4));
    }

    private static int indexFor(int h, int length) {
        return h & (length -1);
    }

    /**
     * Returns the index of first hash
     * @param element
     * @return
     */
    private int hash1(T element) {
        return indexFor(hash(element.hashCode()), CAPACITY);
    }

    /**
     * Return the index for second hash
     * @param element
     * @return
     */
    private int hash2(T element) {
        int h = element.hashCode();
        if (h < 0) {
            h = h * -1;
        }
        return ((1 + h) % 9) != 0 ? (1 + h) % 9: (1 + h) % 9 + 1;
    }

    /**
     * Returns the Iterator
     * @return
     */
    public Iterator<T> iterator() {
        return new DoubleHashIterator();
    }

    /**
     * Iterator Class to iterate over all the elements of hashSet
     */
    protected class DoubleHashIterator implements Iterator<T> {
        int cursor, index;

        DoubleHashIterator() {
            cursor = -1;
            index = -1;
        }

        /**
         * Check if there is any element left in hash and if true stores that in index and returns true else false
         * @return
         */
        public boolean hasNext() {
            index = cursor;
            index = index + 1;
            while (index < CAPACITY && (set[index] == null || set[index].element == null)) {
                index += 1;
            }
            return index < CAPACITY;
        }

        /**
         * Updates its cursor with the index found in hasNext() and returns value at the cursor
         * @return
         */
        public T next() {
            cursor = index;
            return ((Entry<T>) set[cursor]).element;
        }

    }

    /**
     * Entry class that has generic element and marked flag to check the element was deleted or new
     * @param <E>
     */
    static class Entry<E> {
        private E element;
        private boolean marked = false;

        Entry() {
            element = null;
            marked = false;
        }

        Entry(E x) {
            element = x;
            marked = false;
        }

        Entry(E x, boolean mark) {
            element = x;
            marked = mark;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked)
        {
            this.marked = marked;
        }

        /**
         * Overriding Equals method, so if element of type T is passed, equal function works
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            return Objects.equals(element, o);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, marked);
        }

        public boolean equals(Entry o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?> entry = (Entry<?>) o;
            return marked == entry.marked &&
                    Objects.equals(element, entry.element);
        }

        @Override
        public String toString() {
            return " "+ element + " ";
        }
    }

    /**
     * Search for x and return x. If x is not found, return index where x can be added
     * @param element
     * @return
     */
    private int findLocation(T element) {
        int k = 0;
        int index = -1;
        int hash1 = hash1(element);
        int hash2 = hash2(element);
        int stopCount = (CAPACITY/hash2);
        /**
         * stopCount stops the jumps to loop forever by
         */
        while(k < stopCount) {
            index = (hash1 + k * hash2) % CAPACITY;
            if (set[index] == null || (set[index].equals(element) || (set[index].element == null && !set[index].marked))) {
                return index;
            }
            else if (set[index].marked) {
                break;
            }
            else {
                k++;
            }
        }
        /**
         * stopCount stops the jumps to loop forever by
         * xspot is the first free or empty index found to be returned if the element not found in hashset
         */
        int xspot = index;
        while(k < stopCount) {
            k++;
            index = (hash1 + k * hash2) % CAPACITY;
            if (set[index].equals(element)) {
                return index;
            }
            if (set[index].element == null && !set[index].marked) {
                return xspot;
            }
        }
        //no space to add in set
        /**
         * return -1 if no space to add the new element
         */
        return -1;
    }

    /**
     * Check if the element is present in HashSet
     * @param element
     * @return
     */
    public boolean contains(T element) {
        int location = findLocation(element);
        return set[location] != null && set[location].equals(element);
    }

    /**
     * Add element in hashSet and if no place to add that is when location is -1 then allocate double the memory
     * and Rehash old Hashset in new one
     * @param element
     * @return
     */
    public boolean add(T element) {
        int location  = findLocation(element);
        while(location == -1) {
            //reallocate memory
            //rehash
            Entry[] oldSet = reallocateMemory();
            reHash(oldSet);
            location  = findLocation(element);
        }
        if (set[location] != null && set[location].equals(element)) {
            return false;
        }
        set[location] = new Entry(element);
        size = size + 1;
        return true;
    }

    /**
     * Remove the element if found in hashSet else return null
     * Once the element is removed mark the element field has marked = true means deleted
     * and set that element value to null
     * @param element
     * @return
     */
    public T remove(T element) {
        int location  = findLocation(element);
        if (location  == -1) {
            return null;
        }
        if (set[location] != null && set[location].equals(element)) {
            set[location].element = null;
            set[location].marked = true;
            size = size - 1;
            return element;
        }
        return null;
    }

    @Override
    public String toString() {
        return "DoubleHash{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", CAPACITY=" + CAPACITY +
                ", size=" + size +
                ", set=" + Arrays.toString(set) +
                '}';
    }

    /**
     * Adds the given array to DoubleHash Object and iterates over the hash to return distinct elements
     * this count of distinct elements is returned by this function
     * @param arr
     * @param <T>
     * @return
     */
    static<T extends Comparable<? super T>> int distinctElements(T[ ] arr)
    {
        DoubleHash<T> customSet = new DoubleHash<T>(arr);
        int distinct = 0;
        Iterator<T> itr = customSet.iterator();
        while(itr.hasNext()) {
            itr.next();
            distinct += 1;
        }
        return distinct;
    }

    /**
     * Genarates Random number between given min and max
     * @param min
     * @param max
     * @return
     */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void main(String args[]) {
        /**
         * Creating random numbers array of size capacity
         * checking the time required and distinct elements count of DoubleHash Class with Java HashSet Class
         */
        int capacity = 1000000;
        Integer[] input = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            input[i] = getRandomNumberInRange(-787889798, -10);
        }

        System.out.println("Starting to add in customHashSet ");
        long start = System.currentTimeMillis();
        int distinct = DoubleHash.distinctElements(input);
        long end = System.currentTimeMillis();

        System.out.println("Custom Hash : " + (int)(end-start));
        System.out.println(" Distinct Elements : " + distinct);

        start = System.currentTimeMillis();
        HashSet<Integer> hset = new HashSet<Integer>();
        for (int i = 0; i < capacity; i++) {
            hset.add(input[i]);
        }
        distinct = 0;
        for(int data : hset) {
            distinct += 1;
        }
        end = System.currentTimeMillis();
        System.out.println("Distinct elements : " + distinct);
        System.out.println("System Hash : " + (int)(end-start));

        /**
         * Added some random elements of new DoubleHash Object and checked for contains and removed are working correctly
         */
        DoubleHash<Integer> customSet = new DoubleHash<>();
        customSet.add(7);
        customSet.add(8);
        customSet.add(9);
        customSet.add(10);
        customSet.add(-109823092);
        customSet.add(1798798);
        customSet.add(18987);
        customSet.add(123323);
        customSet.add(11111);
        customSet.add(11);
        customSet.add(11);
        customSet.add(11);
        customSet.add(11);
        System.out.println(customSet);

        System.out.println("remove : " + customSet.remove(11));
        System.out.println(customSet);

        System.out.println("remove : " + customSet.remove(0));
        System.out.println(customSet);

        System.out.println("remove : " + customSet.remove(-109823092));
        System.out.println(customSet);

        System.out.println("remove : " + customSet.remove(123323));
        System.out.println(customSet);


        /**
         * Comparing time required to add, remove and contains elements in Java HashSet and System HashSet
         */
        HashSet<Integer> systemSet = new HashSet<Integer>();
        capacity = 10000000;

        System.out.println("Adding " + capacity + " elements in sets");
        start = System.currentTimeMillis();
        for (int i = 0; i< capacity; i++) {
            systemSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("System Hash : " + (int)(end-start));

        customSet = new DoubleHash<Integer>();
        start = System.currentTimeMillis();
        for (int i = 0; i< capacity; i++) {
            customSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Custom Hash : " + (int)(end-start));

        /*************************************/

        System.out.println("Checking " + (int)(capacity/2) + " from sets");
        start = System.currentTimeMillis();
        for (int i = 0; i< capacity/2; i++) {
            systemSet.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("System Hash : " + (int)(end-start));


        start = System.currentTimeMillis();
        for (int i = 0; i< capacity/2; i++) {
            customSet.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Custom Hash : " + (int)(end-start));

        /************************************/

        System.out.println("Removing " + (int)(capacity/2) + " from sets");
        start = System.currentTimeMillis();
        for (int i = 0; i< capacity/2; i++) {
            systemSet.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("System Hash : " + (int)(end-start));

        start = System.currentTimeMillis();
        for (int i = 0; i< capacity/2; i++) {
            customSet.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Custom Hash : " + (int)(end-start));

        /************************************/

        /**
         * Adding elements of String data type in hashset and checking for all the functionalities
         */
        DoubleHash<String> customSet1 = new DoubleHash<String>();
        customSet1.add("data1");
        customSet1.add("data2");
        customSet1.add("data3");
        customSet1.add("data4");
        customSet1.add("data5");
        customSet1.add("data6");
        customSet1.add("data7");
        customSet1.add("data8");
        customSet1.add("data9");
        customSet1.add("data10");
        customSet1.add("data1");
        customSet1.add("data2");
        Iterator itr = customSet1.iterator();
        while(itr.hasNext()) {
            System.out.println(" Element : " + itr.next());
        }

        System.out.println("Contains data25  : " + customSet1.contains("data25"));
        System.out.println("Contains data5  : " + customSet1.contains("data5"));

        System.out.println("\nAdding new elements to data set");
        customSet1 = new DoubleHash<String>();
        for(int i =0; i < 25; i++) {
            customSet1.add("data" + i);
        }
        itr = customSet1.iterator();
        int count = 0;
        while(itr.hasNext()) {
            System.out.println(" Element : " + itr.next());
            count++;
        }
        System.out.println("Count : " + count);
    }
}
