Here are the formulas for the most common types of progression series—**Arithmetic Progression (AP)**, **Geometric Progression (GP)**, and **Harmonic Progression (HP)**—along with examples for each.

---

### 1. **Arithmetic Progression (AP)**

#### Definition:
- An **Arithmetic Progression** is a sequence of numbers in which the difference between consecutive terms is constant. This difference is called the **common difference** (`d`).

#### General Form:
\[
a, a + d, a + 2d, a + 3d, \dots
\]
Where `a` is the first term and `d` is the common difference.

#### Formulas:

1. **n-th Term of AP (General Term)**:
   \[
   a_n = a + (n - 1) d
   \]
   Where:
    - `a` = first term
    - `d` = common difference
    - `n` = position of the term

2. **Sum of First n Terms of AP**:
   \[
   S_n = \frac{n}{2} [2a + (n - 1)d]
   \]
   Or:
   \[
   S_n = \frac{n}{2} (a + l)
   \]
   Where:
    - `l` is the last term of the AP.

#### Example:
Consider the AP: \( 2, 5, 8, 11, 14, \dots \)
- First term \(a = 2\)
- Common difference \(d = 3\)

1. To find the **7th term**:
   \[
   a_7 = 2 + (7 - 1) \cdot 3 = 2 + 18 = 20
   \]

2. To find the **sum of the first 6 terms**:
   \[
   S_6 = \frac{6}{2} [2 \cdot 2 + (6 - 1) \cdot 3] = 3 \cdot [4 + 15] = 3 \cdot 19 = 57
   \]

---

### 2. **Geometric Progression (GP)**

#### Definition:
- A **Geometric Progression** is a sequence of numbers in which the ratio of consecutive terms is constant. This ratio is called the **common ratio** (`r`).

#### General Form:
\[
a, a r, a r^2, a r^3, \dots
\]
Where `a` is the first term and `r` is the common ratio.

#### Formulas:

1. **n-th Term of GP (General Term)**:
   \[
   a_n = a \cdot r^{n - 1}
   \]
   Where:
    - `a` = first term
    - `r` = common ratio
    - `n` = position of the term

2. **Sum of First n Terms of GP** (for \(r \neq 1\)):
   \[
   S_n = a \frac{1 - r^n}{1 - r}
   \]
   If \(r > 1\), the formula can be written as:
   \[
   S_n = a \frac{r^n - 1}{r - 1}
   \]

3. **Sum of Infinite GP** (if \(|r| < 1\)):
   \[
   S_\infty = \frac{a}{1 - r}
   \]

#### Example:
Consider the GP: \( 3, 6, 12, 24, \dots \)
- First term \(a = 3\)
- Common ratio \(r = 2\)

1. To find the **5th term**:
   \[
   a_5 = 3 \cdot 2^{5 - 1} = 3 \cdot 16 = 48
   \]

2. To find the **sum of the first 4 terms**:
   \[
   S_4 = 3 \cdot \frac{1 - 2^4}{1 - 2} = 3 \cdot \frac{1 - 16}{-1} = 3 \cdot 15 = 45
   \]

3. **Sum of infinite terms** (if \(|r| < 1\)):
- Example: If \(a = 5\), \(r = \frac{1}{2}\):
  \[
  S_\infty = \frac{5}{1 - \frac{1}{2}} = \frac{5}{\frac{1}{2}} = 10
  \]

---

### 3. **Harmonic Progression (HP)**

#### Definition:
- A **Harmonic Progression** is a sequence of numbers derived from the reciprocals of an arithmetic progression (AP).

#### General Form:
\[
\frac{1}{a}, \frac{1}{a + d}, \frac{1}{a + 2d}, \frac{1}{a + 3d}, \dots
\]
Where \(a\) is the first term and \(d\) is the common difference of the corresponding AP.

#### Formula:

1. **n-th Term of HP**:
   The n-th term of HP is the reciprocal of the n-th term of the corresponding AP:
   \[
   T_n = \frac{1}{a_n} = \frac{1}{a + (n - 1)d}
   \]

#### Example:
Consider the HP: \( \frac{1}{2}, \frac{1}{5}, \frac{1}{8}, \frac{1}{11}, \dots \)
- The corresponding AP is: \(2, 5, 8, 11, \dots\)
- First term \(a = 2\), common difference \(d = 3\)

1. To find the **4th term** of HP:
- First, find the 4th term of the corresponding AP: \( a_4 = 2 + (4 - 1) \cdot 3 = 11 \)
- Now, the 4th term of HP is:
  \[
  T_4 = \frac{1}{11}
  \]

---

### 4. **Arithmetic-Geometric Progression (AGP)**

#### Definition:
- An **Arithmetic-Geometric Progression** is a sequence where each term is the product of the corresponding terms of an arithmetic progression (AP) and a geometric progression (GP).

#### General Form:
\[
T_n = (a + (n - 1)d) \cdot r^{n - 1}
\]

#### Formula:

1. **Sum of First n Terms of AGP**:
   For an arithmetic-geometric progression, the sum formula can be more complex and usually involves recurrence relations.

---

These are the key formulas for the major types of progression series: **Arithmetic Progression (AP)**, **Geometric Progression (GP)**, **Harmonic Progression (HP)**, and **Arithmetic-Geometric Progression (AGP)**.