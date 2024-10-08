### Definition of Subsequence

A **subsequence** is a sequence derived from another sequence by deleting some or none of the elements without changing the order of the remaining elements. Unlike subarrays, subsequences can be non-contiguous.

### Formula for Total Subsequences

The total number of subsequences that can be formed from a sequence of length \( n \) is given by the formula:

\[
\text{Total Subsequences} = 2^n
\]

### Explanation:
- Each element in the sequence can either be included in a subsequence or excluded from it.
- Therefore, for each of the \( n \) elements, there are 2 choices (include or exclude).
- Hence, the total number of subsequences is \( 2^n \).

### Example with a Sequence of 7 Elements

Let's consider a sequence of 7 elements:
```
Sequence A = [1, 2, 3, 4, 5, 6, 7]
```

**Calculation**:
- The number of elements \( n = 7 \).
- Using the formula:

\[
\text{Total Subsequences} = 2^7 = 128
\]

### List of Subsequences

For the sequence \( A = [1, 2, 3, 4, 5, 6, 7] \), the subsequences include:

1. **The empty subsequence**:
    - `[]`

2. **Single-element subsequences**:
    - `[1]`
    - `[2]`
    - `[3]`
    - `[4]`
    - `[5]`
    - `[6]`
    - `[7]`

3. **Two-element subsequences**:
    - `[1, 2]`
    - `[1, 3]`
    - `[1, 4]`
    - `[1, 5]`
    - `[1, 6]`
    - `[1, 7]`
    - `[2, 3]`
    - `[2, 4]`
    - `[2, 5]`
    - `[2, 6]`
    - `[2, 7]`
    - `[3, 4]`
    - `[3, 5]`
    - `[3, 6]`
    - `[3, 7]`
    - `[4, 5]`
    - `[4, 6]`
    - `[4, 7]`
    - `[5, 6]`
    - `[5, 7]`
    - `[6, 7]`

4. **Three-element subsequences**:
    - `[1, 2, 3]`
    - `[1, 2, 4]`
    - `[1, 2, 5]`
    - `[1, 2, 6]`
    - `[1, 2, 7]`
    - ...
    - (Continues for all combinations)

5. **Four-element subsequences**:
    - `[1, 2, 3, 4]`
    - `[1, 2, 3, 5]`
    - ...
    - (Continues for all combinations)

6. **Five-element subsequences**:
    - `[1, 2, 3, 4, 5]`
    - ...
    - (Continues for all combinations)

7. **Six-element subsequences**:
    - `[1, 2, 3, 4, 5, 6]`
    - `[1, 2, 3, 4, 5, 7]`
    - ...

8. **The full sequence**:
    - `[1, 2, 3, 4, 5, 6, 7]`

### Summary of Subsequences
For the sequence \( [1, 2, 3, 4, 5, 6, 7] \), there are a total of 128 subsequences, which include all combinations of the elements in the original sequence, maintaining their order. The key characteristics of subsequences include:
- **Non-contiguous**: Elements do not need to be next to each other in the original sequence.
- **Order**: The order of elements is preserved from the original sequence.
- **Length Variation**: Subsequences can have any length from 0 to \( n \), where \( n \) is the length of the original sequence.

### Time Complexity for Finding All Subsequences
If you were to generate all subsequences programmatically, the time complexity would be \( O(n \times 2^n) \) because you need to iterate through all possible combinations, and for each combination, you might need to build the resulting subsequence.

### Methods to Generate Subsequences

#### 1. Brute Force Approach

This approach can be implemented using recursion or iterative methods to generate all possible subsequences.

**Java Code (Brute Force using Recursion)**:
```java
import java.util.ArrayList;
import java.util.List;

public class SubsequenceGenerator {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> subsequences = new ArrayList<>();
        generateSubsequences(array, 0, new ArrayList<>(), subsequences);
        
        // Print all subsequences
        for (List<Integer> subsequence : subsequences) {
            System.out.println(subsequence);
        }
    }

    public static void generateSubsequences(int[] array, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == array.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include the current element
        current.add(array[index]);
        generateSubsequences(array, index + 1, current, result);

        // Exclude the current element
        current.remove(current.size() - 1);
        generateSubsequences(array, index + 1, current, result);
    }
}
```

#### 2. Iterative Approach

You can also generate subsequences iteratively by maintaining a list of current subsequences and expanding it by adding each element.

**Java Code (Iterative)**:
```java
import java.util.ArrayList;
import java.util.List;

public class IterativeSubsequences {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> subsequences = new ArrayList<>();
        
        // Start with the empty subsequence
        subsequences.add(new ArrayList<>());

        for (int num : array) {
            int size = subsequences.size();
            for (int j = 0; j < size; j++) {
                List<Integer> newSubsequence = new ArrayList<>(subsequences.get(j));
                newSubsequence.add(num); // Add the current element to existing subsequences
                subsequences.add(newSubsequence);
            }
        }

        // Print all subsequences
        for (List<Integer> subsequence : subsequences) {
            System.out.println(subsequence);
        }
    }
}
```

### Summary of Approaches to Generate Subsequences

1. **Brute Force (Recursive)**:
    - Simple to understand and implement.
    - Uses recursion to explore each element's inclusion or exclusion.
    - Time Complexity: \( O(n \times 2^n) \) for generating and storing subsequences.

2. **Iterative**:
    - Constructs subsequences by expanding existing subsequences iteratively.
    - Time Complexity: \( O(n \times 2^n) \) for generating and storing subsequences.

You can choose any of these methods based on your requirements, such as whether you need to generate all subsequences, specific conditions to apply, or if you have additional constraints to consider.