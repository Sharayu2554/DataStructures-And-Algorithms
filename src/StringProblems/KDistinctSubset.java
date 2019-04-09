/**
 * Class to Implement KDistinctSubset
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/8/19
 * Project : StringProblems
 **/

package StringProblems;

import java.util.HashMap;

public class KDistinctSubset {

    public int kdis(String s, int K){

        int n = s.length();

        if (n == 0)
            return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map1 = new HashMap<>();
        int count = 0, start = 0;
        int index = 0;

        start = 0;
        int startIndex = 0;
        Character c = s.charAt(0);
        for (int i =0; i <n;) {
            while(i < n && map.size() < K) {
                c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) +1);
                map1.put(c, map.get(c));
                i++;
            }
            startIndex = start;
            while(startIndex < i && map1.size() == K) {
                System.out.println(s.substring(startIndex, i));
                c = s.charAt(startIndex);
                int val = map1.get(c);
                if (val == 1) {
                    map1.remove(c);
                }
                else{
                    map1.put(c, val-1);
                }
                count++;
                startIndex++;
            }
            map1 = (HashMap<Character, Integer>) map.clone();

            if (i < n) {
                c = s.charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    map1.put(c, map.get(c));
                    i++;
                } else {
                    while (start < i && map.size() == K) {
                        Character c1 = s.charAt(start);
                        int val = map.get(c1);
                        if (val == 1) {
                            map.remove(c1);
                            map1.remove(c1);
                        } else {
                            map.put(c1, val - 1);
                            map1.put(c1, val - 1);
                        }
                        start++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {

        KDistinctSubset s = new KDistinctSubset();
        System.out.println(s.kdis("pqpqs", 2));


        System.out.println(s.kdis("pqspqspqs", 2));
    }
}
