/**
 * Class to Implement Permute
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/22/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Permute {

    /*
    Logic very similar to powerset : permute without Duplicates
     */
    //premute without duplicates
    ArrayList<String>  permutesUnique(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s.length() == 0){
            res.add("");
            return res;
        }
        for (int i =0; i < s.length(); i++) {
           char c = s.charAt(i);
           String left = s.substring(0, i);
           String right = s.substring(i+1, s.length());
           ArrayList<String> out = permutesUnique(left + right);
           for (String outs : out) {
               res.add(c + outs);
           }
        }
        return res;
    }

    //Permute with duplicates
    ArrayList<String> permutes(String s) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<Character, Integer> map = processChars(s);
        getPermutes("", map, result, s.length());
        return result;
    }

    HashMap<Character, Integer> processChars(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i =0; i< s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        return map;
    }

    //a2, b4, c1
    void getPermutes(String prefix, HashMap<Character, Integer> map, ArrayList<String> result, int remaining){
        if (remaining == 0){
            result.add(prefix);
            //System.out.println(prefix);
            return;
        }
        for (Character c : map.keySet()) {
            int count = map.get(c);
            if (count > 0) {
                map.put(c, count -1);
                getPermutes(c + prefix, map, result, remaining -1);
                map.put(c, count);
            }
        }
    }

    //if we add ( at index we reduce count of left brace remaining
    //if we add ) at index we reduce count of right brace remaining
    // if number of left brace remaining are more than number of right brace remaining we return since its invalid state
    //first we add left brace at index and recursively call same function with one less left brace value
    //then we add ) at same index and recursively call same function with one less right brace value
    void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {

        if (leftRem < 0 || rightRem < leftRem) {
            return;
        }

        if (leftRem == 0 && rightRem == 0) {
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '(';
            addParen(list, leftRem -1, rightRem, str, index + 1);
            str[index] = ')';
            addParen(list, leftRem, rightRem -1, str, index+1);
        }
    }

    ArrayList<String> generatePar(int count) {
        char[] str = new char[count*2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

    //count coins
    public int makeChange(int n) {
        int[] denom = {25, 10, 5, 1};
        return makeChange(n, denom, 0);
    }

    /**
     * {10, 5, 1}
     * for 16 amount :
     * logic :
     * 0 of 10, 0 of 5, take 1's
     * 0 of 10, 1 of 5, take 1's
     * 0 of 10, 2 of 5, take 1's
     * 0 of 10, 3 of 5, take 1's
     * 1 of 10, 0 of 5, take 1's
     * 1 of 10, 1 of 5, take 1's
     * @param amount
     * @param denoms
     * @param index
     * @return
     */
    public int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length -1) {
            return 1;
        }
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i*denomAmount;
            ways += makeChange(amountRemaining, denoms, index +1);
        }
        return ways;
    }

    public static void main(String args[]) {

        Permute p = new Permute();
        ArrayList<String> ars = p.permutesUnique("abcd");
        int count = 0;
        for (String s : ars) {
            count++;
            System.out.println(count + " : " + s);
        }

        ArrayList<String> ar = p.permutes("abcabbb");
        count = 0;
        for (String s : ar) {
            count++;
            System.out.println(count + " : " + s );
        }

        System.out.println("\n");
        ArrayList<String> rs = p.generatePar(5);
        count = 0;
        for (String s : rs) {
            count++;
            System.out.println(count + " : " + s);
        }

        System.out.println("Count Ways" + p.makeChange(16));
    }
}
