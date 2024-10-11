Let’s explore another example of the **Prototype Pattern**, this time using a more complex scenario involving a `Car` prototype. In this example, we will create a `Car` class that can be cloned to produce variations of a car without needing to create new instances from scratch.

### Problem Scenario

Suppose we have a system that manages a car dealership. Each car has several attributes, such as model, color, and engine type. Instead of creating new car instances manually, we can utilize the Prototype Pattern to clone existing car prototypes with different configurations.

### Step 1: Define the Prototype Interface

First, we define a `Car` interface that includes a `clone()` method.

```java
// Prototype interface
public interface Car {
    Car clone();
    void showDetails();
}
```

### Step 2: Implement Concrete Prototypes

Now, let's implement concrete prototypes for `Sedan` and `SUV` cars.

```java
// Concrete prototype: Sedan
public class Sedan implements Car {
    private String model;
    private String color;
    private String engineType;

    public Sedan(String model, String color, String engineType) {
        this.model = model;
        this.color = color;
        this.engineType = engineType;
    }

    @Override
    public Car clone() {
        return new Sedan(this.model, this.color, this.engineType);
    }

    @Override
    public void showDetails() {
        System.out.println("Sedan Model: " + model + ", Color: " + color + ", Engine: " + engineType);
    }
}

// Concrete prototype: SUV
public class SUV implements Car {
    private String model;
    private String color;
    private String engineType;

    public SUV(String model, String color, String engineType) {
        this.model = model;
        this.color = color;
        this.engineType = engineType;
    }

    @Override
    public Car clone() {
        return new SUV(this.model, this.color, this.engineType);
    }

    @Override
    public void showDetails() {
        System.out.println("SUV Model: " + model + ", Color: " + color + ", Engine: " + engineType);
    }
}
```

### Step 3: Client Code Using the Prototype Pattern

Now, let's create a client that demonstrates how to use the `Car` prototypes.

```java
public class PrototypeDemo {
    public static void main(String[] args) {
        // Create original car prototypes
        Car originalSedan = new Sedan("Toyota Camry", "Black", "V6");
        Car originalSUV = new SUV("Honda CR-V", "White", "I4");

        // Clone the original prototypes
        Car clonedSedan = originalSedan.clone();
        Car clonedSUV = originalSUV.clone();

        // Change attributes of the cloned cars
        // (Simulating a different configuration)
        ((Sedan) clonedSedan).showDetails(); // Output: Sedan Model: Toyota Camry, Color: Black, Engine: V6
        ((SUV) clonedSUV).showDetails();     // Output: SUV Model: Honda CR-V, Color: White, Engine: I4

        // Display original cars
        originalSedan.showDetails(); // Output: Sedan Model: Toyota Camry, Color: Black, Engine: V6
        originalSUV.showDetails();   // Output: SUV Model: Honda CR-V, Color: White, Engine: I4
    }
}
```

### Output

```
Sedan Model: Toyota Camry, Color: Black, Engine: V6
SUV Model: Honda CR-V, Color: White, Engine: I4
Sedan Model: Toyota Camry, Color: Black, Engine: V6
SUV Model: Honda CR-V, Color: White, Engine: I4
```

### Explanation of the Example

1. **Prototype Interface**: The `Car` interface defines the `clone()` method for cloning and a `showDetails()` method to display car attributes.
2. **Concrete Prototypes**:
    - **Sedan**: Implements the `Car` interface and provides a method to clone itself while preserving its attributes.
    - **SUV**: Similar to the `Sedan`, it implements the `Car` interface and can also clone itself with its attributes.
3. **Client Code**:
    - The client creates original car prototypes: `Sedan` and `SUV`.
    - It clones these prototypes using the `clone()` method.
    - It displays the details of both the cloned and original cars, demonstrating that they maintain their properties.

### Conclusion

The **Prototype Pattern** provides a flexible and efficient way to create new objects by cloning existing ones. In this example, we illustrated how car prototypes can be used to instantiate various configurations of cars without requiring the overhead of constructing new instances from scratch. This pattern is particularly beneficial when the creation of an object is complex or costly, allowing for more efficient object management in scenarios like a car dealership.