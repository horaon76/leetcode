The problem, **LeetCode 1467: Probability of a Two Boxes Having The Same Number of Distinct Balls**, involves dividing a set of balls of different colors into two boxes. The goal is to find the **probability** that the two boxes will have the same number of **distinct colors**. Let’s break it down.

### Problem Recap:
- You are given `n` colors of balls, and for each color, you know how many balls are available.
- You need to divide these balls into two boxes such that the number of distinct colors in both boxes is the same.
- For instance, if you have `2 red`, `1 blue`, and `1 green` ball, and you're asked to split them into two boxes, one possible valid division is where both boxes end up with one distinct color each.

### Key Concepts:
1. **Total Ways of Splitting the Balls**:
    - You need to figure out how many **possible ways** you can distribute the balls between the two boxes.

2. **Valid Ways**:
    - Out of the total ways, you only care about the ones where **both boxes have the same number of distinct colors**.

3. **The Goal**:
    - The problem asks for the **probability** that the two boxes have the same number of distinct colors.
    - The probability is defined as:
      \[
      \text{Probability} = \frac{\text{Valid Ways}}{\text{Total Ways}}
      \]

### Approach to Solve:
To solve this problem, the solution uses **backtracking with combinatorics**. Let’s walk through each part.

---

### Explanation of the Solution:

#### 1. **Backtracking Logic**:
- **Backtracking** is a way to try all possible combinations and decide whether a given combination is valid. Here, for each color of balls, we try to assign a certain number of balls to Box 1 and the rest to Box 2. We do this recursively for each color.
- For each color `i`, there are multiple possibilities for how to distribute the `balls[i]` between Box 1 and Box 2:
    - If you assign `x` balls to Box 1, the remaining `balls[i] - x` go to Box 2.
    - You iterate over all possible values of `x` (from `0` to `balls[i]`) and explore each distribution.

#### 2. **Recursive Base Case**:
- The recursion ends when you've assigned balls from all colors. Once you’ve processed all colors, you check two conditions:
    - **Condition 1**: The number of balls in both boxes should be equal.
    - **Condition 2**: The number of distinct colors in both boxes should be the same.

- If both conditions hold, the current way of dividing the balls is counted as a **valid way**.

#### 3. **Counting Total and Valid Ways**:
- During the backtracking process, you maintain two counters:
    - `validWays`: This counts how many ways result in an equal number of distinct colors in both boxes.
    - `totalWays`: This counts all the ways to split the balls (regardless of distinct colors).

#### 4. **Final Probability**:
- Once the backtracking is complete, the probability is simply:
  \[
  \text{Probability} = \frac{\text{validWays}}{\text{totalWays}}
  \]
- This ratio gives you the likelihood that two randomly filled boxes will have the same number of distinct colors.


import java.util.*;

public class ProbabilityOfSameDistinctBalls {
private double validWays = 0;  // To count valid ways where both boxes have the same number of distinct balls
private double totalWays = 0;  // To count all possible ways to distribute the balls

    public double getProbability(int[] balls) {
        int totalBalls = 0;
        for (int ball : balls) {
            totalBalls += ball;  // Calculate total number of balls
        }
        int half = totalBalls / 2;

        // Perform backtracking starting from the first color (index 0)
        backtrack(balls, 0, 0, 0, 0, 0, 0, half);
        
        // Return the ratio of valid ways to total ways
        return validWays / totalWays;
    }

    private void backtrack(int[] balls, int idx, int box1Balls, int box2Balls, int box1Distinct, int box2Distinct, int totalDistinct, int half) {
        if (box1Balls > half || box2Balls > half) {
            return; // Invalid if either box has more than half of the balls
        }

        // Base case: if we have considered all colors
        if (idx == balls.length) {
            if (box1Balls == half && box2Balls == half && box1Distinct == box2Distinct) {
                validWays++;  // Both boxes have the same number of distinct balls
            }
            totalWays++;  // Count this distribution as a valid way (irrespective of distinct ball condition)
            return;
        }

        // Try all possible ways to distribute `balls[idx]` between the two boxes
        for (int i = 0; i <= balls[idx]; i++) {
            int box1NewDistinct = (i > 0 ? 1 : 0);  // Increment distinct count for box 1 if it gets balls of this color
            int box2NewDistinct = (i < balls[idx] ? 1 : 0);  // Increment distinct count for box 2 if it gets balls of this color

            // Recur for the next color
            backtrack(balls, idx + 1, box1Balls + i, box2Balls + (balls[idx] - i), 
                      box1Distinct + (i > 0 ? 1 : 0), 
                      box2Distinct + (i < balls[idx] ? 1 : 0), totalDistinct + 1, half);
        }
    }
}


---

### Example:
Let’s go through a small example:

- Suppose `balls = [2, 1]`, meaning you have 2 red balls and 1 blue ball.

  Possible splits of balls:
    1. **Box 1 gets both red balls, Box 2 gets the blue ball**:
        - Box 1 has 1 distinct color (red), Box 2 has 1 distinct color (blue).
        - This is a valid way because both boxes have 1 distinct color.

    2. **Box 1 gets 1 red and the blue, Box 2 gets 1 red**:
        - Box 1 has 2 distinct colors (red, blue), Box 2 has 1 distinct color (red).
        - This is not valid because Box 1 has more distinct colors.

    3. **Box 1 gets 1 red, Box 2 gets 1 red and the blue**:
        - Box 1 has 1 distinct color (red), Box 2 has 2 distinct colors (red, blue).
        - This is also invalid for the same reason.

  In this example, out of the 3 possible ways, only 1 way is valid (equal number of distinct colors). Hence the probability is \( \frac{1}{3} \).

---

### Backtracking Function Overview:
The function `backtrack()` is the core of the algorithm:
- **Parameters**:
    - `balls[]`: Array representing the number of balls of each color.
    - `idx`: The current color being processed.
    - `box1Balls`, `box2Balls`: The number of balls currently in Box 1 and Box 2.
    - `box1Distinct`, `box2Distinct`: The number of distinct colors in Box 1 and Box 2.
    - `half`: Half the total number of balls (since both boxes need to end up with the same number of balls).

- **Steps**:
    1. If one box exceeds half the total number of balls, that division is invalid, so we stop.
    2. If we've processed all the colors (i.e., `idx == balls.length`), we check if both boxes have the same number of balls and distinct colors.
    3. For each color, try assigning `i` balls to Box 1 (and the rest to Box 2) and recurse.

---

### Complexity:
- **Time Complexity**: \(O(2^n)\), where `n` is the number of colors. This is because for each color, we explore multiple ways to distribute balls between the two boxes. Since backtracking explores all possible combinations, it leads to an exponential time complexity.

- **Space Complexity**: \(O(n)\), due to the recursion stack, where `n` is the number of colors.

---

### Conclusion:
This problem is tackled by trying all possible combinations of distributing balls between two boxes, ensuring that both boxes end up with the same number of distinct colors. By carefully counting the valid and total ways, we compute the desired probability. The solution combines **backtracking** with **combinatorics** to explore the distribution possibilities and check if they satisfy the problem's constraints.