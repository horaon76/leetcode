package Hard;

import java.util.HashMap;

/**
 * You are given several boxes with different colors represented by different positive numbers.
 * <p>
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 * <p>
 * Return the maximum points you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 * <p>
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 * <p>
 * Input: boxes = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 **/
public class RemoveBoxes546 {

    public static int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        int result = calculatePoints(boxes, 0, n - 1, 0, dp);
        for (int i = 0; i < n; i++) {
            System.out.println("Layer " + (i + 1) + ":");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(
                            dp[i][j][k] + " "
                    );
                }
                System.out.println();  // Move to the next row
            }
            System.out.println();  // Move to the next layer
        }
        return result;
    }

    private static int calculatePoints(int[] boxes, int i, int j, int k, int[][][] dp) {
        //1, 3, 2, 2, 2, 3, 4, 3, 1
        if (i > j) return 0; // Base case: no boxes left to remove
        if (dp[i][j][k] != 0) return dp[i][j][k]; // Memoization: if already solved, return result

        // Extend the range to count consecutive boxes of the same color
        while (j > i && boxes[j] == boxes[j - 1]) {
            j--;
            k++;
        }

        // Remove the current group of boxes and calculate the points
        dp[i][j][k] = calculatePoints(boxes, i, j - 1, 0, dp) + (k + 1) * (k + 1);

        // Try merging with other blocks of the same color in between
        for (int m = i; m < j; m++) {
            if (boxes[m] == boxes[j]) {
                dp[i][j][k] = Math.max(dp[i][j][k],
                        calculatePoints(boxes, i, m, k + 1, dp) + calculatePoints(boxes, m + 1, j - 1, 0, dp));
            }
        }

        return dp[i][j][k];
    }

    //n^3
    public static int removeBoxesRecursive(int[] boxes) {
        HashMap<String, Integer> memo = new HashMap<>();
        int result = helper(boxes, 0, boxes.length - 1, 0, memo);
        return result;
    }

    private static int helper(int[] boxes, int i, int j, int k, HashMap<String, Integer> memo) {
        // If there are no boxes to remove, return 0
        if (i > j) return 0;

        // Create a unique key for memoization
        String key = i + "," + j + "," + k;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Process current box: Extend the range by considering same-colored boxes
        while (i + 1 <= j && boxes[i + 1] == boxes[i]) {
            i++;
            k++;
        }

        // Option 1: Remove the contiguous group of boxes (i to j)
        int result = (k + 1) * (k + 1) + helper(boxes, i + 1, j, 0, memo);

        // Option 2: Try to merge non-contiguous same-colored boxes
        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                result = Math.max(result, helper(boxes, i + 1, m - 1, 0, memo) + helper(boxes, m, j, k + 1, memo));
            }
        }

        memo.put(key, result);
        return result;
    }
    public static void main(String[] args) {
        int[] boxes = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println(removeBoxesRecursive(boxes));
    }
}
