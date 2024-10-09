Merge Sort is a popular sorting algorithm that uses the divide-and-conquer technique to sort an array. It divides the array into two halves, recursively sorts each half, and then merges the sorted halves back together.

Here’s a step-by-step implementation of the Merge Sort algorithm in Java:

### Merge Sort Algorithm

1. **Divide**: Split the array into two halves until each sub-array contains one element.
2. **Conquer**: Recursively sort each half.
3. **Combine**: Merge the sorted halves back into a single sorted array.

### Java Implementation

Here’s the code for the Merge Sort algorithm in Java:

```java
public class MergeSort {

    // Main function that sorts the array using merge sort
    public void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Base case: if the array has less than 2 elements, it's already sorted
        }

        // Create a temporary array
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right;

        // Split the array into two halves
        if (array.length % 2 == 0) {
            right = new int[mid];
        } else {
            right = new int[mid + 1];
        }

        // Fill the left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Recursively sort the left and right halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    // Helper function to merge two sorted arrays
    private void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the left and right arrays back into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left array
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy any remaining elements from the right array
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Main method to test the merge sort algorithm
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        
        System.out.println("Original array:");
        printArray(array);
        
        mergeSort.mergeSort(array);
        
        System.out.println("Sorted array:");
        printArray(array);
    }

    // Utility method to print the array
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```

### Explanation of the Code

1. **`mergeSort` Method**:
    - This is the main function that sorts the array.
    - It checks if the length of the array is less than 2 (base case) and returns if it's true.
    - It splits the array into two halves (left and right).
    - It recursively calls `mergeSort` on both halves.

2. **`merge` Method**:
    - This helper method merges two sorted sub-arrays back into the original array.
    - It uses three pointers: `i` for the left array, `j` for the right array, and `k` for the main array.
    - It compares the elements from both arrays and places the smaller element into the main array.

3. **`main` Method**:
    - It creates an instance of `MergeSort`, defines an unsorted array, and calls `mergeSort` to sort it.
    - It prints the original and sorted arrays using the `printArray` utility method.

### Output
When you run the program, it will display:
```
Original array:
38 27 43 3 9 82 10 
Sorted array:
3 9 10 27 38 43 82 
```

This implementation of Merge Sort is efficient with a time complexity of \(O(n \log n)\) and is stable, making it suitable for large datasets.