package sequence;

//https://leetcode.com/problems/longest-consecutive-sequence/

import java.util.HashSet;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 *
 * **/

public class LCS128 {

    public static int getLongestConsecutiveLength(int[] nums) throws Exception {
        if(nums == null){
            throw new Exception("Empty Input");
        }
        if(nums.length == 0){
            return 0;
        }
        HashSet<Integer> numsSet = new HashSet<>();
        for(int num: nums){
            numsSet.add(num);
        }
        int count = 0;
        for(int num: nums){
            if(!numsSet.contains(num - 1)){
                int start = 0;
                int nextSequence = num;
                do{
                    start += 1;
                    nextSequence += 1;
                }while (numsSet.contains(nextSequence));
                count = Math.max(start, count);
            }
        }
        return count;
    }

    public static void main(String args[]) throws Exception {
        int[] nums = new int[]{100,4,200,1,3,2};
        int streak = getLongestConsecutiveLength(nums);

        int[] nums2 = new int[]{0,3,7,2,5,8,4,6,0,1};
        int streak2 = getLongestConsecutiveLength(nums2);

        System.out.print(streak + ", " + streak2);
    }
}

//Time - O(n)
//Space - O(n)