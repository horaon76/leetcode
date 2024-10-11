Let’s walk through another example using the **Factory Design Pattern** for a **Computer** manufacturing system where we create different types of computers like **PC**, **Server**, and **Laptop**. Each type of computer has different configurations like **RAM**, **HDD**, and **CPU**.

This example will demonstrate how a factory can be used to create objects that have similar behavior but differ in their configurations.

### Example: **Computer Factory**

We will define a common interface for all types of computers, and the factory will be responsible for creating instances of different computer types based on input parameters.

#### Step-by-Step Implementation

### Step 1: Create the `Computer` Abstract Class

The `Computer` abstract class will define the common properties and methods that all types of computers (PC, Server, Laptop) will have.

```java
public abstract class Computer {
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();
    
    @Override
    public String toString() {
        return "RAM= " + this.getRAM() + ", HDD= " + this.getHDD() + ", CPU= " + this.getCPU();
    }
}
```

### Step 2: Create Concrete Classes for Each Computer Type

We will now implement the `Computer` abstract class for different types of computers, such as `PC`, `Server`, and `Laptop`. Each concrete class will provide specific implementations for RAM, HDD, and CPU configurations.

#### PC Class:

```java
public class PC extends Computer {
    private String ram;
    private String hdd;
    private String cpu;

    public PC(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}
```

#### Server Class:

```java
public class Server extends Computer {
    private String ram;
    private String hdd;
    private String cpu;

    public Server(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}
```

#### Laptop Class:

```java
public class Laptop extends Computer {
    private String ram;
    private String hdd;
    private String cpu;

    public Laptop(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}
```

### Step 3: Create the `ComputerFactory` Class

Now we will create a `ComputerFactory` class that will decide which type of computer to create based on the input parameters provided.

```java
public class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if ("PC".equalsIgnoreCase(type)) {
            return new PC(ram, hdd, cpu);
        } else if ("Server".equalsIgnoreCase(type)) {
            return new Server(ram, hdd, cpu);
        } else if ("Laptop".equalsIgnoreCase(type)) {
            return new Laptop(ram, hdd, cpu);
        }
        return null;
    }
}
```

### Step 4: Implement the Client Code to Use the Factory

In the client code, we will use the `ComputerFactory` to get computer objects based on the type of computer required. The client doesn’t need to know the specifics of how each computer type is created; it just requests a computer from the factory.

```java
public class FactoryPatternDemo {
    public static void main(String[] args) {
        // Get a PC object
        Computer pc = ComputerFactory.getComputer("PC", "16 GB", "1 TB", "2.9 GHz");
        System.out.println("PC Config: " + pc);
        
        // Get a Server object
        Computer server = ComputerFactory.getComputer("Server", "32 GB", "4 TB", "3.5 GHz");
        System.out.println("Server Config: " + server);
        
        // Get a Laptop object
        Computer laptop = ComputerFactory.getComputer("Laptop", "8 GB", "512 GB", "2.5 GHz");
        System.out.println("Laptop Config: " + laptop);
    }
}
```

### Output:
```
PC Config: RAM= 16 GB, HDD= 1 TB, CPU= 2.9 GHz
Server Config: RAM= 32 GB, HDD= 4 TB, CPU= 3.5 GHz
Laptop Config: RAM= 8 GB, HDD= 512 GB, CPU= 2.5 GHz
```

### Explanation of the Factory Pattern in this Example:

1. **Computer Abstract Class**:
    - The `Computer` class is an abstract class with methods for getting computer properties such as RAM, HDD, and CPU. Each subclass of `Computer` (e.g., `PC`, `Server`, `Laptop`) must implement these methods.

2. **Concrete Computer Classes**:
    - The `PC`, `Server`, and `Laptop` classes each provide their own implementation of the `Computer` abstract class, representing specific configurations for different types of computers.

3. **ComputerFactory**:
    - The `ComputerFactory` class contains a static method `getComputer` that takes the computer type (PC, Server, Laptop) and the specifications (RAM, HDD, CPU) as input parameters.
    - Based on the type, the factory creates and returns the appropriate object (PC, Server, or Laptop).

4. **Client Code**:
    - The client code does not need to know the details of how each computer is created. It simply asks the `ComputerFactory` to return a computer with the specified configurations.
    - This approach abstracts away the creation process and ensures that the client code only deals with the `Computer` class, not its subclasses.

### Benefits of Using the Factory Design Pattern in this Scenario:

1. **Loose Coupling**:
    - The client is decoupled from the specific implementation of the computer types (`PC`, `Server`, `Laptop`). The client only interacts with the `Computer` interface, and the factory handles the creation.

2. **Centralized Object Creation**:
    - All the object creation logic is centralized in the `ComputerFactory`, so any changes to the way computers are created can be managed in one place, making the system easier to maintain and extend.

3. **Scalability**:
    - If a new type of computer (e.g., `Tablet`) needs to be added, we can simply create a new class for `Tablet` and update the factory method without affecting the client code.

4. **Simplifies Code**:
    - The client code is simplified because it doesn’t need to manage or understand the specifics of how different computers are created. It only asks for a `Computer` with certain configurations, and the factory handles the rest.

5. **Consistency**:
    - The factory ensures that the object creation is consistent and follows the same logic for all types of computers, avoiding discrepancies in how different types of objects are created across the system.

### Conclusion:

The **Factory Design Pattern** is highly useful when you need to create different types of objects based on input parameters or conditions but want to hide the creation logic from the client. In this example, the **ComputerFactory** encapsulates the object creation logic, allowing the client to easily request different types of computers without needing to know the details of how each type is created. This makes the code more modular, maintainable, and scalable.