/**
 * Class to Implement Problems
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/14/19
 * Project : Arrays
 **/

package Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Problems {

    /**
     * IsUnique: Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structure ?
     *
     * Assumptions :
     * 1. Empty string returns true
     * 2. a and A are different, case sensitive
     *
     * @param s
     * @return
     */
    public static boolean isUnique(String s) {

        //if string is greater than 128, then some character is definately repeated. since char is 8 bit : 128 chars only
        if (s.length() > 128)
            return false;


        boolean c[] = new boolean[128];
        for (int i =0; i < s.length(); i++) {
            int k = s.charAt(i);
            if (!c[k]) {
                c[k] = true;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * Check Permutations : Given two strings, write a method to decide if one is permutation of the other.
     *
     * Assumption : Permutation without repetition.
     *
     * TODO: OPtimization can be done using in[] instead of map like in isUnique
     * @param x
     * @param y
     * @return
     */
    public static boolean checkPermutations(String x, String y) {

        //if x and y are equal, return false
        if (x.equals(y) || x.length() != y.length())
            return false;

        HashMap<Character, Integer> set = new HashMap<Character, Integer>();
        char k;

        //adding chars and its counts of chars of x in map.
        for (int i=0; i < x.length(); i++) {
            k = x.charAt(i);
            if (set.containsKey(k)) {
                int val = set.get(k);
                set.put(k, val +1);
            }
            else {
                set.put(k, 1);
            }
        }

        //every time if find char in x in y, i reduce its value by 1
        //if value after reducing becomes 0 then remove that key from map
        //if char in y is not in map, then return false since not a permutation.
        for (int i =0; i < y.length(); i++) {
            k = y.charAt(i);
            if (set.containsKey(k)) {
                int val = set.get(k);
                val = val -1;
                if (val == 0)
                    set.remove(k);
                else
                    set.put(k, val);
            }
            else {
                return false;
            }
        }

        //After removing all that chars that are present in y from map
        //if something is still left in map then it's not a permutation.
        //return false
        if (set.size() > 0)
            return false;
        return true;
    }

    /**
     * URIlify : Write a method to replace all spaces in a string with '%20: You may assume that the string has sufficient
     * space at the end to hold the additional characters, and that you are given the "true" length of the string.
     * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
     * @param str
     * @return
     */
    public static String urlify(String str) {
        int n = str.length();
        char[] arr = str.toCharArray();
        int noOfSpaces = 0;

        for (int i =0; i <n; i++) {
            if (arr[i] == ' ') {
                noOfSpaces++;
            }
        }

        int count = noOfSpaces/3; //lastCharIndex-(count*2)--these many spaces @ end
        int j = n-1-(count * 2);
        for (int i = n-1; i >=0 && j >= 0;) {
            if (arr[j] != ' ') {
                arr[i] = arr[j];
                i--;
                j--;
            }
            else {
                arr[i] = '0';
                arr[i-1] = '2';
                arr[i-2] = '%';
                i = i -3;
                j = j -1;
            }
        }
        return Arrays.toString(arr);
    }

    /**
     * .One Away :  There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
     *
     * EXAMPLE
     * pale, ple -> true
     * pales. pale -> true
     * pale. bale -> true
     * pale. bake -> false
     *
     * Conditions :
     * 1. Lengths are same, replacement happened compare each character
     * 2. Lengths differ by one, either removed or added  compare each char and increase index of longer string
     * 3. Lengths differ more than 1 return false
     * @param x
     * @param y
     * @param ind
     * @return
     */
    public static boolean compare(String x, String y, int ind) {
        int change = 0;
        if (ind == 0) {
            for (int i =0; i < x.length(); i++) {
                if (x.charAt(i) != y.charAt(i)) {
                    change++;
                    if (change > 1)
                        return false;
                }
            }
        }
        else {
            int j = 0;
            for (int i =0; i < x.length(); i++) {
                if (x.charAt(i) != y.charAt(j)) {
                    change++;
                    if (change > 1)
                        return false;
                }
                else {
                    j++;
                    if (i +1 < x.length() && j >= y.length()) {
                        change = change + (x.length() - y.length());
                        if (change > 1)
                            return false;
                        else
                            return true;
                    }
                }
            }
        }
        return true;
    }

    public static boolean oneAway(String in, String out) {

        int n = in.length(), m = out.length();
        int diff = Math.abs(n-m);
        if (Math.abs(n-m) > 1)
            return false;
        if (diff == 0) {
            return compare(in, out, 0);
        }
        else if (n-m > 0) {
            return compare(in, out, 1);
        }
        else {
            return compare(out, in, 1);
        }
    }

    /**
     * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
     * For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller
     * than the original string, your method should return the original string. You can assume the string has only
     * uppercase and lowercase letters (a - z).
     *
     * TODO: More optimized solution would be two iterations instead of one.
     * so, in first iteration we calculate the length of the compressed str,
     * so if it is greater than the orginal string we dont need to calculate the whole string and in other case
     * we can intialize the string builder with final value, so it doesnt reinitlize once it hits full capacity,
     * @param str
     * @return
     */
    public static String stringCompression(String str) {

        int  n = str.length();
        if (n < 1)
            return str;

        char prev = str.charAt(0);
        int curCount = 1;
        StringBuilder res = new StringBuilder();
        res.append(prev);

        for (int i = 1; i < n; i++) {
            if (prev == str.charAt(i)) {
                curCount++;
            }
            else {
                res.append(curCount);
                res.append(str.charAt(i));
                curCount = 1;
                prev = str.charAt(i);
            }
        }
        res.append(curCount);
        return str.length() <= res.length() ? str : res.toString();
    }


    public static void main(String args[]) {

        String str = "-_";
        System.out.println(" Has Unique in " + isUnique(str));

        str = "aaaa";
        String y = "aaaa";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //false

        str = "abcd";
        y = "acbd";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //true

        str = "abbcde";
        y = "acbdbe";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //true

        str = "abbbccccdee";
        y = "abcdbcebcec";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //true

        str = "abbbccccdeee";
        y = "abcdbcebcec";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //false

        str = "abbbccccdddee";
        y = "abcdbcebcec";
        System.out.println("Is " + str + " permutation of " + y + " :  " + checkPermutations(str, y)); //false

        str = "Mr John  Smith          ";
        System.out.println("Urlify : Input String " + str + " output : " + urlify(str));

        str = "   ";
        System.out.println("Urlify : Input String " + str + " output : " + urlify(str));

        str = "Mr John Smith    ";
        System.out.println("Urlify : Input String " + str + " output : " + urlify(str));

        str = "Mr";
        System.out.println("Urlify : Input String " + str + " output : " + urlify(str));

        str = "Mr   ";
        System.out.println("Urlify : Input String " + str + " output : " + urlify(str));

        String in = "pale";
        String out = "bale";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "bal";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "pales";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "bale";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "paless";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "ppp";
        out = "bbb";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "ple";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        in = "pale";
        out = "bake";
        System.out.println("OnwAway x : " + in + " y : " + out  + " is " + oneAway(in, out));

        str = "";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "aaaaa";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "aa";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "a";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "abcd";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "aabcccccddaaa";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

        str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbb";
        System.out.println("String Compression : input is " + str + " result is : " + stringCompression(str));

    }
}
