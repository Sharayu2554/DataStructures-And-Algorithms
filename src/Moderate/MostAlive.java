/**
 * Class to Implement MostAlive
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/9/19
 * Project : Moderate
 **/

package Moderate;
import java.util.Arrays;

class MostAlive {

    /**
     * This solution is nLogn
     * @param born
     * @param death
     * @return
     */
    int mostAlive(int[] born, int[] death) {
        Arrays.sort(born);
        Arrays.sort(death);

        int aliveIndex = 0;
        int deathIndex =0;
        int n = born.length;
        int alive = 0;
        int maxAlive = 0;
        int aliveYear = 0;
//        System.out.println(n);
//        System.out.println(Arrays.toString(born));
//        System.out.println(Arrays.toString(death));

        //what if more death are remaning or more alive are remaning
        while(aliveIndex < n && deathIndex < n) {
            if (born[aliveIndex] <= death[deathIndex]) {
                alive++;
                if (alive > maxAlive) {
                    maxAlive = alive;
                    aliveYear = born[aliveIndex];
                }
                aliveIndex++;
            }
            else {
                alive--;
                deathIndex++;
            }
//            System.out.println(alive + " " + aliveIndex + " " + deathIndex + " maxAlive count  " + maxAlive + " year " + aliveYear );
        }
        System.out.println("Alive count " +  maxAlive);
        return aliveYear;
    }

    /**
     * Linear time algorithm n + size of age(max - min)
     * @param born
     * @param death
     * @return
     */
    int mostAliveLinear(int[] born, int[] death) {
        int[] res = new int[101];
        for (int i =0; i < born.length; i++) {
            res[born[i]]++;
            res[death[i] + 1]--;
        }
        int maxVal = 0;
        int val = 0;
        int maxYear = 0;
        for (int i =0; i < 101; i++) {
            val = val + res[i];
            if (val > maxVal) {
                maxVal = val;
                maxYear = i;
            }
        }
        System.out.println("Alive count : " + maxVal);
        return maxYear;
    }

    public static void main(String[] args) {
        MostAlive a = new MostAlive();
        int[] born = {12, 20, 10, 01, 10, 23, 13, 90, 83, 75};
        int[] death = {15, 90, 98, 72, 98, 82, 98, 98, 99, 94};
        System.out.println("Most Alive at year " +  a.mostAlive(born, death));
        System.out.println("Most Alive at year " + a.mostAliveLinear(born, death));
    }

}
