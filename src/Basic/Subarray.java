package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subarray {

    public static void generateSubArray(List<Integer> nums){
        for(int i=0; i< nums.size(); i++){
            for(int j=i; j< nums.size(); j++){
                for(int k=i; k<=j; k++){
                    System.out.print(nums.get(k));
                }
                System.out.println("");
            }
        }
    }

    public static void generateSubArray2(List<Integer> nums){
        for(int i = 0; i < nums.size(); i++){
            List<Integer> subarray = new ArrayList<>();
            for(int j = i; j < nums.size(); j++){
                subarray.add(nums.get(j));
                System.out.println(subarray.toString());
            }
            System.out.println("");
        }
    }


    public static List<List<Integer>> generateFixedLengthSubarrays(List<Integer> nums){
        int size = nums.size();
        int length = 2;
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i<= size - length; i++){
            List<Integer> subarray = new ArrayList<>();
            for(int j = 0; j< length; j++){
                subarray.add(nums.get(i+j));
            }
            result.add(subarray);
        }
        System.out.println(result.toString());
        return result;
    }

    public static void main(String[] args){
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> nums2 = List.of(1, 2, 3, 4, 5); // immutable list
        generateSubArray(nums2);
        System.out.println("nums2");
        generateFixedLengthSubarrays(nums2);
    }
}
