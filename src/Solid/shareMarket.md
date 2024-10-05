Creating a **Share Market Portal** involves various functionalities, such as user management, stock trading, order management, and notifications. Below, I'll provide a structured example that adheres to the SOLID principles.

### Example: Share Market Portal

This example includes the following components:
1. **User Management**: Managing users and their portfolios.
2. **Stock Management**: Managing stocks and their prices.
3. **Order Management**: Placing buy/sell orders.
4. **Notification Services**: Notifying users about order statuses and stock updates.

### Step 1: Define Domain Models

#### User Class

```java
class User {
    private String id;
    private String name;
    private double balance;

    public User(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
```

#### Stock Class

```java
class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```

### Step 2: Order Class

#### Order Class

```java
abstract class Order {
    protected User user;
    protected Stock stock;
    protected int quantity;

    public Order(User user, Stock stock, int quantity) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
    }

    public abstract void execute();
}
```

### Step 3: Concrete Order Classes

#### BuyOrder Class

```java
class BuyOrder extends Order {
    public BuyOrder(User user, Stock stock, int quantity) {
        super(user, stock, quantity);
    }

    @Override
    public void execute() {
        double totalCost = stock.getPrice() * quantity;
        if (user.getBalance() >= totalCost) {
            user.withdraw(totalCost);
            System.out.println("Buy order executed for " + quantity + " shares of " + stock.getSymbol());
        } else {
            throw new IllegalArgumentException("Insufficient balance for buy order.");
        }
    }
}
```

#### SellOrder Class

```java
class SellOrder extends Order {
    public SellOrder(User user, Stock stock, int quantity) {
        super(user, stock, quantity);
    }

    @Override
    public void execute() {
        double totalRevenue = stock.getPrice() * quantity;
        user.deposit(totalRevenue);
        System.out.println("Sell order executed for " + quantity + " shares of " + stock.getSymbol());
    }
}
```

### Step 4: OrderService Class

The `OrderService` class handles order execution and adheres to the Single Responsibility Principle (SRP).

```java
class OrderService {
    public void processOrder(Order order) {
        order.execute(); // Delegate executing to the specific order type
    }
}
```

### Step 5: NotificationService Interface and Implementations

To adhere to the Open/Closed Principle (OCP), we will create an interface for notifications.

```java
interface NotificationService {
    void notifyUser(User user, String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending Email Notification to " + user.getName() + ": " + message);
    }
}

class SMSNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending SMS Notification to " + user.getName() + ": " + message);
    }
}
```

### Step 6: TradingManager Class

The `TradingManager` class handles trading operations and notifications. It encapsulates the logic related to managing trades.

```java
class TradingManager {
    private OrderService orderService;
    private NotificationService notificationService;

    public TradingManager(OrderService orderService, NotificationService notificationService) {
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    public void executeTrade(User user, Stock stock, int quantity, String orderType) {
        Order order;

        if ("BUY".equalsIgnoreCase(orderType)) {
            order = new BuyOrder(user, stock, quantity);
        } else if ("SELL".equalsIgnoreCase(orderType)) {
            order = new SellOrder(user, stock, quantity);
        } else {
            throw new IllegalArgumentException("Unsupported order type");
        }

        orderService.processOrder(order);
        notificationService.notifyUser(user, "Your " + orderType + " order for " + quantity + " shares of " + stock.getSymbol() + " has been executed.");
    }
}
```

### Step 7: Main Application

Now, let’s put everything together in a main application to demonstrate the functionality.

```java
public class ShareMarketPortalApplication {
    public static void main(String[] args) {
        // Create users
        User alice = new User("1", "Alice", 10000.00);
        User bob = new User("2", "Bob", 5000.00);

        // Create stocks
        Stock appleStock = new Stock("AAPL", 150.00);
        Stock googleStock = new Stock("GOOGL", 2800.00);

        // Create services
        OrderService orderService = new OrderService();
        NotificationService emailNotificationService = new EmailNotificationService();
        NotificationService smsNotificationService = new SMSNotificationService();

        // Create trading managers
        TradingManager tradingManagerEmail = new TradingManager(orderService, emailNotificationService);
        TradingManager tradingManagerSMS = new TradingManager(orderService, smsNotificationService);

        // Simulate buying and selling stocks
        tradingManagerEmail.executeTrade(alice, appleStock, 20, "BUY");
        tradingManagerSMS.executeTrade(bob, googleStock, 2, "SELL");

        // Print remaining balances
        System.out.println("Alice's remaining balance: $" + alice.getBalance());
        System.out.println("Bob's remaining balance: $" + bob.getBalance());
    }
}
```

### Summary of SOLID Principles Applied

1. **Single Responsibility Principle (SRP)**:
    - `User`, `Stock`, `Order`, `BuyOrder`, `SellOrder`, `OrderService`, and `TradingManager` classes each have a single responsibility.
    - `NotificationService` implementations are focused on sending notifications.

2. **Open/Closed Principle (OCP)**:
    - The `Order` class can be extended to support new order types (e.g., limit orders) without modifying existing code.
    - The `NotificationService` interface allows for new notification methods to be added without changing existing services.

3. **Liskov Substitution Principle (LSP)**:
    - Any implementation of the `Order` class can be used interchangeably in the `OrderService` without affecting its behavior.

4. **Interface Segregation Principle (ISP)**:
    - The `NotificationService` interface is focused, ensuring that clients only implement the methods they need.

5. **Dependency Inversion Principle (DIP)**:
    - `TradingManager` depends on the `OrderService` and `NotificationService` abstractions, not on concrete implementations. This decoupling allows for easier testing and swapping of services.

### Conclusion

This complete example illustrates the application of all five SOLID principles in a share market portal. By following these principles, the system is modular, maintainable, and extensible. Each component is responsible for a specific function, and the design allows for easy adaptation as new requirements emerge, such as adding new order types, notifications, or additional features related to stock trading and user management.