Sure! Let's explore another example of the **Adapter Pattern**, this time using a real-world scenario where we integrate different charging devices with a common power outlet.

### Scenario
Imagine you have a power outlet that only accepts **European plugs** (Type C), but you want to connect devices that come with **American plugs** (Type A). To solve this, we can use an adapter that converts the American plug interface to a European plug interface.

### Key Components
1. **Target**: The interface that the client expects (`EuropeanPlug`).
2. **Adapter**: The adapter that implements the `EuropeanPlug` interface and adapts the `AmericanPlug` to the expected interface.
3. **Adaptee**: The existing interface that is incompatible with the `EuropeanPlug` (the `AmericanPlug`).

### Step 1: Define the Target Interface
```java
// Target interface
interface EuropeanPlug {
    void connect();
}
```

### Step 2: Define the Adaptee Class
```java
// Adaptee class
class AmericanPlug {
    public void plugIn() {
        System.out.println("American plug is plugged in.");
    }
}
```

### Step 3: Create the Adapter Class
```java
// Adapter class
class AmericanPlugAdapter implements EuropeanPlug {
    private AmericanPlug americanPlug;

    public AmericanPlugAdapter(AmericanPlug americanPlug) {
        this.americanPlug = americanPlug;
    }

    @Override
    public void connect() {
        // Adapting the method call from American plug to the European plug interface
        americanPlug.plugIn();
    }
}
```

### Step 4: Client Code
```java
public class Main {
    public static void main(String[] args) {
        AmericanPlug americanPlug = new AmericanPlug();
        EuropeanPlug europeanPlug = new AmericanPlugAdapter(americanPlug);

        // Use the adapter to connect the American plug to the European outlet
        europeanPlug.connect(); // Output: American plug is plugged in.
    }
}
```

### Explanation
1. **Target Interface (`EuropeanPlug`)**: This interface defines the method `connect()` that the client expects to use.
2. **Adaptee Class (`AmericanPlug`)**: This class represents the existing device interface that is incompatible with the `EuropeanPlug`. It has its own method, `plugIn()`.
3. **Adapter Class (`AmericanPlugAdapter`)**: This class implements the `EuropeanPlug` interface and contains an instance of `AmericanPlug`. In the `connect()` method, it calls the `plugIn()` method of the `AmericanPlug`, effectively adapting the method to fit the expected interface.
4. **Client Code**: In the `Main` class, the client creates an instance of `AmericanPlug`, wraps it in the `AmericanPlugAdapter`, and calls the `connect()` method. The output confirms that the American plug is successfully adapted to fit into the European system.

### Advantages
- **Compatibility**: The Adapter Pattern allows the integration of incompatible interfaces without modifying existing code.
- **Ease of Use**: The client can seamlessly interact with the adapted interfaces, maintaining the abstraction and simplicity in usage.
- **Maintainability**: New devices can easily be integrated by creating new adapters without altering existing system components.

### Use Cases
The Adapter Pattern is commonly used in:
- Integrating legacy systems with new applications.
- Working with third-party libraries where you need to adapt their interfaces to your existing code.
- Implementing plugins or modules that need to work with different interfaces.

This pattern helps create a clean and flexible architecture that can evolve without significant changes to the underlying codebase.