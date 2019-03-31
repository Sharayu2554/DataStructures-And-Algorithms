/**
 *
 * Class to implement Search Algorithms
 * TODO: Need to add more algo's
 * @author Sharayu Sharad Mantri
 *
 */
package SearchAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class SearchAlgos<T> {

    public int binarySearch(T[] input, T x, Comparator<T> com) {

        int mid = input.length/2;
        int low = 0, high = input.length;

        while (low <= high) {
            mid = low + ((high - low) /2);
            if (input[mid] == x) {
                System.out.println(" found " + x + " location :  " +  mid );
                return mid;
            }
            else if (com.compare(x, input[mid]) == -1){
                high = mid -1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String args[]){
        Integer[] input = {1, -3, 2, -5, 7, 6, -1, -4, 11, -23 };
        SearchAlgos<Integer> searchAlgos = new SearchAlgos<>();
        Arrays.sort(input);
        searchAlgos.binarySearch(input, -3, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return(o1.compareTo(o2));
            }
        });
    }
}
