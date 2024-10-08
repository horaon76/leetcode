Here’s a comprehensive comparison of **subsequences**, **subarrays**, and **subsets** along with examples and a tabular explanation.

### Definitions

- **Subsequence**: A sequence derived from another sequence by deleting some or none of the elements without changing the order of the remaining elements. Subsequence elements do not have to be contiguous.

- **Subarray**: A contiguous portion of an array. All elements of a subarray must be in a consecutive sequence from the original array.

- **Subset**: A selection of elements from a set, where the order does not matter, and elements can be repeated or not. A subset can be formed from any combination of the original set's elements, including the empty set and the full set.

### Example
Let’s consider the array \( A = [1, 2, 3] \).

- **Subsequences of A**:
    - `[1]`, `[2]`, `[3]`, `[1, 2]`, `[1, 3]`, `[2, 3]`, `[1, 2, 3]`, `[]` (the empty subsequence)

- **Subarrays of A**:
    - `[1]`, `[2]`, `[3]`, `[1, 2]`, `[2, 3]`, `[1, 2, 3]`

- **Subsets of A**:
    - `[]`, `[1]`, `[2]`, `[3]`, `[1, 2]`, `[1, 3]`, `[2, 3]`, `[1, 2, 3]` (same as subsets since there are no repeated elements)

### Tabular Explanation

| Feature                | Subsequence                           | Subarray                               | Subset                               |
|------------------------|---------------------------------------|---------------------------------------|-------------------------------------|
| **Definition**         | Derived by deleting elements without changing order | A contiguous portion of an array      | A selection of elements from a set  |
| **Order**              | Preserved                             | Preserved                             | Not preserved                        |
| **Contiguity**        | Not required                          | Required                               | Not required                         |
| **Examples**           | `[1, 2]`, `[1, 3]`, `[]`             | `[1, 2]`, `[2, 3]`                   | `[1]`, `[1, 2]`, `[]`               |
| **Total Count**        | \( 2^n \) (for array of length n)   | \( O(n^2) \)                         | \( 2^n \)                           |
| **Empty Element**      | Yes                                   | Yes                                   | Yes                                 |
| **Duplicates Allowed** | Yes                                   | No (must be the same elements)       | Yes (depends on the set)           |
| **Example with [1, 2, 3]** | `[1, 2, 3]`, `[1, 3]`          | `[1, 2]`, `[2, 3]`, `[1, 2, 3]`      | `[1]`, `[2, 3]`, `[1, 2, 3]`       |

### Summary

- **Subsequences** allow for flexibility in selecting elements while preserving order but are not contiguous.
- **Subarrays** focus strictly on contiguous elements in the array.
- **Subsets** can be selected from a set in any order, with or without considering contiguity.

This comparison highlights the distinct characteristics of each concept, helping clarify their definitions and applications in computer science and mathematics.