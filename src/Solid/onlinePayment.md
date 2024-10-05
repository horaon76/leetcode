Let's create a complete example of an **Online Payment System** similar to Google Pay or PayPal that demonstrates the SOLID principles. This example will cover various aspects of an online payment system, including user management, payment processing, and notification services.

### Example: Online Payment System

#### Step 1: Define the Domain Models

We’ll start with the core models: `User`, `Payment`, and `Account`.

```java
// User class
class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

// Account class
class Account {
    private User user;
    private double balance;

    public Account(User user, double initialBalance) {
        this.user = user;
        this.balance = initialBalance;
    }

    public User getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }
}

// Payment class
class Payment {
    private Account sender;
    private Account receiver;
    private double amount;

    public Payment(Account sender, Account receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }
}
```

#### Step 2: PaymentProcessor Class

The `PaymentProcessor` class is responsible for processing payments and follows the Single Responsibility Principle (SRP).

```java
class PaymentProcessor {
    public void processPayment(Payment payment) {
        // Withdraw from sender's account
        payment.getSender().withdraw(payment.getAmount());
        
        // Deposit to receiver's account
        payment.getReceiver().deposit(payment.getAmount());
        
        System.out.println("Payment of $" + payment.getAmount() + " processed from " +
                payment.getSender().getUser().getUsername() + " to " +
                payment.getReceiver().getUser().getUsername());
    }
}
```

#### Step 3: NotificationService Interface and Implementations

To adhere to the Open/Closed Principle (OCP), we will create an interface for notifications.

```java
interface NotificationService {
    void notifyUser(User user, String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending email to " + user.getEmail() + ": " + message);
    }
}

class SMSNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending SMS to " + user.getUsername() + ": " + message);
    }
}
```

#### Step 4: TransactionManager Class

The `TransactionManager` class handles payment transactions and notifications. It encapsulates the logic related to transaction management.

```java
class TransactionManager {
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;

    public TransactionManager(PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }

    public void transferFunds(Account sender, Account receiver, double amount) {
        Payment payment = new Payment(sender, receiver, amount);
        paymentProcessor.processPayment(payment);

        // Notify both sender and receiver
        notificationService.notifyUser(sender.getUser(), "You sent $" + amount + " to " + receiver.getUser().getUsername());
        notificationService.notifyUser(receiver.getUser(), "You received $" + amount + " from " + sender.getUser().getUsername());
    }
}
```

#### Step 5: Main Application

Now, let’s put everything together in a main application to demonstrate the functionality.

```java
public class PaymentApplication {
    public static void main(String[] args) {
        // Create users
        User alice = new User("Alice", "alice@example.com");
        User bob = new User("Bob", "bob@example.com");

        // Create accounts
        Account aliceAccount = new Account(alice, 1000.00);
        Account bobAccount = new Account(bob, 500.00);

        // Create notification service
        NotificationService notificationService = new EmailNotificationService(); // or new SMSNotificationService();

        // Create payment processor and transaction manager
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        TransactionManager transactionManager = new TransactionManager(paymentProcessor, notificationService);

        // Simulate fund transfer
        transactionManager.transferFunds(aliceAccount, bobAccount, 200.00);
        
        // Check balances
        System.out.println("Alice's balance: $" + aliceAccount.getBalance());
        System.out.println("Bob's balance: $" + bobAccount.getBalance());
        
        // Attempt to transfer more than available balance
        try {
            transactionManager.transferFunds(bobAccount, aliceAccount, 600.00); // Should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### Summary of SOLID Principles Applied

1. **Single Responsibility Principle (SRP)**:
    - `User`, `Account`, and `Payment` classes each have a single responsibility.
    - `PaymentProcessor` is responsible for processing payments.
    - `TransactionManager` handles the transaction process and notifications.

2. **Open/Closed Principle (OCP)**:
    - The `NotificationService` interface allows for new notification methods (like push notifications) to be added without modifying existing code.

3. **Liskov Substitution Principle (LSP)**:
    - Any implementation of the `NotificationService` can be used interchangeably without affecting the behavior of the `TransactionManager`. For example, you can switch from `EmailNotificationService` to `SMSNotificationService` seamlessly.

4. **Interface Segregation Principle (ISP)**:
    - The `NotificationService` interface is focused, ensuring that clients only implement the methods they need. For instance, if we later create a `PushNotificationService`, it will only implement the notification method without being burdened with unrelated methods.

5. **Dependency Inversion Principle (DIP)**:
    - `TransactionManager` depends on the `NotificationService` abstraction, not on concrete implementations. This decoupling allows for easier testing and swapping of notification services.

### Conclusion

This complete example illustrates the application of all five SOLID principles in an online payment system. By following these principles, the system is modular, maintainable, and extensible. Each component is responsible for a specific function, and the design allows for easy adaptation as new requirements emerge, such as adding different types of notifications or additional features related to payment processing and user management.