package Hard;

import java.util.PriorityQueue;
/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 *
 * There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 *
 * Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 * Example 2:
 *
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can not reach the target (or even the first gas station).
 * Example 3:
 *
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation: We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 *
 *
 * Constraints:
 *
 * 1 <= target, startFuel <= 109
 * 0 <= stations.length <= 500
 * 1 <= positioni < positioni+1 < target
 * 1 <= fueli < 109
 * **/

public class GasStation2_871 {

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Add the target as a final "station"
        int n = stations.length;
        stations = java.util.Arrays.copyOf(stations, n + 1);
        stations[n] = new int[]{target, 0}; // Add target with 0 fuel

        // Max heap to store available fuel at stations passed
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int stops = 0; // Number of refueling stops
        int currentFuel = startFuel; // Current fuel
        int prevPosition = 0; // Previous station position

        for (int[] station : stations) {
            int position = station[0];
            int fuel = station[1];

            // Calculate fuel used to reach the next station
            currentFuel -= (position - prevPosition);

            // While we do not have enough fuel to reach the next station
            while (currentFuel < 0 && !maxHeap.isEmpty()) {
                // Refuel with the maximum available fuel from previous stations
                currentFuel += maxHeap.poll();
                stops++;
            }

            // If we still don't have enough fuel to reach the next station, return -1
            if (currentFuel < 0) {
                return -1;
            }

            // Add this station's fuel to the max heap
            maxHeap.offer(fuel);
            prevPosition = position; // Update previous position
        }

        return stops; // Return the minimum number of refueling stops
    }

    public static void main(String[] args) {
        int target = 1000;
        int startFuel = 100;
        int[][] stations = {{250, 200}, {500, 100}, {750, 300}};
        int result = minRefuelStops(target, startFuel, stations);
        System.out.println(result); // Output: 2
    }
}

/**
 *
 * Explanation of the Code
 * Input Preparation: The target is added as a "station" with 0 fuel. This makes it easier to treat it as the last stop.
 * Max Heap: A priority queue (maxHeap) is used to store the fuel amounts available at the stations that have been passed but not yet refueled at.
 * Fuel Calculation:
 * For each station, calculate the fuel used to get there by subtracting the position of the previous station from the current position.
 * Refueling:
 * If the current fuel is less than zero (indicating the car can't reach the next station), pop fuel from the max heap to refuel until the car can reach the next station or until the max heap is empty.
 * If the car still can't reach the next station after using available fuel, return -1.
 * Adding Fuel: After reaching a station, add its fuel to the max heap.
 * Return: Finally, return the total number of refueling stops made.
 * Complexity
 * Time Complexity: O(N log N), where N is the number of stations. Each station is processed once, and operations on the priority queue take logarithmic time.
 * Space Complexity: O(N) for storing the max heap.
 * This code should effectively solve the problem, allowing you to determine the minimum number of refueling stops needed to reach the target distance.
 * **/