package Greedy;

import java.util.*;

public class MergeInterval56 {
    public int[][] merge(int[][] intervals) {
        // Step 1: Sort the intervals by the starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Initialize a list to hold the merged intervals
        List<int[]> merged = new ArrayList<>();

        // Step 3: Merge intervals
        for (int[] interval : intervals) {
            // If merged is empty or there is no overlap, add the interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // There is overlap, so merge the intervals
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        // Convert List<int[]> to int[][]
        return merged.toArray(new int[merged.size()][]);
    }
}
/**
 * Time Complexity:
 * 𝑂
 * (
 * 𝑛
 * log
 * ⁡
 * 𝑛
 * )
 * O(nlogn) due to the sorting step, where
 * 𝑛
 * n is the number of intervals.
 * Space Complexity
 * Space Complexity:
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n) for the merged list of intervals, which in the worst case could hold all intervals if none overlap.
 **/
