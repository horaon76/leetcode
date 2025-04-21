You can generate a **list of 100 numbers** in Java in multiple ways. Here's the most common and cleanest method using **Streams**:

---

## ✅ 1. Using Java Streams (Java 8+)

```java
import java.util.List;
import java.util.stream.IntStream;

List<Integer> numbers = IntStream.rangeClosed(1, 100)
                                 .boxed()
                                 .toList(); // Java 16+
```

### 🔹 Explanation:
- `rangeClosed(1, 100)` → includes both 1 and 100.
- `boxed()` → converts primitive `int` to `Integer`.
- `toList()` → collects the stream into a List (Java 16+).

If you're using **Java 8 - 15**, replace `.toList()` with `.collect(Collectors.toList())`:

```java
import java.util.List;
import java.util.stream.Collectors;

List<Integer> numbers = IntStream.rangeClosed(1, 100)
                                 .boxed()
                                 .collect(Collectors.toList());
```

---

## ✅ 2. Using a Traditional Loop

If you’re not using streams:

```java
import java.util.ArrayList;
import java.util.List;

List<Integer> numbers = new ArrayList<>();
for (int i = 1; i <= 100; i++) {
    numbers.add(i);
}
```

---

## 💡 Bonus: Generate 100 Random Numbers

```java
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

Random rand = new Random();
List<Integer> randomNumbers = IntStream.range(0, 100)
    .map(i -> rand.nextInt(1000)) // random number from 0 to 999
    .boxed()
    .collect(Collectors.toList());
```

Let me know if you want to generate even numbers only, odd numbers, multiples of 5, etc.