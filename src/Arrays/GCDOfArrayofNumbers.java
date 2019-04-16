/**
 * Class to Implement GCDOfArrayofNumbers
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/9/19
 * Project : Arrays
 **/

package Arrays;

public class GCDOfArrayofNumbers {

    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Function to find gcd of array of
    // numbers
    static int findGCD(int arr[], int n)
    {
        int result = arr[0];
        for (int i = 1; i < n; i++)
            result = gcd(arr[i], result);

        return result;
    }

    public static void main(String args[]) {
        int a, b;
        int[] myArray ={ 2, 4, 6, 8, 16 };
        int size = myArray.length;
        int result = myArray[0];
        int i = 1;
        while(i<size)
        {
            if(myArray[i]%result==0)
            {
                i++;
            } else {
                result = myArray[i]%result;
                i++;
            }
        }
        System.out.println("GCD is "+result);

        int arr[] = { 2, 4, 6, 8, 16 };
        int n = arr.length;
        System.out.println(findGCD(arr, n));
    }
}

