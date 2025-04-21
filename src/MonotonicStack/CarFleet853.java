package MonotonicStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/**
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 *
 * You are given two integer array position and speed, both of length n, where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
 *
 * A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 *
 * A car fleet is a car or cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.
 *
 * If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
 * The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 * Example 2:
 *
 * Input: target = 10, position = [3], speed = [3]
 *
 * Output: 1
 *
 * Explanation:
 *
 * There is only one car, hence there is only one fleet.
 * Example 3:
 *
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 *
 * Output: 1
 *
 * Explanation:
 *
 * The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.
 * Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 *
 *
 * Constraints:
 *
 * n == position.length == speed.length
 * 1 <= n <= 105
 * 0 < target <= 106
 * 0 <= position[i] < target
 * All the values of position are unique.
 * 0 < speed[i] <= 106
 * **/
public class CarFleet853 {

    public static int carFleet(int target, int[] pos, int[] speed) {
        int N = pos.length, res = 0;

        // Step 1: Create an array to store the cars' position, time to reach the target, and index
        double[][] cars = new double[N][3];  // Array of cars, each car is [position, time, index]

        for (int i = 0; i < N; ++i) {
            // Position, time to reach the target, and index
            cars[i] = new double[] { pos[i], (double)(target - pos[i]) / speed[i], i };
        }



        // Step 2: Sort cars by position (ascending)
        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));  // Sorting by position

        // Step 3: Traverse the cars in reverse order to simulate the fleet formation
        double cur = 0;  // Variable to keep track of the current "leading" fleet time
        for (int i = N - 1; i >= 0; --i) {
            // If current car's time to reach target is greater than the current fleet time,
            // it forms a new fleet.
            if (cars[i][1] > cur) {
                cur = cars[i][1];  // Update the fleet's time
                res++;  // Increment the fleet count
            }
        }

        return res;  // Return the total number of fleets
    }



        public static List<List<Integer>> carFleet1(int target, int[] position, int[] speed) {
            int n = position.length;

            // Combine position, time to target, and original index
            double[][] cars = new double[n][3];
            for (int i = 0; i < n; i++) {
                double time = (double)(target - position[i]) / speed[i];
                cars[i] = new double[]{position[i], time, i}; // {position, time, index}
            }

            // Sort by position descending
            Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

            List<List<Integer>> fleets = new ArrayList<>();
            Deque<Double> stack = new ArrayDeque<>();

            for (double[] car : cars) {
                double pos = car[0], time = car[1];
                if (stack.isEmpty() || time > stack.peek()) {
                    stack.push(time);
                    fleets.add(new ArrayList<>(List.of((int)pos))); // New fleet
                } else {
                    fleets.get(fleets.size() - 1).add((int)pos); // Append to last fleet
                }
            }

            return fleets;
        }




    public static void main(String[] args){
        int[] position = new int[]{10,8,0,5,3, 8, 8};
        int[] speed = new int[]{2,4,1,1,3, 2, 2};
        int target = 12;
        System.out.println(carFleet(target, position, speed));
    }
}
/**
 * Approach:
 * Speed vs Distance:
 *
 * The main idea is to track each car’s time to reach the destination.
 *
 * The time to reach the target for a car is calculated as:
 *
 * time
 * =
 * distance
 * speed
 * time=
 * speed
 * distance
 * ​
 *
 * If two cars reach the target at the same time, they form a fleet.
 *
 * Simulate the Process:
 *
 * Cars that are initially faster and are behind others will catch up to the slower cars ahead of them. This means we need to check which cars will reach the destination at the same time and group them together into fleets.
 *
 * Stack-based Approach:
 *
 * Sort the cars based on their starting positions.
 *
 * For each car, calculate its time to reach the target.
 *
 * If the current car’s time is greater than the car ahead of it (in terms of index), it will form a fleet with the car ahead. If not, it will form its own fleet.
 *
 * Use a stack to track the car times.
 *
 * import java.util.*;
 *
 * public class CarFleet {
 *
 *     public int carFleet(int target, int[] position, int[] speed) {
 *         // Create an array of cars, each containing position and speed
 *         int n = position.length;
 *         Car[] cars = new Car[n];
 *
 *         for (int i = 0; i < n; i++) {
 *             cars[i] = new Car(position[i], speed[i]);
 *         }
 *
 *         // Sort cars by position (ascending order)
 *         Arrays.sort(cars, (a, b) -> a.position - b.position);
 *
 *         // Stack to track the number of fleets
 *         Stack<Double> stack = new Stack<>();
 *
 *         // Calculate the time for each car to reach the destination
 *         for (Car car : cars) {
 *             double time = (double) (target - car.position) / car.speed;
 *
 *             // If the current car takes longer than the car in front of it, they form a new fleet
 *             if (stack.isEmpty() || time > stack.peek()) {
 *                 stack.push(time);
 *             }
 *         }
 *
 *         // The number of fleets is the number of items in the stack
 *         return stack.size();
 *     }
 *
 *     // Helper class to store position and speed of each car
 *     class Car {
 *         int position;
 *         int speed;
 *
 *         Car(int position, int speed) {
 *             this.position = position;
 *             this.speed = speed;
 *         }
 *     }
 *
 *     public static void main(String[] args) {
 *         CarFleet solver = new CarFleet();
 *
 *         // Example Test Case
 *         int[] position = {10, 20, 30};
 *         int[] speed = {2, 1, 3};
 *         int target = 100;
 *
 *         System.out.println("Car Fleets: " + solver.carFleet(target, position, speed));
 *     }
 * }
 * No problem! Let's break it down **super simply** with an example and visuals. You asked:
 *
 * > "Can we print which cars are in which fleet?"
 *
 * Yes — and here's **exactly how it works** using this example:
 *
 * ---
 *
 * ### ✅ Given:
 *
 * ```java
 * target = 12
 * position = [11, 8, 0, 5, 3]
 * speed    = [1, 4, 12, 1, 3]
 * ```
 *
 * Each car is at some position and has a speed. They drive **toward the target (12)**.
 *
 * We calculate how long each car will take to reach the target:
 *
 * | Car # | Position | Speed | Time to Reach Target |
 * |-------|----------|--------|----------------------|
 * | 0     | 11       | 1      | (12 - 11)/1  = 1.0   |
 * | 1     | 8        | 4      | (12 - 8)/4   = 1.0   |
 * | 2     | 0        | 12     | (12 - 0)/12  = 1.0   |
 * | 3     | 5        | 1      | (12 - 5)/1   = 7.0   |
 * | 4     | 3        | 3      | (12 - 3)/3   = 3.0   |
 *
 * ---
 *
 * ### 🚘 Now we simulate how fleets form
 *
 * We **sort by position descending**, because cars closer to the target can’t be caught if they’re faster (they lead the fleet).
 *
 * Sorted:
 *
 * ```
 * Car 0 → pos 11 → time 1.0
 * Car 1 → pos 8  → time 1.0
 * Car 3 → pos 5  → time 7.0
 * Car 4 → pos 3  → time 3.0
 * Car 2 → pos 0  → time 1.0
 * ```
 *
 * ---
 *
 * ### 💥 Fleet Formation:
 *
 * We go from **front to back** (closer to target → farther) and form fleets:
 *
 * #### Car at pos 11 (time 1.0):
 * - No one ahead → becomes **Fleet 1**
 *
 * #### Car at pos 8 (time 1.0):
 * - Time is same as Fleet 1 → **joins Fleet 1**
 *
 * #### Car at pos 5 (time 7.0):
 * - Time > Fleet 1 → too slow to catch up → **new Fleet 2**
 *
 * #### Car at pos 3 (time 3.0):
 * - Can reach earlier than car at pos 5 (time 7.0)
 * - But **slower car ahead slows it down** → joins **Fleet 2**
 *
 * #### Car at pos 0 (time 1.0):
 * - Time < Fleet 2 time → can’t catch → **joins Fleet 2**
 *
 * ---
 *
 * ### ✅ Final Fleets (by position):
 * - **Fleet 1**: [11, 8] (and 0 joins but is far behind, can't catch)
 * - **Fleet 2**: [5, 3, 0]
 *
 * So in code, we output:
 *
 * ```
 * Fleet 1: [11, 8]
 * Fleet 2: [5, 3, 0]
 * ```
 *
 * ---
 *
 * ### 🧠 How the code works (simple logic):
 *
 * 1. Sort all cars by position (rightmost car comes first).
 * 2. For each car:
 *    - If it takes **more time** than the last fleet → it starts a **new fleet**.
 *    - Otherwise, it **joins** the last fleet (because it will crash into it before the target).
 * 3. We just group cars accordingly.
 *
 * ---
 *
 * Would you like me to:
 * - Draw this visually as an image?
 * - Rewrite the code so that **you can see which car (by index or position)** joins which fleet?
 * - Explain how this would look like in real motion?
 *
 * Let me know how you’d like to see it!
 * */