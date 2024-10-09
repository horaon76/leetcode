package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals56 {

    public static int[][] merge(int[][] intervals) {
        // Edge case: If there are no intervals, return an empty array
        if (intervals.length == 0) {
            return new int[0][];
        }

        // Sort intervals based on the starting value of each interval
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Use a list to store the merged intervals
        List<int[]> merged = new ArrayList<>();

        // Start with the first interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // If the intervals overlap, merge them by updating the end of the current interval
            if (currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // If they don't overlap, move to the next interval
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        // Convert the list of intervals to a 2D array and return it
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = merge(intervals);
        System.out.println(Arrays.deepToString(result)); // Output: [[1, 6], [8, 10], [15, 18]]
    }
}
/**
 * Key points:
 * Time Complexity: Sorting the intervals takes O(n log n) time, where n is the number of intervals. Merging them takes O(n) time. Thus, the total time complexity is O(n log n).
 * Space Complexity: The space complexity is O(n) for storing the merged intervals.
 *
 Let's perform a **dry run** of the provided Java solution and discuss alternate approaches.

 ### Input Example: `intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]`

 #### Step 1: Sort the intervals by their starting value
 - Sorted intervals: `[[1, 3], [2, 6], [8, 10], [15, 18]]`
 - Already sorted, so no changes.

 #### Step 2: Initialize `currentInterval` to the first interval `[1, 3]`
 - Add `[1, 3]` to the `merged` list.

 #### Step 3: Iterate through the intervals:
 1. Compare the current interval `[1, 3]` with the next interval `[2, 6]`:
 - `currentEnd = 3`, `nextStart = 2`, `nextEnd = 6`.
 - Since `currentEnd >= nextStart`, the intervals overlap. So, merge them by updating `currentInterval[1] = max(3, 6) = 6`.
 - The `merged` list now holds: `[[1, 6]]`.

 2. Compare `currentInterval = [1, 6]` with the next interval `[8, 10]`:
 - `currentEnd = 6`, `nextStart = 8`, `nextEnd = 10`.
 - Since `currentEnd < nextStart`, there's no overlap. Move to the next interval.
 - Add `[8, 10]` to `merged`. The list is now: `[[1, 6], [8, 10]]`.

 3. Compare `currentInterval = [8, 10]` with the next interval `[15, 18]`:
 - `currentEnd = 10`, `nextStart = 15`, `nextEnd = 18`.
 - Since `currentEnd < nextStart`, there's no overlap. Move to the next interval.
 - Add `[15, 18]` to `merged`. The list is now: `[[1, 6], [8, 10], [15, 18]]`.

 #### Step 4: Return the `merged` list: `[[1, 6], [8, 10], [15, 18]]`.

 ### Alternate Solutions:

 #### 1. **Using a Stack**

 We can use a stack to help manage the merging process, pushing the intervals onto the stack and merging the top of the stack with the next interval if they overlap.

 Here’s a solution using a stack:

 ```java
 import java.util.*;

 public class SolutionStack {
 public int[][] merge(int[][] intervals) {
 if (intervals.length == 0) {
 return new int[0][];
 }

 Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // Sort by start

 Stack<int[]> stack = new Stack<>();
 stack.push(intervals[0]);

 for (int i = 1; i < intervals.length; i++) {
 int[] top = stack.peek();
 int[] current = intervals[i];

 // If intervals overlap, merge them
 if (top[1] >= current[0]) {
 top[1] = Math.max(top[1], current[1]);
 } else {
 stack.push(current);
 }
 }

 // Convert the stack to an array
 return stack.toArray(new int[stack.size()][]);
 }

 public static void main(String[] args) {
 SolutionStack sol = new SolutionStack();
 int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
 int[][] result = sol.merge(intervals);
 System.out.println(Arrays.deepToString(result)); // Output: [[1, 6], [8, 10], [15, 18]]
 }
 }
 ```

 **Time Complexity**: `O(n log n)` due to sorting.
 **Space Complexity**: `O(n)` due to the stack.

 #### 2. **In-Place Merging (without extra space)**

 You can merge intervals **in-place**, reducing space complexity by modifying the input array.

 ```java
 import java.util.Arrays;

 public class InPlaceSolution {
 public int[][] merge(int[][] intervals) {
 if (intervals.length == 0) {
 return new int[0][];
 }

 Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // Sort intervals

 int idx = 0; // Position to place the next merged interval

 for (int i = 1; i < intervals.length; i++) {
 // If the current interval overlaps with the previous one
 if (intervals[idx][1] >= intervals[i][0]) {
 intervals[idx][1] = Math.max(intervals[idx][1], intervals[i][1]);
 } else {
 // Move to the next interval
 idx++;
 intervals[idx] = intervals[i];
 }
 }

 // Only return up to idx+1 intervals (non-overlapping merged intervals)
 return Arrays.copyOf(intervals, idx + 1);
 }

 public static void main(String[] args) {
 InPlaceSolution sol = new InPlaceSolution();
 int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
 int[][] result = sol.merge(intervals);
 System.out.println(Arrays.deepToString(result)); // Output: [[1, 6], [8, 10], [15, 18]]
 }
 }
 ```

 **Time Complexity**: `O(n log n)` (due to sorting).
 **Space Complexity**: `O(1)` (modifying the input array in place, no additional data structures).

 #### 3. **Divide and Conquer Approach**

 Another approach to solving the problem is using **Divide and Conquer**, which can be useful when solving the problem in a distributed system.

 - **Divide** the interval list into two halves.
 - **Recursively merge** the left and right halves.
 - Finally, **merge** the two halves together.

 This approach is similar to merge sort but involves interval merging.

 **Time Complexity**: `O(n log n)`
 **Space Complexity**: `O(log n)` due to recursion depth.

 ### Conclusion:

 - The **sorting-based approach** (first solution) is the most straightforward and commonly used method.
 - The **stack-based solution** provides an alternative with a more explicit handling of the merging process.
 - The **in-place merging solution** is optimal in terms of space.
 - **Divide and conquer** can be useful in distributed environments.
 *
 *
 * **/