package Graph;

import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * <p>
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * <p>
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 * <p>
 * <p>
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 **/
public class PathWithMinumumEffort1631 {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int[][] dist = new int[rows][cols];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0, 0, 0});

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int effort = top[0], x = top[1], y = top[2];

            if (effort > dist[x][y]) continue;

            if (x == rows - 1 && y == cols - 1) return effort;

            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                    int new_effort = Math.max(effort, Math.abs(heights[x][y] - heights[nx][ny]));
                    if (new_effort < dist[nx][ny]) {
                        dist[nx][ny] = new_effort;
                        minHeap.add(new int[]{new_effort, nx, ny});
                    }
                }
            }
        }
        return -1;
    }
}
/**
 *
 * Time Complexity:
 * Priority Queue Operations: Each cell can be added to the queue multiple times, but in the worst case, it is processed
 * 𝑂
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * O(R×C) times, where
 * 𝑅
 * R is the number of rows and
 * 𝐶
 * C is the number of columns.
 * Edge Relaxations: For each cell, we process 4 directions, so the total number of operations is proportional to
 * 𝑂
 * (
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * log
 * ⁡
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * )
 * O((R×C)log(R×C)).
 * Thus, the time complexity is
 * 𝑂
 * (
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * log
 * ⁡
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * )
 * O((R×C)log(R×C)), where
 * 𝑅
 * R is the number of rows and
 * 𝐶
 * C is the number of columns.
 *
 * Space Complexity:
 * Priority Queue and Effort Matrix: Both the priority queue and the effort matrix take up
 * 𝑂
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * O(R×C) space.
 * Therefore, the space complexity is
 * 𝑂
 * (
 * 𝑅
 * ×
 * 𝐶
 * )
 * O(R×C).
 * **/