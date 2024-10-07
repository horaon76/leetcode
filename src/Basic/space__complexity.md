Calculating space complexity involves determining the amount of memory space required by an algorithm relative to the input size. Space complexity is influenced by several factors, including:

1. **Input Size**: The amount of memory required for inputs.
2. **Auxiliary Space**: The extra space required by the algorithm to perform computations (temporary variables, data structures, recursive stack space, etc.).

### How to Calculate Space Complexity

**General Steps**:
1. Identify the inputs and their size.
2. Analyze any additional data structures used.
3. Consider the space used by recursive calls, which grows with the depth of recursion.

### Examples of Space Complexity Calculation

1. **Example: Iterative Algorithm**
   ```java
   public int sum(int[] arr) {
       int sum = 0; // constant space for sum
       for (int i = 0; i < arr.length; i++) {
           sum += arr[i]; // no additional space
       }
       return sum;
   }
   ```
    - **Input Size**: \( n \) (length of the array)
    - **Auxiliary Space**: \( O(1) \) (only one integer `sum`)
    - **Total Space Complexity**: \( O(1) \)

2. **Example: Using Extra Data Structure**
   ```java
   public List<Integer> findDuplicates(int[] arr) {
       Set<Integer> duplicates = new HashSet<>(); // additional space for duplicates
       for (int i : arr) {
           duplicates.add(i);
       }
       return new ArrayList<>(duplicates);
   }
   ```
    - **Input Size**: \( n \) (length of the array)
    - **Auxiliary Space**: \( O(n) \) (the HashSet can store all elements)
    - **Total Space Complexity**: \( O(n) \)

3. **Example: Recursive Function**
   ```java
   public int factorial(int n) {
       if (n == 0) return 1;
       return n * factorial(n - 1);
   }
   ```
    - **Input Size**: \( n \)
    - **Auxiliary Space**: The maximum depth of the recursion stack is \( n \) (each recursive call adds a frame to the stack).
    - **Total Space Complexity**: \( O(n) \)

### Examples of Recursion

Recursion is a programming technique where a function calls itself to solve a problem. Here are a few classic examples:

1. **Factorial Calculation**
   ```java
   public int factorial(int n) {
       if (n == 0) return 1;
       return n * factorial(n - 1);
   }
   ```

2. **Fibonacci Sequence**
   ```java
   public int fibonacci(int n) {
       if (n <= 1) return n;
       return fibonacci(n - 1) + fibonacci(n - 2);
   }
   ```

3. **Sum of an Array**
   ```java
   public int sum(int[] arr, int n) {
       if (n <= 0) return 0;
       return sum(arr, n - 1) + arr[n - 1];
   }
   ```

4. **Reverse a String**
   ```java
   public String reverse(String str) {
       if (str.isEmpty()) return str;
       return reverse(str.substring(1)) + str.charAt(0);
   }
   ```

### Analyzing Space Complexity of Recursive Examples

1. **Factorial**:
    - **Space Complexity**: \( O(n) \) (due to the recursion stack)

2. **Fibonacci**:
    - **Space Complexity**: \( O(n) \) (due to the recursion stack)

3. **Sum of an Array**:
    - **Space Complexity**: \( O(n) \) (due to the recursion stack)

4. **Reverse a String**:
    - **Space Complexity**: \( O(n) \) (due to the recursion stack)

### Summary
- Space complexity quantifies the memory used by an algorithm.
- It considers input size, auxiliary space, and recursive stack space.
- Recursion often leads to increased space complexity due to the additional stack frames created for each recursive call.

If you have further questions or need more examples, feel free to ask!