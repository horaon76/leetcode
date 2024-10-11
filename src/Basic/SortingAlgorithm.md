Sorting algorithms are fundamental algorithms used to arrange the elements of a list or array in a specific order, typically in ascending or descending order. There are various sorting algorithms, each with its own approach, efficiency, and use cases. Here’s an overview of some of the most common sorting algorithms, categorized by their general method:

### 1. **Comparison-Based Sorting Algorithms**
These algorithms sort by comparing elements and are generally O(n²) for simple implementations, but some can achieve O(n log n) efficiency.

- **Bubble Sort**
    - **Description**: Repeatedly swaps adjacent elements if they are in the wrong order.
    - **Time Complexity**: O(n²)
    - **Space Complexity**: O(1)

- **Selection Sort**
    - **Description**: Divides the array into a sorted and an unsorted part, repeatedly selecting the smallest (or largest) element from the unsorted part.
    - **Time Complexity**: O(n²)
    - **Space Complexity**: O(1)

- **Insertion Sort**
    - **Description**: Builds the sorted array one element at a time by repeatedly taking the next element and inserting it into the correct position.
    - **Time Complexity**: O(n²) in the worst case, O(n) in the best case.
    - **Space Complexity**: O(1)

- **Merge Sort**
    - **Description**: A divide-and-conquer algorithm that divides the array into halves, recursively sorts them, and then merges the sorted halves.
    - **Time Complexity**: O(n log n)
    - **Space Complexity**: O(n) (due to temporary arrays for merging)

- **Quick Sort**
    - **Description**: A divide-and-conquer algorithm that selects a "pivot" element and partitions the array into elements less than and greater than the pivot, recursively sorting the partitions.
    - **Time Complexity**: O(n log n) on average, O(n²) in the worst case (rare).
    - **Space Complexity**: O(log n) for the recursive stack.

- **Heap Sort**
    - **Description**: Converts the array into a binary heap structure and repeatedly extracts the maximum (or minimum) element to produce a sorted array.
    - **Time Complexity**: O(n log n)
    - **Space Complexity**: O(1)

### 2. **Non-Comparison-Based Sorting Algorithms**
These algorithms can achieve linear time complexity for specific types of data, typically when the range of input values is known.

- **Counting Sort**
    - **Description**: Counts the occurrences of each unique element and then calculates the position of each element in the output array.
    - **Time Complexity**: O(n + k) (where k is the range of input values)
    - **Space Complexity**: O(k)

- **Radix Sort**
    - **Description**: Sorts numbers by processing individual digits (or bits) from the least significant to the most significant using a stable sorting algorithm (like counting sort).
    - **Time Complexity**: O(nk) (where k is the number of digits)
    - **Space Complexity**: O(n + k)

- **Bucket Sort**
    - **Description**: Divides the input array into a finite number of buckets, sorts each bucket individually (using another sorting algorithm), and then concatenates the sorted buckets.
    - **Time Complexity**: O(n + k) (average case)
    - **Space Complexity**: O(n)

### 3. **Hybrid Sorting Algorithms**
These algorithms combine multiple sorting techniques to optimize performance.

- **Timsort**
    - **Description**: A hybrid sorting algorithm derived from merge sort and insertion sort, used in Python’s built-in sort functions.
    - **Time Complexity**: O(n log n) in the worst case.
    - **Space Complexity**: O(n)

### 4. **Parallel and External Sorting Algorithms**
Used for large datasets or distributed systems.

- **External Sort**
    - **Description**: Used for handling datasets that cannot fit into memory, typically involving dividing the data into manageable chunks, sorting them individually, and merging them.

- **Parallel Sort**
    - **Description**: Utilizes multiple processors or threads to perform sorting operations simultaneously, improving performance for large datasets.

### Summary Table

