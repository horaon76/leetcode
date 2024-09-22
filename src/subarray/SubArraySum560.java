package subarray;

import java.util.HashMap;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * **/
public class SubArraySum560 {
    /**
     * Complexity Analysis
     *
     * Time complexity : O(n
     * 3
     *  ). Considering every possible subarray takes O(n
     * 2
     *  ) time. For each of the subarray we calculate the sum taking O(n) time in the worst case, taking a total of O(n
     * 3
     *  ) time.
     *
     * Space complexity : O(1). Constant space is used.
     * **/
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    public static int subarraySum1(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }
    public static void main(String args[]) {
        int[] nums = new int[]{1, 1, 1};
        int[] nums1 = new int[]{3,4,7,2,-3,1,4,2};
        System.out.println(subarraySum(nums, 2));
        System.out.println(subarraySum1(nums1, 7));
    }
}
