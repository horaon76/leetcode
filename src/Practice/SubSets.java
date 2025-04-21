package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>(); //Space - O(n⋅2n)
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        //total recursive calls - 2^n
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]); // O(k) -> O(n)
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    public static List<List<Integer>> subsets1(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        backtrack1(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public static void backtrack1(List<List<Integer>> result, List<Integer> subset, int[] nums, int start){
        result.add(subset);
        for(int i = start; i < nums.length; i++){
            subset.add(nums[i]);
            backtrack(result, subset, nums, i);
            subset.remove(subset.size() - 1);
        }
    }



    public static List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with the empty subset

        for (int num : nums) {
            // Create new subsets by adding the current number to each existing subset
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subset : result) {
                List<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(num);
                newSubsets.add(newSubset);
            }
            result.addAll(newSubsets); // Add all the new subsets to the result
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        //Time Complexity - O(n⋅2^n)// Space Complexity - n.2^n-1
        System.out.println(subsets(nums));
    }
}
//[
//        [],
//        [1], [1, 2], [1, 2, 3], [1, 2, 3, 4], [1, 2, 4],
//        [1, 3], [1, 3, 4], [1, 4],
//        [2], [2, 3], [2, 3, 4], [2, 4],
//        [3], [3, 4],
//        [4]
//        ]
