package DP;

import java.util.*;

public class FrogJump403 {

    public static boolean canCross(int[] stones) {
        // If the first jump is not possible, return false
        if (stones[1] != 1) return false;

        // Memoization map to store results of visited stone and last jump
        Map<Integer, Set<Integer>> memo = new HashMap<>();
        for (int stone : stones) {
            memo.put(stone, new HashSet<>());
        }

        // Starting the DFS from the first stone
        return canReachLastStone(stones, 0, 0, memo);
    }

    private static boolean canReachLastStone(int[] stones, int index, int lastJump, Map<Integer, Set<Integer>> memo) {
        int currentStone = stones[index];

        // If we've reached the last stone, return true
        if (index == stones.length - 1) return true;

        // If this combination of stone and jump has been visited, skip it
        if (memo.get(currentStone).contains(lastJump)) return false;

        // Try all possible next jumps (lastJump - 1, lastJump, lastJump + 1)
        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {
            if (jump <= 0) continue; // Skip invalid jumps

            int nextPosition = currentStone + jump;
            int nextIndex = findStoneIndex(stones, nextPosition);

            if (nextIndex != -1) {
                if (canReachLastStone(stones, nextIndex, jump, memo)) {
                    return true;
                }
            }
        }

        // Mark this combination of stone and jump as visited
        memo.get(currentStone).add(lastJump);
        return false;
    }

    private static int findStoneIndex(int[] stones, int target) {
        // Binary search to find the stone at target position
        int left = 0, right = stones.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (stones[mid] == target) return mid;
            if (stones[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(canCross(stones)); // Output: true
    }
}

//Time Complexity:
//O(N^2) where N is the number of stones, as the algorithm checks for possible jumps for each stone.