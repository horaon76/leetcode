To effectively solve matrix-related problems on LeetCode, understanding certain mathematical fundamentals is essential. Below is a summary of these key concepts, complete with illustrations and examples:

### 1. **Matrix Definition and Notation**
- A **matrix** is a rectangular array of numbers, arranged in rows and columns.
- Each element is referenced by its row and column index: \(a_{ij}\) refers to the element in the \(i^{th}\) row and \(j^{th}\) column.

**Illustration**:

\[
A = \begin{bmatrix}
1 & 2 & 3 \\
4 & 5 & 6 \\
7 & 8 & 9
\end{bmatrix}
\]

In this \(3 \times 3\) matrix:
- \(a_{11} = 1\), \(a_{23} = 6\), \(a_{32} = 8\)

### 2. **Matrix Dimensions**
- The size of a matrix is expressed as \(m \times n\), where \(m\) is the number of rows and \(n\) is the number of columns.
- Example: A \(2 \times 3\) matrix has 2 rows and 3 columns.

**Illustration**:

\[
B = \begin{bmatrix}
1 & 2 & 3 \\
4 & 5 & 6
\end{bmatrix}
\]
This matrix \(B\) has dimensions \(2 \times 3\).

### 3. **Matrix Addition and Subtraction**
- Matrices can be added or subtracted if they have the same dimensions. The operation is performed element-wise.

**Illustration**:
Let \(C\) and \(D\) be two \(2 \times 2\) matrices:

\[
C = \begin{bmatrix}
1 & 2 \\
3 & 4
\end{bmatrix}, \quad
D = \begin{bmatrix}
5 & 6 \\
7 & 8
\end{bmatrix}
\]

**Addition**:
\[
C + D = \begin{bmatrix}
1+5 & 2+6 \\
3+7 & 4+8
\end{bmatrix} = \begin{bmatrix}
6 & 8 \\
10 & 12
\end{bmatrix}
\]

### 4. **Matrix Multiplication**
- The product of two matrices \(A\) (of size \(m \times n\)) and \(B\) (of size \(n \times p\)) is a matrix \(C\) (of size \(m \times p\)).
- The element \(c_{ij}\) is calculated as the dot product of the \(i^{th}\) row of \(A\) and the \(j^{th}\) column of \(B\).

**Illustration**:

Let \(A\) and \(B\) be matrices:
\[
A = \begin{bmatrix}
1 & 2 \\
3 & 4
\end{bmatrix}, \quad
B = \begin{bmatrix}
5 & 6 \\
7 & 8
\end{bmatrix}
\]

**Multiplication**:
\[
C = A \cdot B = \begin{bmatrix}
1 \cdot 5 + 2 \cdot 7 & 1 \cdot 6 + 2 \cdot 8 \\
3 \cdot 5 + 4 \cdot 7 & 3 \cdot 6 + 4 \cdot 8
\end{bmatrix} = \begin{bmatrix}
19 & 22 \\
43 & 50
\end{bmatrix}
\]

### 5. **Transpose of a Matrix**
- The **transpose** of a matrix \(A\) is obtained by flipping it over its diagonal.

**Illustration**:
For matrix \(A\):
\[
A = \begin{bmatrix}
1 & 2 \\
3 & 4
\end{bmatrix}
\]

**Transpose**:
\[
A^T = \begin{bmatrix}
1 & 3 \\
2 & 4
\end{bmatrix}
\]

### 6. **Identity Matrix**
- The **identity matrix** is a square matrix where all the elements of the principal diagonal are ones, and all other elements are zeros.
- It acts as the multiplicative identity for matrices.

**Illustration**:
The \(2 \times 2\) identity matrix \(I\):
\[
I = \begin{bmatrix}
1 & 0 \\
0 & 1
\end{bmatrix}
\]

### 7. **Determinant**
- The **determinant** is a scalar value that provides important information about a square matrix, such as whether it is invertible.

**Illustration**:
For a \(2 \times 2\) matrix:
\[
A = \begin{bmatrix}
a & b \\
c & d
\end{bmatrix} \quad \Rightarrow \quad \text{det}(A) = ad - bc
\]

### 8. **Inverse of a Matrix**
- The **inverse** of a matrix \(A\) (denoted \(A^{-1}\)) exists only for square matrices and is defined such that \(A \cdot A^{-1} = I\).

**Illustration**:
For a \(2 \times 2\) matrix \(A\):
\[
A = \begin{bmatrix}
a & b \\
c & d
\end{bmatrix}
\]
The inverse is given by:
\[
A^{-1} = \frac{1}{ad - bc} \begin{bmatrix}
d & -b \\
-c & a
\end{bmatrix}
\]
provided that \(ad - bc \neq 0\).

### 9. **Special Types of Matrices**
- **Diagonal Matrix**: A matrix where all off-diagonal elements are zero.

**Illustration**:
\[
D = \begin{bmatrix}
1 & 0 & 0 \\
0 & 2 & 0 \\
0 & 0 & 3
\end{bmatrix}
\]

- **Symmetric Matrix**: A matrix that is equal to its transpose (\(A = A^T\)).

**Illustration**:
\[
S = \begin{bmatrix}
1 & 2 \\
2 & 3
\end{bmatrix}
\]

