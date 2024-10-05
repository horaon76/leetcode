package Subarray;

/**
 * A subarray is a contiguous part of the array. An array that is inside another array. For example, consider the array [1, 2, 3, 4], There are 10 non-empty sub-arrays. The subarrays are (1), (2), (3), (4), (1,2), (2,3), (3,4), (1,2,3), (2,3,4) and (1,2,3,4). In general, for an array/string of size n, there are n*(n+1)/2 non-empty subarrays/substrings.
 * All Non-empty Subarrays
 * 1
 * 1 2
 * 1 2 3
 * 1 2 3 4
 * 2
 * 2 3
 * 2 3 4
 * 3
 * 3 4
 * 4
 * <p>
 * Total subarray - n*(n+1)/2
 **/
public class subArray {

    static void subArray(int n, int[] arr) {
        // Pick starting point
        for (int i = 0; i < n; i++) {
            // Pick ending point
            for (int j = i; j < n; j++) {
                // Print subarray between current starting
                // and ending points
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 3, 4};
        subArray(nums.length, nums);
    }

}
