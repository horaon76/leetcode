package Basic;

public class bitBasic {

    // Method for AND operation between two integers
    public static int andOperation(int a, int b) {
        return a & b;
    }

    // Method for OR operation between two integers
    public static int orOperation(int a, int b) {
        return a | b;
    }

    // Method for XOR operation between two integers
    public static int xorOperation(int a, int b) {
        return a ^ b;
    }

    // Method for left shift (a << n)
    public static int leftShift(int a, int n) {
        return a << n;
    }

    // Method for right shift (a >> n)
    public static int rightShift(int a, int n) {
        return a >> n;
    }

    // Method to count the number of 1 bits in the binary representation of an integer
    public static int countSetBits(int a) {
        return Integer.bitCount(a); // Uses built-in method to count set bits
    }

    // Method to check if a bit is set at a specific position
    public static boolean isBitSet(int a, int pos) {
        return (a & (1 << pos)) != 0;
    }

    // Method to set a bit at a specific position
    public static int setBit(int a, int pos) {
        return a | (1 << pos);
    }

    // Method to clear a bit at a specific position
    public static int clearBit(int a, int pos) {
        return a & ~(1 << pos);
    }

    // Method to toggle a bit at a specific position
    public static int toggleBit(int a, int pos) {
        return a ^ (1 << pos);
    }

    // Method to get the binary representation of a number as a string
    public static String toBinaryString(int a) {
        return Integer.toBinaryString(a);
    }

    // Method to convert an integer to a binary string
    public static String intToBinary(int num) {
        return Integer.toBinaryString(num);
    }

    // Method to print the bit representation of an integer (32-bit)
    public static String intToBitRepresentation(int num) {
        StringBuilder bitRepresentation = new StringBuilder();

        // Iterate through all 32 bits (Java's int is 32 bits)
        for (int i = 31; i >= 0; i--) {
            // Use bitwise AND and shift to check if the ith bit is set
            bitRepresentation.append((num >> i) & 1);
        }

        return bitRepresentation.toString();
    }

    // Example usage in main method
    public static void main(String[] args) {
        int a = 5;  // Binary: 0101
        int b = 3;  // Binary: 0011

        // AND, OR, XOR
        System.out.println("AND Operation: " + andOperation(a, b));  // Output: 1
        System.out.println("OR Operation: " + orOperation(a, b));    // Output: 7
        System.out.println("XOR Operation: " + xorOperation(a, b));  // Output: 6

        // Left and Right Shift
        System.out.println("Left Shift: " + leftShift(a, 2));        // Output: 20 (0101 << 2 -> 10100)
        System.out.println("Right Shift: " + rightShift(a, 1));      // Output: 2 (0101 >> 1 -> 0010)

        // Bit Count
        System.out.println("Set Bit Count: " + countSetBits(a));     // Output: 2

        // Bit Manipulation
        System.out.println("Is Bit 1 Set: " + isBitSet(a, 1));       // Output: true
        System.out.println("Set Bit 2: " + setBit(a, 2));            // Output: 7 (Binary: 0111)
        System.out.println("Clear Bit 0: " + clearBit(a, 0));        // Output: 4 (Binary: 0100)
        System.out.println("Toggle Bit 1: " + toggleBit(a, 1));      // Output: 7 (Binary: 0111)

        // Binary String Representation
        System.out.println("Binary Representation of a: " + toBinaryString(a)); // Output: 101

        int num = 23;

        // Convert to binary string
        String binaryString = intToBinary(num);
        System.out.println("Binary String of " + num + ": " + binaryString);

        // Get full 32-bit representation
        String bitRepresentation = intToBitRepresentation(num);
        System.out.println("32-bit Representation of " + num + ": " + bitRepresentation);

    }
}
/**
 * AND Operation: 1
 * OR Operation: 7
 * XOR Operation: 6
 * Left Shift: 20
 * Right Shift: 2
 * Set Bit Count: 2
 * Is Bit 1 Set: true
 * Set Bit 2: 7
 * Clear Bit 0: 4
 * Toggle Bit 1: 7
 * Binary Representation of a: 101
 *
 *
 * Binary String of 23: 10111
 * 32-bit Representation of 23: 00000000000000000000000000010111
 **/