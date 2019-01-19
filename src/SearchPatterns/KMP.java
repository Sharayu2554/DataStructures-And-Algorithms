/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/19/2019
 * Project : SearchPatterns
 */

package SearchPatterns;

import java.util.Arrays;

public class KMP {

    /**
     * Degenerating property, means pattern having sub patterns
     * appearing more than once in pattern, are considered
     * This property is followed by KMP
     */

    /**
     * KMP Preprocess a pattern string before searching for it in main string
     * Preprcoessing done using LPS : longest proper prefix
     */

    public static int[] computeLPS(char[] subString) {
        int[] lps = new int[subString.length];
        int index = 0;
        for (int i = 1; i < subString.length;) {
            if (subString[i] == subString[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            }
            else {
                if (index != 0) {
                    index = lps[index -1];
                }
                else  {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static boolean KMP(char[] str, char[] subString) {
        int[] lps = computeLPS(subString);
        int index = 0;
        for (int i = 0; i < str.length;) {
            if (str[i] == subString[index]) {
                i++;
                index++;
            }
            else {
                if (index != 0) {
                    index = lps[index-1];
                }
                else {
                    i++;
                }
            }
        }
        if (index == subString.length) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        System.out.println(KMP(str.toCharArray(), subString.toCharArray()));
    }
}
