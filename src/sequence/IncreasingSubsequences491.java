package sequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements.
// You may return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [4,6,7,7]
//Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
//Example 2:
//
//Input: nums = [4,4,3,2,1]
//Output: [[4,4]]
public class IncreasingSubsequences491 {

    int[] arr;
    HashSet<List<Integer>> hashSet = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        arr = nums;
        List<Integer> arrayList = new ArrayList<>();
        recursion(arrayList, 0);
        List<List<Integer>> result = new ArrayList<>(hashSet);
        return result;
    }

    public void recursion(List<Integer> arrayList, int index) {
        if (arrayList.size() >= 2) hashSet.add(new ArrayList(arrayList));

        for (int i = index; i < arr.length; i++) {
            if (arrayList.size() == 0 || arr[i] >= arrayList.get(arrayList.size() - 1)) {
                arrayList.add(arr[i]);
                recursion(arrayList, i + 1);
                arrayList.remove(arrayList.size() - 1);
            }
        }

    }

}

/*
* Explanation
Backtracking Steps
Recursive Exploration:

At each index, decide whether to include the current element in the subsequence.
Skip the current element if:
It violates the non-decreasing order.
It is a duplicate at the current recursion level.
Base Case:

Add the current subsequence to the result if its length is ≥ 2.
Backtrack:

After exploring all options from a given state, remove the last element to explore other possibilities.
Dry Run
Input: nums = [4, 6, 7, 7]
Start at index = 0, current = [].
Add 4, recurse to index = 1.
Add 6, recurse to index = 2.
Add 7, recurse to index = 3.
Add 7, complete subsequence [4, 6, 7, 7].
Backtrack to [4, 6, 7].
Backtrack to [4, 6].
Add 7 (skip duplicate 7 at same recursion level), recurse to [4, 7].
Continue similarly.
Output:
[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7, 7]]

Complexity Analysis
Time Complexity:
There are
2
𝑛
2
n
  subsequences for an array of size
𝑛
n.
In the worst case, we generate all subsequences and filter valid ones, leading to
𝑂
(
𝑛
×
2
𝑛
)
O(n×2
n
 ).
Space Complexity:
The recursion stack depth is
𝑂
(
𝑛
)
O(n).
Additional space is used for the current list and used set, leading to
𝑂
(
𝑛
)
O(n).
*
* import java.util.*;

public class IncreasingSubsequences {
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<Set<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] >= nums[j]) {
                    for (List<Integer> subsequence : dp.get(j)) {
                        List<Integer> newSubsequence = new ArrayList<>(subsequence);
                        newSubsequence.add(nums[i]);
                        dp.get(i).add(newSubsequence);
                    }
                }
            }
            dp.get(i).add(Collections.singletonList(nums[i]));
        }

        Set<List<Integer>> result = new HashSet<>();
        for (Set<List<Integer>> set : dp) {
            for (List<Integer> subsequence : set) {
                if (subsequence.size() > 1) {
                    result.add(subsequence);
                }
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        for (List<Integer> seq : subsequences) {
            System.out.println(seq);
        }
    }
}

* */