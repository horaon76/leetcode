Great question! The expression:

```java
num = num * 10 + (s.charAt(i) - '0');
```

is used to **convert characters into multi-digit numbers** while parsing a string.

---

### 💡 Explanation:

- `s.charAt(i)` gets the character at index `i` in the string.
- Subtracting `'0'` from a digit character like `'7'` gives the actual integer value `7`.

So:
```java
'7' - '0'  -->  55 - 48 = 7
```

---

### ✅ Why multiply by 10?

To **build numbers digit by digit**, especially multi-digit numbers like `23`, `109`, etc., you need to shift the existing digits left (like moving from 2 to 20), then add the new digit.

Here’s how it works step by step:

---

### 🔢 Example: parsing `"123"`

```java
String s = "123";
int num = 0;

for (int i = 0; i < s.length(); i++) {
    num = num * 10 + (s.charAt(i) - '0');
}
```

Let’s walk through it:

| i | s.charAt(i) | num before     | num * 10 | s.charAt(i) - '0' | num after      |
|---|--------------|----------------|----------|--------------------|----------------|
| 0 | '1'          | 0              | 0        | 1                  | 0 + 1 = 1      |
| 1 | '2'          | 1              | 10       | 2                  | 10 + 2 = 12    |
| 2 | '3'          | 12             | 120      | 3                  | 120 + 3 = 123  |

So `num = 123` in the end.

---

### 🧠 Why this matters:
When parsing a string like `"3 + 245 * (17 - 2)"`, this logic helps correctly extract and build `245` and `17` from characters `'2'`, `'4'`, `'5'`, etc.

---

Let me know if you want a visual trace or code demo for a specific string!