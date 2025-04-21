package Practice;

public class BinarySearch {

    public static int doBinarySearch(int[] nums, int target){
        int low = 0;
        int high = nums.length;
        while(low <= high){
            int mid = (low + high) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                high = mid-1;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(doBinarySearch(nums,6));
    }
}

