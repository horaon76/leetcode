package Hard;

/**
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * <p>
 * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 * <p>
 * Given an integer array machines representing the number of dresses in each washing machine from left to right on the line, return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: machines = [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 * Example 2:
 * <p>
 * Input: machines = [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 * Example 3:
 * <p>
 * Input: machines = [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all three washing machines have the same number of dresses.
 **/
public class SuperWashingMachines517 {

    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int totalDresses = 0;

        // Calculate total dresses and average
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        if (totalDresses % n != 0) {
            return -1; // Not possible to balance if total is not divisible by number of machines
        }

        int target = totalDresses / n;
        int maxMoves = 0;
        int sumExcess = 0; // To track excess dresses while iterating

        for (int dresses : machines) {
            // Calculate excess or deficit
            int excess = dresses - target;
            sumExcess += excess;

            // If excess is positive, we can take dresses out; if negative, we need to bring in dresses
            // Max between the absolute excess and accumulated excess
            maxMoves = Math.max(maxMoves, Math.max(Math.abs(excess), sumExcess));
        }

        return maxMoves;
    }
}


