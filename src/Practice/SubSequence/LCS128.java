package Practice.SubSequence;

import java.util.HashSet;

public class LCS128 {

    public static int findLCS(int[] nums, int length){
        int count = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num: nums){
            hashSet.add(num);
        }
        for(int num: nums){
            if(!hashSet.contains(num - 1)){
                int start = 0;
                int nextSequence = num;
                do{
                    start = start + 1;
                    nextSequence += 1;
                }while(hashSet.contains(nextSequence));
                count = Math.max(count, start);
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(findLCS(nums, nums.length));

    }
}
