package SubSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://www.geeksforgeeks.org/backtracking-to-find-all-subsets/
public class generateSubsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String args[]) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(subsets(nums));
    }
}
