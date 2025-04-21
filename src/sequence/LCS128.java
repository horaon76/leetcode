package sequence;

//https://leetcode.com/problems/longest-consecutive-sequence/

import java.util.HashSet;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 *
 * **/

public class LCS128 {

    public static int getLongestConsecutiveLength(int[] nums) throws Exception {
        if(nums == null){
            throw new Exception("Empty Input");
        }
        if(nums.length == 0){
            return 0;
        }
        HashSet<Integer> numsSet = new HashSet<>();
        for(int num: nums){
            numsSet.add(num);
        }
        int count = 0;
        for(int num: nums){
            if(!numsSet.contains(num - 1)){
                int start = 0;
                int nextSequence = num;
                do{
                    start += 1;
                    nextSequence += 1;
                }while (numsSet.contains(nextSequence));
                count = Math.max(start, count);
            }
        }
        return count;
    }

    public static void main(String args[]) throws Exception {
        int[] nums = new int[]{100,4,200,1,3,2};
        int streak = getLongestConsecutiveLength(nums);

//        int[] nums2 = new int[]{0,3,7,2,5,8,4,6,0,1};
//        int streak2 = getLongestConsecutiveLength(nums2);

//        System.out.print(streak + ", " + streak2);
    }
}

//Time - O(n)
//Space - O(n)
//The worst-case time complexity of this algorithm is **not \(O(n^2)\)** because of how the inner loop operates. Let me explain why the worst-case complexity remains \(O(n)\), even with nested loops.
//
//---
//
//### **Key Insight: Each Number is Processed Exactly Once**
//
//- The outer loop iterates over all numbers in the array, i.e., \(O(n)\).
//- The inner loop only executes for numbers that are the **start of a sequence**. Once a sequence starts, the inner loop traverses the sequence exactly once.
//
//**Important:**
//Each element is processed in the inner loop only **once**, either as part of its sequence or another sequence. The `numsSet` check ensures that no number is repeatedly considered for starting a sequence.
//
//---
//
//### **Why \(O(n)\), Not \(O(n^2)\)?**
//
//#### Worst-Case Example
//Consider an input like `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`:
//1. In the **outer loop**, every number from 1 to 10 is considered.
//2. The **inner loop** will only execute for the first number (`1`), as all other numbers (`2, 3, ..., 10`) are part of the same sequence.
//
//   - For `num = 1`, the inner loop runs for 10 iterations to cover the entire sequence `[1, 2, ..., 10]`.
//   - For `num = 2, 3, ..., 10`, the inner loop doesn't run because `numsSet.contains(num - 1)` is `true`.
//
//Thus, the total number of operations for the inner loop across all iterations is **equal to the total number of elements in the array, \(n\)**.
//
//#### Key Property
//Each element contributes **once** to either:
//- Starting a sequence (inner loop runs).
//- Being skipped because it is part of an already-detected sequence.
//
//This ensures that the inner loop does not re-process the same numbers, keeping the total number of iterations proportional to \(n\).
//
//---
//
//### **Comparison with \(O(n^2)\)**
//
//For a truly \(O(n^2)\) complexity:
//- The inner loop would have to run \(n\) times for each of the \(n\) iterations of the outer loop.
//- In this algorithm, such behavior does not occur because:
//  - The `numsSet.contains(num - 1)` check ensures that the inner loop only processes **unique sequences**.
//  - Every element is processed only **once** in the inner loop.
//
//---
//
//### **Formal Proof**
//1. **Outer Loop:** \(O(n)\) — Iterates through all elements in `nums`.
//2. **Inner Loop:** Runs once for each number in the longest consecutive sequence:
//   - The length of all sequences combined is at most \(n\) (total number of elements).
//   - The inner loop processes each element **exactly once** across all sequences.
//
//Therefore, the total time complexity of the algorithm is \(O(n)\), not \(O(n^2)\).
//
//---
//
//### **Intuition with Example**
//
//#### Input: `[100, 4, 200, 1, 3, 2]`
//1. Outer loop processes numbers: `[100, 4, 200, 1, 3, 2]`.
//2. Inner loop:
//   - For `100`: Processes sequence `[100]` (1 iteration).
//   - For `4`: Skipped (`numsSet.contains(3)` is true).
//   - For `200`: Processes sequence `[200]` (1 iteration).
//   - For `1`: Processes sequence `[1, 2, 3, 4]` (4 iterations).
//   - For `3` and `2`: Skipped (`numsSet.contains(2)` and `numsSet.contains(1)` are true).
//
//Total inner loop iterations: \(1 + 1 + 4 = 6\) (equal to the size of the input).
//
//---
//
//### **Conclusion**
//The inner loop contributes at most \(O(n)\) iterations across all outer loop executions. Thus, the algorithm has **\(O(n)\) time complexity** even in the worst case.
//Yes, there are other ways to solve the problem of finding the length of the longest consecutive sequence in an array. Here are a few alternative approaches:
//
//---
//
//### **1. Using Sorting**
//#### **Approach:**
//1. Sort the array.
//2. Iterate through the sorted array and count the length of consecutive sequences.
//3. Track the maximum length encountered.
//
//#### **Code:**
//```java
//import java.util.Arrays;
//
//public static int getLongestConsecutiveLengthWithSorting(int[] nums) {
//    if (nums == null || nums.length == 0) {
//        return 0;
//    }
//
//    Arrays.sort(nums); // Sort the array in O(n log n)
//    int maxLength = 1;
//    int currentLength = 1;
//
//    for (int i = 1; i < nums.length; i++) {
//        if (nums[i] == nums[i - 1] + 1) { // Consecutive element
//            currentLength++;
//        } else if (nums[i] != nums[i - 1]) { // Reset if not duplicate
//            currentLength = 1;
//        }
//        maxLength = Math.max(maxLength, currentLength);
//    }
//    return maxLength;
//}
//```
//
//#### **Time Complexity:**
//- Sorting takes \(O(n \log n)\).
//- Iterating through the sorted array takes \(O(n)\).
//- **Total:** \(O(n \log n)\).
//
//#### **Space Complexity:**
//- \(O(1)\), assuming in-place sorting.
//
//---
//
//### **2. Optimized HashSet Solution (Current Approach)**
//This is the current solution you provided, which has \(O(n)\) time complexity and uses a `HashSet`. It remains the most efficient approach for unsorted data.
//
//---
//
//### **3. Union-Find (Disjoint Set Union)**
//#### **Approach:**
//1. Use Union-Find to group elements into connected components representing consecutive sequences.
//2. Find the largest component size.
//
//#### **Steps:**
//1. Create a `parent` and `rank` array to manage disjoint sets.
//2. For each number, try to union it with its neighbors (`num - 1` and `num + 1`).
//3. After processing, count the size of each component to determine the longest sequence.
//
//#### **Code:**
//```java
//import java.util.HashMap;
//
//public static int getLongestConsecutiveLengthWithUnionFind(int[] nums) {
//    if (nums == null || nums.length == 0) {
//        return 0;
//    }
//
//    HashMap<Integer, Integer> parent = new HashMap<>();
//    HashMap<Integer, Integer> size = new HashMap<>();
//
//    for (int num : nums) {
//        if (!parent.containsKey(num)) {
//            parent.put(num, num);
//            size.put(num, 1);
//
//            // Union with neighbors
//            if (parent.containsKey(num - 1)) {
//                union(num, num - 1, parent, size);
//            }
//            if (parent.containsKey(num + 1)) {
//                union(num, num + 1, parent, size);
//            }
//        }
//    }
//
//    int maxLength = 0;
//    for (int s : size.values()) {
//        maxLength = Math.max(maxLength, s);
//    }
//    return maxLength;
//}
//
//private static void union(int x, int y, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> size) {
//    int rootX = find(x, parent);
//    int rootY = find(y, parent);
//    if (rootX != rootY) {
//        parent.put(rootX, rootY);
//        size.put(rootY, size.get(rootY) + size.get(rootX));
//    }
//}
//
//private static int find(int x, HashMap<Integer, Integer> parent) {
//    if (parent.get(x) != x) {
//        parent.put(x, find(parent.get(x), parent)); // Path compression
//    }
//    return parent.get(x);
//}
//```
//
//#### **Time Complexity:**
//- Union-Find with path compression and union by size has nearly \(O(1)\) operations per union or find.
//- Total complexity is \(O(n)\) for \(n\) numbers.
//
//#### **Space Complexity:**
//- \(O(n)\) for `parent` and `size` maps.
//
//---
//
//### **4. Using a HashMap for Range Tracking**
//#### **Approach:**
//1. Use a `HashMap` to map numbers to the lengths of the consecutive sequences they belong to.
//2. For each number, check if it's part of an existing sequence or starts a new sequence.
//3. Update the boundaries of the sequences dynamically.
//
//#### **Code:**
//```java
//import java.util.HashMap;
//
//public static int getLongestConsecutiveLengthWithHashMap(int[] nums) {
//    if (nums == null || nums.length == 0) {
//        return 0;
//    }
//
//    HashMap<Integer, Integer> map = new HashMap<>();
//    int maxLength = 0;
//
//    for (int num : nums) {
//        if (!map.containsKey(num)) {
//            int left = map.getOrDefault(num - 1, 0);
//            int right = map.getOrDefault(num + 1, 0);
//            int currentLength = left + right + 1;
//
//            map.put(num, currentLength);
//            maxLength = Math.max(maxLength, currentLength);
//
//            // Update boundaries
//            map.put(num - left, currentLength);
//            map.put(num + right, currentLength);
//        }
//    }
//    return maxLength;
//}
//```
//
//#### **Time Complexity:**
//- \(O(n)\), as each number is processed once.
//- HashMap lookups and updates are \(O(1)\) on average.
//
//#### **Space Complexity:**
//- \(O(n)\) for the `HashMap`.
//
//---
//
//### **Comparison of Methods**
//| **Approach**               | **Time Complexity** | **Space Complexity** | **When to Use**                |
//|-----------------------------|---------------------|-----------------------|---------------------------------|
//| Sorting                    | \(O(n \log n)\)     | \(O(1)\)              | When sorting is acceptable.    |
//| HashSet (Optimized)        | \(O(n)\)            | \(O(n)\)              | Most efficient for unsorted data. |
//| Union-Find                 | \(O(n)\)            | \(O(n)\)              | Useful for understanding disjoint sets. |
//| HashMap for Range Tracking | \(O(n)\)            | \(O(n)\)              | Alternate efficient method.    |
//
//---
//
//### **Recommendation**
//- For practical usage, the **HashSet (Optimized)** or **HashMap for Range Tracking** approaches are the best, as they achieve \(O(n)\) time complexity with straightforward implementation.
//- Use **Union-Find** for academic purposes or if explicitly required to group elements into disjoint sets.