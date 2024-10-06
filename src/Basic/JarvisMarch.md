**Jarvis March**, also known as the **gift wrapping algorithm**, is a straightforward and intuitive method for computing the convex hull of a set of points in 2D space. The algorithm is named after its developer, R. L. Jarvis, who introduced it in 1973. It is a **greedy algorithm** that operates by "wrapping" the points around the convex hull in a systematic manner.

### How Jarvis March Works

The key idea behind Jarvis March is to start from the leftmost point (the point with the smallest x-coordinate) and iteratively select the next point that forms the smallest angle with respect to the current point until the convex hull is complete.

### Steps of the Algorithm

1. **Find the Leftmost Point**:
    - Identify the point with the smallest x-coordinate. If there are ties, choose the one with the smallest y-coordinate. This point will be the starting point of the convex hull.

2. **Initialize the Hull**:
    - Add the leftmost point to the convex hull.

3. **Iterate to Find the Convex Hull**:
    - Repeat the following steps until you return to the starting point:
        - Set the current point as the last point added to the convex hull.
        - Select the next point from the remaining points. For each remaining point, check the orientation with respect to the current point and the last point in the hull:
            - Use the cross product to determine the orientation:
                - If the cross product is positive, the point is to the left (counter-clockwise).
                - If it’s negative, the point is to the right (clockwise).
                - If it’s zero, the points are collinear.
        - Choose the point that makes the largest counter-clockwise angle with the current point (or the rightmost point if collinear).
        - Add this point to the convex hull.

4. **Complete the Hull**:
    - Continue until the algorithm returns to the starting point, indicating that the convex hull has been formed.

### Pseudocode

Here's a high-level pseudocode representation of Jarvis March:

```plaintext
function jarvisMarch(points):
    if points is empty:
        return []

    // Step 1: Find the leftmost point
    start = point with minimum x (and minimum y if necessary)
    hull = []  // Initialize the hull

    current = start
    repeat:
        hull.append(current)  // Add current point to hull
        next_point = points[0]  // Start with the first point
        for each point in points:
            if point == current:  // Skip the current point
                continue
            if next_point == current or cross(current, next_point, point) > 0:
                next_point = point  // Update the next point
        current = next_point  // Move to the next point

    return hull  // Return the convex hull points
```

### Cross Product Function

The cross product can be used to determine the orientation of the points:

```plaintext
cross(p, q, r) = (q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x)
```

- A positive result indicates a counter-clockwise turn.
- A negative result indicates a clockwise turn.
- A zero result indicates collinearity.

### Time Complexity

- **Worst-case**: \(O(n^2)\) — This occurs when the algorithm needs to check every point for each point in the hull.
- **Best-case**: \(O(n)\) — This happens when the points are already in convex position (forming a hull without any internal points).

### Space Complexity

- The space complexity is \(O(n)\) due to storing the points in the hull.

### Example

Let’s say we have the following set of points:

```
(0, 0), (1, 1), (2, 2), (2, 0), (1, 2), (0, 2), (1, 0)
```

**Step-by-step Execution**:

1. Start with the leftmost point `(0, 0)`.
2. The next point with the largest counter-clockwise turn from `(0, 0)` is `(0, 2)`.
3. From `(0, 2)`, the next point is `(2, 2)`.
4. From `(2, 2)`, the next point is `(2, 0)`.
5. Finally, from `(2, 0)`, the algorithm wraps back to `(0, 0)`.

The resulting convex hull would be:

```
(0, 0), (0, 2), (2, 2), (2, 0)
```

### Implementation in Java

Here's a sample implementation of Jarvis March in Java:

```java
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class JarvisMarch {

    public static List<Point> convexHull(Point[] points) {
        int n = points.length;
        if (n < 3) return Arrays.asList(points); // Convex hull is not possible

        List<Point> hull = new ArrayList<>();
        int leftmost = 0;

        // Step 1: Find the leftmost point
        for (int i = 1; i < n; i++) {
            if (points[i].x < points[leftmost].x ||
                (points[i].x == points[leftmost].x && points[i].y < points[leftmost].y)) {
                leftmost = i;
            }
        }

        int current = leftmost;

        do {
            hull.add(points[current]); // Add current point to hull
            int next = (current + 1) % n; // Next point
            
            // Check each point
            for (int i = 0; i < n; i++) {
                if (i == current) continue;
                // Cross product to find the next point
                if (cross(points[current], points[next], points[i]) > 0) {
                    next = i; // Update next point
                }
            }

            current = next; // Move to next point
        } while (current != leftmost); // Stop when we wrap around

        return hull; // Return the convex hull points
    }

    // Cross product function
    private static int cross(Point o, Point a, Point b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 0), new Point(1, 1), new Point(2, 2),
            new Point(2, 0), new Point(1, 2), new Point(0, 2),
            new Point(1, 0)
        };

        List<Point> hull = convexHull(points);
        System.out.println("Convex Hull Points:");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
```

### Conclusion

Jarvis March is a simple yet effective algorithm for finding the convex hull of a set of points. While its time complexity is not as efficient as other algorithms like Andrew's monotone chain, its intuitive nature makes it easy to understand and implement. If you have any further questions or need clarification on specific aspects, feel free to ask!