The Dutch National Flag algorithm is a well-known sorting algorithm that efficiently partitions an array into three distinct categories. It was proposed by Edsger Dijkstra and is often used for sorting problems involving three distinct values. The algorithm is particularly useful for problems such as sorting colors (like the Dutch national flag) or any situation where you need to sort elements based on three categories.

### Problem Statement
Given an array containing three different types of elements (usually represented by integers 0, 1, and 2), the goal is to sort the array such that all elements of the same type are grouped together. For example, if you have an array of integers where 0 represents red, 1 represents white, and 2 represents blue, you want to sort them as `[0, 0, 1, 1, 1, 2, 2]`.

### Algorithm Explanation
The Dutch National Flag algorithm uses a single pass through the array with three pointers:
- **Low**: Pointer for the next position of 0.
- **Mid**: Pointer for the current element being evaluated.
- **High**: Pointer for the next position of 2.

The algorithm proceeds as follows:
1. Initialize `low` and `mid` to the start of the array and `high` to the end of the array.
2. Iterate through the array:
    - If the current element (`arr[mid]`) is 0:
        - Swap it with `arr[low]`, then increment both `low` and `mid`.
    - If the current element is 1:
        - Just increment `mid`.
    - If the current element is 2:
        - Swap it with `arr[high]`, then decrement `high` (do not increment `mid` since we need to evaluate the swapped element).

### Java Implementation
Here’s a Java implementation of the Dutch National Flag algorithm:

```java
public class DutchNationalFlag {

    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            switch (nums[mid]) {
                case 0: // Current element is 0
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1: // Current element is 1
                    mid++;
                    break;
                case 2: // Current element is 2
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println("Sorted array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
```

### Example Execution
For the input array `{2, 0, 2, 1, 1, 0}`, the execution of the algorithm will proceed as follows:

1. Initial state: `low = 0`, `mid = 0`, `high = 5`
    - Array: `[2, 0, 2, 1, 1, 0]`

2. `mid` points to `2`: Swap with `high`
    - Result: `[0, 0, 2, 1, 1, 2]`, `high` decremented to 4.

3. `mid` points to `0`: Swap with `low`
    - Result: `[0, 0, 2, 1, 1, 2]`, `low` incremented to 1, `mid` incremented to 1.

4. `mid` points to `0`: Swap with `low`
    - Result: `[0, 0, 2, 1, 1, 2]`, `low` incremented to 2, `mid` incremented to 2.

5. `mid` points to `2`: Swap with `high`
    - Result: `[0, 0, 1, 1, 2, 2]`, `high` decremented to 3.

6. `mid` points to `1`: Just increment `mid`
    - Result: `[0, 0, 1, 1, 2, 2]`, `mid` incremented to 3.

7. `mid` points to `1`: Just increment `mid`
    - Result: `[0, 0, 1, 1, 2, 2]`, `mid` incremented to 4.

Now `mid` exceeds `high`, and the loop terminates.

### Complexity Analysis
- **Time Complexity**: O(n), where n is the number of elements in the array, since we only make a single pass through the array.
- **Space Complexity**: O(1), as we are sorting the array in place without using any additional data structures.

This algorithm is highly efficient for sorting arrays with three distinct values. If you have any further questions or need more examples, feel free to ask!