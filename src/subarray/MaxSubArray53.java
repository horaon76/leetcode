package subarray;

/**
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 **/
public class MaxSubArray53 {

    public static int maxSubArrayO_N(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int  i =0;i<nums.length;i++){
            sum+=nums[i];
            maxSum =  Math.max(maxSum,sum);
            if(sum<0){
                sum = 0;
            }
        }
        return maxSum;

    }
    public static int maxSubArray(int[] nums) {
        int res = nums[0];

        // Outer loop for starting point of subarray
        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;

            // Inner loop for ending point of subarray
            for (int j = i; j < nums.length; j++) {
                currSum = currSum + nums[j];

                // Update res if currSum is greater than res
                res = Math.max(res, currSum);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums1 = new int[]{-2,-3, -10, -5};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArrayO_N(nums1));

    }
}
