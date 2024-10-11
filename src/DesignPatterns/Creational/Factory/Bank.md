Let’s go through another detailed example using the **Factory Design Pattern** in a different scenario.

### Example: **Bank Loan System**

Consider a scenario where a bank provides various types of loans such as **Home Loan**, **Car Loan**, and **Personal Loan**. The system needs to handle the creation of these different loan objects, but we don't want the client code to directly instantiate the loan types. Instead, we’ll use the **Factory Design Pattern** to handle this.

#### Step-by-Step Implementation

### Step 1: Create a `Loan` Interface

The `Loan` interface will define a common method `getInterestRate()` that all types of loans must implement.

```java
public interface Loan {
    void getInterestRate();
}
```

### Step 2: Create Concrete Classes for Each Loan Type

Now, let’s implement the `Loan` interface in different classes for specific loan types such as `HomeLoan`, `CarLoan`, and `PersonalLoan`.

#### HomeLoan Class:

```java
public class HomeLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Interest rate for Home Loan is 6.5%");
    }
}
```

#### CarLoan Class:

```java
public class CarLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Interest rate for Car Loan is 8.2%");
    }
}
```

#### PersonalLoan Class:

```java
public class PersonalLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Interest rate for Personal Loan is 10.5%");
    }
}
```

### Step 3: Create the `LoanFactory` Class

The `LoanFactory` class is responsible for creating instances of the different loan types. It will contain a method `getLoan` that takes a string input and returns an appropriate `Loan` object based on the input.

```java
public class LoanFactory {
    // Method to create loan objects based on the input
    public Loan getLoan(String loanType) {
        if (loanType == null || loanType.isEmpty())
            return null;
        
        switch (loanType.toUpperCase()) {
            case "HOME":
                return new HomeLoan();
            case "CAR":
                return new CarLoan();
            case "PERSONAL":
                return new PersonalLoan();
            default:
                throw new IllegalArgumentException("Unknown loan type: " + loanType);
        }
    }
}
```

### Step 4: Implement the Client Code to Use the Factory

In the client code, we will use the `LoanFactory` to get loan objects without worrying about the details of the specific loan class.

```java
public class FactoryPatternExample {
    public static void main(String[] args) {
        // Create the factory object
        LoanFactory loanFactory = new LoanFactory();
        
        // Get a Home Loan object
        Loan homeLoan = loanFactory.getLoan("HOME");
        homeLoan.getInterestRate();  // Output: Interest rate for Home Loan is 6.5%
        
        // Get a Car Loan object
        Loan carLoan = loanFactory.getLoan("CAR");
        carLoan.getInterestRate();  // Output: Interest rate for Car Loan is 8.2%
        
        // Get a Personal Loan object
        Loan personalLoan = loanFactory.getLoan("PERSONAL");
        personalLoan.getInterestRate();  // Output: Interest rate for Personal Loan is 10.5%
    }
}
```

### Explanation of the Factory Pattern in this Example:

1. **Loan Interface**:
    - The `Loan` interface defines the common method `getInterestRate()`, which all loan types must implement.

2. **Concrete Loan Classes**:
    - We have three concrete loan classes (`HomeLoan`, `CarLoan`, and `PersonalLoan`) that implement the `Loan` interface.
    - Each loan type provides its own implementation of the `getInterestRate()` method, representing different interest rates for different loans.

3. **LoanFactory**:
    - The factory (`LoanFactory`) has a `getLoan` method that takes the type of loan as input (`HOME`, `CAR`, `PERSONAL`).
    - Based on the input string, it returns an instance of the corresponding loan class.
    - If a new loan type needs to be added in the future (e.g., `EducationLoan`), we simply add a new concrete class and update the factory logic without touching the client code.

4. **Client Code**:
    - The client code doesn’t need to know the exact class that implements the loan. It only interacts with the `Loan` interface and uses the factory to get the specific loan object.
    - This **decouples** the client from the concrete implementations, following the principle of **loose coupling**.

### Benefits of Using the Factory Design Pattern in this Scenario:

1. **Abstraction**:
    - The client only knows about the abstract `Loan` interface, not the specific implementation details of each loan type (such as `HomeLoan` or `CarLoan`). This makes the system easier to understand and maintain.

2. **Single Responsibility**:
    - The `LoanFactory` class handles all logic related to object creation, while the client class is only responsible for using the objects. This separation of concerns makes the system more modular.

3. **Scalability**:
    - If the bank decides to offer a new loan type in the future, such as an `EducationLoan`, you can easily extend the system by adding a new class that implements the `Loan` interface and updating the factory method. The rest of the system (client code) remains unchanged.

4. **Loose Coupling**:
    - The client code is not directly dependent on concrete classes like `HomeLoan`, `CarLoan`, or `PersonalLoan`. This makes the system more flexible and easier to modify or extend.

5. **Error Handling**:
    - The factory method can handle invalid input gracefully (e.g., by throwing an exception for an unknown loan type). This provides a centralized point for managing errors related to object creation.

### When to Use the Factory Pattern:

- **When you have multiple subclasses or types** that share the same interface or parent class and you need to determine which subclass to instantiate based on some input or logic.
- **When object creation logic is complex** or involves various configurations or conditions.
- **When you want to decouple the client code from concrete classes**, allowing you to extend or modify your system without changing the client code.

In this example, the **LoanFactory** centralizes object creation, hides the instantiation details from the client, and makes it easy to extend the system in the future. This is a typical scenario where the **Factory Pattern** proves useful.