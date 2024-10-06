package Hard;
import java.util.*;

public class CutOffTreesForGolfEvent675 {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Directions for moving up, down, left, right
    private static final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public static int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int rows = forest.size();
        int cols = forest.get(0).size();

        // Step 1: Extract the trees with their coordinates
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    trees.add(new int[] { height, i, j });
                }
            }
        }

        // Step 2: Sort trees by height
        trees.sort( (a, b) -> Integer.compare(a[0], b[0]));

        // Step 3: Start from (0, 0) and calculate the total steps
        int totalSteps = 0;
        int startX = 0, startY = 0;

        for (int[] tree : trees) {
            int targetX = tree[1];
            int targetY = tree[2];
            int steps = bfs(forest, startX, startY, targetX, targetY);

            if (steps == -1) {
                return -1; // If any tree is unreachable
            }

            totalSteps += steps;
            startX = targetX;
            startY = targetY;
        }

        return totalSteps;
    }

    // BFS to find the shortest path from (startX, startY) to (targetX, targetY)
    private static int bfs(List<List<Integer>> forest, int startX, int startY, int targetX, int targetY) {
        if (startX == targetX && startY == targetY) {
            return 0;
        }

        int rows = forest.size();
        int cols = forest.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                for (int[] direction : DIRECTIONS) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY] && forest.get(newX).get(newY) != 0) {
                        if (newX == targetX && newY == targetY) {
                            return steps;
                        }

                        queue.offer(new Point(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
        }

        return -1; // If unreachable
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 4),
                Arrays.asList(7, 6, 5)
        );

        int result = cutOffTree(forest);
        System.out.println("Minimum steps to cut all trees: " + result); // Output: 6
    }
}
/**
 *### Combined Time Complexity:
 *
 * So, the total time complexity of the solution is the sum of:
 *
 * 1. **Tree Sorting**: \( O(T \log T) \)
 * 2. **BFS for Each Tree**: \( O(T \times m \times n) \)
 *
 * Thus, the **overall time complexity** is:
 * \[
 * O(T \log T + T \times m \times n)
 * \]
 * Where:
 * - `T` is the number of trees.
 * - `m` is the number of rows in the grid.
 * - `n` is the number of columns in the grid.
 *
 * ### Summary:
 * - The **\(O(T \log T)\)** term comes from sorting the trees by their height.
 * - The **\(O(T \times m \times n)\)** term comes from performing BFS for each tree.
 *
 * Both terms contribute to the overall complexity, so the final time complexity is the sum of these two.
 *
 * Let me know if you have any further questions!
 * **/
//Space Complexity: 𝑂 ( 𝑚 × 𝑛 ) O(m×n), for the queue and the visited array.