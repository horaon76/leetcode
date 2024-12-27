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

    public static void main(String args[]) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(subsets(nums));
    }
}
//To generate subsets iteratively, we can use the **iterative approach** where we start with an empty subset and iteratively add each element to all existing subsets. Here's the implementation in **Java**:
//
//### Intuition
//1. Start with an empty subset: `[]`.
//2. For each element in the input array:
//   - Take all existing subsets.
//   - Create new subsets by adding the current element to each existing subset.
//   - Add these new subsets to the list of subsets.
//3. Repeat for all elements in the array.
//
//This way, we iteratively build all possible subsets.
//
//---
//
//### Java Implementation
//
//```java
//import java.util.*;
//
//public class SubsetsIterative {
//
//    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(new ArrayList<>()); // Start with the empty subset
//
//        for (int num : nums) {
//            // Create new subsets by adding the current number to each existing subset
//            List<List<Integer>> newSubsets = new ArrayList<>();
//            for (List<Integer> subset : result) {
//                List<Integer> newSubset = new ArrayList<>(subset);
//                newSubset.add(num);
//                newSubsets.add(newSubset);
//            }
//            result.addAll(newSubsets); // Add all the new subsets to the result
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        List<List<Integer>> subsets = subsets(nums);
//        System.out.println("All subsets:");
//        for (List<Integer> subset : subsets) {
//            System.out.println(subset);
//        }
//    }
//}
//```
//
//---
//
//### Example Walkthrough
//#### Input: `nums = [1, 2, 3]`
//
//1. Start with: `result = [[]]`
//2. Add `1`:
//   - Existing subsets: `[]`
//   - New subsets: `[1]`
//   - Result: `[[], [1]]`
//3. Add `2`:
//   - Existing subsets: `[], [1]`
//   - New subsets: `[2], [1, 2]`
//   - Result: `[[], [1], [2], [1, 2]]`
//4. Add `3`:
//   - Existing subsets: `[], [1], [2], [1, 2]`
//   - New subsets: `[3], [1, 3], [2, 3], [1, 2, 3]`
//   - Result: `[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]`
//
//---
//
//### Time Complexity
//- **Outer loop** iterates through all `n` elements: \(O(n)\).
//- **Inner loop** iterates through all existing subsets at each step, which grows exponentially: \(O(2^n)\).
//- **Adding new subsets**: Each subset has at most `n` elements, so copying takes \(O(n)\).
//
//**Overall Time Complexity:** \(O(n \cdot 2^n)\).
//
//### Space Complexity
//- The total space needed for the result list is proportional to the number of subsets: \(O(2^n)\).
//- Each subset can have up to `n` elements, so the total space is \(O(n \cdot 2^n)\).
//
//**Overall Space Complexity:** \(O(n \cdot 2^n)\).