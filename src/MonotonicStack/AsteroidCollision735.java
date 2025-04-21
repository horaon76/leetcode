package MonotonicStack;

import java.util.*;

public class AsteroidCollision735 {

    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            // Handle collision using monotonic stack rules
            if (asteroid > 0) {
                // Right-moving asteroid — push it
                stack.addLast(asteroid);
            } else {
                // Left-moving asteroid — check for collisions
                while (!stack.isEmpty() && stack.peekLast() > 0 && stack.peekLast() < -asteroid) {
                    stack.pollLast(); // Top right-moving asteroid explodes
                }

                // Same size collision — both explode
                if (!stack.isEmpty() && stack.peekLast() == -asteroid) {
                    stack.pollLast();
                }
                // No collision or all right-moving ones have exploded
                else if (stack.isEmpty() || stack.peekLast() < 0) {
                    stack.addLast(asteroid);
                }
            }
        }

        // Convert to array
        int[] result = new int[stack.size()];
        int i = 0;
        for (int a : stack) {
            result[i++] = a;
        }
        return result;
    }


    // Main method for testing
    public static void main(String[] args) {

        // Test case
        int[] input = {5, 10, -5};
        int[] result = asteroidCollision(input);

        System.out.println("Final State of Asteroids: " + Arrays.toString(result));
    }
}

