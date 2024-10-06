package Hard;

/**
 * Given four integers sx, sy, tx, and ty, return true if it is possible to convert the point (sx, sy) to the point (tx, ty) through some operations, or false otherwise.
 * <p>
 * The allowed operation on some point (x, y) is to convert it to either (x, x + y) or (x + y, y).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: true
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * Example 2:
 * <p>
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: false
 * Example 3:
 * <p>
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sx, sy, tx, ty <= 109
 **/

public class ReachingPoints780 {

    public static boolean canReachRecursive(int sx, int sy, int tx, int ty) {
        // Base case: if the target is smaller than the starting point in both dimensions
        if (tx < sx || ty < sy) {
            return false;
        }

        // If the starting point matches the target point
        if (tx == sx && ty == sy) {
            return true;
        }

        // Reverse the operations
        // Use modulo to find the previous state
        if (tx > ty) {
            return canReachRecursive(sx, sy, tx - ty, ty); // Move from (x + y, y) to (x, y)
        } else {
            return canReachRecursive(sx, sy, tx, ty - tx); // Move from (x, x + y) to (x, y)
        }
    }

    public boolean canReach(int sx, int sy, int tx, int ty) {
        // If we surpass either target, return false
        while (tx >= sx && ty >= sy) {
            if (tx == sx && ty == sy) {
                return true;
            }
            if (tx > ty) {
                tx -= ty; // Reverse Operation 1
            } else {
                ty -= tx; // Reverse Operation 2
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int sx = 1, sy = 1;
        int tx = 3, ty = 5;
        boolean result = canReachRecursive(sx, sy, tx, ty);
        System.out.println("Can reach: " + result);
    }
}
//Space & Time - O(log(max(tx, ty)))