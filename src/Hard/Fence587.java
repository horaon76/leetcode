package Hard;
/**
 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 * <p>
 * Fence the entire garden using the minimum length of rope, as it is expensive. The garden is well-fenced only if all the trees are enclosed.
 * <p>
 * Return the coordinates of trees that are exactly located on the fence perimeter. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * Explanation: All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.
 **/

import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Fence587 {
    private static int cross(Point o, Point a, Point b) {
        int result = (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);

        if (result > 0) {
            return 1;  // Counter-clockwise
        } else if (result < 0) {
            return -1; // Clockwise
        } else {
            return 0;  // Collinear
        }
    }

    // Method to compute the convex hull
    public static int[][] outerTrees(int[][] points) {
        if (points.length <= 1) return points;

        List<Point> pointList = new ArrayList<>();
        for (int[] p : points) {
            pointList.add(new Point(p[0], p[1]));
        }

        // Sort points lexicographically (by x, then by y)
        Collections.sort(pointList, (a, b) -> a.x != b.x ? Integer.compare(a.x, b.x) : Integer.compare(a.y, b.y));

        // Build lower hull
        List<Point> lower = new ArrayList<>();
        for (Point p : pointList) {
            while (lower.size() >= 2) {
                Point last = lower.get(lower.size() - 1);
                Point secondLast = lower.get(lower.size() - 2);
                int crossProduct = cross(secondLast, last, p);
                if (crossProduct == -1) break; // Clockwise
                if (crossProduct == 0 && isBetween(secondLast, last, p)) {
                    break; // Skip adding if p is between last and secondLast
                }
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }

        // Build upper hull
        List<Point> upper = new ArrayList<>();
        for (int i = pointList.size() - 1; i >= 0; i--) {
            Point p = pointList.get(i);
            while (upper.size() >= 2) {
                Point last = upper.get(upper.size() - 1);
                Point secondLast = upper.get(upper.size() - 2);
                int crossProduct = cross(secondLast, last, p);
                if (crossProduct == -1) break; // Clockwise
                if (crossProduct == 0 && isBetween(secondLast, last, p)) {
                    break; // Skip adding if p is between last and secondLast
                }
                upper.remove(upper.size() - 1);
            }
            upper.add(p);
        }

        // Remove the last point of each half because it is repeated at the beginning of the other half
        upper.remove(upper.size() - 1);
        lower.addAll(upper);

        // Convert the result to an int[][] array
        int[][] result = new int[lower.size()][2];
        for (int i = 0; i < lower.size(); i++) {
            result[i][0] = lower.get(i).x;
            result[i][1] = lower.get(i).y;
        }
        return result;
    }

    // Function to check if point c is between points a and b
    private static boolean isBetween(Point a, Point b, Point c) {
        return (Math.min(a.x, b.x) <= c.x && c.x <= Math.max(a.x, b.x) &&
                Math.min(a.y, b.y) <= c.y && c.y <= Math.max(a.y, b.y));
    }

    // Main method for testing
    public static void main(String[] args) {
        int[][] points = {
                {1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {3, 0}, {3, 4}, {4, 2}
        };

        int[][] fence = outerTrees(points);

        System.out.println("Convex Hull Points:");
        for (int[] p : fence) {
            System.out.println(Arrays.toString(p));
        }
    }
}
