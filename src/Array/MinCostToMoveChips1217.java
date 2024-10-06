package Array;

/**
 * We have n chips, where the position of the ith chip is position[i].
 * <p>
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 * <p>
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 **/
public class MinCostToMoveChips1217 {

    public int minCostToMoveChips(int[] position) {
        int evenCount = 0;
        int oddCount = 0;

        // Count the number of chips at even and odd positions
        for (int pos : position) {
            if (pos % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        // The cost is the minimum of moving all chips to either even or odd positions
        return Math.min(evenCount, oddCount);
    }
}
//O(n): We iterate through the position array once to count the even and odd chips.
/**
 * The space complexity of the solution to LeetCode 1217 is O(1), which means constant space. Here's why:
 *
 * The algorithm only uses a few integer variables (evenCount, oddCount) to store the count of chips at even and odd positions, regardless of the size of the input array position.
 * No additional space (such as arrays, lists, or hash maps) is allocated that depends on the input size n.
 * Since the space used does not grow with the size of the input array, the space complexity is O(1).
 *
 * **/
