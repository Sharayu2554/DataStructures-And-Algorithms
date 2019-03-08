/**
 * Class to Implement NumberTOString
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/8/19
 * Project : Moderate
 **/

package Moderate;

import java.util.Arrays;
import java.util.LinkedList;

public class NumberTOString {

    String[] small = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    String[] tens = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    String[] big = {"", "Thousand", "Million", "Billion", "Trillion" };

    String convert(int num) {
        String res = "";
        if (num < 20) {
            return small[num];
        }
        int c = num % 100;
        if (c == 0) {
        }
        else if (c < 20) {
            res = small[c] + " " + res;
        }
        else {
            int c1 = c % 10;
            if (c1 != 0) {
                res = small[c1] + " " +  res;
            }
            res = tens[c / 10] + " " + res;
        }
        num = num /100;
        if (num != 0) {
            res = small[num] + " Hundred " + res;
        }
        return res;
    }

    String convertWhole(int num) {
        System.out.print(num + " ");
        if(num == 0) {
            return small[num];
        }
        String res = "";
        int bigIndex = 0;
        while (num > 0) {
            int c = num % 1000;
            String chunk = convert(c);
            if (chunk != "zero") {
                res = ", " + chunk + " " + big[bigIndex] + res;
            }
            bigIndex++;
            num = num /1000;
        }
        return res;
    }

    public static void main(String args[]) {
        NumberTOString n = new NumberTOString();
        System.out.println(n.convertWhole(0));
        System.out.println(n.convertWhole(1));
        System.out.println(n.convertWhole(10));
        System.out.println(n.convertWhole(11));
        System.out.println(n.convertWhole(19));
        System.out.println(n.convertWhole(20));
        System.out.println(n.convertWhole(21));
        System.out.println(n.convertWhole(29));
        System.out.println(n.convertWhole(30));
        System.out.println(n.convertWhole(40));
        System.out.println(n.convertWhole(99));
        System.out.println(n.convertWhole(100));
        System.out.println(n.convertWhole(101));
        System.out.println(n.convertWhole(109));
        System.out.println(n.convertWhole(110));
        System.out.println(n.convertWhole(111));
        System.out.println(n.convertWhole(119));
        System.out.println(n.convertWhole(120));
        System.out.println(n.convertWhole(121));
        System.out.println(n.convertWhole(129));
        System.out.println(n.convertWhole(130));
        System.out.println(n.convertWhole(140));
        System.out.println(n.convertWhole(250));
        System.out.println(n.convertWhole(999));
        System.out.println(n.convertWhole(1000));
        System.out.println(n.convertWhole(1999));
        System.out.println(n.convertWhole(10999));
        System.out.println(n.convertWhole(11999));
        System.out.println(n.convertWhole(20999));
        System.out.println(n.convertWhole(21999));
        System.out.println(n.convertWhole(30999));
        System.out.println(n.convertWhole(109999));
        System.out.println(n.convertWhole(110999));
        System.out.println(n.convertWhole(111999));
        System.out.println(n.convertWhole(120999));
        System.out.println(n.convertWhole(129999));
        System.out.println(n.convertWhole(130999));
        System.out.println(n.convertWhole(230999));
        System.out.println(n.convertWhole(1000999));
        System.out.println(n.convertWhole(1001999));
        System.out.println(n.convertWhole(1011999));
        System.out.println(n.convertWhole(1100999));
        System.out.println(n.convertWhole(1101999));
        System.out.println(n.convertWhole(1111999));
        System.out.println(n.convertWhole(1120999));
        System.out.println(n.convertWhole(1123999));
        System.out.println(n.convertWhole(1234999));
        System.out.println(n.convertWhole(9999999));
        System.out.println(n.convertWhole(999999999));
        System.out.println(n.convertWhole(1000000999));
    }
}
