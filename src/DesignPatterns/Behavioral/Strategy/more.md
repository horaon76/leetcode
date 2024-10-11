Here are some industry-related examples of the **Strategy Pattern** with detailed implementations in Java.

### Example 1: Delivery Method Selection in E-commerce

In an e-commerce application, users can choose from various delivery methods (Standard, Express, or Same-Day delivery). The Strategy Pattern allows the application to select the appropriate delivery method dynamically based on the user's choice.

#### Implementation

```java
// Strategy Interface
interface DeliveryStrategy {
    void deliver(String packageName);
}

// Concrete Strategy: Standard Delivery
class StandardDelivery implements DeliveryStrategy {
    @Override
    public void deliver(String packageName) {
        System.out.println("Delivering " + packageName + " using Standard Delivery (5-7 days).");
    }
}

// Concrete Strategy: Express Delivery
class ExpressDelivery implements DeliveryStrategy {
    @Override
    public void deliver(String packageName) {
        System.out.println("Delivering " + packageName + " using Express Delivery (2-3 days).");
    }
}

// Concrete Strategy: Same-Day Delivery
class SameDayDelivery implements DeliveryStrategy {
    @Override
    public void deliver(String packageName) {
        System.out.println("Delivering " + packageName + " using Same-Day Delivery.");
    }
}

// Context
class DeliveryService {
    private DeliveryStrategy deliveryStrategy;

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void deliverPackage(String packageName) {
        deliveryStrategy.deliver(packageName);
    }
}

// Client code
public class DeliveryDemo {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();

        // Choose Standard Delivery
        deliveryService.setDeliveryStrategy(new StandardDelivery());
        deliveryService.deliverPackage("Laptop");

        // Choose Express Delivery
        deliveryService.setDeliveryStrategy(new ExpressDelivery());
        deliveryService.deliverPackage("Smartphone");

        // Choose Same-Day Delivery
        deliveryService.setDeliveryStrategy(new SameDayDelivery());
        deliveryService.deliverPackage("Book");
    }
}
```

### Explanation

- **DeliveryStrategy**: The interface defining the `deliver` method.
- **Concrete Strategies**: `StandardDelivery`, `ExpressDelivery`, and `SameDayDelivery` implement different delivery methods.
- **DeliveryService**: The context that uses the selected delivery strategy.

### Example 2: Notification System

In a messaging application, users can receive notifications through various channels (Email, SMS, or Push Notifications). The Strategy Pattern enables the application to choose the notification method dynamically.

#### Implementation

```java
// Strategy Interface
interface NotificationStrategy {
    void sendNotification(String message);
}

// Concrete Strategy: Email Notification
class EmailNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email Notification: " + message);
    }
}

// Concrete Strategy: SMS Notification
class SMSNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}

// Concrete Strategy: Push Notification
class PushNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}

// Context
class NotificationService {
    private NotificationStrategy notificationStrategy;

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void notifyUser(String message) {
        notificationStrategy.sendNotification(message);
    }
}

// Client code
public class NotificationDemo {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        // Choose Email Notification
        notificationService.setNotificationStrategy(new EmailNotification());
        notificationService.notifyUser("Your order has been shipped!");

        // Choose SMS Notification
        notificationService.setNotificationStrategy(new SMSNotification());
        notificationService.notifyUser("Your package will arrive tomorrow.");

        // Choose Push Notification
        notificationService.setNotificationStrategy(new PushNotification());
        notificationService.notifyUser("New messages available in your inbox.");
    }
}
```

### Explanation

- **NotificationStrategy**: The interface defining the `sendNotification` method.
- **Concrete Strategies**: `EmailNotification`, `SMSNotification`, and `PushNotification` implement different notification methods.
- **NotificationService**: The context that uses the selected notification strategy.

### Example 3: Tax Calculation

In an e-commerce application, different products may have different tax calculation methods (e.g., VAT, Sales Tax, or No Tax). The Strategy Pattern can be used to encapsulate the tax calculation logic.

#### Implementation

```java
// Strategy Interface
interface TaxStrategy {
    double calculateTax(double price);
}

// Concrete Strategy: VAT Tax
class VATTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.2; // 20% VAT
    }
}

// Concrete Strategy: Sales Tax
class SalesTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.1; // 10% Sales Tax
    }
}

// Concrete Strategy: No Tax
class NoTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return 0; // No tax
    }
}

// Context
class ShoppingCart {
    private TaxStrategy taxStrategy;

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double calculateTotal(double price) {
        double tax = taxStrategy.calculateTax(price);
        return price + tax;
    }
}

// Client code
public class TaxDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Calculate total with VAT
        cart.setTaxStrategy(new VATTax());
        double totalWithVAT = cart.calculateTotal(100);
        System.out.println("Total with VAT: $" + totalWithVAT);

        // Calculate total with Sales Tax
        cart.setTaxStrategy(new SalesTax());
        double totalWithSalesTax = cart.calculateTotal(100);
        System.out.println("Total with Sales Tax: $" + totalWithSalesTax);

        // Calculate total with No Tax
        cart.setTaxStrategy(new NoTax());
        double totalWithNoTax = cart.calculateTotal(100);
        System.out.println("Total with No Tax: $" + totalWithNoTax);
    }
}
```

### Explanation

- **TaxStrategy**: The interface defining the `calculateTax` method.
- **Concrete Strategies**: `VATTax`, `SalesTax`, and `NoTax` implement different tax calculation methods.
- **ShoppingCart**: The context that uses the selected tax strategy.

### When to Use Strategy Pattern

- **Multiple Algorithms**: Use the Strategy Pattern when you have multiple algorithms for a particular task and want to choose the appropriate one at runtime.
- **Decoupling**: It helps decouple the code, allowing you to change the behavior of a class without modifying its structure.
- **Avoiding Conditional Statements**: It can reduce the use of conditional logic (like `if` or `switch` statements) by encapsulating the logic in separate classes.

By employing the Strategy Pattern, you can create flexible and maintainable applications that are easy to extend with new behaviors.