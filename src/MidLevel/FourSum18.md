LeetCode problem 18 is **"4Sum"**, where you are given an integer array `nums` and an integer `target`. The task is to return all unique quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:
- `a != b != c != d`
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

### Problem Statement:
Given an array `nums` of `n` integers, return all the unique quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:
- `nums[a] + nums[b] + nums[c] + nums[d] == target`
- No two quadruplets should be the same.

### Approach 1: Sorting + Two Pointers (Optimal Solution)
This approach extends the 3Sum problem to 4Sum using two nested loops combined with the two-pointer technique.

#### Steps:
1. Sort the array `nums`.
2. Use two loops for `i` and `j` to fix two numbers, then apply the two-pointer technique to find the remaining two numbers.
3. Skip duplicates to ensure the uniqueness of quadruplets.

#### Code (Java):
```java
import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for i

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates for j

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = solution.fourSum(nums, target);
        System.out.println(result);
    }
}
```

### Approach 2: Hash Map with Pair Sum (Alternative Approach)
This approach uses a hash map to store pairs of numbers and their sums, allowing you to look up two pairs that together meet the target.

#### Code (Java):
```java
import java.util.*;

public class SolutionHashMap {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums);
        Map<Integer, List<int[]>> map = new HashMap<>();
        Set<List<Integer>> set = new HashSet<>();

        // Store all pair sums in the map
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(new int[]{i, j});
            }
        }

        // Iterate through the array and find two pairs whose sums add to the target
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int complement = target - nums[i] - nums[j];
                if (map.containsKey(complement)) {
                    for (int[] pair : map.get(complement)) {
                        if (pair[0] > j) { // Ensure no overlap
                            List<Integer> quadruplet = Arrays.asList(nums[i], nums[j], nums[pair[0]], nums[pair[1]]);
                            Collections.sort(quadruplet);  // Sorting to avoid duplicate sets
                            set.add(quadruplet);
                        }
                    }
                }
            }
        }

        result.addAll(set);
        return result;
    }

    public static void main(String[] args) {
        SolutionHashMap solution = new SolutionHashMap();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = solution.fourSum(nums, target);
        System.out.println(result);
    }
}
```

### Approach 3: Brute Force (Naive Approach)
This approach checks all quadruplets using four nested loops. However, this approach is inefficient with **O(n⁴)** time complexity and should only be used for small inputs.

#### Code (Java):
```java
import java.util.*;

public class SolutionBruteForce {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> quadruplet = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(quadruplet);
                            set.add(quadruplet);
                        }
                    }
                }
            }
        }

        result.addAll(set);
        return result;
    }

    public static void main(String[] args) {
        SolutionBruteForce solution = new SolutionBruteForce();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = solution.fourSum(nums, target);
        System.out.println(result);
    }
}
```

### Summary of Approaches:
1. **Sorting + Two Pointers**: This is the most optimal solution with **O(n³)** time complexity. It works by fixing two numbers and using two pointers to find the other two numbers.
2. **Hash Map with Pair Sum**: This approach uses a hash map to store pair sums and is still **O(n³)** in time complexity but may add complexity in terms of memory usage.
3. **Brute Force**: A naive approach with **O(n⁴)** time complexity and is inefficient for large inputs.

The **Sorting + Two Pointers** method is the most commonly used approach due to its balance of simplicity and performance.