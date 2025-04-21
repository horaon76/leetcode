package Practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    public static void generateSubsequence(int[] nums){
        List<List<Integer>> result = new ArrayList<>(); //n.2^(n-1) subsets
        processSubsequence(nums, result, 0, new ArrayList<>());
        System.out.println(result);
    }

    public static void processSubsequence(int[] nums, List<List<Integer>> result, int index, List<Integer> subSequence){
        if(index == nums.length){
            result.add(new ArrayList<>(subSequence));
            return;
        }
        subSequence.add(nums[index]);
        processSubsequence(nums, result, index +1, subSequence);

        subSequence.remove(subSequence.size() - 1);
        processSubsequence(nums, result, index + 1, subSequence);
    }

    public static void generateSubsequenceUsingBits(int[] nums){
        List<List<Integer>> result = new ArrayList<>(); //n.2^(n-1) subsets
        for(int i = 0; i< Math.pow(2, nums.length); i++){ // 2^n
            List<Integer> subSequence = new ArrayList<>();
            for(int j = 0; j<nums.length; j++){ // n
                if(BigInteger.valueOf(i).testBit(j)){
                    subSequence.add(nums[j]); // O(1)
                }
            }
            result.add(new ArrayList<>(subSequence)); //O(n)
        }
        System.out.println(result);

    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        //time - n.2^n; space - n.2^n-1;(recursion space + 2^n elements)
        //generateSubsequence(nums);
        //Time Complexity - O(n⋅2^n)// Space Complexity - n.2^n-1
        generateSubsequenceUsingBits(nums);
    }
}
