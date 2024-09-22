package sequence;

public class LongestSubArray674 {

    public static int getLIS(int[] nums){
        int length = 1;
        int currentMax = 1;
        for(int i=1; i< nums.length; i++){
            if(nums[i - 1] < nums[i]){
                currentMax += 1;
            }else{
                length = Math.max(currentMax, length);
                currentMax = 1;
            }
        }
        return length;
    }

    public static void main(String[] args){
        int nums[] = new int[]{1,3,5,6,7,4,5,6,7,8,9,10,7};
        int nums1[] = new int[]{2,2,2,2,2};
        System.out.print(getLIS(nums));
        System.out.print(getLIS(nums1));
    }

}
