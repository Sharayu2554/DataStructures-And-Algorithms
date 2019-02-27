/**
 * Class to Implement BooleanEval
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/25/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.HashMap;

public class BooleanEval {

    int countEval(String s, boolean result, HashMap<String, Integer> memo) {

        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool(s) == result ? 1 : 0 ;

        if (memo.containsKey(result + s)) return memo.get(result + s);

        String left = "", right= "";
        int count = 0;
        for (int i=1; i<s.length(); i+=2) {
            left = s.substring(0, i);
            char c = s.charAt(i);
            right = s.substring(i+1, s.length());

            int leftTrue = countEval(left, true, memo);
            int leftFalse = countEval(left, false, memo);
            int rightTrue = countEval(right, true, memo);
            int rightFalse = countEval(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
            int trueCount = 0;
            if (c == '|') {
                trueCount += leftTrue * rightTrue +
                        leftTrue * rightFalse +
                        leftFalse * rightTrue ;
            }
            else if (c == '&') {
                trueCount += leftTrue * rightTrue;
            }
            else if (c == '^') {
                trueCount += leftTrue * rightFalse +
                        leftFalse * rightTrue;
            }
            count += result ? trueCount : total - trueCount;
        }
        memo.put(result + s, count);
        return count;
    }

    boolean stringToBool(String c){
        return c.equals("1") ? true : false;
    }

    public static void main(String args[]) {
        BooleanEval b = new BooleanEval();
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println(" count ways " + b.countEval("1^0|0|1", false, map));
    }
}
