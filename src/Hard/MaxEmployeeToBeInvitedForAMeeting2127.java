package Hard;

import java.util.Arrays;

/*
* A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.



Example 1:


Input: favorite = [2,2,1,2]
Output: 3
Explanation:
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3.
Example 2:

Input: favorite = [1,2,0]
Output: 3
Explanation:
Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.
Example 3:


Input: favorite = [3,0,1,4,1]
Output: 4
Explanation:
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.


Constraints:

n == favorite.length
2 <= n <= 105
0 <= favorite[i] <= n - 1
favorite[i] != i
*  */
import java.util.*;

public class MaxEmployeeToBeInvitedForAMeeting2127 {


    public static int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        for (int f : favorite) {
            indegree[f]++;
        }

        // Step 1: Find all chains leading to mutual favorite pairs
        int[] maxChainLength = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        // Process all nodes with zero indegree (they can't be in any cycle)
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int fav = favorite[curr];
            maxChainLength[fav] = Math.max(maxChainLength[fav], maxChainLength[curr] + 1);
            indegree[fav]--;
            if (indegree[fav] == 0) {
                queue.offer(fav);
            }
        }

        // Step 2: Process the mutual favorite pairs
        int maxPairsSum = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (favorite[favorite[i]] == i && i < favorite[i]) {  // mutual favorite pair
                maxPairsSum += maxChainLength[i] + maxChainLength[favorite[i]] + 2;
            }
        }

        // Step 3: Find the largest cycle
        int maxCycleLength = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int cycleLength = 0;
                int curr = i;
                while (!visited[curr]) {
                    visited[curr] = true;
                    curr = favorite[curr];
                    cycleLength++;
                }
                maxCycleLength = Math.max(maxCycleLength, cycleLength);
            }
        }

        // The result is the maximum of mutual favorite pairs' chain sum or largest cycle length
        return Math.max(maxPairsSum, maxCycleLength);
    }

    public static void main(String[] args) {
        int[] favorite = {2, 2, 1, 2};
        System.out.println(maximumInvitations(favorite)); // Output: 3
    }
}
