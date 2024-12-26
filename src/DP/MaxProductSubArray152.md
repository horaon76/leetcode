package DP;
//Given an integer array nums, find a
//subarray
// that has the largest product, and return the product.
//
//The test cases are generated so that the answer will fit in a 32-bit integer.
//
//
//
//Example 1:
//
//Input: nums = [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
//Example 2:
//
//Input: nums = [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
//
//
//Constraints:
//
//1 <= nums.length <= 2 * 104
//-10 <= nums[i] <= 10
//The product of any subarray of nums is guaranteed to fit in a 32-bit integer.

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

Here are **all possible solutions** for the **Maximum Product Subarray** problem (LeetCode 152) implemented in Java.

---

### **1. Optimized Dynamic Programming (Single Pass)**

This approach uses two variables (`maxProduct` and `minProduct`) to track the maximum and minimum product up to the current index.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            if (num < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(num, maxProduct * num);
            minProduct = Math.min(num, minProduct * num);

            result = Math.max(result, maxProduct);
        }

        return result;
    }
}
```

---

### **2. Naive Dynamic Programming (Two Arrays)**

In this approach, two arrays are used to store the maximum and minimum product up to each index. This is less space-efficient.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] maxProduct = new int[nums.length];
        int[] minProduct = new int[nums.length];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxProduct[i] = Math.max(nums[i], maxProduct[i - 1] * nums[i]);
                minProduct[i] = Math.min(nums[i], minProduct[i - 1] * nums[i]);
            } else {
                maxProduct[i] = Math.max(nums[i], minProduct[i - 1] * nums[i]);
                minProduct[i] = Math.min(nums[i], maxProduct[i - 1] * nums[i]);
            }
            result = Math.max(result, maxProduct[i]);
        }

        return result;
    }
}
```

---

### **3. Brute Force (All Subarrays)**

This approach evaluates all possible subarrays and computes their product. It is inefficient but can be useful for small arrays.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int result = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                result = Math.max(result, product);
            }
        }

        return result;
    }
}
```

---

### **4. Divide and Conquer Approach**

This approach splits the array into two halves and computes the maximum product recursively. It is less common for this problem due to added complexity.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        return maxProductHelper(nums, 0, nums.length - 1);
    }

    private int maxProductHelper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;

        int leftMax = maxProductHelper(nums, left, mid);
        int rightMax = maxProductHelper(nums, mid + 1, right);

        int crossMax = maxCrossingProduct(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingProduct(int[] nums, int left, int mid, int right) {
        int leftProduct = Integer.MIN_VALUE;
        int product = 1;
        for (int i = mid; i >= left; i--) {
            product *= nums[i];
            leftProduct = Math.max(leftProduct, product);
        }

        int rightProduct = Integer.MIN_VALUE;
        product = 1;
        for (int i = mid + 1; i <= right; i++) {
            product *= nums[i];
            rightProduct = Math.max(rightProduct, product);
        }

        return leftProduct * rightProduct;
    }
}
```

---

### **5. Kadane's Algorithm Variation**

This approach is similar to Kadane’s algorithm for the maximum subarray sum but adapted for products.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int tempMax = Math.max(num, Math.max(currentMax * num, currentMin * num));
            currentMin = Math.min(num, Math.min(currentMax * num, currentMin * num));
            currentMax = tempMax;

            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }
}
```

---

### Summary of Approaches
| **Approach**                     | **Time Complexity** | **Space Complexity** |
|-----------------------------------|---------------------|-----------------------|
| Optimized DP (Single Pass)        | \(O(n)\)            | \(O(1)\)             |
| Naive DP (Two Arrays)             | \(O(n)\)            | \(O(n)\)             |
| Brute Force (All Subarrays)       | \(O(n^2)\)          | \(O(1)\)             |
| Divide and Conquer               | \(O(n \log n)\)     | \(O(\log n)\)        |
| Kadane's Algorithm Variation      | \(O(n)\)            | \(O(1)\)             |

Would you like further explanations or any additional variations?