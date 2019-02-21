/**
 * Class to Implement StringProblems
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/11/19
 * Project : StringProblems
 **/

package StringProblems;

import java.util.Arrays;

public class RemoveOccurenceOfChar {


    public static String removeOccurenceOfChar(char[] input, char c) {
        System.out.println("\n Input String " + Arrays.toString(input));
        int i =0, j=0;
        while (j < input.length) {
            if (input[j] == c) {
                j++;
            }
            else {
                input[i] = input[j];
                i++;
                j++;
            }
        }

        while(i < j) {
            input[i] = ' ';
            i++;
        }

        System.out.println(" Output String " + Arrays.toString(input));
        return input.toString();
    }

    public static void main(String args[]) {

        /**
         * Given String remove all occurence of a character form string, inPLace and RunTIme O(N)
         */
        removeOccurenceOfChar("abcdabcdabcd".toCharArray(), 'a');
        removeOccurenceOfChar("aaaaaaabbbbb".toCharArray(), 'a');
        removeOccurenceOfChar("aaaaaaaaaaaa".toCharArray(), 'a');
        removeOccurenceOfChar("bbbbbbbbbbbb".toCharArray(), 'a');
    }
}
