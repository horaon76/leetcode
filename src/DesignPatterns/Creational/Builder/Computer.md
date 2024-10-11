Let's explore another example of the **Builder Pattern**, this time focusing on a **Computer** class. This example will demonstrate how the builder pattern can effectively manage the construction of complex objects with various configurations.

### Problem Example
When configuring a computer, various components can be selected or customized, such as the CPU, RAM, storage type, and operating system. The Builder Pattern allows us to create a `Computer` object step by step, making it easy to create various configurations without the complexity of multiple constructors.

### Step 1: Define the **Computer** Class

The `Computer` class will have several attributes related to its configuration.

```java
// Product class
public class Computer {
    private final String cpu;                // e.g., "Intel i7"
    private final String ram;                // e.g., "16GB"
    private final String storage;            // e.g., "512GB SSD"
    private final String graphicsCard;       // e.g., "NVIDIA RTX 3080"
    private final String operatingSystem;    // e.g., "Windows 10"

    // Private constructor, accessible only via the Builder
    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.operatingSystem = builder.operatingSystem;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + cpu + '\'' +
                ", RAM='" + ram + '\'' +
                ", Storage='" + storage + '\'' +
                ", Graphics Card='" + graphicsCard + '\'' +
                ", Operating System='" + operatingSystem + '\'' +
                '}';
    }

    // Static nested Builder class
    public static class ComputerBuilder {
        private String cpu;                // Default is null
        private String ram;                // Default is null
        private String storage;            // Default is null
        private String graphicsCard;       // Default is null
        private String operatingSystem;    // Default is null

        // Method to set the CPU
        public ComputerBuilder setCPU(String cpu) {
            this.cpu = cpu;
            return this;
        }

        // Method to set the RAM
        public ComputerBuilder setRAM(String ram) {
            this.ram = ram;
            return this;
        }

        // Method to set the storage
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        // Method to set the graphics card
        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        // Method to set the operating system
        public ComputerBuilder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        // Build method to create the final Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}
```

In the `Computer` class:
- **Attributes**: The class includes attributes for CPU, RAM, storage, graphics card, and operating system.
- **Private Constructor**: The constructor is private, ensuring that `Computer` objects can only be created through the builder.
- **ComputerBuilder**: This static nested class provides methods to customize each attribute of the `Computer`.

### Step 2: Client Code Using the Builder

In this part, we will demonstrate how to create different configurations of computers using the `ComputerBuilder`.

```java
// Client code
public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Create a computer with specific configurations
        Computer gamingPC = new Computer.ComputerBuilder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 3090")
                .setOperatingSystem("Windows 11")
                .build();

        System.out.println(gamingPC);

        // Create a computer for office use with different configurations
        Computer officePC = new Computer.ComputerBuilder()
                .setCPU("Intel i5")
                .setRAM("16GB")
                .setStorage("512GB HDD")
                .setOperatingSystem("Windows 10")
                .build();

        System.out.println(officePC);
    }
}
```

### Output:
```
Computer{CPU='Intel i9', RAM='32GB', Storage='1TB SSD', Graphics Card='NVIDIA RTX 3090', Operating System='Windows 11'}
Computer{CPU='Intel i5', RAM='16GB', Storage='512GB HDD', Graphics Card='null', Operating System='Windows 10'}
```

### Explanation:
1. **Computer (Product)**: The `Computer` class represents the product being built, containing various attributes relevant to a computer's configuration.
2. **ComputerBuilder (Builder)**: The nested `ComputerBuilder` class provides methods for each attribute. Each method returns the builder instance, allowing for method chaining.
3. **Client**: The client code demonstrates creating different `Computer` objects with varying configurations, calling only the relevant methods. The `build()` method finalizes the creation and returns a `Computer` object.

### Benefits of the Builder Pattern:
- **Readable Code**: The builder allows for clear and readable code, making it easy to see what configurations are being set for the computer.
- **Immutability**: Once built, the `Computer` object is immutable, ensuring that its state does not change after creation.
- **Flexibility**: The builder can create various configurations without the need for complex constructor overloads, avoiding confusion and potential errors.

### Conclusion:
The **Builder Pattern** is highly useful for constructing complex objects with many customizable attributes, as illustrated by the `Computer` example. It enhances readability, flexibility, and maintainability while promoting immutability and simplifying object creation. This approach allows for easy configurations, making it clear how each part of the object is constructed.