| **Algorithm**     | **Best Case** | **Average Case** | **Worst Case** | **Space Complexity** |
|--------------------|---------------|------------------|----------------|-----------------------|
| Bubble Sort        | O(n)          | O(n²)            | O(n²)          | O(1)                  |
| Selection Sort     | O(n²)         | O(n²)            | O(n²)          | O(1)                  |
| Insertion Sort     | O(n)          | O(n²)            | O(n²)          | O(1)                  |
| Merge Sort         | O(n log n)    | O(n log n)       | O(n log n)     | O(n)                  |
| Quick Sort         | O(n log n)    | O(n log n)       | O(n²)          | O(log n)              |
| Heap Sort          | O(n log n)    | O(n log n)       | O(n log n)     | O(1)                  |
| Counting Sort      | O(n + k)      | O(n + k)         | O(n + k)       | O(k)                  |
| Radix Sort         | O(nk)         | O(nk)            | O(nk)          | O(n + k)              |
| Bucket Sort        | O(n + k)      | O(n)             | O(n²)          | O(n)                  |
| Timsort            | O(n log n)    | O(n log n)       | O(n log n)     | O(n)                  |

### Choosing the Right Sorting Algorithm
The choice of sorting algorithm depends on various factors:
- The size of the dataset.
- The range of input values.
- The need for stability (maintaining the order of equal elements).
- Memory constraints.

If you need more detailed explanations or examples for any specific sorting algorithm, feel free to ask!

Below are Java implementations for various sorting algorithms, including both comparison-based and non-comparison-based algorithms. Each implementation includes a simple example to demonstrate how to use the algorithm.

