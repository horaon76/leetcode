### XOR
The XOR (exclusive OR) operation works on the binary representation of numbers. It compares corresponding bits of two numbers and outputs `1` if the bits are different, and `0` if the bits are the same.

### XOR Truth Table:
| A | B | A ^ B |
|---|---|-------|
| 0 | 0 |   0   |
| 0 | 1 |   1   |
| 1 | 0 |   1   |
| 1 | 1 |   0   |

So, the XOR operation compares each bit of two numbers and follows these rules:
1. If the bits are the same, the result is `0`.
2. If the bits are different, the result is `1`.

### Example: XOR Operation with Bit Representation

Let's walk through an example of how XOR works with actual bit representations. Consider two numbers: `4` and `5`.

- Binary representation of `4` is: `0100`
- Binary representation of `5` is: `0101`

Now, let’s XOR these two numbers bit by bit:

```
    0100   (4 in binary)
  ^ 0101   (5 in binary)
  -------
    0001   (Result is 1 in binary, which is 1 in decimal)
```

So, `4 ^ 5 = 1`.

### XOR in the Single Number Problem
Now, let's see how XOR helps us find the single number in an array like `[4, 1, 2, 1, 2]`.

1. Start with `result = 0` and XOR every number in the array:
    - `result = 0 ^ 4 = 4` (`0 XOR anything` gives the number itself).
    - `result = 4 ^ 1 = 5` (XOR bit by bit: `0100 ^ 0001 = 0101`, which is `5`).
    - `result = 5 ^ 2 = 7` (XOR bit by bit: `0101 ^ 0010 = 0111`, which is `7`).
    - `result = 7 ^ 1 = 6` (XOR bit by bit: `0111 ^ 0001 = 0110`, which is `6`).
    - `result = 6 ^ 2 = 4` (XOR bit by bit: `0110 ^ 0010 = 0100`, which is `4`).

2. At the end of this process, `result = 4`, which is the number that appears only once.

### Detailed XOR Steps:

Let's look at the detailed bitwise XOR for each step in the array `[4, 1, 2, 1, 2]`:

1. `result = 0 ^ 4`:
   ```
   0000 (0)
   ^
   0100 (4)
   -----
   0100 (4)   => result = 4
   ```

2. `result = 4 ^ 1`:
   ```
   0100 (4)
   ^
   0001 (1)
   -----
   0101 (5)   => result = 5
   ```

3. `result = 5 ^ 2`:
   ```
   0101 (5)
   ^
   0010 (2)
   -----
   0111 (7)   => result = 7
   ```

4. `result = 7 ^ 1`:
   ```
   0111 (7)
   ^
   0001 (1)
   -----
   0110 (6)   => result = 6
   ```

5. `result = 6 ^ 2`:
   ```
   0110 (6)
   ^
   0010 (2)
   -----
   0100 (4)   => result = 4
   ```

At the end, we get `result = 4`, which is the single number that appears only once in the array.

This process demonstrates how XOR works at the bit level to cancel out numbers that appear twice and leave behind the number that appears only once.

### AND Operation
The **AND** operation compares the corresponding bits of two numbers and outputs `1` only if **both** bits are `1`. Otherwise, it outputs `0`.

### AND Truth Table:
| A | B | A & B |
|---|---|-------|
| 0 | 0 |   0   |
| 0 | 1 |   0   |
| 1 | 0 |   0   |
| 1 | 1 |   1   |

So, the AND operation produces `1` only when both bits are `1`. In all other cases, the result is `0`.

### Example: AND Operation with Bit Representation

Let’s take two numbers, `4` and `5`, as an example:

- Binary representation of `4`: `0100`
- Binary representation of `5`: `0101`

Now, we will perform the AND operation on these two numbers bit by bit:

```
    0100   (4 in binary)
  & 0101   (5 in binary)
  -------
    0100   (Result is 4 in binary, which is 4 in decimal)
```

So, `4 & 5 = 4`.

### Steps of the AND Operation:
Here’s the detailed bitwise AND calculation between `4` and `5`:

1. Compare the first bits from the left:
    - Both are `0`, so the result is `0`.
2. Compare the second bits:
    - Both are `1`, so the result is `1`.
3. Compare the third bits:
    - The first bit is `0`, and the second bit is `1`, so the result is `0`.
4. Compare the fourth bits:
    - Both are `0`, so the result is `0`.

Therefore, the AND result of `4` and `5` is:
```
0100 (which is 4 in decimal)
```

### AND Operation in an Array (Not the Same as XOR's Use)
The AND operation doesn't work the same way as XOR for finding a single number in an array of numbers where every number appears twice except one. This is because the AND operation doesn't have the property of "cancelling out" repeated elements. Instead, it keeps returning `0` whenever any bit is `0` across the numbers.

However, here's an example using the AND operation on multiple numbers:

### AND Steps for the array `[4, 1, 2, 1, 2]`:

1. Start with `result = -1` (since the AND operation with any number and `-1` keeps the number unchanged).

2. Perform AND between all elements:
    - `result = -1 & 4 = 4` (because `-1` has all bits set to `1` in binary).
    - `result = 4 & 1 = 0` (since `0100 & 0001 = 0000`).
    - `result = 0 & 2 = 0` (because any number ANDed with `0` is `0`).
    - `result = 0 & 1 = 0`.
    - `result = 0 & 2 = 0`.

As you can see, the AND operation results in `0` because the AND of numbers doesn't help in distinguishing between repeated numbers and a single one in this scenario.

### Summary:
- The **AND operation** works by comparing bits of two numbers and returns `1` only when both bits are `1`, otherwise it returns `0`.
- While the **XOR operation** is useful for solving problems like "finding the single number" in an array, the **AND operation** isn't suitable for that purpose because it tends to produce `0` when bits mismatch, and it doesn't have the cancellation properties like XOR.

