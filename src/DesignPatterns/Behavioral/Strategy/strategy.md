The **Strategy Pattern** is a behavioral design pattern that enables selecting an algorithm's behavior at runtime. It defines a family of algorithms, encapsulates each one, and makes them interchangeable. The Strategy Pattern lets the algorithm vary independently from the clients that use it.

### Key Components

1. **Context**: The class that contains a reference to a strategy object.
2. **Strategy Interface**: An interface common to all supported algorithms.
3. **Concrete Strategies**: Implementations of the strategy interface that encapsulate specific algorithms.

### Use Cases

- When you have multiple algorithms for a specific task, and you want to choose which one to use at runtime.
- To avoid conditional statements (like `if` or `switch`) for selecting the algorithm.
- To achieve loose coupling between clients and the algorithms they use.

### Industry Examples of Strategy Pattern

#### Example 1: Payment Processing in E-commerce

In an e-commerce application, customers can pay using various payment methods such as credit card, PayPal, or cryptocurrency. Using the Strategy Pattern allows the application to choose the payment method dynamically.

#### Implementation

```java
// Strategy Interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategy: Credit Card Payment
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
    }
}

// Concrete Strategy: PayPal Payment
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal: " + email);
    }
}

// Concrete Strategy: Cryptocurrency Payment
class CryptoPayment implements PaymentStrategy {
    private String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Cryptocurrency Wallet: " + walletAddress);
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Client code
public class PaymentDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay using credit card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(100);

        // Pay using PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);

        // Pay using cryptocurrency
        cart.setPaymentStrategy(new CryptoPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout(300);
    }
}
```

### Explanation

- **PaymentStrategy**: The interface for all payment strategies.
- **Concrete Strategies**: `CreditCardPayment`, `PayPalPayment`, and `CryptoPayment` implement the payment strategies.
- **ShoppingCart**: The context class that uses the payment strategy.

---

#### Example 2: Sorting Algorithms

In data processing applications, you might want to sort data using different algorithms (e.g., QuickSort, MergeSort, BubbleSort). The Strategy Pattern allows you to switch between sorting algorithms at runtime.

#### Implementation

```java
// Strategy Interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete Strategy: QuickSort
class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using QuickSort");
        // Implementation of QuickSort algorithm
        // For simplicity, just printing the sorted array
        Arrays.sort(array); // Using built-in sort for demonstration
        System.out.println(Arrays.toString(array));
    }
}

// Concrete Strategy: MergeSort
class MergeSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using MergeSort");
        // Implementation of MergeSort algorithm
        Arrays.sort(array); // Using built-in sort for demonstration
        System.out.println(Arrays.toString(array));
    }
}

// Context
class Sorter {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void sort(int[] array) {
        sortingStrategy.sort(array);
    }
}

// Client code
public class SortingDemo {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        int[] array = {5, 2, 8, 3, 1};

        // Sort using QuickSort
        sorter.setSortingStrategy(new QuickSort());
        sorter.sort(array);

        // Sort using MergeSort
        sorter.setSortingStrategy(new MergeSort());
        sorter.sort(array);
    }
}
```

### Explanation

- **SortingStrategy**: The interface for sorting strategies.
- **Concrete Strategies**: `QuickSort` and `MergeSort` implement sorting algorithms.
- **Sorter**: The context class that uses the sorting strategy.

---

### When to Use Strategy Pattern

1. **Multiple Algorithms**: When you have multiple ways to perform a task and want to allow the client to choose which one to use at runtime.
2. **Avoiding Conditional Logic**: When using multiple conditional statements to choose an algorithm, the Strategy Pattern can simplify the code.
3. **Decoupling**: When you want to decouple the client from the specific implementations of an algorithm.

The Strategy Pattern enhances flexibility and maintainability by encapsulating algorithms and providing a clean interface for them.