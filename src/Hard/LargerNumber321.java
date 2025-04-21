package Hard;


import java.util.*;

public class LargerNumber321 {
    public static void main(String[] args) {

        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] maxResult = new int[k];

        for (int i = Math.max(0, k - m); i <= Math.min(k, n); i++) {
            int[] subseq1 = maxSubsequence(nums1, i);
            int[] subseq2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(subseq1, subseq2);

            if (greater(candidate, 0, maxResult, 0)) {
                maxResult = candidate;
            }
        }

        return maxResult;
    }

    // Get max subsequence of length k
    private static int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1;
        int toRemove = nums.length - k;

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && toRemove > 0) {
                top--;
                toRemove--;
            }
            if (top + 1 < k) {
                stack[++top] = num;
            } else {
                toRemove--;
            }
        }

        return stack;
    }

    // Merge two subsequences into the max possible number
    private static int[] merge(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, pos = 0;

        while (i < nums1.length || j < nums2.length) {
            if (greater(nums1, i, nums2, j)) {
                merged[pos++] = nums1[i++];
            } else {
                merged[pos++] = nums2[j++];
            }
        }

        return merged;
    }

    // Compare two sequences starting from index i and j
    private static boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] != nums2[j])
                return nums1[i] > nums2[j];
            i++;
            j++;
        }
        return (nums1.length - i) > (nums2.length - j);
    }
}
//Idea:
//Try all possible splits: pick i digits from nums1 and k - i from nums2 where 0 ≤ i ≤ k.
//
//For each split:
//
//Get the max subsequence of length i from nums1.
//
//Get the max subsequence of length k - i from nums2.
//
//Merge them to get the largest combination.
//
//Keep track of the maximum combination.