### 1. **Bubble Sort**
```java
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(arr);
        System.out.println("Sorted array (Bubble Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 2. **Selection Sort**
```java
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap arr[i] and arr[minIndex]
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        System.out.println("Sorted array (Selection Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 3. **Insertion Sort**
```java
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements that are greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        insertionSort(arr);
        System.out.println("Sorted array (Insertion Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 4. **Merge Sort**
```java
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Fill left and right arrays
        for (int i = 0; i < mid; i++) left[i] = arr[i];
        for (int i = mid; i < arr.length; i++) right[i - mid] = arr[i];

        // Recursively sort left and right halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy remaining elements
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(arr);
        System.out.println("Sorted array (Merge Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 5. **Quick Sort**
```java
public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i + 1] and arr[high]
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array (Quick Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 6. **Heap Sort**
```java
public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        heapSort(arr);
        System.out.println("Sorted array (Heap Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 7. **Counting Sort**
```java
public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = findMax(arr);
        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        // Count occurrences of each value
        for (int num : arr) {
            count[num]++;
        }

        // Change count[i] so that it contains the actual position of this number in output[]
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy the output array to arr[]
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr =

 {4, 2, 2, 8, 3, 3, 1};
        countingSort(arr);
        System.out.println("Sorted array (Counting Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 8. **Radix Sort**
```java
public class RadixSort {
    public static void radixSort(int[] arr) {
        int max = findMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences of each digit
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        // Change count[i] so that it contains the actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[]
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
        System.out.println("Sorted array (Radix Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### 9. **Bucket Sort**
```java
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Create n empty buckets
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Put array elements in different buckets
        for (float num : arr) {
            int bucketIndex = (int) (num * n); // Assuming the numbers are in the range [0, 1)
            buckets[bucketIndex].add(num);
        }

        // Sort individual buckets
        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenate all buckets into arr
        int index = 0;
        for (ArrayList<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.897f, 0.565f, 0.656f, 0.123f, 0.665f, 0.343f};
        bucketSort(arr);
        System.out.println("Sorted array (Bucket Sort):");
        for (float num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### Summary
These examples provide implementations of different sorting algorithms in Java. Each algorithm has its strengths and weaknesses, and the choice of which one to use depends on the specific requirements and constraints of the problem you are solving.

Certainly! Below are Java examples of various non-comparison-based sorting algorithms, hybrid sorting algorithms, parallel sorting algorithms, and external sorting algorithms.

### Non-Comparison-Based Sorting Algorithms

#### 1. **Counting Sort**
```java
public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = findMax(arr);
        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        // Count occurrences of each value
        for (int num : arr) {
            count[num]++;
        }

        // Change count[i] so that it contains the actual position of this number in output[]
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy the output array to arr[]
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        countingSort(arr);
        System.out.println("Sorted array (Counting Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

#### 2. **Radix Sort**
```java
public class RadixSort {
    public static void radixSort(int[] arr) {
        int max = findMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences of each digit
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        // Change count[i] so that it contains the actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[]
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
        System.out.println("Sorted array (Radix Sort):");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

#### 3. **Bucket Sort**
```java
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Create n empty buckets
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Put array elements in different buckets
        for (float num : arr) {
            int bucketIndex = (int) (num * n); // Assuming the numbers are in the range [0, 1)
            buckets[bucketIndex].add(num);
        }

        // Sort individual buckets
        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenate all buckets into arr
        int index = 0;
        for (ArrayList<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.897f, 0.565f, 0.656f, 0.123f, 0.665f, 0.343f};
        bucketSort(arr);
        System.out.println("Sorted array (Bucket Sort):");
        for (float num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### Hybrid Sorting Algorithms

#### 1. **Timsort**
Timsort is a hybrid sorting algorithm derived from merge sort and insertion sort. It is used in Python's built-in `sorted()` function and Java's `Arrays.sort()` for objects.

```java
import java.util.Arrays;

public class TimSort {
    // Minimum size of a run
    private static final int RUN = 32;

    // Function to perform insertion sort
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Function to merge sorted runs
    private static void merge(int[] arr, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    // Main timsort function
    public static void timSort(int[] arr) {
        int n = arr.length;

        // Sort individual subarrays of size RUN
        for (int start = 0; start < n; start += RUN) {
            int end = Math.min(start + RUN - 1, n - 1);
            insertionSort(arr, start, end);
        }

        // Start merging from size RUN (or 32). It will merge
        // to form size 64, then 128, 256, etc. 
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 21, 7, 23, 19};
        timSort(arr);
        System.out.println("Sorted array (Tim Sort):");
        System.out.println(Arrays.toString(arr));
    }
}
```

### Parallel Sorting Algorithms

#### 1. **Parallel Merge Sort**
You can implement parallel merge sort using Java's `ForkJoinPool`. Below is an example of parallel merge sort:

```java
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort extends RecursiveAction {
    private final int[] arr;
    private final int left;
    private final int right;

    public ParallelMergeSort(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (right - left < 2) {
            return;
        }

        int mid = (left + right) / 2;
        ParallelMergeSort leftTask = new ParallelMergeSort(arr, left, mid);
        ParallelMergeSort rightTask = new ParallelMergeSort(arr, mid, right);

        invokeAll(leftTask, rightTask);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int

 left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 21, 7, 23, 19, 50, 30};
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ParallelMergeSort(arr, 0, arr.length));
        System.out.println("Sorted array (Parallel Merge Sort): " + Arrays.toString(arr));
    }
}
```

### External Sorting Algorithms

#### 1. **External Merge Sort**
External sorting is used for sorting large amounts of data that cannot fit into memory. Below is a simplified example of external merge sort, assuming we have multiple sorted runs stored in files.

```java
import java.io.*;
import java.util.*;

public class ExternalMergeSort {
    private static final int MAX_RECORDS = 100; // Maximum records in memory

    public static void externalSort(String[] inputFiles, String outputFile) throws IOException {
        PriorityQueue<BufferedReader> minHeap = new PriorityQueue<>(Comparator.comparingInt(ExternalMergeSort::getNextNumber));

        // Initialize minHeap with the first number from each file
        for (String inputFile : inputFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            minHeap.offer(reader);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            while (!minHeap.isEmpty()) {
                BufferedReader reader = minHeap.poll();
                String line = reader.readLine();
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                    minHeap.offer(reader); // Offer back the reader for the next iteration
                }
            }
        }

        // Close readers
        for (BufferedReader reader : minHeap) {
            reader.close();
        }
    }

    private static int getNextNumber(BufferedReader reader) {
        try {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE; // Signal that this file is exhausted
        }
    }

    public static void main(String[] args) throws IOException {
        String[] inputFiles = {"file1.txt", "file2.txt", "file3.txt"}; // Sorted input files
        String outputFile = "sorted_output.txt";
        externalSort(inputFiles, outputFile);
        System.out.println("External sorting completed. Output written to " + outputFile);
    }
}
```

### Summary
These examples demonstrate various sorting algorithms, including non-comparison-based, hybrid, parallel, and external sorting methods. Each algorithm has specific use cases, strengths, and weaknesses, so the choice depends on the problem at hand.