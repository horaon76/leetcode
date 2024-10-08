package Hard;
/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array. (Only one repeated value is considered in the valid path).
 * The score is defined as the sum of unique values in a valid path.
 *
 * Return the maximum score you can obtain of all possible valid paths. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 * Example 2:
 *
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 * Example 3:
 *
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[i] <= 107
 * nums1 and nums2 are strictly increasing.
 * **/
public class MaxScore1537 {

    public static int maxSum(int[] nums1, int[] nums2) {
        int MOD = 1_000_000_007;
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0;
        long result = 0;

        // Traverse both arrays
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                // Add to sum1 if nums1[i] is smaller
                sum1 += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                // Add to sum2 if nums2[j] is smaller
                sum2 += nums2[j++];
            } else {
                // Common element found, choose the maximum of both sums and add it to result
                result += Math.max(sum1, sum2) + nums1[i];
                result %= MOD;
                // Move past the common element in both arrays and reset the sums
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        // Add the remaining elements from nums1 and nums2
        while (i < nums1.length) {
            sum1 += nums1[i++];
        }

        while (j < nums2.length) {
            sum2 += nums2[j++];
        }

        // Add the maximum of the remaining sums
        result += Math.max(sum1, sum2);
        result %= MOD;

        return (int) result;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 4, 5, 8, 10};
        int[] nums2 = {4, 6, 8, 9};
        System.out.println(maxSum(nums1, nums2));  // Output: 30

        int[] nums3 = {1, 3, 5, 7, 9};
        int[] nums4 = {3, 5, 100};
        System.out.println(maxSum(nums3, nums4));  // Output: 109
    }
}