In Java 17, the introduction of **records** and **sealed classes** brought significant enhancements to the language, making it easier to work with data and manage inheritance hierarchies. Below is an overview of each feature, along with examples.

### 1. Records

**What are Records?**
- Records are a special kind of class in Java introduced in Java 14 as a preview feature and made stable in Java 16. They are designed to be a quick and easy way to create data classes.
- A record automatically generates a constructor, accessors (getters), `equals()`, `hashCode()`, and `toString()` methods based on its fields.

**Benefits of Using Records:**
- Reduced boilerplate code: You don't need to write getter methods, `equals()`, `hashCode()`, and `toString()` manually.
- Immutability: Records are inherently immutable, meaning once created, their state cannot be changed.

**Example of a Record:**

```java
public record Person(String name, int age) {
    // You can also add methods if needed
    public String greet() {
        return "Hello, my name is " + name + " and I am " + age + " years old.";
    }
}

// Using the record
public class RecordExample {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);
        System.out.println(person); // Output: Person[name=Alice, age=30]
        System.out.println(person.greet()); // Output: Hello, my name is Alice and I am 30 years old.
    }
}
```

### 2. Sealed Classes

**What are Sealed Classes?**
- Sealed classes allow you to control which classes can extend or implement them. This is useful for defining a closed hierarchy of classes.
- By using sealed classes, you can enforce constraints on how a class can be extended, improving type safety and making the code easier to maintain.

**Benefits of Using Sealed Classes:**
- Control over class hierarchies: You can specify which subclasses are permitted.
- Improved maintainability: Reduces the risk of unintended subclasses, making the code easier to reason about.

**Example of Sealed Classes:**

```java
// Sealed class
public sealed class Shape permits Circle, Rectangle {
}

// Subclass 1
public final class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

// Subclass 2
public final class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}

// Using sealed classes
public class SealedClassExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println("Circle Area: " + circle.area()); // Output: Circle Area: 78.53981633974483
        System.out.println("Rectangle Area: " + rectangle.area()); // Output: Rectangle Area: 24.0
    }
}
```

### Key Points

- **Records**:
    - Simple data carriers with automatic generation of methods.
    - Immutable and concise, reducing boilerplate code.

- **Sealed Classes**:
    - Allow you to control which classes can inherit from a given class.
    - Provide a clearer and more maintainable class hierarchy.

### When to Use Records and Sealed Classes

- **Use Records** when you need simple data containers that don’t require mutable state or complex behavior. They are ideal for DTOs (Data Transfer Objects), configuration data, or any case where you want a simple representation of data.

- **Use Sealed Classes** when you want to enforce specific inheritance constraints. This is particularly useful in scenarios like defining a restricted set of operations (like shapes in a graphics application) or when designing a domain model with a closed set of types.

### Conclusion

Both records and sealed classes enhance Java's capabilities for creating cleaner and more maintainable code, especially in the context of data handling and class hierarchies. These features are particularly beneficial in modern Java applications that require clear and structured data representations.

# More 
Sure! Here are examples of **records** and **sealed classes** related to an e-commerce application, illustrating how these features can be used effectively.

### E-commerce Example Using Records

Let's define a simple **record** for representing products in an e-commerce application.

#### Product Record

```java
public record Product(String id, String name, double price, int quantity) {
    // You can add custom methods if needed
    public double totalPrice() {
        return price * quantity;
    }
}

// Using the Product record
public class EcommerceRecordExample {
    public static void main(String[] args) {
        Product product = new Product("P123", "Laptop", 799.99, 2);
        
        System.out.println(product); // Output: Product[id=P123, name=Laptop, price=799.99, quantity=2]
        System.out.println("Total Price: $" + product.totalPrice()); // Output: Total Price: $1599.98
    }
}
```

### E-commerce Example Using Sealed Classes

Now, let's define a **sealed class** hierarchy for various types of orders in our e-commerce system.

#### Sealed Class Hierarchy

```java
// Sealed class for Order
public sealed class Order permits OnlineOrder, InStoreOrder {
    private final String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public abstract double calculateTotal(); // Abstract method to calculate total
}

// Subclass for Online Orders
public final class OnlineOrder extends Order {
    private final double shippingCost;
    private final double orderValue;

    public OnlineOrder(String orderId, double shippingCost, double orderValue) {
        super(orderId);
        this.shippingCost = shippingCost;
        this.orderValue = orderValue;
    }

    @Override
    public double calculateTotal() {
        return orderValue + shippingCost; // Total = Order value + Shipping cost
    }
}

// Subclass for In-Store Orders
public final class InStoreOrder extends Order {
    private final double discount;
    private final double orderValue;

    public InStoreOrder(String orderId, double discount, double orderValue) {
        super(orderId);
        this.discount = discount;
        this.orderValue = orderValue;
    }

    @Override
    public double calculateTotal() {
        return orderValue - discount; // Total = Order value - Discount
    }
}

// Using the sealed class hierarchy
public class EcommerceSealedExample {
    public static void main(String[] args) {
        Order onlineOrder = new OnlineOrder("O1001", 15.00, 200.00);
        Order inStoreOrder = new InStoreOrder("O1002", 20.00, 150.00);

        System.out.println("Online Order ID: " + onlineOrder.getOrderId());
        System.out.println("Total for Online Order: $" + onlineOrder.calculateTotal()); // Output: Total for Online Order: $215.0
        
        System.out.println("In-Store Order ID: " + inStoreOrder.getOrderId());
        System.out.println("Total for In-Store Order: $" + inStoreOrder.calculateTotal()); // Output: Total for In-Store Order: $130.0
    }
}
```

### Key Points of the E-commerce Examples

- **Product Record**:
    - The `Product` record is a simple data structure that holds essential product information such as `id`, `name`, `price`, and `quantity`.
    - It includes a method `totalPrice()` to compute the total price based on the quantity.

- **Order Sealed Class**:
    - The `Order` class is sealed, permitting only `OnlineOrder` and `InStoreOrder` as its subclasses. This ensures that the order types are well-defined and restricts any unintended subclassing.
    - Each subclass implements the `calculateTotal()` method differently based on how the total cost is determined, with `OnlineOrder` factoring in shipping costs and `InStoreOrder` applying discounts.

### When to Use Records and Sealed Classes in E-commerce

- **Use Records** when you need to represent simple data structures like `Product`, where the focus is on the data rather than behavior.

- **Use Sealed Classes** for modeling a limited set of behaviors in a controlled manner, such as different types of orders. This ensures clarity in your codebase, making it easier to understand and maintain the relationships and behaviors of the different classes.

These examples demonstrate how records and sealed classes can help create a structured and maintainable design in an e-commerce application, ensuring clarity and consistency in the data and behavior of entities.