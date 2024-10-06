package Hard;

import java.util.*;

/**
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Example 2:
 *
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Example 3:
 *
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 *
 *
 * Constraints:
 *
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * Each value board[i][j] is unique.
 * **/
public class SlidingPuzzle773 {
    private static final String TARGET = "123450";
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left

    public static int slidingPuzzle(int[][] board) {
        String start = boardToString(board);
        if (start.equals(TARGET)) return 0; // Already solved

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Check all possible moves
                List<String> nextStates = getNextStates(current);
                for (String next : nextStates) {
                    if (next.equals(TARGET)) {
                        return steps + 1; // Found the target
                    }
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }

        return -1; // If no solution found
    }

    private static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    private static List<String> getNextStates(String state) {
        List<String> nextStates = new ArrayList<>();
        char[] chars = state.toCharArray();
        int zeroIndex = state.indexOf('0');

        // Possible moves based on the index of '0'
        for (int[] dir : DIRECTIONS) {
            int newRow = zeroIndex / 3 + dir[0];
            int newCol = zeroIndex % 3 + dir[1];
            int newZeroIndex = newRow * 3 + newCol;

            // Check if the new position is valid
            if (newRow >= 0 && newRow < 2 && newCol >= 0 && newCol < 3) {
                // Swap '0' with the adjacent number
                char temp = chars[newZeroIndex];
                chars[newZeroIndex] = '0';
                chars[zeroIndex] = temp;
                nextStates.add(new String(chars));
                // Swap back to restore the original state
                chars[zeroIndex] = '0';
                chars[newZeroIndex] = temp;
            }
        }
        return nextStates;
    }

    public static void main(String[] args) {
//        int[][] board = {{1, 2, 3}, {4, 0, 5}}; // Example input
//        int result = slidingPuzzle(board);
//        System.out.println("Minimum number of moves: " + result);
        //5 moves
        int[][] board2 = {{4,1,2},{5,0,3}};
        int result2 = slidingPuzzle(board2);
        System.out.println("Minimum number of moves: " + result2);
    }
}
/**
 * The line `for (int i = 0; i < size; i++)` in the BFS implementation for LeetCode 773 (Sliding Puzzle) plays a crucial role in controlling the breadth-first search process. Let’s break down its purpose and significance in the context of the solution.
 *
 * ### Purpose of the Loop
 *
 * In the BFS implementation, this loop is used to process all nodes (or configurations, in this case) at the current level of the search before moving on to the next level. Here’s why this is important:
 *
 * 1. **Level-wise Processing**:
 *    - In BFS, nodes are explored level by level. The `size` variable holds the number of nodes that are present in the current level of the search tree.
 *    - The loop ensures that all nodes at the current level are processed before moving on to the next level. This guarantees that the first time we reach the target configuration, it will be through the shortest path (minimum moves).
 *
 * 2. **Preventing Infinite Loops**:
 *    - Without this loop, the algorithm could potentially process new configurations that were just added to the queue in the same iteration, causing it to explore deeper into the search tree rather than finishing the current level.
 *
 * ### Example Illustration
 *
 * Let’s illustrate this with an example configuration for better understanding.
 *
 * **Consider the following scenario**: You start with the board configuration `123405`, and you can generate new states:
 *
 * 1. **Current Configuration**:
 *    ```
 *    1 2 3
 *    4 0 5
 *    ```
 *
 * 2. **New Configurations Generated**:
 *    - After processing `123405`, you might generate:
 *      - `123540` (by moving `5` left)
 *      - `120345` (by moving `4` left)
 *
 * 3. **Queue After Processing**:
 *    - After this level, your queue may look like this:
 *    ```
 *    Queue: ["123540", "120345"]
 *    ```
 *
 * 4. **Process Next Level**:
 *    - The loop iterates for the current level's size (let’s say it was `2` in this case). It ensures that:
 *      - **First iteration (`i = 0`)**: It processes `123540`.
 *      - **Second iteration (`i = 1`)**: It processes `120345`.
 *    - After both are processed, you can then move on to the next level with all configurations generated in the current level.
 *
 * ### Code Context
 *
 * Here’s the relevant portion of the code again for context:
 *
 * ```java
 * while (!queue.isEmpty()) {
 *     int size = queue.size(); // Current level size
 *     for (int i = 0; i < size; i++) {
 *         String current = queue.poll(); // Dequeue a node
 *
 *         // Process the current node
 *         for (String next : getNextStates(current)) {
 *             if (next.equals(TARGET)) return steps + 1; // Found target
 *             if (!visited.contains(next)) {
 *                 queue.offer(next); // Enqueue the next state
 *                 visited.add(next); // Mark as visited
 *             }
 *         }
 *     }
 *     steps++; // Increment steps after processing the current level
 * }
 * ```
 *
 * ### Summary
 *
 * - **Control Flow**: The `for (int i = 0; i < size; i++)` loop ensures that each configuration generated from the current state is processed completely before moving on to the next state in the queue.
 * - **Breadth-First Nature**: This pattern is essential for maintaining the breadth-first search nature, ensuring that the search explores all configurations at the current level before going deeper.
 *
 * **/