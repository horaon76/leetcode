Certainly! Let’s take the e-commerce example from above and explain how the SOLID principles can be applied in detail. This will demonstrate how each principle contributes to a well-structured and maintainable system.

### 1. Single Responsibility Principle (SRP)

**Definition**: A class should have one, and only one, reason to change.

**Application in E-commerce Example**:

- **ShoppingCart Class**: This class is responsible solely for managing items in the shopping cart. It handles adding and removing items and calculating the total price. If the way items are managed changes (for example, changing from an ArrayList to a different collection), only this class needs to be modified.

    ```java
    class ShoppingCart {
        private List<Item> items;

        public ShoppingCart() {
            this.items = new ArrayList<>();
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public void removeItem(Item item) {
            items.remove(item);
        }

        public double calculateTotal() {
            return items.stream().mapToDouble(Item::getPrice).sum();
        }

        public List<Item> getItems() {
            return items;
        }
    }
    ```

- **EmailService Class**: If you want to send confirmation emails after a purchase, you could create a separate `EmailService` class to handle that logic, which keeps the responsibilities distinct.

    ```java
    class EmailService {
        public void sendConfirmationEmail(String email) {
            // Logic to send confirmation email
        }
    }
    ```

### 2. Open/Closed Principle (OCP)

**Definition**: Software entities should be open for extension but closed for modification.

**Application in E-commerce Example**:

- **PaymentProcessor Interface**: By defining a `PaymentProcessor` interface, you can introduce new payment methods without changing the existing code. For instance, if you want to add a `BitcoinProcessor`, you just create a new class that implements `PaymentProcessor`.

    ```java
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    class PayPalProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            System.out.println("Processing PayPal payment of " + amount);
        }
    }

    class BitcoinProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            System.out.println("Processing Bitcoin payment of " + amount);
        }
    }
    ```

### 3. Liskov Substitution Principle (LSP)

**Definition**: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

**Application in E-commerce Example**:

- **Substitutable Payment Processors**: If you have a class that uses a `PaymentProcessor`, you can replace it with any of its subclasses (like `PayPalProcessor` or `BitcoinProcessor`) without changing the logic of the `CheckoutService`.

    ```java
    class CheckoutService {
        private PaymentProcessor paymentProcessor;

        public CheckoutService(PaymentProcessor paymentProcessor) {
            this.paymentProcessor = paymentProcessor;
        }

        public void checkout(ShoppingCart cart) {
            double total = cart.calculateTotal();
            paymentProcessor.processPayment(total);
        }
    }
    ```

### 4. Interface Segregation Principle (ISP)

**Definition**: No client should be forced to depend on methods it does not use.

**Application in E-commerce Example**:

- **Separation of Payment Types**: Instead of having a single large interface for all payment-related functionalities, you can create smaller, focused interfaces. For instance, you might have a separate interface for processing refunds.

    ```java
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    interface Refundable {
        void processRefund(double amount);
    }

    class PayPalProcessor implements PaymentProcessor, Refundable {
        @Override
        public void processPayment(double amount) {
            // PayPal payment logic
        }

        @Override
        public void processRefund(double amount) {
            // PayPal refund logic
        }
    }

    class CreditCardProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            // Credit Card payment logic
        }
        
        // No refund method here, thus adhering to ISP
    }
    ```

### 5. Dependency Inversion Principle (DIP)

**Definition**: High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Application in E-commerce Example**:

- **Dependence on Abstractions**: Instead of `CheckoutService` depending directly on concrete classes like `PayPalProcessor`, it depends on the `PaymentProcessor` interface. This allows you to swap out implementations without changing the `CheckoutService`.

    ```java
    class CheckoutService {
        private PaymentProcessor paymentProcessor;

        public CheckoutService(PaymentProcessor paymentProcessor) {
            this.paymentProcessor = paymentProcessor;
        }

        public void checkout(ShoppingCart cart) {
            double total = cart.calculateTotal();
            paymentProcessor.processPayment(total);
        }
    }
    ```

- **Using Dependency Injection**: You can use dependency injection frameworks (like Spring) to manage the creation and injection of `PaymentProcessor` instances, making it easier to manage dependencies and configurations.

### Conclusion

Applying the SOLID principles to the e-commerce system not only leads to cleaner and more organized code but also enhances maintainability, flexibility, and testability. Each class and interface has a clear responsibility, making it easier to understand and extend the system as new requirements emerge. By following these principles, developers can create robust software that can adapt to changing business needs with minimal risk of introducing bugs.