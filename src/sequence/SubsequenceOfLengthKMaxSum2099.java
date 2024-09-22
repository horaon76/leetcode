package sequence;

import java.util.*;

/**
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 * <p>
 * Return any such subsequence as an integer array of length k.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * Example 2:
 * <p>
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * Example 3:
 * <p>
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 **/
public class SubsequenceOfLengthKMaxSum2099 {
    /**
     * Sub sequence does not have any order so put everything in a queue and maintain the largest in PQ.
     * **/
    public static int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> v = new ArrayList<>();
        for (int x : nums) {
            pq.add(x);
            v.add(x);
        }

        int[] ans = new int[k];

        for (int i = 0; i < nums.length - k; i++){
            Integer item = pq.peek();
            v.remove(item);
            pq.remove();
        }


        for (int i = 0; i < k; i++)
            ans[i] = v.get(i);

        return ans;
    }
    public static void main(String args[]){
        int[] nums = new int[]{2,1,3,3};
        int[] nums1 = new int[]{-1,-2,3,4};
        int[] nums2 = new int[]{3,4,3,5,1,1,1,1,9};
//        System.out.println(Arrays.toString(maxSubsequence(nums, 2)));
//        System.out.println(Arrays.toString(maxSubsequence(nums1, 3)));
        System.out.println(Arrays.toString(maxSubsequence(nums2, 2)));
    }
}
