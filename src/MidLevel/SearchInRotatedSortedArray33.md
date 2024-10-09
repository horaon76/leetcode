LeetCode problem 33 is **"Search in Rotated Sorted Array"**, where you are given a rotated sorted array and a target value. You need to search for the target and return its index. If it's not found, return `-1`.

### Problem Statement:
Given an array of integers `nums` sorted in ascending order, rotated at some pivot unknown to you beforehand (e.g., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`), and a target value, write a function to search for `target` in `nums`. If `target` exists, return its index; otherwise, return `-1`.

### Approach: Modified Binary Search (Optimal Solution)
The key to solving this problem is to modify the binary search to handle the rotated array. Even though the array is rotated, one half will always be sorted, and we can determine which side to search based on the target's value.

#### Steps:
1. Find the middle element of the array.
2. Determine which part of the array (left or right) is sorted.
3. If the target lies within the sorted half, search that half; otherwise, search the other half.
4. Repeat the process with binary search until the target is found or the subarray size reduces to zero.

#### Time Complexity: **O(log n)**

#### Code (Java):
```java
public class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid is the target
            if (nums[mid] == target) return mid;

            // Determine which part is sorted
            if (nums[left] <= nums[mid]) {  // Left side is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;  // Search in the left half
                } else {
                    left = mid + 1;  // Search in the right half
                }
            } else {  // Right side is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Search in the right half
                } else {
                    right = mid - 1;  // Search in the left half
                }
            }
        }

        return -1;  // Target not found
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = solution.search(nums, target);
        System.out.println(result);  // Output: 4
    }
}
```

### Explanation:
1. **Initial Binary Search Setup**: We define two pointers, `left` and `right`, which represent the start and end of the search range.
2. **Determine the Sorted Half**:
    - If `nums[left] <= nums[mid]`, it means the left half is sorted.
    - Otherwise, the right half is sorted.
3. **Search Based on Target**:
    - If the left half is sorted and the target is within the range `[nums[left], nums[mid]]`, then we adjust the `right` pointer to search the left side.
    - If the right half is sorted and the target is within the range `[nums[mid], nums[right]]`, then we adjust the `left` pointer to search the right side.
4. **Final Return**: If the target is found, its index is returned. If not, return `-1`.

### Edge Cases:
- **Array with one element**: The array might contain only one element.
- **Target not present**: The target might not exist in the array.
- **Rotated by zero positions**: The array might not be rotated at all, behaving like a normal binary search problem.

#### Example 1:
Input:
```java
nums = [4,5,6,7,0,1,2], target = 0
```
Output:
```
4 (index of 0 in the array)
```

#### Example 2:
Input:
```java
nums = [4,5,6,7,0,1,2], target = 3
```
Output:
```
-1 (target not found)
```

### Summary:
The key idea is to identify which half of the array is sorted, then check if the target is in that half, and finally adjust the search range accordingly. This modification of binary search ensures **O(log n)** time complexity, making it efficient for large arrays.


Here are some alternative solutions to LeetCode 33 **"Search in Rotated Sorted Array"**:

### Approach 2: Recursive Binary Search
Instead of using an iterative approach, we can implement the modified binary search recursively. The logic remains the same, but it uses recursion to find the target.

#### Steps:
1. Use the middle element to check which half is sorted.
2. Recursively search the appropriate half depending on the target value.
3. Base case: when the left pointer crosses the right pointer, return `-1` if the target is not found.

#### Code (Java):
```java
public class SolutionRecursive {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;  // Base case: target not found
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;  // Target found
        }

        // Determine which half is sorted
        if (nums[left] <= nums[mid]) {  // Left half is sorted
            if (target >= nums[left] && target < nums[mid]) {
                return binarySearch(nums, target, left, mid - 1);  // Search in the left half
            } else {
                return binarySearch(nums, target, mid + 1, right);  // Search in the right half
            }
        } else {  // Right half is sorted
            if (target > nums[mid] && target <= nums[right]) {
                return binarySearch(nums, target, mid + 1, right);  // Search in the right half
            } else {
                return binarySearch(nums, target, left, mid - 1);  // Search in the left half
            }
        }
    }

    public static void main(String[] args) {
        SolutionRecursive solution = new SolutionRecursive();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = solution.search(nums, target);
        System.out.println(result);  // Output: 4
    }
}
```

### Explanation:
- This solution implements the same logic as the iterative one but uses recursive function calls to narrow down the search space.
- The base case occurs when `left > right`, which means the target is not in the array.

---

### Approach 3: Find Pivot and Perform Two Binary Searches
This approach divides the problem into two parts:
1. Find the pivot point where the array is rotated.
2. Once the pivot is found, the array is effectively split into two sorted subarrays. Perform a binary search on the correct subarray depending on the target value.

#### Steps:
1. Use binary search to find the pivot index where the rotation occurs.
2. Use binary search again to find the target either in the left or the right subarray.

#### Code (Java):
```java
public class SolutionPivot {
    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);

        // If pivot is found, search in the respective sorted part of the array
        if (pivot == -1) {
            return binarySearch(nums, 0, nums.length - 1, target);  // No pivot found, array is not rotated
        }

        if (nums[pivot] == target) {
            return pivot;
        }

        if (target >= nums[0]) {
            return binarySearch(nums, 0, pivot - 1, target);  // Search in the left subarray
        }

        return binarySearch(nums, pivot + 1, nums.length - 1, target);  // Search in the right subarray
    }

    private int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid;  // Pivot point found
            }

            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid - 1;  // Pivot point found
            }

            if (nums[left] >= nums[mid]) {
                right = mid - 1;  // Move to the left side
            } else {
                left = mid + 1;  // Move to the right side
            }
        }

        return -1;  // No pivot found (array is not rotated)
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SolutionPivot solution = new SolutionPivot();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = solution.search(nums, target);
        System.out.println(result);  // Output: 4
    }
}
```

### Explanation:
1. **Find the Pivot**: This is where the array has been rotated. The pivot is found using a binary search, checking if `nums[mid] > nums[mid + 1]` or `nums[mid] < nums[mid - 1]`.
2. **Binary Search**: Once the pivot is found, apply binary search on the subarray that contains the target. If the target is greater than or equal to `nums[0]`, search the left part; otherwise, search the right part.

---

### Approach 4: Linear Search (Brute Force)
A very simple approach is to perform a linear search through the array to find the target. This is the least efficient solution but guarantees a correct result.

#### Code (Java):
```java
public class SolutionLinear {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SolutionLinear solution = new SolutionLinear();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = solution.search(nums, target);
        System.out.println(result);  // Output: 4
    }
}
```

### Explanation:
- This is the brute force approach, where you iterate through the entire array to find the target. Its time complexity is **O(n)**, making it inefficient for large arrays, but it is the simplest method to implement.

---

### Summary of Approaches:
1. **Iterative Binary Search (O(log n))**: The most common and efficient approach, directly modified for rotated arrays.
2. **Recursive Binary Search (O(log n))**: A recursive version of binary search that works in a similar way.
3. **Find Pivot and Two Binary Searches (O(log n))**: First, find the pivot, then perform binary search on the appropriate half.
4. **Linear Search (O(n))**: A simple brute force method, but inefficient for large arrays.

The most optimal solution remains the **iterative binary search** with **O(log n)** complexity, but the pivot-based method can also be useful depending on the situation.