### Summary
Understanding these mathematical fundamentals about matrices will greatly enhance your ability to solve matrix-related problems on platforms like LeetCode. The ability to manipulate matrices, perform operations, and apply properties is critical for tackling many coding challenges, especially those involving algorithms, dynamic programming, and graph theory.

## More

### 1. **Matrix Representation and Accessing Elements**
- Understand how to represent a matrix using 2D arrays (e.g., `int[][] matrix` in Java).
- Be able to access and manipulate elements using their indices: `matrix[i][j]`.

### Example Problem
- **Problem**: Given a matrix, find the maximum element.
- **Solution**:
   ```java
   public int findMax(int[][] matrix) {
       int max = Integer.MIN_VALUE;
       for (int i = 0; i < matrix.length; i++) {
           for (int j = 0; j < matrix[i].length; j++) {
               max = Math.max(max, matrix[i][j]);
           }
       }
       return max;
   }
   ```

### 2. **Matrix Traversal Techniques**
- **Row-wise Traversal**: Iterate through each row and then each column.
- **Column-wise Traversal**: Iterate through each column and then each row.
- **Spiral Order Traversal**: Traverse the matrix in a spiral order.

### Example Problem
- **Problem**: Print the matrix in spiral order.
- **Solution**:
   ```java
   public List<Integer> spiralOrder(int[][] matrix) {
       List<Integer> result = new ArrayList<>();
       if (matrix.length == 0) return result;
       
       int top = 0, bottom = matrix.length - 1;
       int left = 0, right = matrix[0].length - 1;

       while (top <= bottom && left <= right) {
           // Traverse from left to right
           for (int i = left; i <= right; i++) result.add(matrix[top][i]);
           top++;
           
           // Traverse from top to bottom
           for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
           right--;
           
           if (top <= bottom) {
               // Traverse from right to left
               for (int i = right; i >= left; i--) result.add(matrix[bottom][i]);
               bottom--;
           }
           if (left <= right) {
               // Traverse from bottom to top
               for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
               left++;
           }
       }
       return result;
   }
   ```

### 3. **Matrix Rotation**
- Understand how to rotate a matrix (e.g., 90 degrees clockwise) using in-place algorithms.

### Example Problem
- **Problem**: Rotate an \(n \times n\) matrix by 90 degrees.
- **Solution**: (Provided in a previous response.)

### 4. **Matrix Transpose**
- Be familiar with how to transpose a matrix by swapping elements along the diagonal.

### Example Problem
- **Problem**: Transpose a given matrix.
- **Solution**:
   ```java
   public void transpose(int[][] matrix) {
       int n = matrix.length;
       for (int i = 0; i < n; i++) {
           for (int j = i; j < n; j++) {
               int temp = matrix[i][j];
               matrix[i][j] = matrix[j][i];
               matrix[j][i] = temp;
           }
       }
   }
   ```

### 5. **Searching and Finding Elements**
- Know how to perform binary search on rows or columns if the matrix is sorted.
- Understand how to check for the presence of a target value in a matrix.

### Example Problem
- **Problem**: Search a 2D matrix for a target value.
- **Solution**:
   ```java
   public boolean searchMatrix(int[][] matrix, int target) {
       if (matrix.length == 0) return false;
       int m = matrix.length, n = matrix[0].length;
       int left = 0, right = m * n - 1;
       
       while (left <= right) {
           int mid = left + (right - left) / 2;
           int midValue = matrix[mid / n][mid % n];
           if (midValue == target) return true;
           else if (midValue < target) left = mid + 1;
           else right = mid - 1;
       }
       return false;
   }
   ```

### 6. **Dynamic Programming on Matrices**
- Many problems involve dynamic programming (DP) concepts where you might need to fill a DP table based on some conditions derived from the matrix.

### Example Problem
- **Problem**: Given a matrix of integers, find the maximum sum of a path from the top to the bottom.
- **Solution**:
   ```java
   public int maxPathSum(int[][] grid) {
       int m = grid.length;
       int n = grid[0].length;
       
       // DP array initialization
       for (int i = 1; i < m; i++) {
           grid[i][0] += grid[i - 1][0]; // First column
       }
       for (int j = 1; j < n; j++) {
           grid[0][j] += grid[0][j - 1]; // First row
       }
       
       // Fill the DP array
       for (int i = 1; i < m; i++) {
           for (int j = 1; j < n; j++) {
               grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
           }
       }
       return grid[m - 1][n - 1]; // Bottom-right corner
   }
   ```

### 7. **Matrix Multiplication**
- Be familiar with how to perform matrix multiplication, including understanding the conditions for valid multiplication.

### Example Problem
- **Problem**: Multiply two matrices.
- **Solution**:
   ```java
   public int[][] multiply(int[][] A, int[][] B) {
       int m = A.length, n = A[0].length, p = B[0].length;
       int[][] result = new int[m][p];

       for (int i = 0; i < m; i++) {
           for (int j = 0; j < p; j++) {
               for (int k = 0; k < n; k++) {
                   result[i][j] += A[i][k] * B[k][j];
               }
           }
       }
       return result;
   }
   ```

