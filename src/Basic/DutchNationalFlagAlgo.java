package Basic;

public class DutchNationalFlagAlgo {

    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] colors = {2, 0, 1, 2, 0, 1, 0};
        sortColors(colors);
        for (int color : colors) {
            System.out.print(color + " ");
        }
        // Output: 0 0 0 1 1 2 2
    }
}
