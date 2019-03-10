/**
 * Class to Implement MasterGame
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/9/19
 * Project : Moderate
 **/

package Moderate;

import java.util.HashMap;
import java.util.HashSet;

public class MasterGame {

    void masterGame(String sol, String g) {

        System.out.print(" Sol : " + sol + " guess : " + g + " " );
        if (sol.length() != 4 || g.length() != 4) {
            return;
        }
        HashSet<Character> s = new HashSet<>();
        s.add('R');
        s.add('G');
        s.add('B');
        s.add('Y');

        HashMap<Character, Integer> m = new HashMap<>(); //instead of hashmap we can use code and array of size 4
        //like R = 0, G = 1, B = 2, Y = 3
        //and if R occurs we add one to frequency of arr[0]
        int hit = 0, pseudo= 0;
        for (int i =0; i< 4; i++) {
            if (!s.contains(g.charAt(i))) {
                System.out.println("invalid character ");
                return;
            }
            if (sol.charAt(i) == g.charAt(i))
                hit++;
            else
                m.put(sol.charAt(i), m.getOrDefault(sol.charAt(i), 0) + 1);
        }

        if (hit != 4) {
            for (int i =0; i < 4; i++) {
                if (m.containsKey(g.charAt(i))) {
                    pseudo++;
                    int c = m.get(g.charAt(i));
                    c = c -1;
                    if (c == 0){
                        m.remove(g.charAt(i));
                    }
                }
            }
        }

        System.out.println(" hit " + hit + " pseudo-hit " + pseudo);
    }

    public static void main(String args[]) {
            MasterGame m = new MasterGame();
            m.masterGame("RGBY", "RRGB");
            m.masterGame("RRRR", "RRRR");
            m.masterGame("RRRR", "BBBB");
            m.masterGame("RGBY", "YRGB");
            m.masterGame("RGBY", "RGBY");
            m.masterGame("RBYC", "RBYC");
            m.masterGame("RRBY", "RGRB");
    }
}
