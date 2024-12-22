package Subarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    static void subArrayOptimised(int n, int[] arr) {
        // Pick starting point
        for (int i = 0; i < n; i++) {
            StringBuilder subArrayStr = new StringBuilder();

            // Pick ending point
            for (int j = i; j < n; j++) {
                // Dynamically build the subarray string
                subArrayStr.append(arr[j]).append(" ");

                // Print the subarray
                System.out.println(subArrayStr.toString().trim());
            }
        }
    }

    static void subArrayWithArray(int n, int[] arr) {
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>(); // Temporary list to store the subarray

            for (int j = i; j < n; j++) {
                temp.add(arr[j]); // Add element to the list

                // Print the current subarray
                System.out.println(temp.toString());
                // Convert the list to a string and print it
//                String subArrayStr = temp.stream()
//                        .map(String::valueOf) // Convert each element to a string
//                        .collect(Collectors.joining(" "));
//                System.out.println(subArrayStr);
            }
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 3, 4};
//        subArray(nums.length, nums);
        subArrayWithArray(nums.length, nums);

    }

}
//Let's break down the time complexity of the `subArrayWithArray` method:
//
//```java
//static void subArrayWithArray(int n, int[] arr) {
//    for (int i = 0; i < n; i++) {
//        List<Integer> temp = new ArrayList<>(); // Temporary list to store the subarray
//
//        for (int j = i; j < n; j++) {
//            temp.add(arr[j]); // Add element to the list
//
//            // Convert the list to a string and print it
//            String subArrayStr = temp.stream()
//                    .map(String::valueOf) // Convert each element to a string
//                    .collect(Collectors.joining(" "));
//            System.out.println(subArrayStr);
//        }
//    }
//}
//```
//
//### **Time Complexity Breakdown**
//
//#### 1. **Outer Loop (`i` loop)**:
//   - This loop runs **`n`** times, where `n` is the length of the input array `arr`.
//
//#### 2. **Inner Loop (`j` loop)**:
//   - For each value of `i`, the inner loop runs from `j = i` to `j = n - 1`, i.e., the number of iterations is **`n - i`**.
//   - Hence, the inner loop's time complexity for a single value of `i` is **`O(n - i)`**, which is **`O(n)`** for each `i`.
//
//#### 3. **Operations Inside the Inner Loop**:
//   - **`temp.add(arr[j])`**: Adding an element to the `ArrayList` takes **`O(1)`** time (amortized).
//
//   - **`temp.stream().map(String::valueOf).collect(Collectors.joining(" "))`**:
//     - **`temp.stream()`**: Creating a stream over the list takes **`O(k)`**, where `k` is the current size of `temp`. For each iteration of the inner loop, the size of `temp` is **`j - i + 1`**.
//     - **`map(String::valueOf)`**: This converts each element in the list to a string, which takes **`O(k)`** where `k` is the number of elements in `temp` at the time.
//     - **`collect(Collectors.joining(" "))`**: Collecting the elements into a single string involves concatenating the elements, which also takes **`O(k)`**.
//
//   - So, the string conversion and joining operation inside the inner loop take **`O(k)`** time, where `k` is the size of `temp`.
//
//#### 4. **Total Time Complexity**:
//   - For each `i`, the inner loop runs **`n - i`** times.
//   - In each inner loop iteration, the complexity of converting the list to a string is **`O(k)`**, where `k = j - i + 1`.
//
//   Therefore, the overall time complexity can be computed by summing over all iterations of `i` and `j`:
//
//   \[
//   \sum_{i=0}^{n-1} \sum_{j=i}^{n-1} O(j - i + 1)
//   \]
//
//   Which simplifies to:
//
//   \[
//   O(n) + O(2n) + O(3n) + ... + O(n^2)
//   \]
//
//   This is an arithmetic series summing to **`O(n^2)`**.
//
//---
//
//### **Final Time Complexity**:
//- The time complexity of this function is **`O(n^3)`**, because for each element, you are adding it to a list, and converting the list to a string requires iterating over all its elements, leading to a cubic complexity due to the nested loops and the string concatenation.
//
//---
//
//### **Space Complexity**:
//- The space complexity is driven by the space used for the `temp` list, which can grow to size `n` in the worst case. Therefore, the auxiliary space complexity is **`O(n)`**.
//
