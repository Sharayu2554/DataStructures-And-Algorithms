/**
 * Class to Implement LongestRepeatedSubString
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/12/19
 * Project : StringProblems
 **/

package StringProblems;

import java.util.Arrays;
import java.util.HashMap;

public class LongestNonRepeatedSubString {

    public static int LNRSS(char[] arr) {

        System.out.println("\nInput String " + Arrays.toString(arr));
        int i =0, j = 0, ans = 0, k= 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while(i < arr.length) {
            if (map.containsKey(arr[i])) {
                k = map.get(arr[i]);
                if (k >= j){
                    j = k;
                    ans = i - j -1;
                }
            }
            map.put(arr[i], i);
            ans += 1;
            res = Math.max(res, ans);
            i++;
        }
        System.out.println("Result : " + res);
        return res;
    }

    public static void main(String args[]) {
        LNRSS("bananas".toCharArray()); //3 : ban
        LNRSS("geeksforgeeks".toCharArray()); //7 eksforg
        LNRSS("gegoogeoks".toCharArray()); //5geoks
        LNRSS("bbbbbbbbb".toCharArray()); //1 b
        LNRSS("1234567".toCharArray()); //7 1234567
    }
}