For AND operation, when both numbers have matching `1` bits, they persist in the result. Otherwise, it clears them to `0`.

# Shift Operations
The **left shift (`<<`)** and **right shift (`>>`)** operators are bitwise shift operations that move the bits of a number left or right by a specified number of positions.

### Left Shift (`<<`)
The **left shift** operator moves the bits of a number to the left by the specified number of positions. Each left shift operation adds a `0` to the rightmost side, and the leftmost bits are discarded.

- **Effectively, left shifting by `n` positions multiplies the number by `2^n`.**

### Example: Left Shift
Let’s take the number `5` as an example, whose binary representation is `0101`.

#### Shift by 1 position to the left (`5 << 1`):
```
  0101 (5 in binary)
<< 1
  -----
  1010 (Result is 10 in binary, which is 10 in decimal)
```

The bits are shifted one position to the left, and a `0` is appended on the right. Therefore, `5 << 1 = 10`.

#### Explanation:
- **Binary representation of 5**: `0101`
- After shifting left by 1, you get `1010`, which is **10** in decimal.

#### Another Example: Shift by 2 positions (`5 << 2`):
```
  0101 (5 in binary)
<< 2
  -----
  10100 (Result is 20 in binary, which is 20 in decimal)
```

The bits are shifted two positions to the left, and two `0`s are appended on the right. Therefore, `5 << 2 = 20`.

### Summary of Left Shift:
- `5 << 1` shifts the bits of `5` by 1 place, multiplying it by `2^1 = 2`, resulting in `10`.
- `5 << 2` shifts the bits of `5` by 2 places, multiplying it by `2^2 = 4`, resulting in `20`.

### Right Shift (`>>`)
The **right shift** operator moves the bits of a number to the right by the specified number of positions. Each right shift operation discards the rightmost bits, and new bits are filled in from the left based on the type of shift:
- **Logical Right Shift (`>>>`)**: Fills with `0`s from the left (used with unsigned numbers).
- **Arithmetic Right Shift (`>>`)**: Fills with the sign bit (the leftmost bit) for signed numbers, keeping the sign intact.

- **Effectively, right shifting by `n` positions divides the number by `2^n` and truncates towards zero** (integer division).

### Example: Right Shift
Let’s take the number `5` again, whose binary representation is `0101`.

#### Shift by 1 position to the right (`5 >> 1`):
```
  0101 (5 in binary)
>> 1
  -----
  0010 (Result is 2 in binary, which is 2 in decimal)
```

The bits are shifted one position to the right, and the leftmost bit is filled with `0` (in the case of a logical shift), or the sign bit in the case of an arithmetic shift. Therefore, `5 >> 1 = 2`.

#### Explanation:
- **Binary representation of 5**: `0101`
- After shifting right by 1, you get `0010`, which is **2** in decimal.

#### Another Example: Shift by 2 positions (`5 >> 2`):
```
  0101 (5 in binary)
>> 2
  -----
  0001 (Result is 1 in binary, which is 1 in decimal)
```

The bits are shifted two positions to the right, discarding the last two bits. Therefore, `5 >> 2 = 1`.

### Summary of Right Shift:
- `5 >> 1` shifts the bits of `5` by 1 place to the right, effectively dividing it by `2^1 = 2`, resulting in `2`.
- `5 >> 2` shifts the bits of `5` by 2 places to the right, effectively dividing it by `2^2 = 4`, resulting in `1`.

### Shift Operations in Action with Bit Representation:

#### Left Shift Example (`5 << 1`):
```
  Original:    0101  (5 in binary)
  Shift left:  1010  (Left shift by 1 gives 10)
```

#### Right Shift Example (`5 >> 1`):
```
  Original:    0101  (5 in binary)
  Shift right: 0010  (Right shift by 1 gives 2)
```

### General Formulas:
1. **Left shift (`<<`)**:
    - Shifting left by `n` positions multiplies the number by `2^n`.
2. **Right shift (`>>`)**:
    - Shifting right by `n` positions divides the number by `2^n` (integer division).

### Key Differences Between Left and Right Shifts:
- **Left Shift**: Fills in `0`s on the right, effectively multiplying the number by powers of 2.
- **Right Shift**: Removes bits on the right and fills in from the left with either `0`s or the sign bit (for signed numbers), effectively dividing the number by powers of 2.

# Examples
Here are more examples of left and right shift operations with their inputs and outputs:

### Left Shift (`<<`) Examples:
1. **`3 << 1`**:
    - Input: `3` (binary `0011`)
    - Output: `6` (binary `0110`)

2. **`6 << 2`**:
    - Input: `6` (binary `0110`)
    - Output: `24` (binary `11000`)

3. **`7 << 3`**:
    - Input: `7` (binary `0111`)
    - Output: `56` (binary `111000`)

4. **`10 << 2`**:
    - Input: `10` (binary `1010`)
    - Output: `40` (binary `101000`)

5. **`15 << 1`**:
    - Input: `15` (binary `1111`)
    - Output: `30` (binary `11110`)

---

### Right Shift (`>>`) Examples:
1. **`8 >> 1`**:
    - Input: `8` (binary `1000`)
    - Output: `4` (binary `0100`)

2. **`10 >> 2`**:
    - Input: `10` (binary `1010`)
    - Output: `2` (binary `0010`)

3. **`20 >> 3`**:
    - Input: `20` (binary `10100`)
    - Output: `2` (binary `00010`)

4. **`15 >> 1`**:
    - Input: `15` (binary `1111`)
    - Output: `7` (binary `0111`)

5. **`9 >> 2`**:
    - Input: `9` (binary `1001`)
    - Output: `2` (binary `0010`)