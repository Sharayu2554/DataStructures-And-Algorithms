/**
 * Class to Implement Power
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/26/19
 * Project : BasicFunctions
 **/

package BasicFunctions;

public class Power {

    //Naive apporach complexity is O(N)
    //This apporach complexity if O(log N)
    //Recurrence Relation : T(n) = T(n/2) + 1
    public static int power(int x, int n) {
        if (n == 0)
            return 1;
        else {
            int p = power(x * x, n/2);
            return n %2 == 0 ? p : p * x;
        }
    }

    public static void main(String args[]) {
        System.out.println(" 4 ^ 2 : " + power(4, 2));
        System.out.println(" 4 ^ 3 : " + power(4, 3));
        System.out.println(" 4 ^ 4 : " + power(4, 4));
    }
}
