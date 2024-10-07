Sure! Below are examples of the algorithms mentioned, along with brief explanations and Java code snippets where applicable.

### 1. **Graham's Scan**
**Example**: Finding the convex hull of a set of points.

```java
import java.util.*;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class GrahamScan {
    
    // Function to find the orientation of the triplet (p, q, r).
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // Function to perform Graham's Scan
    public static List<Point> grahamScan(Point[] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p.y));
        Arrays.sort(points, 1, points.length, Comparator.comparingDouble(p -> Math.atan2(p.y - points[0].y, p.x - points[0].x)));

        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);
        hull.push(points[2]);

        for (int i = 3; i < points.length; i++) {
            while (hull.size() > 1 && orientation(hull.get(hull.size() - 2), hull.peek(), points[i]) != 2) {
                hull.pop();
            }
            hull.push(points[i]);
        }

        return hull;
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 3), new Point(2, 2), new Point(1, 1),
            new Point(2, 1), new Point(3, 0), new Point(0, 0),
            new Point(3, 3)
        };

        List<Point> convexHull = grahamScan(points);
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
```

### 2. **Jarvis's March (Gift Wrapping Algorithm)**
**Example**: Finding the convex hull using Jarvis's March.

```java
import java.util.*;

class JarvisMarch {
    
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    public static List<Point> jarvisMarch(Point[] points) {
        List<Point> hull = new ArrayList<>();
        int n = points.length;

        if (n < 3) return hull;

        int leftMost = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].x < points[leftMost].x) leftMost = i;
        }

        int p = leftMost, q;
        do {
            hull.add(points[p]);
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientation(points[p], points[i], points[q]) == 2) {
                    q = i;
                }
            }

            p = q;
        } while (p != leftMost);

        return hull;
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 3), new Point(2, 2), new Point(1, 1),
            new Point(2, 1), new Point(3, 0), new Point(0, 0),
            new Point(3, 3)
        };

        List<Point> convexHull = jarvisMarch(points);
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
```

### 3. **Divide and Conquer for Convex Hull**
**Example**: Using the divide-and-conquer method to find the convex hull.

```java
import java.util.*;

class DivideAndConquerConvexHull {
    
    // Helper function to find the convex hull
    static List<Point> convexHull(Point[] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        return findConvexHull(points, 0, points.length - 1);
    }

    static List<Point> findConvexHull(Point[] points, int left, int right) {
        if (right - left <= 1) return Arrays.asList(points[left]);

        int mid = (left + right) / 2;
        List<Point> leftHull = findConvexHull(points, left, mid);
        List<Point> rightHull = findConvexHull(points, mid + 1, right);

        return mergeHulls(leftHull, rightHull);
    }

    static List<Point> mergeHulls(List<Point> leftHull, List<Point> rightHull) {
        // Merge left and right hulls (not shown here for brevity)
        return new ArrayList<>(); // Placeholder
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 0), new Point(1, 1), new Point(2, 2),
            new Point(2, 0), new Point(0, 2)
        };

        List<Point> convexHull = convexHull(points);
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
```

### 4. **Line Intersection**
**Example**: Detecting the intersection between two line segments.

```java
class LineIntersection {

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        return (val > 0) ? 1 : (val < 0) ? 2 : 0;
    }

    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        return (o1 != o2 && o3 != o4);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point q1 = new Point(10, 1);
        Point p2 = new Point(1, 2);
        Point q2 = new Point(10, 2);

        if (doIntersect(p1, q1, p2, q2)) {
            System.out.println("Intersect");
        } else {
            System.out.println("Do not intersect");
        }
    }
}
```

### 5. **Point in Polygon**
**Example**: Checking if a point is inside a polygon using the ray-casting algorithm.

```java
class PointInPolygon {
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isInside(Point[] polygon, Point p) {
        int n = polygon.length;
        boolean result = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((polygon[i].y > p.y) != (polygon[j].y > p.y) &&
                (p.x < (polygon[j].x - polygon[i].x) * (p.y - polygon[i].y) / (polygon[j].y - polygon[i].y) + polygon[i].x)) {
                result = !result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Point[] polygon = {
            new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10)
        };
        Point p = new Point(20, 20);

        if (isInside(polygon, p)) {
            System.out.println("Inside");
        } else {
            System.out.println("Outside");
        }
    }
}
```

### 6. **Polygon Triangulation**
**Example**: A naive implementation to triangulate a simple polygon.

```java
import java.util.*;

class PolygonTriangulation {
    
    static List<List<Point>> triangulate(Point[] polygon) {
        List<List<Point>> triangles = new ArrayList<>();
        int n = polygon.length;

        if (n < 3) return triangles; // A polygon must have at least 3 vertices

        for (int i = 1; i < n - 1;

 i++) {
            triangles.add(Arrays.asList(polygon[0], polygon[i], polygon[i + 1]));
        }

        return triangles;
    }

    public static void main(String[] args) {
        Point[] polygon = {
            new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)
        };

        List<List<Point>> triangles = triangulate(polygon);
        for (List<Point> triangle : triangles) {
            System.out.println("Triangle: " + triangle);
        }
    }
}
```

### 7. **Voronoi Diagram** (Conceptual, not implemented)
- Voronoi diagrams are complex structures that partition a plane into regions based on distances to a specific set of points. They require advanced data structures and are generally more complex to implement.

### 8. **Closest Pair of Points** (Conceptual, not implemented)
- This algorithm typically uses a divide-and-conquer approach to find the closest pair of points among a set.

### 9. **Sweep Line Algorithm** (Conceptual, not implemented)
- The sweep line algorithm is a powerful technique used in various geometric algorithms, such as line intersection, to manage events as a vertical line sweeps across the plane.

### Summary
These examples illustrate how various algorithms in computational geometry can be implemented in Java. They cover key concepts such as convex hulls, intersection detection, point containment, and polygon triangulation. If you would like more detailed examples or explanations of any specific algorithm, let me know!