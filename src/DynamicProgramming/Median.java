/**
 * Class to Implement Median
 *
 * @author Sharayu Sharad Mantri
 * Date : 5/28/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;


/*
find median of two sorted arrays
 */
public class Median {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        int start = 0;
        int end = len1;

        while (start <= end) {

            int px = (start + end)/2;
            int py = (len1 + len2 +1)/2 - px;

            int maxX = px == 0 ? Integer.MIN_VALUE : nums1[px-1];
            int minX = px == len1 ? Integer.MAX_VALUE : nums1[px];

            int maxY = py == 0 ? Integer.MIN_VALUE : nums2[py-1];
            int minY = py == len2 ? Integer.MAX_VALUE : nums2[py];

            if (maxX <= minY && maxY <= minX) {

                if ((len1 + len2) % 2 == 0) {
                    return (double)(Math.max(maxX, maxY) + Math.min(minX, minY))/2;
                }
                else {
                    return (double)Math.max(maxX, maxY);
                }
            }
            else if (maxX > minY) {
                end = px -1;
            }
            else {
                start = px + 1;
            }
        }
        return 0;
    }
}
