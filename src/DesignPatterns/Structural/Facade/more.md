Here are some industry-related examples of the **Facade Pattern** implemented in Java. Each example focuses on a different domain, showcasing the pattern's versatility.

### Example 1: **Online Food Delivery System**

#### Scenario
In an online food delivery system, various components need to work together for order placement, payment processing, and order delivery tracking. A facade can simplify this interaction.

#### Implementation

```java
// Subsystem 1: Order Service
class OrderService {
    public void createOrder(String foodItem) {
        System.out.println("Order created for: " + foodItem);
    }
}

// Subsystem 2: Payment Service
class PaymentService {
    public void processPayment(double amount) {
        System.out.println("Payment of $" + amount + " processed.");
    }
}

// Subsystem 3: Delivery Service
class DeliveryService {
    public void scheduleDelivery(String foodItem) {
        System.out.println("Delivery scheduled for: " + foodItem);
    }
}

// Facade
class FoodDeliveryFacade {
    private OrderService orderService;
    private PaymentService paymentService;
    private DeliveryService deliveryService;

    public FoodDeliveryFacade() {
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
        this.deliveryService = new DeliveryService();
    }

    public void placeOrder(String foodItem, double amount) {
        orderService.createOrder(foodItem);
        paymentService.processPayment(amount);
        deliveryService.scheduleDelivery(foodItem);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        FoodDeliveryFacade foodDeliveryFacade = new FoodDeliveryFacade();
        foodDeliveryFacade.placeOrder("Pizza", 15.99);
    }
}
```

### Output
```
Order created for: Pizza
Payment of $15.99 processed.
Delivery scheduled for: Pizza
```

### Explanation
- **Subsystem Classes**: `OrderService`, `PaymentService`, and `DeliveryService` handle the different functionalities in the food delivery process.
- **Facade Class**: `FoodDeliveryFacade` simplifies the order placement process by encapsulating the calls to the various services.
- **Client Code**: The client can place an order using the facade without needing to manage each service individually.

---

### Example 2: **E-Commerce Shopping Cart**

#### Scenario
In an e-commerce application, a shopping cart system requires interaction with product services, inventory checks, and checkout processes. The facade can simplify these interactions.

#### Implementation

```java
// Subsystem 1: Product Service
class ProductService {
    public void displayProduct(String productName) {
        System.out.println("Displaying product: " + productName);
    }
}

// Subsystem 2: Inventory Service
class InventoryService {
    public void checkAvailability(String productName) {
        System.out.println("Checking availability for: " + productName);
    }
}

// Subsystem 3: Checkout Service
class CheckoutService {
    public void processCheckout(String productName, double amount) {
        System.out.println("Processing checkout for: " + productName + " Amount: $" + amount);
    }
}

// Facade
class ShoppingCartFacade {
    private ProductService productService;
    private InventoryService inventoryService;
    private CheckoutService checkoutService;

    public ShoppingCartFacade() {
        this.productService = new ProductService();
        this.inventoryService = new InventoryService();
        this.checkoutService = new CheckoutService();
    }

    public void purchaseProduct(String productName, double amount) {
        productService.displayProduct(productName);
        inventoryService.checkAvailability(productName);
        checkoutService.processCheckout(productName, amount);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ShoppingCartFacade shoppingCart = new ShoppingCartFacade();
        shoppingCart.purchaseProduct("Laptop", 999.99);
    }
}
```

### Output
```
Displaying product: Laptop
Checking availability for: Laptop
Processing checkout for: Laptop Amount: $999.99
```

### Explanation
- **Subsystem Classes**: `ProductService`, `InventoryService`, and `CheckoutService` manage the functionalities involved in purchasing a product.
- **Facade Class**: `ShoppingCartFacade` provides a simplified interface for purchasing a product.
- **Client Code**: The client uses the facade to perform the purchase without dealing with the complexities of each subsystem.

---

### Example 3: **Library Management System**

#### Scenario
In a library management system, different functionalities such as book searching, user registration, and book borrowing can be encapsulated using a facade.

#### Implementation

```java
// Subsystem 1: Book Service
class BookService {
    public void findBook(String title) {
        System.out.println("Finding book: " + title);
    }
}

// Subsystem 2: User Service
class UserService {
    public void registerUser(String userName) {
        System.out.println("Registering user: " + userName);
    }
}

// Subsystem 3: Borrow Service
class BorrowService {
    public void borrowBook(String userName, String title) {
        System.out.println(userName + " borrowed the book: " + title);
    }
}

// Facade
class LibraryFacade {
    private BookService bookService;
    private UserService userService;
    private BorrowService borrowService;

    public LibraryFacade() {
        this.bookService = new BookService();
        this.userService = new UserService();
        this.borrowService = new BorrowService();
    }

    public void borrowBook(String userName, String title) {
        userService.registerUser(userName);
        bookService.findBook(title);
        borrowService.borrowBook(userName, title);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacade();
        libraryFacade.borrowBook("Alice", "1984");
    }
}
```

### Output
```
Registering user: Alice
Finding book: 1984
Alice borrowed the book: 1984
```

### Explanation
- **Subsystem Classes**: `BookService`, `UserService`, and `BorrowService` represent the different functionalities in the library system.
- **Facade Class**: `LibraryFacade` provides a simplified interface for borrowing a book, coordinating calls to the necessary services.
- **Client Code**: The client can easily borrow a book using the facade without managing the complexities of user registration and book searching.

---

### Summary
These examples illustrate how the **Facade Pattern** can be applied in various industry scenarios, such as online food delivery, e-commerce shopping carts, and library management systems. By providing a simplified interface to complex subsystems, the facade pattern improves code maintainability and usability, allowing clients to interact with the system easily.