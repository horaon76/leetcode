package Array;

import java.util.*;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 * Example 2:
 * <p>
 * Input: stones = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 **/
public class LastStoneWeight1046 {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> maxPq = new PriorityQueue<>(stones.length, Comparator.reverseOrder());
        for (int stone : stones) {
            maxPq.add(stone);
        }
        while (maxPq.size() >= 2) {
            int y = maxPq.poll();
            int x = maxPq.poll();
            if (y > x) {
                maxPq.add(y - x);
            }
        }
        return maxPq.isEmpty() ? 0 : maxPq.peek();
    }
}
