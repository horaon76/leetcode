package Array;

import java.util.*;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * **/
public class RotateArray189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Handle k greater than n
        int[] rotated = new int[n];

        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        // Copy rotated array back to original
        System.arraycopy(rotated, 0, nums, 0, n);
    }
    public void rotateReverse(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return;
        }

        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotateCyclicReplacement(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Handle k greater than n

        int count = 0; // Count of numbers rotated
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
    public void rotateCollectionBased(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Handle k greater than n
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        Collections.rotate(list, k);

        for (int i = 0; i < n; i++) {
            nums[i] = list.get(i);
        }
    }

    public void rotateQueue(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Handle k greater than n

        Queue<Integer> queue = new LinkedList<>();

        for (int num : nums) {
            queue.offer(num);
        }

        for (int i = 0; i < k; i++) {
            queue.offer(queue.poll());
        }

        for (int i = 0; i < n; i++) {
            nums[i] = queue.poll();
        }
    }
}
/**
 *
 *
 | Approach                     | Time Complexity | Space Complexity |
 |------------------------------|------------------|------------------|
 | Extra Array                  | O(n)             | O(n)             |
 | Reverse Method               | O(n)             | O(1)             |
 | Cyclic Replacements          | O(n)             | O(1)             |
 | Java Collections             | O(n)             | O(n)             |
 | Queue                        | O(n)             | O(n)             |
 * **/