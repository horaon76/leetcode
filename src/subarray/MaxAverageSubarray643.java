package subarray;
/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 * **/
public class MaxAverageSubarray643 {

    public static Double findAverageSum(int[] nums, int k){
        Double average = 0.0;
        int sum = 0;
        for(int i = 0; i<k; i++){
            sum += nums[i];
        }
        average = (double) (sum / k);
        for(int i = k; i< nums.length; i++){
            sum += nums[i] - nums[i - k];
            Double currentAverage = (double) sum / k;
            average = Math.max(average, currentAverage);
        }
        return average;
    }

    public static void main(String args[]){
        int k = 4;
        int[] nums = new int[]{1,12,-5,-6,50,3};
        int[] nums1 = new int[]{1,1,1,1,1,1};
        System.out.println(findAverageSum(nums, k));
        System.out.println(findAverageSum(nums1, k));
    }
}
