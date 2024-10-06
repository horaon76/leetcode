### **Permutations and Combinations with Example**

Permutations and combinations are two concepts from combinatorics that help determine how to count selections from a set. Both are used to figure out how many ways items can be arranged or selected, but they differ in whether or not the **order** of the items matters.

---

### **Permutations**: When **order matters**.

- A **permutation** is an arrangement of items where the order of selection is important.
- For example, if you're selecting positions in a race (1st, 2nd, 3rd), the order matters because getting 1st is different from getting 3rd.

#### **Formula for Permutations**:
The number of ways to arrange `r` items from a set of `n` is given by:
\[
P(n, r) = \frac{n!}{(n-r)!}
\]
Where `n!` is the factorial of `n` (i.e., \(n! = n \times (n-1) \times \dots \times 1\)).

#### **Example of Permutations**:
Let’s say you have **5 students**: A, B, C, D, and E, and you want to select **3 students** to assign different tasks (task 1, task 2, and task 3).

- Since the **order matters** (because each student gets a specific task), this is a permutation problem.

The number of permutations is:
\[
P(5, 3) = \frac{5!}{(5-3)!} = \frac{5 \times 4 \times 3 \times 2 \times 1}{2 \times 1} = 60
\]

So, there are **60 different ways** to assign tasks to 3 students from a group of 5, with order being important.

---

### **Combinations**: When **order does not matter**.

- A **combination** is a selection of items where the order **doesn't** matter.
- For example, if you're selecting 3 students to be part of a committee from a group of 5, the order doesn’t matter—being selected is the same regardless of order.

#### **Formula for Combinations**:
The number of ways to choose `r` items from a set of `n` when order doesn't matter is given by:
\[
C(n, r) = \frac{n!}{r! \times (n-r)!}
\]
Where `r!` accounts for the fact that the order of the chosen `r` items doesn't matter.

#### **Example of Combinations**:
Let’s take the same 5 students: A, B, C, D, and E, and you want to select **3 students** to form a committee.

- Since the **order doesn’t matter** (being part of the committee is the same no matter the selection order), this is a combination problem.

The number of combinations is:
\[
C(5, 3) = \frac{5!}{3! \times (5-3)!} = \frac{5 \times 4 \times 3 \times 2 \times 1}{(3 \times 2 \times 1) \times (2 \times 1)} = 10
\]

So, there are **10 different ways** to choose 3 students from a group of 5 for the committee, where order doesn't matter.

---

### **Probability**:
**Probability** measures the likelihood of an event happening. It’s calculated as the ratio of **favorable outcomes** to the **total possible outcomes**.

#### **Formula for Probability**:
\[
P(A) = \frac{\text{Number of favorable outcomes}}{\text{Total number of possible outcomes}}
\]
Where:
- `P(A)` is the probability of event `A` occurring.
- "Favorable outcomes" are the specific outcomes we are interested in.
- "Total outcomes" are all possible outcomes in the sample space.

---

### **Example of Probability with Permutations and Combinations**

#### Problem:
Consider a box containing **5 red balls**, **4 blue balls**, and **3 green balls**. Suppose you randomly draw **2 balls** from the box **without replacement**. What is the probability that both balls are red?

#### **Step 1: Total Possible Outcomes**
- The total number of balls = \( 5 + 4 + 3 = 12 \).
- We are selecting 2 balls from a set of 12, and since the **order of selection doesn’t matter**, we use **combinations** to find the total number of possible outcomes:
  \[
  C(12, 2) = \frac{12!}{2! \times (12-2)!} = \frac{12 \times 11}{2 \times 1} = 66
  \]
  So, there are **66 total possible ways** to select 2 balls.

#### **Step 2: Favorable Outcomes (Both Red)**
- We are interested in the case where both balls drawn are red. Since there are 5 red balls, the number of ways to select 2 red balls from the 5 is:
  \[
  C(5, 2) = \frac{5!}{2! \times (5-2)!} = \frac{5 \times 4}{2 \times 1} = 10
  \]
  So, there are **10 favorable ways** to select 2 red balls.

#### **Step 3: Probability**
- The probability of drawing 2 red balls is the ratio of favorable outcomes to total possible outcomes:
  \[
  P(\text{2 red balls}) = \frac{C(5, 2)}{C(12, 2)} = \frac{10}{66} = \frac{5}{33}
  \]
  So, the probability is \( \frac{5}{33} \), or about 0.1515 (15.15%).

---

### **More Complex Probability Example (Using Permutations)**

#### Problem:
Suppose you have **10 horses** running a race. What is the probability that **horse A** finishes first and **horse B** finishes second?

#### **Step 1: Total Possible Outcomes**
- For the total number of outcomes, we need to consider how all 10 horses can finish in any order. Since the **order matters**, this is a permutation problem:
  \[
  P(10, 2) = \frac{10!}{(10-2)!} = 10 \times 9 = 90
  \]
  There are 90 different ways the top 2 horses can finish the race.

#### **Step 2: Favorable Outcomes (A finishes 1st, B finishes 2nd)**
- There is **only 1 specific outcome** where horse A finishes 1st and horse B finishes 2nd.

#### **Step 3: Probability**
- The probability of horse A finishing 1st and horse B finishing 2nd is:
  \[
  P(\text{A 1st, B 2nd}) = \frac{1}{90}
  \]
  So, the probability is \( \frac{1}{90} \), or about 0.0111 (1.11%).

---

### **Summary**:
- **Permutations** count how many ways we can arrange items when **order matters**.
- **Combinations** count how many ways we can choose items when **order doesn’t matter**.
- **Probability** is the ratio of favorable outcomes to total possible outcomes.

The concepts of permutations and combinations are essential for calculating probabilities in cases where multiple items or choices are involved, especially in real-world scenarios such as drawing cards, choosing teams, and selecting orders of events.