package MonotonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 * **/
public class NGE496 {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack=new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int num: nums2){
            while(!stack.isEmpty() && num > stack.peek())
                map.put(stack.pop(), num);
            stack.push(num);
        }

        int i=0;
        for(int num : nums1){
            result[i++] = map.getOrDefault(num, -1);
        }
        return result;
    }
    public static void main(String args[]) throws Exception {
        //nums1 = [4,1,2], nums2 = [1,3,4,2]
        //distinct array
        int[] nums = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(nums, nums2)));
    }
}

//Great! Let's dry run the updated input:
//
//```java
//int[] nums1 = {4, 1, 2};
//int[] nums2 = {4, 2, 1, 8};
//```
//
//---
//
//## 🔁 Goal:
//For each element in `nums1`, find the **next greater element in `nums2`** after its first appearance.
//We'll use a **monotonic stack** to build a `map` of each number's next greater.
//
//---
//
//## 🧠 Step-by-Step Dry Run — Build `map` from `nums2`
//
//Initial state:
//- `stack = []`
//- `map = {}`
//
//---
//
//### 🔹 Step 1: num = 4
//- Stack empty → push `4`
//- `stack = [4]`
//
//---
//
//### 🔹 Step 2: num = 2
//- `2 < stack.peek()` → push `2`
//- `stack = [4, 2]`
//
//---
//
//### 🔹 Step 3: num = 1
//- `1 < stack.peek()` → push `1`
//- `stack = [4, 2, 1]`
//
//---
//
//### 🔹 Step 4: num = 8
//Now we start popping since `8 > top`
//
//1. `8 > 1` → pop `1`, `map[1] = 8`
//2. `8 > 2` → pop `2`, `map[2] = 8`
//3. `8 > 4` → pop `4`, `map[4] = 8`
//
//- `stack = []`
//- Push `8`
//- `stack = [8]`
//
//---
//
//### ✅ Final map:
//```java
//{
//  1 = 8,
//  2 = 8,
//  4 = 8
//}
//```
//
//---
//
//## 🔍 Now Lookup for `nums1 = [4, 1, 2]`
//
//| Number | Lookup in map | Result |
//|--------|----------------|--------|
//| 4      | map[4] = 8     | ✅     |
//| 1      | map[1] = 8     | ✅     |
//| 2      | map[2] = 8     | ✅     |
//
//---
//
//## ✅ Final Output:
//```java
//[8, 8, 8]
//```
//
//---
//
//Let me know if you'd like me to walk through a case where some values don’t have a next greater!
