Here’s an example of Hadlock's algorithm applied to a matrix and its corresponding Java implementation.

### Matrix Example
Consider the following 5x5 grid, where `1` represents obstacles and `0` represents free paths. We want to find the shortest path from the top-left corner (start) to the bottom-right corner (goal).

```
[
 [0, 0, 0, 0, 1],
 [1, 1, 0, 1, 0],
 [0, 0, 0, 1, 0],
 [0, 1, 0, 0, 0],
 [1, 0, 0, 0, 0]
]
```

The start point is `(0, 0)` and the goal point is `(4, 4)`.

### Java Code

```java
import java.util.*;

class Hadlock {
    static class Node {
        int x, y, detours;

        Node(int x, int y, int detours) {
            this.x = x;
            this.y = y;
            this.detours = detours;
        }
    }

    // Directions for up, down, left, and right
    private static final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    // Function to implement Hadlock's algorithm
    public static int hadlock(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<Node> deque = new LinkedList<>(); // Use a deque to handle detours efficiently

        deque.addFirst(new Node(startX, startY, 0)); // Start at (startX, startY)

        while (!deque.isEmpty()) {
            Node current = deque.removeFirst();

            int x = current.x, y = current.y, detours = current.detours;

            if (x == goalX && y == goalY) {
                return detours; // Goal found
            }

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValidMove(grid, newX, newY, rows, cols)) {
                    boolean movingTowardGoal = (Math.abs(newX - goalX) + Math.abs(newY - goalY) < 
                                                Math.abs(x - goalX) + Math.abs(y - goalY));

                    if (movingTowardGoal) {
                        deque.addFirst(new Node(newX, newY, detours)); // Move towards goal, no extra detour
                    } else {
                        deque.addLast(new Node(newX, newY, detours + 1)); // Move away from goal, extra detour
                    }
                }
            }
        }

        return -1; // No path found
    }

    // Function to check if the move is valid (i.e., within bounds and not an obstacle)
    private static boolean isValidMove(int[][] grid, int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0}
        };

        int startX = 0, startY = 0, goalX = 4, goalY = 4;
        int result = hadlock(grid, startX, startY, goalX, goalY);

        if (result != -1) {
            System.out.println("Shortest path found with detours: " + result);
        } else {
            System.out.println("No path found");
        }
    }
}
```

### Explanation of the Code:

1. **Node Class:** This class stores each node's position `(x, y)` and the number of detours from the direct path.

2. **Main Function (hadlock):**
    - A deque (double-ended queue) is used for handling nodes. Nodes that are moving toward the goal are added to the front of the deque (priority), while nodes moving away are added to the back (penalty for detours).
    - The algorithm continues until the goal is reached or no more nodes are available.

3. **isValidMove:** This function checks if a move is within grid bounds and if the cell is not blocked by an obstacle (`1`).

4. **Goal:** The goal is reached when the current node's coordinates match the goal's coordinates, and the number of detours made is returned.

### Output:
```
Shortest path found with detours: 1
```

This shows that the shortest path to the goal from `(0, 0)` to `(4, 4)` involves only 1 detour.

# BFS vs Hadlock
Hadlock's algorithm and Breadth-First Search (BFS) are both pathfinding algorithms that use a queue-like structure, but they differ in how they prioritize exploring paths, particularly in the handling of detours or paths that deviate from the straight line towards the goal. Here’s a detailed comparison:

### 1. **Queue vs. Deque**
- **BFS**: Uses a simple **queue** (FIFO — First In, First Out) where nodes are explored level by level in a breadth-first manner. All neighboring nodes are enqueued in the order they are encountered, without any special prioritization.
- **Hadlock’s Algorithm**: Uses a **deque** (double-ended queue), which allows adding nodes at both the front and back. This helps prioritize nodes that move toward the goal over nodes that move away, giving a form of preferential treatment to more promising paths.

### 2. **Handling of Detours**
- **BFS**: Does not differentiate between moving toward the goal or away from the goal. It explores all neighboring nodes equally and adds them to the queue without any prioritization.
    - BFS guarantees the shortest path when all moves have the same cost (like in a grid without diagonal movement), as it explores all possible paths equally.
- **Hadlock’s Algorithm**: Prioritizes nodes that move **toward** the goal and penalizes moves that involve detours.
    - It adds nodes that move toward the goal to the front of the deque (high priority, no additional detour cost) and those that move away from the goal to the back (lower priority, adding to the detour count).

### 3. **Manhattan Distance Heuristic**
- **BFS**: Does not use any heuristics or distance-based optimizations. It simply explores all possible paths level by level, regardless of their distance to the goal.
- **Hadlock’s Algorithm**: Relies on **Manhattan distance** to determine whether a move is towards or away from the goal. It adjusts the deque based on how "straight" the path is towards the goal. Moves closer to the goal are favored, while moves that deviate from this straight line are penalized with extra detours.

### 4. **Pathfinding Efficiency**
- **BFS**: In scenarios where all moves have the same cost (e.g., grid movement with no obstacles), BFS is guaranteed to find the shortest path, but it may take more time because it explores all paths equally, even if they are moving away from the goal.
- **Hadlock’s Algorithm**: It can be more efficient in grid-based environments because it minimizes detours by focusing on the direct path first. This makes it faster in certain cases, especially when obstacles are sparse. However, it may not always find the absolute optimal path if detours are required but not sufficiently explored.

### 5. **Memory Usage**
- **BFS**: Can require more memory because it stores all nodes of the current level (or "breadth") in the queue before moving on to the next level. In large grids, this can lead to high memory consumption.
- **Hadlock’s Algorithm**: Uses a deque to dynamically adjust the order of node exploration based on how they contribute to detours. This selective prioritization can reduce the number of nodes stored at any given time, potentially making it more memory efficient in certain grid configurations.

### 6. **Optimality of Solution**
- **BFS**: Guaranteed to find the shortest path (in terms of number of steps) in a uniform-cost grid, as it explores every possible node.
- **Hadlock’s Algorithm**: It is designed to quickly find a path with minimal detours in a grid, but it is **not guaranteed to always find the absolutely shortest path** in all cases, particularly if there are complex obstacles requiring substantial backtracking.

### Code Comparison

Let’s look at simplified versions of BFS and Hadlock to highlight their differences.

#### BFS Example (Java):
```java
import java.util.*;

public class BFS {
    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public static int bfs(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(new Node(startX, startY));
        visited[startX][startY] = true;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                
                if (current.x == goalX && current.y == goalY) {
                    return steps;
                }
                
                for (int[] dir : DIRECTIONS) {
                    int newX = current.x + dir[0];
                    int newY = current.y + dir[1];
                    
                    if (isValidMove(grid, newX, newY, rows, cols) && !visited[newX][newY]) {
                        queue.add(new Node(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
        
        return -1; // No path found
    }

    private static boolean isValidMove(int[][] grid, int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0}
        };

        int startX = 0, startY = 0, goalX = 4, goalY = 4;
        int result = bfs(grid, startX, startY, goalX, goalY);

        if (result != -1) {
            System.out.println("Shortest path found in steps: " + result);
        } else {
            System.out.println("No path found");
        }
    }
}
```

#### Hadlock Example (Already Provided):
```java
// Reference the previously provided Hadlock's algorithm implementation
```

### Key Differences in Behavior:
- **BFS** explores each node level by level and does not prioritize based on distance from the goal. This can make it slower if the grid is large or if there are long detours to explore.
- **Hadlock** tries to prioritize moves that get closer to the goal and minimizes detours, which can make it faster in cases where the grid is relatively open, but it may not always guarantee the shortest path when obstacles are abundant.
