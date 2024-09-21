package sequence;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 **/
public class LIS300 {

    public static int getLIS(int[] nums) {
        int length = 0;
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i] && dp[i] < dp[j] +1){
                    dp[i] = dp[j] + 1;
                    length = Math.max(length, dp[i]);
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int lis = getLIS(nums);
        System.out.print(lis);
    }
}
