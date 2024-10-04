package DP;

public class MaxProductSubArray152 {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap currentMax and currentMin
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            currentMax = Math.max(nums[i], currentMax * nums[i]);
            currentMin = Math.min(nums[i], currentMin * nums[i]);

            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{2, 3, -2, 4};
        int[] nums1 = new int[]{-2,0,-1, 3, 4};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums1));

    }
}
