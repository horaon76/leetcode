### **Permutations and Combinations**: Formulas and Examples

#### **1. Permutations**:
A **permutation** is an arrangement of objects in a specific order. The order matters in permutations.

##### **Formula for Permutations**:
- **Without Repetition** (for selecting `r` objects from `n` total objects):
  \[
  P(n, r) = \frac{n!}{(n - r)!}
  \]
  Where:
    - `n!` is the factorial of `n`.
    - `r` is the number of objects selected.

- **With Repetition** (where repetition of elements is allowed):
  \[
  P(n, r) = n^r
  \]

##### **Examples of Permutations**:

1. **Example 1**: Find the number of ways to arrange 3 letters out of the 5 letters A, B, C, D, E.
   \[
   P(5, 3) = \frac{5!}{(5 - 3)!} = \frac{5!}{2!} = \frac{120}{2} = 60
   \]
   So, there are 60 ways to arrange 3 letters from 5.

2. **Example 2**: How many ways can 4 people be seated in a row from a group of 7 people?
   \[
   P(7, 4) = \frac{7!}{(7 - 4)!} = \frac{7!}{3!} = \frac{5040}{6} = 840
   \]
   There are 840 ways to seat 4 people from a group of 7.

3. **Example 3**: Find the number of ways to arrange 4 digits out of 6 digits (1, 2, 3, 4, 5, 6) where repetition is **allowed**.
   \[
   P(6, 4) = 6^4 = 1296
   \]
   There are 1296 possible arrangements when repetition is allowed.

4. **Example 4**: How many different ways can 5 out of 8 books be arranged on a shelf?
   \[
   P(8, 5) = \frac{8!}{(8 - 5)!} = \frac{8!}{3!} = \frac{40320}{6} = 6720
   \]
   There are 6720 ways to arrange 5 books out of 8.

5. **Example 5**: How many ways can the first, second, and third place be awarded to 10 participants in a race?
   \[
   P(10, 3) = \frac{10!}{(10 - 3)!} = \frac{10!}{7!} = \frac{3628800}{5040} = 720
   \]
   There are 720 ways to assign 1st, 2nd, and 3rd place to 10 participants.

---

#### **2. Combinations**:
A **combination** is a selection of objects where the order does **not** matter.

##### **Formula for Combinations**:
- **Without Repetition** (for selecting `r` objects from `n` total objects):
  \[
  C(n, r) = \frac{n!}{r!(n - r)!}
  \]

- **With Repetition** (where repetition of elements is allowed):
  \[
  C(n + r - 1, r) = \frac{(n + r - 1)!}{r!(n - 1)!}
  \]

##### **Examples of Combinations**:

1. **Example 1**: Find the number of ways to choose 3 students from a group of 6.
   \[
   C(6, 3) = \frac{6!}{3!(6 - 3)!} = \frac{6!}{3!3!} = \frac{720}{6 \times 6} = 20
   \]
   There are 20 ways to choose 3 students from 6.

2. **Example 2**: How many ways can a committee of 4 people be selected from 10 people?
   \[
   C(10, 4) = \frac{10!}{4!(10 - 4)!} = \frac{10!}{4!6!} = \frac{3628800}{24 \times 720} = 210
   \]
   There are 210 ways to select a committee of 4 from 10 people.

3. **Example 3**: Find the number of ways to choose 2 cards from a deck of 52 cards.
   \[
   C(52, 2) = \frac{52!}{2!(52 - 2)!} = \frac{52!}{2!50!} = \frac{52 \times 51}{2 \times 1} = 1326
   \]
   There are 1326 ways to choose 2 cards from a deck of 52.

4. **Example 4**: How many ways can 5 balls be chosen from 8 different colored balls (without repetition)?
   \[
   C(8, 5) = \frac{8!}{5!(8 - 5)!} = \frac{8!}{5!3!} = \frac{40320}{120 \times 6} = 56
   \]
   There are 56 ways to choose 5 balls from 8.

5. **Example 5**: Find the number of ways to choose 3 toppings from 6 available toppings for a pizza.
   \[
   C(6, 3) = \frac{6!}{3! (6 - 3)!} = \frac{6!}{3!3!} = \frac{720}{6 \times 6} = 20
   \]
   There are 20 ways to choose 3 toppings from 6 available.

##### **Examples of Combinations with Repetition**:

1. **Example 1**: How many ways can you choose 3 objects from 5 types with repetition allowed?
   \[
   C(5 + 3 - 1, 3) = C(7, 3) = \frac{7!}{3!4!} = \frac{5040}{6 \times 24} = 35
   \]
   There are 35 ways to choose 3 objects from 5 types with repetition.

