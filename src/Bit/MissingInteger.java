/**
 * Class to Implement MissingInteger
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/15/19
 * Project : Bit
 **/

package Bit;

import java.util.ArrayList;

public class MissingInteger {

    /**
     * Number of 2's
     */

    int get2sIn10s(int N) {
        if(N < 2){
            return 0;
        }
        if (N >=2 && N <= 10) {
            return 1;
        }
        if(N % 10 != 0)
            return 0;
        String s = "";
        return 10 * get2sIn10s(N/10) + N/10;
    }

    int get2sInN(int N){

        int len = Integer.toString(N).length();
        int power = (int)Math.pow(10, len -1);

        int count = 0;
        while (len != 0) {
            int a = N / power;
            int right = N % power;
            int sum = a * get2sIn10s(power);
            if (a > 2) {
                sum += power;
            }
            else if (a == 2){
                sum += right + 1;
            }
            N = right;
            power = power/10;
            len--;
            count += sum;
        }
        return count;
    }

    public static void main(String args[]){

        MissingInteger m = new MissingInteger();
        int a = 2;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 11;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 12;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 13;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 14;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 20;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 21;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 22;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 23;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 29;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 30;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 31;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 100;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 200;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 219;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 220;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 221;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 222;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 223;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 229;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 230;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 299;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 300;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 319;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 320;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 321;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 322;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 329;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 330;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 331;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 1000;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2000;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2002;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2012;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2022;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2032;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2042;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2100;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2200;
        System.out.println(a + " : " + m.get2sInN(a));
        a = 2300;
        System.out.println(a + " : " + m.get2sInN(a));
    }
}
