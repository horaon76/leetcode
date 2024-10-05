package Solid;

import java.util.*;

// Item class
//Step 1: Define the Domain Models
//We’ll start by defining the core models: Item, ShoppingCart, PaymentProcessor, and Discount.
class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
//Step 2: ShoppingCart Class
//The ShoppingCart will adhere to the Single Responsibility Principle (SRP) by only handling item management.


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
// Define PaymentProcessor Interface and Implementations
// This adheres to the Dependency Inversion Principle (DIP) and Open/Closed Principle (OCP) by allowing new payment methods to be added easily.

interface PaymentProcessor {
    void processPayment(double amount);
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of " + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of " + amount);
    }
}
//Step 4: Define Discount Interface and Implementations
//This setup follows the Open/Closed Principle (OCP) as well.

interface Discount {
    double apply(double total);
}

class NoDiscount implements Discount {
    @Override
    public double apply(double total) {
        return total;
    }
}

class PercentageDiscount implements Discount {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double apply(double total) {
        return total - (total * percentage / 100);
    }
}

//Step 5: Create CheckoutService
//The CheckoutService class coordinates the checkout process, adhering to SRP, and is flexible enough to support various payment processors and discounts
class CheckoutService {
    private PaymentProcessor paymentProcessor;
    private Discount discount;

    public CheckoutService(PaymentProcessor paymentProcessor, Discount discount) {
        this.paymentProcessor = paymentProcessor;
        this.discount = discount;
    }

    public void checkout(ShoppingCart cart) {
        double total = discount.apply(cart.calculateTotal());
        paymentProcessor.processPayment(total);
    }
}

public class Ecommerce {
    public static void main(String[] args) {
        // Create some items
        Item item1 = new Item("Laptop", 1000.00);
        Item item2 = new Item("Smartphone", 800.00);

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(item1);
        cart.addItem(item2);

        // Create payment processor and discount
        PaymentProcessor paymentProcessor = new PayPalProcessor(); // or new CreditCardProcessor();
        Discount discount = new PercentageDiscount(10); // 10% discount

        // Create checkout service
        CheckoutService checkoutService = new CheckoutService(paymentProcessor, discount);

        // Perform checkout
        checkoutService.checkout(cart);
    }
}
/**
 *Summary of SOLID Principles Applied
 * Single Responsibility Principle (SRP):
 *
 * ShoppingCart handles item management.
 * CheckoutService manages the checkout process.
 * Each payment processor has its own class.
 * Open/Closed Principle (OCP):
 *
 * New payment methods can be added by creating new classes implementing the PaymentProcessor interface.
 * New discount types can be added by creating new classes implementing the Discount interface.
 * Liskov Substitution Principle (LSP):
 *
 * Any class implementing PaymentProcessor or Discount can be used interchangeably without breaking the functionality.
 * Interface Segregation Principle (ISP):
 *
 * Interfaces are focused, allowing classes to implement only the methods they need.
 * Dependency Inversion Principle (DIP):
 *
 * CheckoutService depends on abstractions (PaymentProcessor and Discount), not on concrete implementations.
 * Conclusion
 * This complete example illustrates the application of all five SOLID principles in an E-commerce system, making the code modular, maintainable, and extensible. Each class has a clear responsibility, and the design allows for easy extension without modifying existing code.
 *
 * **/