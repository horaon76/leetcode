Sure! Here are some examples of using the Java `PriorityQueue` class, which is part of the Java Collections Framework. A `PriorityQueue` is a data structure that holds elements in a priority order. By default, it orders elements according to their natural ordering, or by a specified comparator provided at queue construction time.

### Basic Example of PriorityQueue

#### 1. **Using Natural Ordering**
In this example, we will create a `PriorityQueue` of integers and demonstrate how it automatically orders them.

```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create a PriorityQueue of integers
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements to the PriorityQueue
        pq.add(10);
        pq.add(20);
        pq.add(15);
        pq.add(30);

        // Display the elements of the PriorityQueue
        System.out.println("PriorityQueue elements (natural ordering): " + pq);

        // Removing elements from the PriorityQueue
        while (!pq.isEmpty()) {
            System.out.println("Removed element: " + pq.poll());
        }
    }
}
```

**Output:**
```
PriorityQueue elements (natural ordering): [10, 20, 15, 30]
Removed element: 10
Removed element: 15
Removed element: 20
Removed element: 30
```

### Example with Custom Comparator

#### 2. **Using Custom Ordering**
In this example, we will create a `PriorityQueue` of strings ordered by their lengths.

```java
import java.util.PriorityQueue;
import java.util.Comparator;

public class CustomPriorityQueueExample {
    public static void main(String[] args) {
        // Create a PriorityQueue with a custom comparator (by string length)
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));

        // Adding elements to the PriorityQueue
        pq.add("apple");
        pq.add("banana");
        pq.add("kiwi");
        pq.add("grape");
        pq.add("strawberry");

        // Display the elements of the PriorityQueue
        System.out.println("PriorityQueue elements (ordered by length): " + pq);

        // Removing elements from the PriorityQueue
        while (!pq.isEmpty()) {
            System.out.println("Removed element: " + pq.poll());
        }
    }
}
```

**Output:**
```
PriorityQueue elements (ordered by length): [kiwi, apple, grape, banana, strawberry]
Removed element: kiwi
Removed element: apple
Removed element: grape
Removed element: banana
Removed element: strawberry
```

### PriorityQueue with Objects

#### 3. **Using PriorityQueue with Custom Objects**
In this example, we will create a `PriorityQueue` of a custom class `Person` ordered by age.

```java
import java.util.PriorityQueue;
import java.util.Comparator;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class PriorityQueueWithObjectsExample {
    public static void main(String[] args) {
        // Create a PriorityQueue with a custom comparator (by age)
        PriorityQueue<Person> pq = new PriorityQueue<>(Comparator.comparingInt(person -> person.age));

        // Adding elements to the PriorityQueue
        pq.add(new Person("Alice", 30));
        pq.add(new Person("Bob", 25));
        pq.add(new Person("Charlie", 35));
        pq.add(new Person("Diana", 20));

        // Display the elements of the PriorityQueue
        System.out.println("PriorityQueue elements (ordered by age): " + pq);

        // Removing elements from the PriorityQueue
        while (!pq.isEmpty()) {
            System.out.println("Removed element: " + pq.poll());
        }
    }
}
```

**Output:**
```
PriorityQueue elements (ordered by age): [Diana (20), Bob (25), Alice (30), Charlie (35)]
Removed element: Diana (20)
Removed element: Bob (25)
Removed element: Alice (30)
Removed element: Charlie (35)
```

### Example of Capacity and Growth

#### 4. **Initial Capacity and Growth**
In this example, we will create a `PriorityQueue` with an initial capacity and demonstrate its growth.

```java
import java.util.PriorityQueue;

public class PriorityQueueCapacityExample {
    public static void main(String[] args) {
        // Create a PriorityQueue with an initial capacity of 5
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);

        // Adding elements to the PriorityQueue
        for (int i = 1; i <= 10; i++) {
            pq.add(i);
        }

        // Display the elements of the PriorityQueue
        System.out.println("PriorityQueue elements (after adding 10 elements): " + pq);

        // Removing elements from the PriorityQueue
        while (!pq.isEmpty()) {
            System.out.println("Removed element: " + pq.poll());
        }
    }
}
```

**Output:**
```
PriorityQueue elements (after adding 10 elements): [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Removed element: 1
Removed element: 2
Removed element: 3
Removed element: 4
Removed element: 5
Removed element: 6
Removed element: 7
Removed element: 8
Removed element: 9
Removed element: 10
```

### Summary
These examples illustrate how to use the `PriorityQueue` class in Java for different types of elements, including primitives, custom objects, and custom comparators. The `PriorityQueue` class is a versatile data structure for managing collections of elements with priorities.

If you have any specific scenarios in mind or further questions about `PriorityQueue`, feel free to ask!