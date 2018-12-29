/**
 *
 * Class to implement Get Max Sub Array Sum
 * @author Sharayu Sharad Mantri
 *
 */
package MaxSubArraySum;

public class MaxSubArraySum {

    //Divide and Conquer Algorithm takes O(NlogN)
    //Hence, Kadane's approch is better since
    //Run Time : O(N)
    public static int kadanesMethod(int[] input) {
        int sum = 0, ans = 0, startIndex = -1, lastIndex = -1;
        boolean flag = false;

        for (int i = 0; i < input.length; i++) {
            if (sum + input[i] > ans || flag) {
                sum = sum + input[i];
                ans = sum;

                if (startIndex == -1 || flag ) {
                    startIndex = lastIndex = i;
                    flag = false;
                }
                else {
                    lastIndex = i;
                }
            }
            else if (sum + input[i] < 0){
                sum = 0;
                flag = true;
            }
            else {
                sum = sum + input[i];
            }
        }
        System.out.println(" Max Sub Array Sum is " + ans + " start index of the sub array is " + startIndex + " and End Index is " + lastIndex);
        return ans;
    }

    public static int kadanesMethodWithoutIndex(int[] input) {
        int sum = 0, ans = 0;
        for (int i = 0; i < input.length; i++) {
            if (sum + input[i] > 0) {
                sum += input[i];
            }
            else {
                sum = 0;
            }
            ans = ans > sum ? ans : sum;
        }
        System.out.println(" Max sub array sum is " + ans);
        return ans;
    }

    public static void main(String args[]) {
        int[] input = {1, -3, 2, -5, 7, 6, -1, -4, 11, -23 };
        kadanesMethod(input);
        kadanesMethodWithoutIndex(input);
        int[] input2 = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        kadanesMethod(input2);
        kadanesMethodWithoutIndex(input2);
    }
}
