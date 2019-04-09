/**
 * Class to Implement FriendPair
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/7/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

public class FriendPair {

    public static void main(String args[]) {

        int n = 2;
        int a = 1, b = 2, c = 0;
        if (n <= 2) {
            c = n;
        }
        for (int i = 3; i <= n; i++) {
            c = b + (i - 1) * a;
            a = b;
            b = c;
        }
        System.out.println(n + " pair count " + c);
    }
}
