The **Builder Pattern** is a creational design pattern that provides a way to construct complex objects step by step. It allows you to create different representations of the same object using the same construction process.

### Key Points of the Builder Pattern:
1. **Complex Object Construction**: It is used when you need to construct an object that requires several steps, making it complex to create all at once.
2. **Immutability**: The pattern allows the construction of immutable objects by separating the construction process from the object itself.
3. **Readable Code**: It promotes readable and flexible code by letting you set optional parameters using a chain of method calls.

### Problem Example:
Imagine you have a class `Car` that can have various properties such as engine type, GPS system, seats, airbags, etc. Without the builder pattern, you would either need to pass all the parameters through the constructor (which can be confusing and hard to manage for optional fields) or use a large number of overloaded constructors. The Builder Pattern solves this by separating the construction of the `Car` into distinct steps.

### Structure of the Builder Pattern:
- **Builder Interface/Abstract Class**: Defines methods to build different parts of a product.
- **Concrete Builder**: Implements the Builder interface and provides specific implementations for building parts of the product.
- **Product**: The final object that is being built, which has various components or attributes.
- **Director**: Orchestrates the construction process and calls the builder methods to assemble the product.

### When to Use:
- When the object creation process involves many parameters or steps.
- When some of the parameters are optional and the object can be created in different variations.

### Example: **Building a House**

Let’s say we want to build a **House** that can have optional attributes such as having a garage, swimming pool, garden, etc. Instead of having a huge constructor with all the optional attributes, we can use the Builder Pattern to construct the house step by step.

#### Step 1: Define the **House** class

This is the product we want to build. It will have optional features like garage, garden, and swimming pool.

```java
// Product class
public class House {
    private boolean hasGarage;
    private boolean hasSwimmingPool;
    private boolean hasGarden;

    // Private constructor, only accessible by the Builder
    private House(HouseBuilder builder) {
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasGarden = builder.hasGarden;
    }

    @Override
    public String toString() {
        return "House [Garage: " + hasGarage + ", Swimming Pool: " + hasSwimmingPool + ", Garden: " + hasGarden + "]";
    }

    // Static nested Builder class
    public static class HouseBuilder {
        private boolean hasGarage;
        private boolean hasSwimmingPool;
        private boolean hasGarden;

        // Method to set whether the house will have a garage
        public HouseBuilder setGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        // Method to set whether the house will have a swimming pool
        public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        // Method to set whether the house will have a garden
        public HouseBuilder setGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        // Method to build and return the House object
        public House build() {
            return new House(this);
        }
    }
}
```

In the above class:
- **HouseBuilder** is the builder class that provides methods for setting each optional property.
- The **build()** method finalizes the creation process and returns a `House` object.

#### Step 2: Client Code Using the Builder

Here, the client will use the `HouseBuilder` to set the optional parameters step by step and then call `build()` to get the final product.

```java
// Client code
public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Creating a house with a garage and garden but no swimming pool
        House house1 = new House.HouseBuilder()
                .setGarage(true)
                .setGarden(true)
                .build();

        System.out.println(house1);

        // Creating a house with a swimming pool only
        House house2 = new House.HouseBuilder()
                .setSwimmingPool(true)
                .build();

        System.out.println(house2);
    }
}
```

#### Output:
```
House [Garage: true, Swimming Pool: false, Garden: true]
House [Garage: false, Swimming Pool: true, Garden: false]
```

### Explanation:
1. **House (Product)**: The final object we are constructing.
2. **HouseBuilder (Builder)**: Provides methods to set the optional attributes of the house and has a `build()` method to construct the house.
3. **Client**: Uses the `HouseBuilder` to construct a house step by step, calling only the methods relevant to the features they want.

### Benefits of the Builder Pattern:
1. **Readable Code**: The client can construct the object in a readable and intuitive way by chaining method calls.
2. **Immutability**: Once the object is built, it is immutable because the final object is created by calling the `build()` method.
3. **Customization**: It allows customization without having constructors with many parameters, avoiding the "telescoping constructor" problem.
4. **Step-by-Step Construction**: The object can be built step by step, and only the necessary parameters need to be set. The builder ensures that the object is always in a consistent state before it is returned.

### Conclusion:
The **Builder Pattern** is useful when constructing complex objects that have optional parameters or configurations. By using a step-by-step approach, it promotes flexible object creation and avoids having too many overloaded constructors. The `House` example shows how the builder pattern can simplify the creation process for objects with various optional features.