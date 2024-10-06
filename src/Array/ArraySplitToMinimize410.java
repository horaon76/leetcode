package Array;

/**
 * Solution Outline:
 * We want to minimize the largest sum among the subarrays. A binary search approach can be used to find the solution by determining the minimum possible "largest sum" using these steps:
 * <p>
 * The minimum possible value for the largest subarray sum is the maximum number in the array (because at least one subarray will contain this number).
 * The maximum possible value for the largest subarray sum is the sum of all elements in the array (when the whole array is considered as a single subarray).
 * Use binary search between these two values to find the smallest possible largest sum where the array can be split into m subarrays.
 * Binary Search and Greedy Approach:
 * The idea is to use binary search to minimize the largest sum.
 * For each candidate sum (mid of binary search range), check if it's possible to split the array into at most m subarrays where no subarray sum exceeds this candidate.
 **/
public class ArraySplitToMinimize410 {

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num); // The minimum largest sum must be at least the max element
            right += num;               // The maximum largest sum is the sum of all elements
        }

        // Perform binary search between left and right
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Check if we can split into m subarrays with largest sum <= mid
            if (canSplit(nums, m, mid)) {
                right = mid; // Try to find a smaller largest sum
            } else {
                left = mid + 1; // Increase the candidate largest sum
            }
        }

        return left; // The minimized largest sum
    }

    private boolean canSplit(int[] nums, int m, int maxSum) {
        int currentSum = 0;
        int subarrays = 1; // Start with one subarray

        for (int num : nums) {
            currentSum += num;

            // If adding this number exceeds maxSum, start a new subarray
            if (currentSum > maxSum) {
                subarrays++;
                currentSum = num; // Start the new subarray with the current number

                if (subarrays > m) {
                    return false; // Too many subarrays
                }
            }
        }

        return true; // Successfully split into m or fewer subarrays
    }
}
/**
 * Explanation:
 * Binary Search: We search between the max value of the array (minimum possible largest sum) and the total sum (maximum possible largest sum).
 * Greedy Check (canSplit): For each candidate sum, we check if it's possible to split the array into at most m subarrays, such that the largest sum of any subarray is less than or equal to the candidate sum.
 * Return: Once the binary search converges, left will hold the minimized largest sum.
 * Time Complexity:
 * O(n log(sum(nums) - max(nums))):
 * Binary search runs in O(log(sum(nums) - max(nums)))
 * For each mid value in binary search, we iterate over the array in O(n) to check if the split is possible.
 * **/
