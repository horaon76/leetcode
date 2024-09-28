package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * <p>
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * <p>
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 * <p>
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 * [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 * [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 * [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 * [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 * [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 * [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 * [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 * <p>
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 **/
public class PacificAtlanticWaterFlow417 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Initialize the boolean arrays for Pacific and Atlantic oceans
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Perform DFS from Pacific Ocean (top and left borders)
        for (int i = 0; i < rows; i++) {
            dfs(heights, pacific, i, 0); // Left border
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, pacific, 0, j); // Top border
        }

        // Perform DFS from Atlantic Ocean (bottom and right borders)
        for (int i = 0; i < rows; i++) {
            dfs(heights, atlantic, i, cols - 1); // Right border
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, atlantic, rows - 1, j); // Bottom border
        }

        // Collect cells that can flow to both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] ocean, int row, int col) {
        ocean[row][col] = true; // Mark cell as reachable by the ocean
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions for DFS

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new position is within bounds and can flow to this cell
            if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length &&
                    !ocean[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, ocean, newRow, newCol); // Recur for the adjacent cell
            }
        }
    }
}
/**
 * Complexity Analysis:
 * Time Complexity: The time complexity remains
 * 𝑂
 * (
 * 𝑛
 * ×
 * 𝑚
 * )
 * O(n×m) since every cell is processed once.
 * Space Complexity: The space complexity is also
 * 𝑂
 * (
 * 𝑛
 * ×
 * 𝑚
 * )
 * O(n×m) due to the boolean arrays used for tracking which cells can reach each ocean.
 **/
