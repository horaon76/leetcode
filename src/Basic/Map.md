In Java, the `Map` interface and its implementation `HashMap` provide various operations for managing key-value pairs. Below are common operations you can perform with `Map` and `HashMap`, along with example code snippets for each operation.

### Basic Operations

1. **Create a HashMap**
   ```java
   Map<String, Integer> map = new HashMap<>();
   ```

2. **Put (Insert/Update)**
    - Adds a key-value pair to the map.
   ```java
   map.put("A", 1);    // Add key "A" with value 1
   map.put("B", 2);    // Add key "B" with value 2
   map.put("A", 3);    // Update key "A" to value 3
   ```

3. **Get (Retrieve)**
    - Retrieves the value associated with a key.
   ```java
   Integer value = map.get("A"); // Returns 3
   ```

4. **Remove**
    - Removes the key-value pair for the specified key.
   ```java
   map.remove("B");    // Removes key "B"
   ```

5. **Contains Key**
    - Checks if a key exists in the map.
   ```java
   boolean hasKey = map.containsKey("A"); // Returns true
   ```

6. **Contains Value**
    - Checks if a value exists in the map.
   ```java
   boolean hasValue = map.containsValue(2); // Returns false
   ```

7. **Size**
    - Returns the number of key-value pairs in the map.
   ```java
   int size = map.size(); // Returns 1
   ```

8. **Is Empty**
    - Checks if the map is empty.
   ```java
   boolean isEmpty = map.isEmpty(); // Returns false
   ```

### Iterating Through a HashMap

9. **Key Set**
    - Returns a set view of the keys contained in the map.
   ```java
   Set<String> keys = map.keySet();
   for (String key : keys) {
       System.out.println(key);
   }
   ```

10. **Values**
    - Returns a collection view of the values contained in the map.
    ```java
    Collection<Integer> values = map.values();
    for (Integer value : values) {
        System.out.println(value);
    }
    ```

11. **Entry Set**
    - Returns a set view of the key-value pairs contained in the map.
    ```java
    Set<Map.Entry<String, Integer>> entries = map.entrySet();
    for (Map.Entry<String, Integer> entry : entries) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    ```

### Other Useful Operations

12. **Clear**
    - Removes all key-value pairs from the map.
    ```java
    map.clear(); // Empties the map
    ```

13. **Put If Absent**
    - Adds the key-value pair only if the key is not already present.
    ```java
    map.putIfAbsent("C", 4); // Adds "C" with value 4 if "C" is not already in the map
    ```

14. **Get Or Default**
    - Returns the value associated with the key, or a default value if the key is not present.
    ```java
    Integer defaultValue = map.getOrDefault("D", 0); // Returns 0, as "D" is not present
    ```

15. **Remove If**
    - Removes the entry for a key only if currently mapped to a specified value.
    ```java
    map.remove("A", 3); // Removes "A" only if its current value is 3
    ```

16. **Replace**
    - Replaces the entry for a key only if it is currently mapped to a specified value.
    ```java
    map.replace("A", 3, 5); // Replaces value of "A" with 5 only if current value is 3
    ```

17. **Replace**
    - Replaces the value for a specified key.
    ```java
    map.replace("A", 6); // Replaces the value of "A" with 6
    ```

### Example Code
Here's a complete example demonstrating the above operations:

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();

        // Put elements
        map.put("A", 1);
        map.put("B", 2);
        map.put("A", 3); // Update "A"

        // Get element
        System.out.println("Value of A: " + map.get("A")); // Output: 3

        // Remove element
        map.remove("B");

        // Check if key exists
        System.out.println("Contains key A: " + map.containsKey("A")); // Output: true
        System.out.println("Contains key B: " + map.containsKey("B")); // Output: false

        // Size of the map
        System.out.println("Size: " + map.size()); // Output: 1

        // Iterating through keys
        System.out.println("Keys:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // Iterating through values
        System.out.println("Values:");
        for (Integer value : map.values()) {
            System.out.println(value);
        }

        // Iterating through entries
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Clear the map
        map.clear();
        System.out.println("Size after clear: " + map.size()); // Output: 0
    }
}
```

### Summary
These operations cover most of the common tasks you would perform with `HashMap` in Java. The `HashMap` class is part of the Java Collections Framework and provides an efficient way to store and manipulate key-value pairs.

# iteration
In Java 17, you can use several new and improved methods for iterating over a `Map`. One notable addition is the use of the `Stream` API, which allows for a more functional programming style. Below are some examples demonstrating different ways to iterate over a `Map` using Java 17.

### 1. Using `forEach` Method

The `forEach` method provides a convenient way to iterate through the entries of a `Map`.

```java
import java.util.HashMap;
import java.util.Map;

public class MapIterationExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Using forEach method
        map.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }
}
```

### 2. Using `Stream` API

You can convert the `Map` to a stream and use various stream operations.

#### a. Iterating Over Entries

```java
import java.util.HashMap;
import java.util.Map;

public class MapStreamIteration {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Using Stream API to iterate over entries
        map.entrySet().stream()
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
```

#### b. Filtering and Collecting

You can also filter entries and collect results into a new map or list.

```java
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStreamFilter {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        // Filtering entries where the value is greater than 2
        Map<String, Integer> filteredMap = map.entrySet().stream()
            .filter(entry -> entry.getValue() > 2)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Filtered Map: " + filteredMap);
    }
}
```

### 3. Iterating Over Keys and Values

You can separately iterate over the keys and values using the `keySet` and `values` methods.

#### a. Iterating Over Keys

```java
import java.util.HashMap;
import java.util.Map;

public class MapKeyIteration {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Iterating over keys
        for (String key : map.keySet()) {
            System.out.println("Key: " + key);
        }
    }
}
```

#### b. Iterating Over Values

```java
import java.util.HashMap;
import java.util.Map;

public class MapValueIteration {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Iterating over values
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }
    }
}
```

### 4. New Methods in Java 9 and Later

Although not specific to Java 17, it’s worth noting that starting from Java 9, the `Map` interface introduced the `Map.of` and `Map.copyOf` methods for creating immutable maps easily. This can simplify your code when you need to create maps for iteration.

#### Example of Creating an Immutable Map

```java
import java.util.Map;

public class ImmutableMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);

        // Iterating over an immutable map
        map.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }
}
```

### Summary

In Java 17, you can iterate over a `Map` using various methods, including the `forEach` method, the `Stream` API, and traditional loops for keys and values. The `Stream` API allows for more functional-style operations, making your code cleaner and more expressive. The introduction of `Map.of` provides a convenient way to create immutable maps for quick iterations.