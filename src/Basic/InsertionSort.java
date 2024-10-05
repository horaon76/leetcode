package Basic;

/**
 * **Insertion Sort** is a simple and intuitive sorting algorithm that builds the final sorted array one element at a time. It is much like the way you would sort playing cards in your hand: you take each card and insert it into its correct position relative to the already sorted cards.
 *
 * ### Key Idea:
 * - **Insertion Sort** maintains a "sorted" portion of the array.
 * - It picks one element at a time from the unsorted portion and places it into the correct position in the sorted portion.
 * - This process continues until the entire array is sorted.
 *
 * ### Algorithm Steps:
 * 1. Start from the second element (index 1) because the first element is considered sorted.
 * 2. Compare the current element with the elements in the sorted portion (i.e., elements to its left).
 * 3. Shift the larger elements one position to the right to make space for the current element.
 * 4. Insert the current element into its correct position.
 * 5. Repeat this for all elements in the array.
 *
 * ### Insertion Sort Example:
 *
 * Let’s take the array `[5, 2, 9, 1, 5, 6]` and sort it using insertion sort.
 *
 * - Initial array: `[5, 2, 9, 1, 5, 6]`
 * - First pass: Compare `2` with `5`. Shift `5` to the right and insert `2`. The array becomes `[2, 5, 9, 1, 5, 6]`.
 * - Second pass: Compare `9` with `5`. No shift needed as `9` is larger. The array remains `[2, 5, 9, 1, 5, 6]`.
 * - Third pass: Compare `1` with `9`, `5`, and `2`. Shift `9`, `5`, and `2` to the right and insert `1`. The array becomes `[1, 2, 5, 9, 5, 6]`.
 * - Fourth pass: Compare `5` with `9`. Shift `9` to the right and insert `5`. The array becomes `[1, 2, 5, 5, 9, 6]`.
 * - Fifth pass: Compare `6` with `9`. Shift `9` to the right and insert `6`. The array becomes `[1, 2, 5, 5, 6, 9]`.
 *
 * Final sorted array: `[1, 2, 5, 5, 6, 9]`
 *
 * ### Insertion Sort Code (Java):
 *

 *
 * ### Explanation of the Code:
 * 1. **Outer Loop** (`i = 1` to `n-1`): This loop iterates over each element starting from the second one.
 * 2. **Inner Loop** (`while j >= 0`): For each element, it checks how many of the previous elements are greater than it and shifts them to the right.
 * 3. The **key** is inserted into its correct position in the sorted portion of the array.
 *
 * ### Time Complexity:
 * - **Best Case**: `O(n)` — This happens when the array is already sorted. The inner loop does not perform any shifts.
 * - **Worst Case**: `O(n^2)` — This happens when the array is sorted in reverse order. Each element needs to be compared with every other element in the sorted portion.
 * - **Average Case**: `O(n^2)` — On average, the algorithm requires about half the comparisons and shifts as the worst case.
 *
 * ### Space Complexity:
 * - **O(1)** — Insertion sort is an **in-place** sorting algorithm, meaning it only requires a constant amount of extra memory.
 *
 * ### Advantages:
 * - Simple and intuitive to implement.
 * - Efficient for small datasets or nearly sorted arrays (adaptive).
 * - **Stable** sorting algorithm: It preserves the relative order of equal elements.
 *
 * ### Disadvantages:
 * - Not efficient for large datasets due to its `O(n^2)` time complexity.
 * **/
class InsertionSort {
    // Function to sort array using insertion sort
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];  // The element to be inserted
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift the larger element to the right
                j = j - 1;
            }
            arr[j + 1] = key;  // Insert the key at its correct position
        }
    }

    // Utility function to print array
    public void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        InsertionSort sorter = new InsertionSort();
        System.out.println("Original array:");
        sorter.printArray(arr);

        sorter.sort(arr);

        System.out.println("Sorted array:");
        sorter.printArray(arr);
    }
}