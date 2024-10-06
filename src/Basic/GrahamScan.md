Here's a Java implementation of the **Graham Scan** algorithm for finding the convex hull of a set of points in a 2D plane. This implementation includes defining the points, sorting them, and constructing the convex hull using the orientation concepts we discussed earlier.

### Java Code for Graham Scan Algorithm

```java
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Comparator to sort points by polar angle with respect to the base point
    public static Comparator<Point> polarOrder(Point base) {
        return (p1, p2) -> {
            int angle1 = (p1.y - base.y) * (p2.x - base.x);
            int angle2 = (p2.y - base.y) * (p1.x - base.x);
            return angle1 - angle2; // This gives a positive/negative/zero result
        };
    }
}

public class GrahamScan {
    
    // Method to compute the convex hull
    public static List<Point> convexHull(Point[] points) {
        if (points.length < 3) return Arrays.asList(points); // Convex hull is not possible

        // Find the point with the lowest y-coordinate (and leftmost if tie)
        Point base = Arrays.stream(points).min(Comparator.comparingInt(p -> p.y).thenComparingInt(p -> p.x)).orElse(points[0]);
        
        // Sort the points based on polar angle with the base point
        Arrays.sort(points, Point.polarOrder(base));

        // Initialize the stack for convex hull
        Stack<Point> hull = new Stack<>();
        hull.push(points[0]); // First point in hull

        // Traverse sorted points
        for (Point p : points) {
            while (hull.size() > 1) {
                Point top = hull.pop();
                Point nextToTop = hull.peek();
                if (orientation(nextToTop, top, p) > 0) {
                    hull.push(top);
                    break; // If it makes a left turn, keep the point in the hull
                }
            }
            hull.push(p); // Add current point to the hull
        }

        return new ArrayList<>(hull); // Convert stack to list and return
    }

    // Function to determine the orientation of the triplet (p, q, r)
    private static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0; // Collinear
        return (val > 0) ? 1 : -1; // Clockwise or counterclockwise
    }

    public static void main(String[] args) {
        // Example points
        Point[] points = {
            new Point(0, 3), new Point(2, 2), new Point(1, 1),
            new Point(2, 1), new Point(3, 0), new Point(0, 0),
            new Point(3, 3)
        };

        List<Point> hull = convexHull(points);

        System.out.println("Convex Hull:");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
```

### Explanation of the Code:
1. **Point Class**:
    - Represents a point in 2D space with `x` and `y` coordinates.
    - Contains a static method `polarOrder` for comparing points based on their polar angle relative to a base point.

2. **Convex Hull Method**:
    - The `convexHull` method checks if there are fewer than three points, in which case it returns the original points.
    - It finds the point with the lowest y-coordinate (and leftmost in case of a tie) as the base point.
    - It sorts the points based on their polar angles relative to the base point using the `polarOrder` comparator.
    - It then initializes a stack and iterates through the sorted points, adding them to the hull or removing points that create a clockwise turn.

3. **Orientation Function**:
    - The `orientation` function calculates the orientation of the triplet formed by three points.
    - It returns:
        - `0` if the points are collinear,
        - `1` for clockwise,
        - `-1` for counter-clockwise.

4. **Main Method**:
    - The `main` method creates an example set of points, calls the `convexHull` method, and prints the resulting convex hull.

### Example Output
For the provided example points, the output might look like:
```
Convex Hull:
(0, 0)
(0, 3)
(3, 3)
(3, 0)
```

### Notes
- This algorithm has a time complexity of \(O(n \log n)\) due to the sorting step.
- The stack operations used for constructing the hull contribute to an \(O(n)\) complexity for the hull construction itself.

Feel free to run this code and adjust the points in the `main` method to see how the convex hull changes! Let me know if you have any questions or need further clarifications.


## How to choose the base point
Let's go through another example using different points to illustrate how the code snippet works in finding the base point with the lowest \(y\)-coordinate (and the leftmost point in case of ties).

### Example Points

Consider the following points:

- \( P_1(2, 3) \)
- \( P_2(1, 1) \)
- \( P_3(2, 1) \)
- \( P_4(3, 2) \)
- \( P_5(0, 1) \)

We can represent these points in an array:

```java
Point[] points = {
    new Point(2, 3), 
    new Point(1, 1), 
    new Point(2, 1), 
    new Point(3, 2), 
    new Point(0, 1)
};
```

### Execution Flow

Let's analyze the line of code again:

```java
Point base = Arrays.stream(points)
    .min(Comparator.comparingInt(p -> p.y).thenComparingInt(p -> p.x))
    .orElse(points[0]);
```

1. **Step 1: Create a Stream of Points**
    - The `stream()` method creates a stream of the points.

2. **Step 2: Find the Minimum Point**
    - The `min` function evaluates the points based on their \(y\)-coordinates, and breaks ties using the \(x\)-coordinates.

3. **Comparing \(y\) Values**:
    - \( P_1(2, 3) \) has \( y = 3 \)
    - \( P_2(1, 1) \) has \( y = 1 \)
    - \( P_3(2, 1) \) has \( y = 1 \)
    - \( P_4(3, 2) \) has \( y = 2 \)
    - \( P_5(0, 1) \) has \( y = 1 \)

   **Lowest \(y\)-coordinates**: \( P_2(1, 1) \), \( P_3(2, 1) \), and \( P_5(0, 1) \) all have \(y = 1\).

4. **Step 3: Handle Ties with \(x\) Values**:
    - Among the points with \( y = 1 \):
        - \( P_2(1, 1) \) has \( x = 1 \)
        - \( P_3(2, 1) \) has \( x = 2 \)
        - \( P_5(0, 1) \) has \( x = 0 \)

   Since \( P_5(0, 1) \) has the smallest \(x\)-coordinate among these points, it will be chosen as the base point.

### Final Decision
- **Base Point**: \( P_5(0, 1) \)

### Summary of Execution
- The line of code successfully finds the base point with the lowest \(y\)-coordinate, which is \( P_5(0, 1) \), ensuring that if there are ties in the \(y\)-value, it selects the leftmost point.

### Output
If you were to print the base point after executing the above code, you would see:

```java
System.out.println("Base Point: (" + base.x + ", " + base.y + ")");
```

**Output:**
```
Base Point: (0, 1)
```

### Conclusion
This example demonstrates how the algorithm selects the point \( P_5(0, 1) \) as the base point, effectively handling ties by checking the \(x\)-coordinates when multiple points share the same \(y\)-coordinate. If you have further questions or need more examples, feel free to ask!
