package Basic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsequence {


    public static void generateSubsequence(List<Integer> nums, int size){
        int totalSubsequence = (int) Math.pow(2, size);
        for(int counter = 0; counter < totalSubsequence; counter++){
            for(int j = 0; j < size; j++){
                //(counter & (1 << j)) != 0
                if(BigInteger.valueOf(counter).testBit(j)){
                    System.out.print(nums.get(j));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4));
        generateSubsequence(nums, nums.size());
    }

}
