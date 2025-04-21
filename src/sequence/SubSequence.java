package sequence;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1234 - 2^n -1 subsequence
 * Subsequence: A subsequence is a sequence that can be derived from another sequence by removing zero or more elements, without changing the order of the remaining elements.
 * <p>
 * For the same example, there are 15 sub-sequences. They are (1), (2), (3), (4), (1,2), (1,3),(1,4), (2,3), (2,4), (3,4), (1,2,3), (1,2,4), (1,3,4), (2,3,4), (1,2,3,4). More generally, we can say that for a sequence of size n, we can have (2n-1) non-empty sub-sequences in total.
 * 1
 * 2
 * 1 2
 * 3
 * 1 3
 * 2 3
 * 1 2 3
 * 4
 * 1 4
 * 2 4
 * 1 2 4
 * 3 4
 * 1 3 4
 * 2 3 4
 * 1 2 3 4
 **/
public class SubSequence {
    static void printSubsequences(int n, int[] arr) {
        /* Number of subsequences is (2**n -1)*/
        int opsize = (int) Math.pow(2, n);

        /* Run from counter 000..1 to 111..1*/
        for (int counter = 1; counter < opsize; counter++) {
            System.out.println("binary " + Integer.toBinaryString(counter));
            for (int j = 0; j < n; j++) {
                /* Check if jth bit in the counter is set
                    If set then print jth element from arr[] */
                if (BigInteger.valueOf(counter).testBit(j)){
                    System.out.print(arr[j] + " ");
                }

            }
            System.out.println();
        }
    }

    public static List<List<Integer>> generateSubSequence(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        generateSubSequenceBackTrack(nums, 0, new ArrayList<>(),  result);
//        result.sort(Comparator.comparingInt(List::size));
        System.out.println(result);
        return result;
    }

    public static void generateSubSequenceBackTrack(int[] nums,  int index, List<Integer> currentList, List<List<Integer>> result){
        // Base case: if we've processed all elements

        if (index == nums.length) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        int currentNumber = nums[index];
        // Include the current element
        currentList.add(nums[index]);
        generateSubSequenceBackTrack(nums, index + 1, currentList, result);

        // Backtrack and exclude the current element
        currentList.remove(currentList.size() - 1);
        generateSubSequenceBackTrack(nums, index + 1, currentList, result);
    }

    public static void main(String args[]) {
        int nums[] = new int[]{1, 2, 3, 4};
        printSubsequences(nums.length, nums);
        System.out.println("Backtrack");
        generateSubSequence(nums);
    }

}
