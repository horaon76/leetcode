To generate all subsequences of a given array, we can use a **recursive approach**. Each element of the array can either be included in a subsequence or excluded, leading to \(2^n\) possible subsequences.

---

### **Code for Generating Subsequences**
Here’s how you can generate all subsequences in Java:

```java
import java.util.*;

public class Subsequences {

    public static List<List<Integer>> generateSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generate(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generate(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Base case: if we've processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include the current element
        current.add(nums[index]);
        generate(nums, index + 1, current, result);

        // Backtrack and exclude the current element
        current.remove(current.size() - 1);
        generate(nums, index + 1, current, result);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsequences = generateSubsequences(nums);
        System.out.println(subsequences);
    }
}
```

---

### **Example Execution for `nums = [1, 2, 3]`**

#### Recursive Tree Representation:
- At index 0:
    - Include `1`: Proceed with `[1]`
    - Exclude `1`: Proceed with `[]`

- At index 1:
    - Include `2`: Add `2` to both paths
    - Exclude `2`: Skip `2` in both paths

- At index 2:
    - Include `3`: Add `3` to all paths
    - Exclude `3`: Skip `3` in all paths

#### Final Output:
```
[[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
```

---

### **Time and Space Complexity**

#### Time Complexity:
- Each element has 2 choices (include or exclude).
- Total subsets (subsequences) = \(2^n\).
- Generating each subsequence takes \(O(n)\) time for copying to the result.
- Total time complexity:
  \[
  O(n \cdot 2^n)
  \]

#### Space Complexity:
- Recursive stack space: \(O(n)\) (maximum depth of recursion).
- Result storage: \(O(n \cdot 2^n)\) for storing all subsequences.
- Total space complexity:
  \[
  O(n \cdot 2^n)
  \]


The **bit manipulation approach** to generate all subsequences (or subsets) is an efficient method. This approach leverages the fact that there are \(2^n\) subsequences for an array of size \(n\), and the binary representation of numbers from \(0\) to \(2^n - 1\) can be used to represent the inclusion or exclusion of elements in a subset.

---

### **Bit Manipulation Approach**

#### **Key Idea**
- Use the binary representation of integers from \(0\) to \(2^n - 1\) to decide which elements to include in each subsequence.
    - A bit value of `1` means the corresponding element is included.
    - A bit value of `0` means the corresponding element is excluded.

#### **Steps**
1. Iterate through all numbers from \(0\) to \(2^n - 1\).
2. For each number, check each bit to determine which elements to include in the subsequence.

---

### **Code Implementation**
```java
import java.util.*;

public class SubsequencesBitManipulation {

    public static List<List<Integer>> generateSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n subsets

        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if the j-th bit in i is set (1)
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsequences = generateSubsequences(nums);
        System.out.println(subsequences);
    }
}
```

---

### **Example Execution**

#### Input:
`nums = [1, 2, 3]`

#### Binary Representation of Subsets:
| Decimal (`i`) | Binary Representation (`i`) | Subsequence |
|---------------|------------------------------|-------------|
| 0             | `000`                        | `[]`        |
| 1             | `001`                        | `[1]`       |
| 2             | `010`                        | `[2]`       |
| 3             | `011`                        | `[1, 2]`    |
| 4             | `100`                        | `[3]`       |
| 5             | `101`                        | `[1, 3]`    |
| 6             | `110`                        | `[2, 3]`    |
| 7             | `111`                        | `[1, 2, 3]` |

#### Final Output:
```
[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
```

---

### **Time and Space Complexity**

#### Time Complexity:
1. We iterate through \(2^n\) numbers.
2. For each number, we check \(n\) bits to determine which elements to include.
   \[
   O(n \cdot 2^n)
   \]

#### Space Complexity:
1. Result storage: \(O(n \cdot 2^n)\), as we store \(2^n\) subsequences, each of size up to \(n\).
2. Auxiliary space for the current subset: \(O(n)\).

\[
O(n \cdot 2^n)
\]


Yes, we can generate all subsequences iteratively using a **similar approach to generating subsets**. This involves starting with an empty subsequence and iteratively adding each element to all existing subsequences in the result list.

---

### **Iterative Approach to Generate Subsequences**

#### **Steps**
1. Start with an initial list containing an empty subsequence: `result = [[]]`.
2. For each element in the input array:
    - Iterate over the existing subsequences in `result`.
    - Create new subsequences by appending the current element to each existing subsequence.
    - Add these new subsequences to the `result`.
3. Return the `result` after processing all elements.

---

### **Code Implementation**

```java
import java.util.*;

public class SubsequencesIterative {

    public static List<List<Integer>> generateSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with an empty subsequence

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                // Create a new subsequence by adding the current number
                List<Integer> newSubsequence = new ArrayList<>(result.get(i));
                newSubsequence.add(num);
                result.add(newSubsequence);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsequences = generateSubsequences(nums);
        System.out.println(subsequences);
    }
}
```

---

### **Example Execution**

#### Input:
`nums = [1, 2, 3]`

#### Iterative Steps:

1. **Start:**
    - `result = [[]]` (contains only the empty subsequence)

2. **Processing 1:**
    - Existing subsequences: `[[]]`
    - Add `1` to each subsequence:
        - New subsequences: `[[1]]`
    - Update `result`: `[[], [1]]`

3. **Processing 2:**
    - Existing subsequences: `[[], [1]]`
    - Add `2` to each subsequence:
        - New subsequences: `[[2], [1, 2]]`
    - Update `result`: `[[], [1], [2], [1, 2]]`

4. **Processing 3:**
    - Existing subsequences: `[[], [1], [2], [1, 2]]`
    - Add `3` to each subsequence:
        - New subsequences: `[[3], [1, 3], [2, 3], [1, 2, 3]]`
    - Update `result`: `[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]`

#### Final Output:
```
[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
```

---

### **Time and Space Complexity**

#### **Time Complexity:**
1. Each element in `nums` is processed once.
2. For each element, we iterate over all existing subsequences. The number of subsequences doubles at each step:
    - First element: \(1\) subsequence
    - Second element: \(2\) subsequences
    - Third element: \(4\) subsequences
    - Total subsequences: \(2^n\)
3. Generating new subsequences involves copying, which takes \(O(n)\) time for subsequences of size \(n\) in the worst case.

\[
O(n \cdot 2^n)
\]

#### **Space Complexity:**
1. **Result Storage:**
    - Storing \(2^n\) subsequences, each of size up to \(n\): \(O(n \cdot 2^n)\).
2. **Auxiliary Space:**
    - Temporary space for new subsequences in each iteration: \(O(n)\).

\[
O(n \cdot 2^n)
\]