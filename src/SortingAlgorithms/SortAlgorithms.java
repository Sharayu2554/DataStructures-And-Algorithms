/**
 *
 * Class to implement all Sorting Algorithms
 * TODO: Bucket Sort, Radix Sort and more
 * @author Sharayu Sharad Mantri
 *
 */
package SortingAlgorithms;

import java.util.*;

public class SortAlgorithms<T> {

    //returns index of min item in the array
    private int minInArray(T[] input, int start, int n, Comparator<T> com) {
        //check size of array
        if (start + n > input.length) {
            return -1;
        }

        T min = input[start];
        int minIndex = start;
        for (int i = start + 1; i < start + n; i++) {
            if (com.compare(input[i], min) == -1) {
                min = input[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void swap(T[] input, int i, int j) {
        T temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    //baisc sortign algo's for warm up
    /**
     * logic : find min in array put in begining, find next min in remaning and put it in 2nd position and so on
     * O(N ^ 2) not good at all
     * @param input
     * @param com
     */
    public void selectionSort(T[] input, Comparator<T> com) {
        int minIndex = -1;
        for (int i = 0; i < input.length; i++) {
            minIndex = minInArray(input, i, input.length - i, com);
            if (minIndex == -1)
                break;
            swap(input, i, minIndex);
        }
        return;
    }

    /**
     * logic : compare two adajcent element adn sort then and carry out this n-1 passes
     * Pass 1 :
     * ( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Here, algorithm compares the first two elements, and swaps since 5 > 1.
     * ( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4
     * ( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2
     * ( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), Now, since these elements are already in order (8 > 5), algorithm does not swap them.
     * RT : O(N ^ 2) horrible
     * @param input
     * @param com
     */
    public void bubbleSort(T[] input, Comparator<T> com) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length -1; j++) {
                if (com.compare(input[j], input[j +1]) == 1) {
                    swap(input, j, j+1);
                }
            }
        }
    }

    /**
     *  Like you arrange cards and go in reverse order
     *  RT : O(N ^ 2)
     * @param input
     * @param com comparator
     */

    public void insertionSort(T[] input, Comparator<T> com) {
        int j = 0;
        for (int i = 1; i < input.length; i++) {
            j = i -1;
            T temp = input[i];
            while (j >= 0 && com.compare(input[j], temp) == 1) {
                input[j + 1] = input[j];
                j--;
            }
            input[j + 1] = temp;
        }
    }

    public T[] dualPrivotQuickSort(T[] input, Comparator<T> com) {
        return input;
    }


    /**
     * RT : O(log N)
     * @param input
     * @param parent
     * @param size
     * @param com
     */
    private void heapify(T[] input, int parent, int size,  Comparator<T> com) {
        if (2 * parent + 1 >= size) {
            return;
        }

        int largest = parent;
        int leftChild = 2 * parent + 1, rightChild = 2 * parent + 2;
        if (leftChild < size && com.compare(input[largest], input[leftChild]) == -1) {
            largest = leftChild;
        }
        if (rightChild < size && com.compare(input[largest], input[rightChild]) == -1) {
            largest = rightChild;
        }
        if (largest != parent) {
            swap(input, parent, largest);
        }
        else {
            return;
        }
        heapify(input, largest, size, com);
    }

    /**
     * RT : O(N * logN)
     * Applications : sort a nearly sorted array, K sorted array
     * Kth largest elemnt in an array
     * @param input
     * @param com
     * @return
     */
    public T[] heapSort(T[] input, Comparator<T> com) {
        for (int i = input.length/2 -1; i >= 0; i--) {
            heapify(input, i, input.length, com);
        }

        for (int i = input.length -1; i >= 0; i--) {
            swap(input, i, 0);
            heapify(input, 0, i , com);
        }
        return input;
    }

    /**
     * Merge Sort
     * @param A
     * @param com
     * @return
     */
    public void mergeSort(T[] A, T[] B, Comparator<T> com) {
        System.arraycopy(A, 0, B, 0, A.length);
        mergeSort(A, B, 0, A.length, com);
        //result is in A
    }

    public void mergeSort(T[] A, T[] B, int low, int n, Comparator<T> com) {
        /**
         * We can also set some threshold and call insertionSort if array is in that threshold , reduce the complexity
         */
        if (n == 1) {
            return ;
        }
        mergeSort(B, A, low, n/2, com);
        mergeSort(B, A, low + (n/2), n -(n/2), com);
        merge(A, B, low, low + (n/2)-1, low + n-1, com);
    }

    public void merge(T[] A, T[] B, int low, int mid, int high, Comparator<T> com) {
        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high) {
            if (com.compare(B[i], B[j]) == -1 || com.compare(B[i], B[j]) == 0) {
                A[k++] = B[i++];
            }
            else {
                A[k++] = B[j++];
            }
        }

        while (i <= mid) {
            A[k++] = B[i++];
        }

        while (j <= high) {
            A[k++] = B[j++];
        }
    }

    public T[] quickSort(T[] input, Comparator<T> com) {
        /**
         * partition :
         * quicksort lowe half
         * quick sort upper half
         */
        return input;
    }

    public static void main(String args[]) {
        Integer[] input = {1, -3, 2, -5, 7, 6, -1, -4, 11, -23 };
        Integer[] output =  new Integer[input.length];
        SortAlgorithms sa = new SortAlgorithms();

//        sa.mergeSort(input, output, new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        System.out.println(Arrays.toString(input));


//        sa.selectionSort(input, (o1, o2) -> ((Integer) o1).compareTo(((Integer) o2)));
//        System.out.println(Arrays.toString(input));

//        sa.bubbleSort(input, (o1, o2) -> ((Integer) o1).compareTo(((Integer) o2)));
//        System.out.println(Arrays.toString(input));

//        sa.insertionSort(input, (o1, o2) -> ((Integer) o1).compareTo(((Integer) o2)));
//        System.out.println(Arrays.toString(input));

        sa.heapSort(input, (o1, o2) -> ((Integer) o1).compareTo(((Integer) o2)));
        System.out.println(Arrays.toString(input));
    }
}
