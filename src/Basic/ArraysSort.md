The `Arrays.sort` method in Java provides several variations to sort arrays of different types (primitive types and objects) and allows customization through comparators. Here is a comprehensive list of variations with examples:

---

### **1. Sorting Primitive Arrays**
The `Arrays.sort` method can be used to sort arrays of primitive types (e.g., `int`, `long`, `char`, `float`, `double`).

#### **Syntax**
```java
Arrays.sort(array);
```

#### **Example**
```java
int[] nums = {5, 3, 8, 1, 2};
Arrays.sort(nums); // Sorts in ascending order
System.out.println(Arrays.toString(nums)); // Output: [1, 2, 3, 5, 8]
```

---

### **2. Sorting a Subarray of Primitive Arrays**
You can sort only a portion of the array by specifying the start (inclusive) and end (exclusive) indices.

#### **Syntax**
```java
Arrays.sort(array, fromIndex, toIndex);
```

#### **Example**
```java
int[] nums = {5, 3, 8, 1, 2};
Arrays.sort(nums, 1, 4); // Sorts elements from index 1 to 3
System.out.println(Arrays.toString(nums)); // Output: [5, 1, 3, 8, 2]
```

---

### **3. Sorting Object Arrays (Default Order)**
The `Arrays.sort` method can sort an array of objects that implement the `Comparable` interface.

#### **Syntax**
```java
Arrays.sort(objectArray);
```

#### **Example**
```java
String[] words = {"banana", "apple", "cherry"};
Arrays.sort(words); // Sorts in ascending alphabetical order
System.out.println(Arrays.toString(words)); // Output: [apple, banana, cherry]
```

---

### **4. Sorting Object Arrays with a Custom Comparator**
You can define a custom comparator to specify the sorting order.

#### **Syntax**
```java
Arrays.sort(objectArray, Comparator);
```

#### **Example**
```java
String[] words = {"banana", "apple", "cherry"};
Arrays.sort(words, (a, b) -> b.compareTo(a)); // Sorts in descending order
System.out.println(Arrays.toString(words)); // Output: [cherry, banana, apple]
```

---

### **5. Sorting a Subarray of Object Arrays**
Similar to primitive arrays, you can sort a subarray of objects.

#### **Syntax**
```java
Arrays.sort(objectArray, fromIndex, toIndex);
```

#### **Example**
```java
String[] words = {"banana", "apple", "cherry", "date"};
Arrays.sort(words, 1, 3); // Sorts only from index 1 to 2
System.out.println(Arrays.toString(words)); // Output: [banana, apple, cherry, date]
```

---

### **6. Sorting Object Arrays with Custom Comparator (Subarray)**
You can combine custom comparators and subarray sorting.

#### **Syntax**
```java
Arrays.sort(objectArray, fromIndex, toIndex, Comparator);
```

#### **Example**
```java
String[] words = {"banana", "apple", "cherry", "date"};
Arrays.sort(words, 1, 4, (a, b) -> b.compareTo(a)); // Sorts index 1 to 3 in descending order
System.out.println(Arrays.toString(words)); // Output: [banana, date, cherry, apple]
```

---

### **7. Sorting Multi-Dimensional Arrays**
For 2D arrays, you can sort rows based on a specific column using a custom comparator.

#### **Syntax**
```java
Arrays.sort(array, Comparator);
```

#### **Example**
```java
int[][] matrix = {
    {3, 5},
    {1, 2},
    {4, 1}
};
// Sort by the first column
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));
for (int[] row : matrix) {
    System.out.println(Arrays.toString(row)); // Output: [1, 2], [3, 5], [4, 1]
}
```

---

### **8. Sorting Custom Objects**
For custom classes, you can implement the `Comparable` interface or use a custom comparator.

#### **Using `Comparable`**
```java
class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}

Person[] people = {
    new Person("Alice", 30),
    new Person("Bob", 25),
    new Person("Charlie", 35)
};
Arrays.sort(people); // Sort by age
for (Person p : people) {
    System.out.println(p.name + " - " + p.age);
}
// Output:
// Bob - 25
// Alice - 30
// Charlie - 35
```

#### **Using Comparator**
```java
Arrays.sort(people, (a, b) -> a.name.compareTo(b.name)); // Sort by name
```

---

### **9. Parallel Sorting for Primitives**
The `Arrays.parallelSort` method divides the array into subarrays, sorts them concurrently, and then merges the results.

#### **Syntax**
```java
Arrays.parallelSort(array);
```

#### **Example**
```java
int[] nums = {5, 3, 8, 1, 2};
Arrays.parallelSort(nums);
System.out.println(Arrays.toString(nums)); // Output: [1, 2, 3, 5, 8]
```

---

### **10. Parallel Sorting Object Arrays**
`Arrays.parallelSort` is also available for object arrays.

#### **Syntax**
```java
Arrays.parallelSort(objectArray, Comparator);
```

#### **Example**
```java
String[] words = {"banana", "apple", "cherry"};
Arrays.parallelSort(words, (a, b) -> b.compareTo(a)); // Sorts in descending order
System.out.println(Arrays.toString(words)); // Output: [cherry, banana, apple]
```

---

### Summary of Variations

| **Method**                        | **Description**                                                |
|------------------------------------|---------------------------------------------------------------|
| `Arrays.sort(array)`              | Sorts entire primitive array in ascending order               |
| `Arrays.sort(array, from, to)`    | Sorts a subarray of primitives                                |
| `Arrays.sort(objectArray)`        | Sorts entire object array using natural order                |
| `Arrays.sort(objectArray, comp)`  | Sorts object array with custom comparator                    |
| `Arrays.sort(array, from, to, comp)` | Sorts a subarray of objects with custom comparator          |
| `Arrays.parallelSort(array)`      | Parallel sort for primitives                                 |
| `Arrays.parallelSort(objArray, comp)` | Parallel sort for objects with custom comparator          |

--- 

These variations provide flexibility for sorting, whether working with primitives, objects, subarrays, or requiring custom sorting logic.