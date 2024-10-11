Let's explore another example of the **Builder Pattern** by creating a **Pizza** class, which has various customizable attributes. This will illustrate how the builder pattern can simplify the creation of complex objects with multiple optional attributes.

### Problem Example
When making a pizza, you might want to customize various aspects, such as the size, type of crust, type of cheese, and toppings. The Builder Pattern can help you manage these customizable attributes without needing to create numerous constructors or complex object instantiation.

### Step 1: Define the **Pizza** Class

This is the product we want to build, which includes various attributes like size, crust type, cheese type, and toppings.

```java
// Product class
public class Pizza {
    private final String size;           // e.g., "Small", "Medium", "Large"
    private final String crustType;      // e.g., "Thin", "Thick"
    private final String cheeseType;     // e.g., "Mozzarella", "Cheddar"
    private final boolean hasPepperoni;  // Topping
    private final boolean hasMushrooms;  // Topping
    private final boolean hasOlives;      // Topping

    // Private constructor, accessible only via the Builder
    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.crustType = builder.crustType;
        this.cheeseType = builder.cheeseType;
        this.hasPepperoni = builder.hasPepperoni;
        this.hasMushrooms = builder.hasMushrooms;
        this.hasOlives = builder.hasOlives;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", crustType='" + crustType + '\'' +
                ", cheeseType='" + cheeseType + '\'' +
                ", hasPepperoni=" + hasPepperoni +
                ", hasMushrooms=" + hasMushrooms +
                ", hasOlives=" + hasOlives +
                '}';
    }

    // Static nested Builder class
    public static class PizzaBuilder {
        private String size;               // Default is null
        private String crustType;          // Default is null
        private String cheeseType;         // Default is null
        private boolean hasPepperoni;      // Default is false
        private boolean hasMushrooms;      // Default is false
        private boolean hasOlives;         // Default is false

        // Method to set the size of the pizza
        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        // Method to set the crust type
        public PizzaBuilder setCrustType(String crustType) {
            this.crustType = crustType;
            return this;
        }

        // Method to set the cheese type
        public PizzaBuilder setCheeseType(String cheeseType) {
            this.cheeseType = cheeseType;
            return this;
        }

        // Method to add pepperoni
        public PizzaBuilder addPepperoni() {
            this.hasPepperoni = true;
            return this;
        }

        // Method to add mushrooms
        public PizzaBuilder addMushrooms() {
            this.hasMushrooms = true;
            return this;
        }

        // Method to add olives
        public PizzaBuilder addOlives() {
            this.hasOlives = true;
            return this;
        }

        // Build method to create the final Pizza object
        public Pizza build() {
            return new Pizza(this);
        }
    }
}
```

In the `Pizza` class:
- **Attributes**: `size`, `crustType`, `cheeseType`, and boolean flags for optional toppings.
- **Private Constructor**: The constructor is private to ensure that objects are created only through the builder.
- **PizzaBuilder**: The static nested class that provides methods for setting the pizza’s attributes.

### Step 2: Client Code Using the Builder

In this part, we will demonstrate how to create different pizzas using the `PizzaBuilder`.

```java
// Client code
public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Create a medium-sized pizza with thin crust, mozzarella cheese, and pepperoni
        Pizza pizza1 = new Pizza.PizzaBuilder()
                .setSize("Medium")
                .setCrustType("Thin")
                .setCheeseType("Mozzarella")
                .addPepperoni()
                .build();

        System.out.println(pizza1);

        // Create a large pizza with thick crust, cheddar cheese, mushrooms, and olives
        Pizza pizza2 = new Pizza.PizzaBuilder()
                .setSize("Large")
                .setCrustType("Thick")
                .setCheeseType("Cheddar")
                .addMushrooms()
                .addOlives()
                .build();

        System.out.println(pizza2);
    }
}
```

### Output:
```
Pizza{size='Medium', crustType='Thin', cheeseType='Mozzarella', hasPepperoni=true, hasMushrooms=false, hasOlives=false}
Pizza{size='Large', crustType='Thick', cheeseType='Cheddar', hasPepperoni=false, hasMushrooms=true, hasOlives=true}
```

### Explanation:
1. **Pizza (Product)**: The `Pizza` class represents the product being built, with various customizable attributes.
2. **PizzaBuilder (Builder)**: The nested `PizzaBuilder` class provides methods to customize the pizza. Each method returns the builder instance, allowing for method chaining.
3. **Client**: The client creates pizza objects using the builder, specifying only the attributes they care about. The `build()` method finalizes the creation process and returns a `Pizza` object.

### Benefits of the Builder Pattern:
- **Flexible Object Creation**: Clients can create different variations of the pizza easily and clearly without needing multiple constructors.
- **Immutability**: Once built, a `Pizza` object is immutable, ensuring its state does not change after creation.
- **Optional Parameters**: The builder pattern elegantly handles optional parameters, allowing clients to only specify the attributes they want.

### Conclusion:
The **Builder Pattern** is highly effective for constructing complex objects with many configurable attributes, as seen in the `Pizza` example. This approach enhances readability, flexibility, and maintainability of the code while promoting immutability. The pizza-building scenario illustrates how the builder pattern can streamline the creation of objects with numerous optional features.