package Hard;

import java.util.*;

public class LargestRectangle84 {

    public static void largestRectangleWithDimensions(int[] heights) {
        int n = heights.length;
        int[] extended = new int[n + 1];  // append 0 at the end
        System.arraycopy(heights, 0, extended, 0, n);

        int maxArea = 0;
        int maxHeight = 0;
        int maxWidth = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Sentinel

        for (int i = 0; i < extended.length; i++) {
            while (stack.peek() != -1 && extended[i] < extended[stack.peek()]) {
                int height = extended[stack.pop()];
                int width = i - stack.peek() - 1;
                int area = height * width;
                if (area > maxArea) {
                    maxArea = area;
                    maxHeight = height;
                    maxWidth = width;
                }
            }
            stack.push(i);
        }

        System.out.println("Max Area: " + maxArea);
        System.out.println("Width: " + maxWidth);
        System.out.println("Height: " + maxHeight);
    }

    public static void main(String[] args) {
        int[] heights = {2, 2, 6, 8, 1, 1};
        largestRectangleWithDimensions(heights);
    }
}
