Here are detailed industry examples of the **Mediator Pattern** implemented in Java, focusing on specific scenarios in different domains.

### 1. **Chat Application**
In a chat application, a mediator can manage the communication between multiple users (participants) in a chat room.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // Message should not be received by the user sending it
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

// Colleague Class
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}

// Concrete Colleague
class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sending: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Client code
public class MediatorPatternChatApp {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.sendMessage("Hello, everyone!");
        bob.sendMessage("Hi, Alice!");
    }
}
```

### Explanation
- **Mediator**: The `ChatMediator` interface defines methods for sending messages and adding users.
- **Concrete Mediator**: The `ChatRoom` class implements the mediator interface and handles message sending and user management.
- **Colleague Classes**: The `User` abstract class and its concrete subclass `ChatUser` represent users in the chat system, which communicate through the mediator.

---

### 2. **Air Traffic Control System**
In an air traffic control system, the mediator can facilitate communication between various aircraft and the control tower.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface AirTrafficControl {
    void registerFlight(Flight flight);
    void requestLanding(Flight flight);
}

// Concrete Mediator
class ControlTower implements AirTrafficControl {
    private List<Flight> flights;

    public ControlTower() {
        flights = new ArrayList<>();
    }

    @Override
    public void registerFlight(Flight flight) {
        flights.add(flight);
    }

    @Override
    public void requestLanding(Flight flight) {
        System.out.println(flight.getFlightName() + " is requesting landing.");
        // Here, you can add logic to determine if the flight can land
        System.out.println(flight.getFlightName() + " cleared to land.");
    }
}

// Colleague Class
abstract class Flight {
    protected AirTrafficControl mediator;
    protected String flightName;

    public Flight(AirTrafficControl mediator, String flightName) {
        this.mediator = mediator;
        this.flightName = flightName;
    }

    public abstract void land();
    public String getFlightName() {
        return flightName;
    }
}

// Concrete Colleague
class CommercialFlight extends Flight {

    public CommercialFlight(AirTrafficControl mediator, String flightName) {
        super(mediator, flightName);
    }

    @Override
    public void land() {
        mediator.requestLanding(this);
    }
}

// Client code
public class MediatorPatternAirTrafficControlDemo {
    public static void main(String[] args) {
        AirTrafficControl controlTower = new ControlTower();

        Flight flight1 = new CommercialFlight(controlTower, "Flight 101");
        Flight flight2 = new CommercialFlight(controlTower, "Flight 202");

        controlTower.registerFlight(flight1);
        controlTower.registerFlight(flight2);

        flight1.land();
        flight2.land();
    }
}
```

### Explanation
- **Mediator**: The `AirTrafficControl` interface defines methods for registering flights and handling landing requests.
- **Concrete Mediator**: The `ControlTower` class implements the mediator interface, managing flight registrations and landing requests.
- **Colleague Classes**: The `Flight` abstract class and its concrete subclass `CommercialFlight` represent different types of flights.

---

### 3. **Online Shopping System**
In an online shopping system, the mediator can facilitate communication between different components such as the shopping cart, product catalog, and payment system.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface ShoppingMediator {
    void addToCart(Product product, Customer customer);
    void checkout(Customer customer);
}

// Concrete Mediator
class ShoppingCartMediator implements ShoppingMediator {
    private List<Product> cart = new ArrayList<>();

    @Override
    public void addToCart(Product product, Customer customer) {
        cart.add(product);
        System.out.println(customer.getName() + " added " + product.getName() + " to the cart.");
    }

    @Override
    public void checkout(Customer customer) {
        System.out.println(customer.getName() + " is checking out with items:");
        for (Product product : cart) {
            System.out.println(" - " + product.getName());
        }
    }
}

// Colleague Class
abstract class Customer {
    protected ShoppingMediator mediator;
    protected String name;

    public Customer(ShoppingMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void addProductToCart(Product product);
    public abstract void checkout();
    public String getName() {
        return name;
    }
}

// Concrete Colleague
class OnlineCustomer extends Customer {

    public OnlineCustomer(ShoppingMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void addProductToCart(Product product) {
        mediator.addToCart(product, this);
    }

    @Override
    public void checkout() {
        mediator.checkout(this);
    }
}

// Product class
class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Client code
public class MediatorPatternOnlineShoppingDemo {
    public static void main(String[] args) {
        ShoppingMediator mediator = new ShoppingCartMediator();

        Customer customer = new OnlineCustomer(mediator, "John Doe");
        Product product1 = new Product("Laptop");
        Product product2 = new Product("Smartphone");

        customer.addProductToCart(product1);
        customer.addProductToCart(product2);
        customer.checkout();
    }
}
```

### Explanation
- **Mediator**: The `ShoppingMediator` interface defines methods for adding products to the cart and checking out.
- **Concrete Mediator**: The `ShoppingCartMediator` class implements the mediator interface and manages the shopping cart and checkout process.
- **Colleague Classes**: The `Customer` abstract class and its concrete subclass `OnlineCustomer` represent customers in the shopping system.

---

### 4. **Employee Management System**
In an employee management system, a mediator can facilitate communication between different departments (like HR, IT, and Finance).

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface EmployeeMediator {
    void notifyDepartment(String message, Department department);
}

// Concrete Mediator
class CompanyMediator implements EmployeeMediator {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        departments.add(department);
    }

    @Override
    public void notifyDepartment(String message, Department department) {
        for (Department d : departments) {
            if (d != department) {
                d.receiveNotification(message);
            }
        }
    }
}

// Colleague Class
abstract class Department {
    protected EmployeeMediator mediator;

    public Department(EmployeeMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void sendNotification(String message);
    public abstract void receiveNotification(String message);
}

// Concrete Colleague
class HRDepartment extends Department {

    public HRDepartment(EmployeeMediator mediator) {
        super(mediator);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("HR Department: " + message);
        mediator.notifyDepartment(message, this);
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("HR Department received: " + message);
    }
}

class ITDepartment extends Department {

    public ITDepartment(EmployeeMediator mediator) {
        super(mediator);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("IT Department: " + message);
        mediator.notifyDepartment(message, this);
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("IT Department received: " + message);
    }
}

// Client code
public class MediatorPatternEmployeeManagementDemo {
    public static void main(String[] args) {
        CompanyMediator

 mediator = new CompanyMediator();

        Department hr = new HRDepartment(mediator);
        Department it = new ITDepartment(mediator);

        mediator.addDepartment(hr);
        mediator.addDepartment(it);

        hr.sendNotification("New hiring policies are in place.");
        it.sendNotification("System maintenance scheduled for this weekend.");
    }
}
```

### Explanation
- **Mediator**: The `EmployeeMediator` interface defines methods for notifying departments.
- **Concrete Mediator**: The `CompanyMediator` class implements the mediator interface, managing communication between departments.
- **Colleague Classes**: The `Department` abstract class and its concrete subclasses (`HRDepartment` and `ITDepartment`) represent different departments within the company.

---

These examples illustrate how the Mediator Pattern can effectively decouple components in different domains, simplifying communication and enhancing maintainability.