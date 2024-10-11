To keep two numbers using only one of the numbers, you can use a mathematical trick often employed in certain data structures and algorithms (DSA) questions, especially on LeetCode. This is particularly useful when you want to store or manipulate two values but only have space or the ability to track one.

Here are a few common techniques:

### 1. **Using Bitwise XOR (Exclusive OR)**
- You can use bitwise XOR to encode two numbers and later decode them.
- This works because XOR of a number with itself is 0, and XOR is both commutative and associative.

**Encoding Two Numbers**:
   ```python
   # Suppose you have two numbers a and b
   encoded = a ^ b  # XOR a and b, storing result in 'encoded'
   ```

**Decoding (if you have one of the original numbers)**:
- If you know one of the numbers, you can retrieve the other.
   ```python
   # To retrieve 'b' from 'encoded' and 'a':
   b = encoded ^ a
   # Similarly, to retrieve 'a':
   a = encoded ^ b
   ```

### 2. **Using a Mathematical Formula**
- You can store two numbers using their **sum** and **difference**. This works well when numbers are integers and don’t overflow.

**Storing the Sum and Difference**:
   ```python
   sum_val = a + b
   diff_val = a - b
   ```

**Retrieving the Original Numbers**:
- From the sum and difference:
   ```python
   a = (sum_val + diff_val) // 2
   b = (sum_val - diff_val) // 2
   ```

### 3. **Using Multiplication (without Overflow)**
- This method is less common due to potential overflow issues, but if the values are small, you can store them as a product and sum.
- For two integers `a` and `b`, you can store their product and sum.

**Encoding**:
   ```python
   product = a * b
   sum_val = a + b
   ```

**Decoding**:
- Using the quadratic formula to solve the equation.
   ```python
   # You can solve this using the equations:
   # x + y = sum_val
   # x * y = product
   # This is solved by quadratic equation, and you can compute `a` and `b` using these.
   ```

These tricks help in solving space-efficient problems on platforms like LeetCode, where you need to maintain two numbers but are restricted to using only one variable.

Yes, there's another clever method using **multiplication modulo** and **division**, which is particularly useful when you're dealing with positive integers. Here's how it works:

### 4. **Using Multiplication and Modulo**
This method works by storing the two numbers `a` and `b` in a single integer using multiplication and division, along with modular arithmetic. It relies on the fact that you can encode the numbers into a larger number by multiplying them and then retrieve them using modulo and division operations.

#### Steps:

**Encoding Two Numbers `a` and `b`:**

You can encode two numbers `a` and `b` into one number by assuming that `a` is smaller than a known maximum value `M`. This allows us to store the numbers as:

```python
encoded = a * M + b
```

Here, `M` is a sufficiently large constant that is guaranteed to be greater than `b` (the second number), like the maximum value `b` can take.

**Decoding the Numbers:**

- To retrieve `a`, you simply divide the encoded number by `M`.

  ```python
  a = encoded // M
  ```

- To retrieve `b`, you take the encoded number modulo `M`.

  ```python
  b = encoded % M
  ```

#### Example:

Let’s say you have two numbers, `a = 12` and `b = 45`, and you decide that `M = 100` (assuming `b` will always be less than `100`).

1. **Encoding:**
   ```python
   encoded = a * 100 + b  # 12 * 100 + 45 = 1200 + 45 = 1245
   ```

2. **Decoding:**
    - To get `a`:
      ```python
      a = 1245 // 100  # a = 12
      ```
    - To get `b`:
      ```python
      b = 1245 % 100   # b = 45
      ```

#### Use Case:
This method is used in problems where the two numbers are bounded, meaning you know that one of the numbers (say `b`) will never exceed a certain value `M`. This constraint allows you to use multiplication and modulo effectively.

#### Pros and Cons:
- **Pros:**
    - Efficient for encoding and decoding two numbers using a single value.
    - Can be particularly helpful when both numbers are relatively small and have a defined range.

- **Cons:**
    - You need to ensure that `M` is large enough so that there’s no overlap between the two numbers. If `b` can be very large, this method might not work well due to potential overflow issues.

This method is often used in competitive programming and DSA problems where space constraints are important and you need to handle multiple values in a compact manner.

# Example

Here’s a table summarizing the different methods for encoding two numbers and retrieving them, with examples for each method:

| **Method**                           | **Encoding Formula**                 | **Decoding Formula for `a`**        | **Decoding Formula for `b`**       | **Example with `a = 12`, `b = 45`** |
|--------------------------------------|--------------------------------------|-------------------------------------|------------------------------------|------------------------------------|
| **1. Bitwise XOR**                   | `encoded = a ^ b`                    | `a = encoded ^ b`                   | `b = encoded ^ a`                  | - **Encoding:** `encoded = 12 ^ 45 = 33` <br> - **Decoding:** <br> `a = 33 ^ 45 = 12`, `b = 33 ^ 12 = 45` |
| **2. Sum and Difference**            | `sum_val = a + b` <br> `diff_val = a - b` | `a = (sum_val + diff_val) // 2`     | `b = (sum_val - diff_val) // 2`    | - **Encoding:** <br> `sum_val = 12 + 45 = 57` <br> `diff_val = 12 - 45 = -33` <br> - **Decoding:** <br> `a = (57 + (-33)) // 2 = 12`, <br> `b = (57 - (-33)) // 2 = 45` |
| **3. Product and Sum (Quadratic)**    | `product = a * b` <br> `sum_val = a + b`  | Use quadratic formula to solve     | Use quadratic formula to solve     | - **Encoding:** <br> `product = 12 * 45 = 540`, `sum_val = 12 + 45 = 57` <br> - **Decoding:** Solve `x^2 - sum_val * x + product = 0` |
| **4. Multiplication and Modulo**      | `encoded = a * M + b` (where `M > b`) | `a = encoded // M`                  | `b = encoded % M`                  | - **Encoding:** <br> (Assume `M = 100`) <br> `encoded = 12 * 100 + 45 = 1245` <br> - **Decoding:** <br> `a = 1245 // 100 = 12`, `b = 1245 % 100 = 45` |

### Explanation of Methods:
1. **Bitwise XOR:**
    - Uses bitwise XOR (`^`) to encode the numbers. The same XOR operation can be used to retrieve one number if the other is known.
    - Simple and efficient but works best with small integers.

2. **Sum and Difference:**
    - Encodes two numbers by their sum and difference, and then retrieves them using basic arithmetic.
    - This method works well with integers where addition and subtraction won't cause overflow.

3. **Product and Sum (Quadratic Formula):**
    - Encodes numbers using their product and sum, then decodes them using the quadratic formula. This method works best for smaller numbers.
    - It involves solving a quadratic equation to decode, which can be complex but useful in specific problems.

4. **Multiplication and Modulo:**
    - Encodes numbers by multiplying one number by a large constant `M` and adding the second number. You can then retrieve the numbers using division and modulo operations.
    - Works well if you know an upper bound for one of the numbers.

These methods are useful in different contexts, depending on the problem constraints. For example, **bitwise XOR** is great for small numbers, while **multiplication and modulo** is often used when numbers have a known upper bound.