package Subarray;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 * <p>
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 **/
public class MaximumLengthOfRepeatedSubarray718 {
    /**
     * Time and Space Complexity:
     * Time Complexity: O(M * N) where M and N are the lengths of nums1 and nums2 respectively.
     * Space Complexity: O(M * N) because of the dp table.
     **/
    public int findLengthDP(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength;
    }

    //Space optimized DP
//    Time and Space Complexity:
//    Time Complexity: O(M * N)
//    Space Complexity: O(N) since we only store two rows at a time.
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] dp = new int[n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            int[] currentRow = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    currentRow[j] = dp[j - 1] + 1;
                    maxLength = Math.max(maxLength, currentRow[j]);
                }
            }
            dp = currentRow;  // Move to the next row
        }

        return maxLength;
    }
}
