package Practice;

import java.util.ArrayList;
import java.util.List;

public class SubArray {

    public static void generateSubArrays(int[] nums, int length){
        for(int i=0; i<length; i++){
            System.out.println(" ");
            for(int j=i; j<length; j++){
                for(int k=i; k<=j; k++){
                    System.out.print(nums[k] + " ");
                }
                System.out.println(" ");
            }
        }
    }

    public static void generateSubArraysOpt(int[] nums, int length){
        for(int i=0; i< length; i++){
            List<Integer> subArrays = new ArrayList<>();
            for(int j =i; j<length; j++){
                subArrays.add(nums[j]);
                System.out.println(subArrays);
            }
        }
    }

    public static void main(String[] args){
        int nums[] = new int[]{1, 2, 3, 4};
        //(4*(4+1))/2 = 10
        generateSubArraysOpt(nums, nums.length);
    }
}
