package Practice;

import java.util.Arrays;

public class RotateArrayInPlace {
    public static void main(String[] args) {
        // Example with Odd Length Array
        int[] oddArray = {1, 2, 3, 4, 5};
        System.out.println("Original Odd Array: " + Arrays.toString(oddArray));
        reverseArray(oddArray);
        System.out.println("Reversed Odd Array: " + Arrays.toString(oddArray));

        // Example with Even Length Array
        int[] evenArray = {1, 2, 3, 4, 5, 6};
        System.out.println("Original Even Array: " + Arrays.toString(evenArray));
        reverseArray(evenArray);
        System.out.println("Reversed Even Array: " + Arrays.toString(evenArray));
    }

    public static void reverseArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            // Swap elements at i and (n - i - 1)
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }
}
