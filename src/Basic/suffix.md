A **suffix array** is a data structure that provides a sorted array of all suffixes of a given string. It is a powerful tool for various string processing tasks, including substring searching, pattern matching, and more.

### Definition
For a given string \( S \) of length \( n \), a suffix is a substring that starts from some position \( i \) (where \( 0 \leq i < n \)) to the end of the string. The suffix array is an array of integers that represent the starting indices of these suffixes, sorted in lexicographical order.

### Example
Consider the string `banana`:
- The suffixes of `banana` are:
    - `banana` (index 0)
    - `anana` (index 1)
    - `nana` (index 2)
    - `ana` (index 3)
    - `a` (index 4)
    - (empty string) (index 5)

The suffixes sorted in lexicographical order are:
- `a` (index 4)
- `ana` (index 3)
- `anana` (index 1)
- `banana` (index 0)
- `nana` (index 2)
- (empty string) (index 5)

So the suffix array for `banana` would be:
```
[4, 3, 1, 0, 2, 5]
```

### Construction of Suffix Array
There are several algorithms to construct a suffix array. Here, we’ll discuss two popular methods:

1. **Naive Approach**:
    - Generate all suffixes.
    - Sort the suffixes lexicographically.
    - This approach has a time complexity of \(O(n^2 \log n)\), which is inefficient for large strings.

2. **Efficient Approach** (using O(n log n) or O(n)):
    - **Radix Sort** or **Prefix Doubling**: This method utilizes sorting techniques to build the suffix array efficiently.

### Implementation in Java
Below is a simple implementation of the suffix array construction using the naive approach and a more efficient approach using sorting:

#### Naive Approach Implementation

```java
import java.util.Arrays;

public class SuffixArray {

    // Function to build a suffix array
    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        String[] suffixes = new String[n];

        // Generate all suffixes
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i);
        }

        // Sort the suffixes
        Arrays.sort(suffixes);

        // Build the suffix array
        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = n - suffixes[i].length();
        }

        return suffixArray;
    }

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArray = buildSuffixArray(text);
        
        System.out.println("Suffix Array:");
        for (int index : suffixArray) {
            System.out.print(index + " ");
        }
    }
}
```

#### Efficient Approach (using a Sorting Algorithm)

A more efficient implementation would involve using a custom sorting technique. Here is a concise version using a sorting-based approach:

```java
import java.util.Arrays;

public class SuffixArray {

    // Function to build a suffix array using an efficient approach
    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        Integer[] suffixArray = new Integer[n];

        // Initialize suffix array with indices
        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
        }

        // Custom sort the suffixes based on the string's characters
        Arrays.sort(suffixArray, (a, b) -> s.substring(a).compareTo(s.substring(b)));

        return Arrays.stream(suffixArray).mapToInt(i -> i).toArray(); // Convert Integer[] to int[]
    }

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArray = buildSuffixArray(text);
        
        System.out.println("Suffix Array:");
        for (int index : suffixArray) {
            System.out.print(index + " ");
        }
    }
}
```

### Output
When you run either implementation with the input `banana`, you will get the output:
```
Suffix Array:
5 3 1 0 4 2 
```
The indices correspond to the sorted suffixes:
- `a` (index 5)
- `ana` (index 3)
- `anana` (index 1)
- `banana` (index 0)
- `nana` (index 4)
- `empty string` (index 2)

### Applications of Suffix Arrays
Suffix arrays have various applications, including:
- Pattern matching (using algorithms like the Knuth-Morris-Pratt).
- String searching algorithms.
- Data compression techniques.
- Finding the longest common substring between two strings.

Suffix arrays are particularly powerful when combined with other data structures like LCP (Longest Common Prefix) arrays to improve efficiency in string operations.