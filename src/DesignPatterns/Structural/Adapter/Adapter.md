The **Adapter Pattern** is a structural design pattern that allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by converting the interface of a class into another interface that the client expects. This pattern is particularly useful when you want to use an existing class but its interface does not match the one you need.

### Key Components
1. **Target**: This is the interface that the client expects.
2. **Adapter**: This is the class that implements the Target interface and translates the requests to the Adaptee interface.
3. **Adaptee**: This is the existing interface that is incompatible with the Target.

### When to Use the Adapter Pattern
- When you want to use a class that is incompatible with the current interface.
- When you want to create a reusable class that cooperates with unrelated or unforeseen classes.

### Example Scenario

Let's consider a scenario where you have a legacy application that uses a `SquarePeg` class, but you want to work with a `RoundPeg` class in a new feature.

#### Step 1: Define the Target Interface
```java
// Target interface
interface RoundPeg {
    void fit();
}
```

#### Step 2: Define the Adaptee Class
```java
// Adaptee class
class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}
```

#### Step 3: Create the Adapter Class
```java
// Adapter class
class SquarePegAdapter implements RoundPeg {
    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public void fit() {
        // The adapter converts the fit method to accommodate the square peg
        System.out.println("Fitting a square peg of width: " + squarePeg.getWidth());
    }
}
```

#### Step 4: Client Code
```java
public class Main {
    public static void main(String[] args) {
        SquarePeg squarePeg = new SquarePeg(5);
        RoundPeg roundPeg = new SquarePegAdapter(squarePeg);

        roundPeg.fit(); // Output: Fitting a square peg of width: 5.0
    }
}
```

### Explanation
1. **Target Interface**: The `RoundPeg` interface defines the method `fit()`.
2. **Adaptee Class**: The `SquarePeg` class has its own implementation and is not compatible with the `RoundPeg` interface.
3. **Adapter Class**: The `SquarePegAdapter` implements the `RoundPeg` interface and holds a reference to the `SquarePeg` instance. It adapts the `fit()` method to make it compatible.
4. **Client Code**: In the `Main` class, the client can use the `SquarePegAdapter` to fit a `SquarePeg` into a system that expects a `RoundPeg`, demonstrating how the adapter pattern works.

### Advantages
- **Flexibility**: You can use existing classes with a new interface without modifying the existing code.
- **Reusability**: Classes can be reused by adapting them to fit into different interfaces.
- **Decoupling**: Reduces the dependencies between interfaces and classes.

The Adapter Pattern is widely used in software development to promote code reusability and maintainability while allowing for flexibility in interfacing different components.