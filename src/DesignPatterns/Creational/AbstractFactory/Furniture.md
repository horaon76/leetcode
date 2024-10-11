### Another Example: **Furniture Store**

Let's explore the **Abstract Factory Pattern** with a real-world example of a **Furniture Store**. Imagine a furniture store that sells **Modern** and **Victorian** style furniture. The store manufactures different types of furniture such as **Chair**, **Sofa**, and **Coffee Table**, with each type available in either a **Modern** or **Victorian** style.

The **Abstract Factory Pattern** can be used to design a system where the client can order a set of furniture without worrying about which style it belongs to. The client only needs to specify the style (Modern or Victorian), and the system will return the appropriate types of furniture.

### Scenario:

A furniture store manufactures different types of furniture (Chair, Sofa, Coffee Table) in different styles (Modern and Victorian). You want to be able to request a family of related products (e.g., all Modern furniture or all Victorian furniture) without worrying about the details of how they are created.

### Step-by-Step Implementation

### Step 1: Define the Abstract Product Interfaces

We will define abstract product interfaces for the types of furniture: **Chair**, **Sofa**, and **Coffee Table**. These will be implemented differently for Modern and Victorian styles.

#### Chair Interface

```java
// Abstract Product - Chair
public interface Chair {
    void sitOn();  // Method to sit on the chair
}
```

#### Sofa Interface

```java
// Abstract Product - Sofa
public interface Sofa {
    void lieOn();  // Method to lie on the sofa
}
```

#### CoffeeTable Interface

```java
// Abstract Product - Coffee Table
public interface CoffeeTable {
    void placeItems();  // Method to place items on the coffee table
}
```

### Step 2: Create Concrete Products

For each type of furniture (Chair, Sofa, Coffee Table), we will create concrete implementations in **Modern** and **Victorian** styles.

#### ModernChair Class

```java
// Concrete Product - Modern Chair
public class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a modern chair.");
    }
}
```

#### VictorianChair Class

```java
// Concrete Product - Victorian Chair
public class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair.");
    }
}
```

#### ModernSofa Class

```java
// Concrete Product - Modern Sofa
public class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a modern sofa.");
    }
}
```

#### VictorianSofa Class

```java
// Concrete Product - Victorian Sofa
public class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a Victorian sofa.");
    }
}
```

#### ModernCoffeeTable Class

```java
// Concrete Product - Modern Coffee Table
public class ModernCoffeeTable implements CoffeeTable {
    @Override
    public void placeItems() {
        System.out.println("Placing items on a modern coffee table.");
    }
}
```

#### VictorianCoffeeTable Class

```java
// Concrete Product - Victorian Coffee Table
public class VictorianCoffeeTable implements CoffeeTable {
    @Override
    public void placeItems() {
        System.out.println("Placing items on a Victorian coffee table.");
    }
}
```

### Step 3: Define the Abstract Factory Interface

The abstract factory will define methods to create each type of furniture. This will be implemented by concrete factories for the **Modern** and **Victorian** styles.

```java
// Abstract Factory
public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    CoffeeTable createCoffeeTable();
}
```

### Step 4: Create Concrete Factories

We will create two concrete factories, one for manufacturing **Modern** furniture and one for **Victorian** furniture. Each factory will create its own style of Chair, Sofa, and Coffee Table.

#### ModernFurnitureFactory Class

```java
// Concrete Factory - Modern Furniture Factory
public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}
```

#### VictorianFurnitureFactory Class

```java
// Concrete Factory - Victorian Furniture Factory
public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}
```

### Step 5: Client Code

The client code will use the abstract factory to get the appropriate set of furniture, based on the style chosen (Modern or Victorian). The client does not need to know the specifics of the furniture creation process.

```java
// Client code
public class FurnitureStore {
    private Chair chair;
    private Sofa sofa;
    private CoffeeTable coffeeTable;

    public FurnitureStore(FurnitureFactory factory) {
        chair = factory.createChair();
        sofa = factory.createSofa();
        coffeeTable = factory.createCoffeeTable();
    }

    public void furnishRoom() {
        chair.sitOn();
        sofa.lieOn();
        coffeeTable.placeItems();
    }

    public static void main(String[] args) {
        // Based on user preference, create the appropriate furniture factory
        String style = "Victorian";  // Could be "Modern" or "Victorian"
        
        FurnitureFactory factory;
        if ("Modern".equalsIgnoreCase(style)) {
            factory = new ModernFurnitureFactory();
        } else if ("Victorian".equalsIgnoreCase(style)) {
            factory = new VictorianFurnitureFactory();
        } else {
            throw new IllegalArgumentException("Unknown furniture style: " + style);
        }

        // Create the furniture store and furnish the room
        FurnitureStore store = new FurnitureStore(factory);
        store.furnishRoom();
    }
}
```

### Output for Victorian Furniture:

```
Sitting on a Victorian chair.
Lying on a Victorian sofa.
Placing items on a Victorian coffee table.
```

### Output for Modern Furniture:

```
Sitting on a modern chair.
Lying on a modern sofa.
Placing items on a modern coffee table.
```

### Explanation of Abstract Factory Pattern in This Example:

1. **Abstract Factory (`FurnitureFactory`)**:
    - The `FurnitureFactory` interface declares methods for creating abstract products: `Chair`, `Sofa`, and `CoffeeTable`.

2. **Concrete Factories (`ModernFurnitureFactory`, `VictorianFurnitureFactory`)**:
    - These concrete factories implement the `FurnitureFactory` interface and provide specific implementations for the products in either **Modern** or **Victorian** style.

3. **Abstract Products (`Chair`, `Sofa`, `CoffeeTable`)**:
    - These are abstract product interfaces that define the methods common to all chairs, sofas, and coffee tables, irrespective of the style.

4. **Concrete Products (`ModernChair`, `VictorianChair`, etc.)**:
    - The concrete products implement the abstract product interfaces for specific styles of furniture (Modern and Victorian).

5. **Client Code**:
    - The client code works with the abstract factory and abstract product interfaces without knowing the concrete classes used, making it flexible and decoupled from specific implementations.

### Benefits of Using Abstract Factory in this Scenario:

1. **Decoupling**:
    - The client is decoupled from the specific furniture implementations. It only knows about the abstract interfaces and the `FurnitureFactory`.

2. **Consistency Across Product Families**:
    - The factory ensures that products from the same family (e.g., all Modern or all Victorian) are used together. You cannot accidentally mix a Modern chair with a Victorian sofa.

3. **Extensibility**:
    - If a new style of furniture (e.g., **Art Deco**) is added, the system can easily be extended by adding new concrete factories and product classes, without modifying the client code.

4. **Flexibility**:
    - The system is flexible in that new furniture styles can be added by extending the abstract factory. You can also easily swap between product families by changing the factory.

### Conclusion:

This **Furniture Store** example illustrates how the **Abstract Factory Pattern** can be used to create families of related objects (in this case, furniture). The pattern promotes loose coupling between the client and the product creation process, ensures consistency across related products (like Modern or Victorian styles), and makes the system flexible and extensible for future modifications.