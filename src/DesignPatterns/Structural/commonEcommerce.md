Let's use an **E-commerce Platform** as a common industry-related example for implementing various design patterns (Adapter, Bridge, Composite, Decorator, Facade, Flyweight, and Proxy). The goal is to manage products, orders, and customers in the e-commerce system.

## E-commerce Platform

### 1. **Adapter Pattern**
#### Purpose
The Adapter pattern allows incompatible interfaces to work together, acting as a bridge between two incompatible interfaces.

#### Implementation

```java
// Target Interface
interface Product {
    void display();
}

// Adaptee
class ExternalProduct {
    public void show() {
        System.out.println("Showing product from external source...");
    }
}

// Adapter
class ExternalProductAdapter implements Product {
    private ExternalProduct externalProduct;

    public ExternalProductAdapter(ExternalProduct externalProduct) {
        this.externalProduct = externalProduct;
    }

    @Override
    public void display() {
        externalProduct.show();
    }
}

// Client code
public class AdapterPatternDemo {
    public static void main(String[] args) {
        ExternalProduct externalProduct = new ExternalProduct();
        Product product = new ExternalProductAdapter(externalProduct);
        product.display();
    }
}
```

### Explanation
- **When to Use**: Use the Adapter pattern when you need to integrate classes that cannot work together due to incompatible interfaces.

---

### 2. **Bridge Pattern**
#### Purpose
The Bridge pattern separates abstraction from implementation, allowing the two to evolve independently.

#### Implementation

```java
// Abstraction
abstract class ShoppingCart {
    protected PaymentProcessor paymentProcessor;

    protected ShoppingCart(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    abstract void checkout(double amount);
}

// Refined Abstraction
class StandardCart extends ShoppingCart {
    public StandardCart(PaymentProcessor paymentProcessor) {
        super(paymentProcessor);
    }

    @Override
    void checkout(double amount) {
        System.out.println("Checking out from Standard Cart...");
        paymentProcessor.processPayment(amount);
    }
}

// Implementor
interface PaymentProcessor {
    void processPayment(double amount);
}

// Concrete Implementor
class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using PayPal.");
    }
}

// Client code
public class BridgePatternDemo {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PayPalProcessor();
        ShoppingCart cart = new StandardCart(paymentProcessor);
        cart.checkout(100.00);
    }
}
```

### Explanation
- **When to Use**: Use the Bridge pattern when you want to decouple an abstraction from its implementation so that both can vary independently.

---

### 3. **Composite Pattern**
#### Purpose
The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions uniformly.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface ProductComponent {
    void showDetails();
}

// Leaf
class ProductLeaf implements ProductComponent {
    private String productName;

    public ProductLeaf(String productName) {
        this.productName = productName;
    }

    @Override
    public void showDetails() {
        System.out.println("Product: " + productName);
    }
}

// Composite
class ProductComposite implements ProductComponent {
    private List<ProductComponent> components = new ArrayList<>();

    public void add(ProductComponent component) {
        components.add(component);
    }

    @Override
    public void showDetails() {
        for (ProductComponent component : components) {
            component.showDetails();
        }
    }
}

// Client code
public class CompositePatternDemo {
    public static void main(String[] args) {
        ProductComponent product1 = new ProductLeaf("Laptop");
        ProductComponent product2 = new ProductLeaf("Smartphone");

        ProductComposite productGroup = new ProductComposite();
        productGroup.add(product1);
        productGroup.add(product2);

        productGroup.showDetails();
    }
}
```

### Explanation
- **When to Use**: Use the Composite pattern when you need to treat individual objects and compositions of objects uniformly.

---

### 4. **Decorator Pattern**
#### Purpose
The Decorator pattern allows behavior to be added to individual objects dynamically, without affecting the behavior of other objects from the same class.

#### Implementation

```java
// Component
interface Item {
    String getDescription();
    double cost();
}

// Concrete Component
class BasicItem implements Item {
    @Override
    public String getDescription() {
        return "Basic Item";
    }

    @Override
    public double cost() {
        return 10.00;
    }
}

// Decorator
abstract class ItemDecorator implements Item {
    protected Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public String getDescription() {
        return item.getDescription();
    }

    @Override
    public double cost() {
        return item.cost();
    }
}

// Concrete Decorator
class GiftWrapDecorator extends ItemDecorator {
    public GiftWrapDecorator(Item item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Gift Wrap";
    }

    @Override
    public double cost() {
        return super.cost() + 2.00; // Additional cost for gift wrap
    }
}

