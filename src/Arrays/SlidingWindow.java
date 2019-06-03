/**
 * Class to Implement SlidingWindow
 *
 * @author Sharayu Sharad Mantri
 * Date : 5/31/19
 * Project : Arrays
 **/

package Arrays;

public class SlidingWindow {

    public int subarraysWithKDistinct(int[] arr, int k) {
        short[] count = new short[arr.length+1];
        short unique=0;
        int i=0, total=0, good=0;
        for(int j=0; j<arr.length; j++){
            if(count[arr[j]]++==0) unique++;
            if(unique>k){
                count[arr[i++]]--;
                unique--;
                good=0;
            }
            while(count[arr[i]]>1){
                count[arr[i++]]--;
                good++;
            }
            if(unique==k) total += good+1;
        }
        return total;
    }

    public static void main(String args[]) {
        int[] input = {1,2,1,2,3};
        int k = 2;

        SlidingWindow s = new SlidingWindow();
        int t = s.subarraysWithKDistinct(input, k);
    }
}