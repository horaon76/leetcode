package DP;

import Graph.MinimumPathSum64;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Triangle120 {
    private static class Cell {
        int row, col, cost;

        Cell(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    public static int minPathSumBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for moving right and down
        int[][] directions = {{1, 1}, {1, 0}};

        // Queue for BFS
        Queue<Triangle120.Cell> queue = new LinkedList<>();
        queue.add(new Triangle120.Cell(0, 0, grid[0][0]));

        // BFS to find the minimum path sum
        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Triangle120.Cell current = queue.poll();

            // If we've reached the bottom-right corner, update minCost
            if (current.row == rows - 1) {
                minCost = Math.min(minCost, current.cost);
            }

            // Explore neighbors (right and down)
            for (int[] direction : directions) {
                int newRow = current.row + direction[0];
                int newCol = current.col + direction[1];

                if (newRow < rows && newCol < cols) {
                    queue.add(new Triangle120.Cell(newRow, newCol, current.cost + grid[newRow][newCol]));
                }
            }
        }

        return minCost;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // Start from the second last row and move upward
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                // Update the current element with the minimum path sum
                triangle.get(row).set(
                        col, triangle.get(row).get(col) +
                        Math.min(
                                triangle.get(row + 1).get(col),
                                triangle.get(row + 1).get(col + 1)
                        )
                );
            }
        }

        // The top element now contains the minimum path sum
        return triangle.get(0).get(0);
    }
    private int[][] memo;

    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE); // Initialize memo with max values
        }
        return findMinPath(triangle, 0, 0);
    }

    private int findMinPath(List<List<Integer>> triangle, int row, int col) {
        // Base case: if we reach the last row
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

        // Check if already computed
        if (memo[row][col] != Integer.MAX_VALUE) {
            return memo[row][col];
        }

        // Calculate minimum path sum from the current position
        int left = findMinPath(triangle, row + 1, col);
        int right = findMinPath(triangle, row + 1, col + 1);

        memo[row][col] = triangle.get(row).get(col) + Math.min(left, right);
        return memo[row][col];
    }

    public static void main(String[] args) {

        int[][] grid2 = {
                {2, 10000, 10000, 10000},
                {3, 4, 10000, 10000},
                {6, 5, 7, 10000},
                {4, 1, 8, 3}
        };

        int result = minPathSumBFS(grid2);
        System.out.println("Minimum Path Sum using BFS: " + result);

//        int result1 = minPathSumDFS(grid);
//        System.out.println("Minimum Path Sum: " + result1);
//
//        int result2 = minPathSumDP(grid);
//        System.out.println("Minimum Path Sum: " + result2);
//
//        int result3 = minPathSumDFSIterative(grid1);
//        System.out.println("Minimum Path Sum: " + result3);

    }
}