2. **Example 2**: How many ways can you distribute 10 identical candies to 4 children?
   \[
   C(4 + 10 - 1, 10) = C(13, 10) = C(13, 3) = \frac{13!}{3!10!} = \frac{286}{1} = 286
   \]
   There are 286 ways to distribute 10 candies to 4 children.

3. **Example 3**: Find the number of ways to select 4 books from 6 types with repetition allowed.
   \[
   C(6 + 4 - 1, 4) = C(9, 4) = \frac{9!}{4!5!} = \frac{362880}{24 \times 120} = 126
   \]
   There are 126 ways to choose 4 books from 6 types with repetition.

4. **Example 4**: How many ways can 5 identical balls be placed in 3 different boxes?
   \[
   C(3 + 5 - 1, 5) = C(7, 5) = C(7, 2) = \frac{7!}{2!5!} = \frac{5040}{2 \times 120} = 21
   \]
   There are 21 ways to place 5 identical balls in 3 boxes.

5. **Example 5**: How many ways can you choose 4 fruits from 7 types with repetition allowed?
   \[
   C(7 + 4 - 1, 4) = C(10, 4) = \frac{10!}{4!6!} = \frac{3628800}{24 \times 720} = 210
   \]
   There are 210 ways to choose 4 fruits from 7 types with repetition.

---

### Summary of Key Formulas

- **Permutations (Without Repetition)**:  
  \[
  P(n, r) = \frac{n!}{(n - r)!}
  \]

- **Permutations (With Repetition)**:  
  \[
  P(n, r) = n^r
  \]

- **Combinations (Without Repetition)**:  
  \[
  C(n, r) = \frac{n!}{r!(n - r)!}
  \]

- **Combinations (With Repetition)**:  
  \[
  C(n + r - 1, r) = \frac{(n + r - 1)!}{r!(n - 1)!}
  \]

These formulas and examples provide a comprehensive understanding of permutations and combinations for various selection and arrangement scenarios.
Permutations
Definition: A permutation is an arrangement of elements from a set in a specific order. The order of arrangement is crucial; thus, different orders of the same set of elements are considered distinct permutations.

Formula: For a set of nnn elements, the number of ways to arrange rrr elements is given by:

P(n,r)=n!(n−r)!P(n, r) = \frac{n!}{(n - r)!}P(n,r)=(n−r)!n!​

where n!n!n! (n factorial) is the product of all positive integers up to nnn.

Example:
Consider the set {A,B,C}{A, B, C}{A,B,C}.
The permutations of taking 2 elements at a time are: AB,AC,BA,BC,CA,CBAB, AC, BA, BC, CA, CBAB,AC,BA,BC,CA,CB.
There are P(3,2)=3!(3−2)!=61=6P(3, 2) = \frac{3!}{(3 - 2)!} = \frac{6}{1} = 6P(3,2)=(3−2)!3!​=16​=6 permutations.
Combinations
Definition: A combination is a selection of elements from a set without regard to the order. The order does not matter, so different arrangements of the same set of elements are considered the same combination.

Formula: For a set of nnn elements, the number of ways to choose rrr elements is given by:

C(n,r)=n!r!(n−r)!C(n, r) = \frac{n!}{r!(n - r)!}C(n,r)=r!(n−r)!n!​

where r!r!r! is the factorial of rrr and (n−r)!(n - r)!(n−r)! accounts for the elements not chosen.

Example:
Consider the same set {A,B,C}{A, B, C}{A,B,C}.
The combinations of taking 2 elements at a time are: AB,AC,BCAB, AC, BCAB,AC,BC.
There are C(3,2)=3!2!(3−2)!=62×1=3C(3, 2) = \frac{3!}{2! (3 - 2)!} = \frac{6}{2 \times 1} = 3C(3,2)=2!(3−2)!3!​=2×16​=3 combinations.
Key Differences
Order:

Permutations: Order matters. ABABAB is different from BABABA.
Combinations: Order does not matter. ABABAB is the same as BABABA.
Count:

The number of permutations is usually greater than the number of combinations when r>0r > 0r>0.
Formulas:

Permutations use the formula P(n,r)P(n, r)P(n,r) while combinations use C(n,r)C(n, r)C(n,r).
Visualizing with Examples
Example 1 (Permutations):

Set: {1,2,3}{1, 2, 3}{1,2,3}
Arranging 2 elements:
Permutations: (1,2),(1,3),(2,1),(2,3),(3,1),(3,2)(1, 2), (1, 3), (2, 1), (2, 3), (3, 1), (3, 2)(1,2),(1,3),(2,1),(2,3),(3,1),(3,2) → Total: 6 permutations.
Example 2 (Combinations):

Set: {1,2,3}{1, 2, 3}{1,2,3}
Choosing 2 elements:
Combinations: {1,2},{1,3},{2,3}{1, 2}, {1, 3}, {2, 3}{1,2},{1,3},{2,3} → Total: 3 combinations.