// Client code
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Item item = new BasicItem();
        System.out.println(item.getDescription() + " costs $" + item.cost());

        Item wrappedItem = new GiftWrapDecorator(item);
        System.out.println(wrappedItem.getDescription() + " costs $" + wrappedItem.cost());
    }
}
```

### Explanation
- **When to Use**: Use the Decorator pattern when you want to add responsibilities to individual objects dynamically and extend their behavior without altering their structure.

---

### 5. **Facade Pattern**
#### Purpose
The Facade pattern provides a simplified interface to a complex subsystem, making it easier to interact with the subsystem.

#### Implementation

```java
// Subsystem 1
class InventoryService {
    public void checkInventory(String productName) {
        System.out.println("Checking inventory for: " + productName);
    }
}

// Subsystem 2
class OrderService {
    public void createOrder(String productName) {
        System.out.println("Creating order for: " + productName);
    }
}

// Facade
class ECommerceFacade {
    private InventoryService inventoryService;
    private OrderService orderService;

    public ECommerceFacade() {
        this.inventoryService = new InventoryService();
        this.orderService = new OrderService();
    }

    public void placeOrder(String productName) {
        inventoryService.checkInventory(productName);
        orderService.createOrder(productName);
    }
}

// Client code
public class FacadePatternDemo {
    public static void main(String[] args) {
        ECommerceFacade eCommerceFacade = new ECommerceFacade();
        eCommerceFacade.placeOrder("Smartphone");
    }
}
```

### Explanation
- **When to Use**: Use the Facade pattern when you want to provide a simple interface to a complex system or set of interfaces.

---

### 6. **Flyweight Pattern**
#### Purpose
The Flyweight pattern is used to reduce the memory footprint by sharing common parts of state among multiple objects.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Product {
    void display();
}

// Concrete Flyweight
class ConcreteProduct implements Product {
    private String name;

    public ConcreteProduct(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("Product: " + name);
    }
}

// Flyweight Factory
class ProductFactory {
    private Map<String, Product> productMap = new HashMap<>();

    public Product getProduct(String name) {
        Product product = productMap.get(name);
        if (product == null) {
            product = new ConcreteProduct(name);
            productMap.put(name, product);
        }
        return product;
    }
}

// Client code
public class FlyweightPatternDemo {
    public static void main(String[] args) {
        ProductFactory productFactory = new ProductFactory();

        Product product1 = productFactory.getProduct("Laptop");
        Product product2 = productFactory.getProduct("Laptop"); // Reuses the existing instance

        product1.display();
        product2.display(); // Same instance reused
    }
}
```

### Explanation
- **When to Use**: Use the Flyweight pattern when you have a large number of similar objects and want to minimize memory usage by sharing common states.

---

### 7. **Proxy Pattern**
#### Purpose
The Proxy pattern provides a surrogate or placeholder for another object to control access to it.

#### Implementation

```java
// Subject Interface
interface UserAccount {
    void accessAccount();
}

// Real Subject
class RealUserAccount implements UserAccount {
    @Override
    public void accessAccount() {
        System.out.println("Accessing user account...");
    }
}

// Proxy
class ProxyUserAccount implements UserAccount {
    private RealUserAccount realAccount;

    public ProxyUserAccount() {
        this.realAccount = new RealUserAccount();
    }

    @Override
    public void accessAccount() {
        System.out.println("Checking permissions...");
        realAccount.accessAccount();
    }
}

// Client code
public class ProxyPatternDemo {
    public static void main(String[] args) {
        UserAccount userAccount = new ProxyUserAccount();
        userAccount.accessAccount();
    }
}
```

### Explanation
- **When to Use**: Use the Proxy pattern when you need to control access to an object, such as for lazy initialization, access control, or logging.

---

## Summary of When to Use Each Pattern in

E-commerce

- **Adapter Pattern**: When integrating third-party services or libraries that have incompatible interfaces (e.g., payment gateways).
- **Bridge Pattern**: When you need to decouple interfaces from their implementations, allowing for independent evolution of both (e.g., different payment methods).
- **Composite Pattern**: When managing a hierarchy of products or categories (e.g., a product category tree).
- **Decorator Pattern**: When you want to add functionalities dynamically to products (e.g., adding gift wrap, custom engraving).
- **Facade Pattern**: When providing a simple interface to a complex subsystem of services (e.g., placing an order involves inventory and payment processing).
- **Flyweight Pattern**: When managing a large number of similar objects efficiently (e.g., sharing product details across similar products).
- **Proxy Pattern**: When controlling access to an object, such as implementing lazy loading or permission checks (e.g., user account access control).

These patterns help structure and manage complex e-commerce systems effectively, enhancing code maintainability, scalability, and flexibility.