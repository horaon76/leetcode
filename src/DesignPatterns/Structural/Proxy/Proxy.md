The **Proxy Pattern** is a structural design pattern that provides a surrogate or placeholder for another object to control access to it. It allows one object to control the behavior of another by serving as an intermediary. The Proxy Pattern can be used to add an additional layer of functionality, such as lazy initialization, access control, logging, or caching.

### Key Benefits
- **Control Access**: Manage and control access to the real subject.
- **Lazy Initialization**: Load resources only when needed.
- **Additional Functionality**: Enhance or modify the behavior of the real subject without changing its interface.

### Types of Proxy
1. **Virtual Proxy**: Delays the creation and initialization of an object until it is needed.
2. **Remote Proxy**: Represents an object that is located in a different address space, typically used in network communication.
3. **Protection Proxy**: Controls access to the object based on permissions.

### Example 1: **Image Loading in a Graphic Application**

#### Scenario
In a graphic application, large images can consume a lot of memory and take time to load. A virtual proxy can be used to delay the loading of images until they are actually needed.

#### Implementation

```java
// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Image is loaded only when displayed
        image1.display(); // Loads the image
        image1.display(); // Does not load again
        image2.display(); // Loads the image
    }
}
```

### Output
```
Loading photo1.jpg
Displaying photo1.jpg
Displaying photo1.jpg
Loading photo2.jpg
Displaying photo2.jpg
```

### Explanation
- **Subject**: `Image` interface defines the common methods.
- **Real Subject**: `RealImage` loads the image from disk and displays it.
- **Proxy**: `ProxyImage` delays the loading of the real image until it is needed.
- **Client Code**: The client uses the proxy to interact with the image, which loads only when displayed.

---

### Example 2: **Access Control in a Banking System**

#### Scenario
In a banking system, certain operations might require specific permissions. A protection proxy can be used to control access to sensitive methods based on user roles.

#### Implementation

```java
// Subject Interface
interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
}

// Real Subject
class RealBankAccount implements BankAccount {
    private double balance;

    public RealBankAccount() {
        this.balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", Balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal of: " + amount);
        }
    }
}

// Proxy
class ProxyBankAccount implements BankAccount {
    private RealBankAccount realAccount;
    private String userRole;

    public ProxyBankAccount(String userRole) {
        this.realAccount = new RealBankAccount();
        this.userRole = userRole;
    }

    @Override
    public void deposit(double amount) {
        realAccount.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        if ("admin".equals(userRole)) {
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access denied: Only admin can withdraw funds.");
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        BankAccount adminAccount = new ProxyBankAccount("admin");
        BankAccount userAccount = new ProxyBankAccount("user");

        adminAccount.deposit(100);
        adminAccount.withdraw(50); // Allowed

        userAccount.deposit(200);
        userAccount.withdraw(50); // Denied
    }
}
```

### Output
```
Deposited: 100.0, Balance: 100.0
Withdrew: 50.0, Balance: 50.0
Deposited: 200.0, Balance: 200.0
Access denied: Only admin can withdraw funds.
```

### Explanation
- **Subject**: `BankAccount` interface defines common methods.
- **Real Subject**: `RealBankAccount` performs actual operations like deposit and withdrawal.
- **Proxy**: `ProxyBankAccount` controls access based on user roles.
- **Client Code**: The client uses the proxy to access bank operations, enforcing access control.

---

### Example 3: **Remote Service Access**

#### Scenario
In a distributed application, a remote service might need to be accessed. A remote proxy can be used to handle communication with the remote service.

#### Implementation

```java
// Subject Interface
interface RemoteService {
    void requestData();
}

// Real Subject
class RealRemoteService implements RemoteService {
    @Override
    public void requestData() {
        System.out.println("Fetching data from remote service...");
    }
}

// Proxy
class RemoteServiceProxy implements RemoteService {
    private RealRemoteService realService;

    public RemoteServiceProxy() {
        this.realService = new RealRemoteService();
    }

    @Override
    public void requestData() {
        System.out.println("Proxy: Preparing to fetch data...");
        realService.requestData();
        System.out.println("Proxy: Data fetched successfully.");
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        RemoteService service = new RemoteServiceProxy();
        service.requestData();
    }
}
```

### Output
```
Proxy: Preparing to fetch data...
Fetching data from remote service...
Proxy: Data fetched successfully.
```

### Explanation
- **Subject**: `RemoteService` interface defines common methods for service interaction.
- **Real Subject**: `RealRemoteService` performs the actual data fetching.
- **Proxy**: `RemoteServiceProxy` acts as an intermediary, adding preparation and confirmation steps around the remote service call.
- **Client Code**: The client interacts with the proxy, which manages access to the real service.

---

### Summary
The **Proxy Pattern** is versatile and can be used in various scenarios, such as lazy loading (image loading), access control (banking system), and remote service access (distributed applications). By serving as an intermediary, proxies can manage interactions, enforce access rules, and optimize resource usage, making them an essential design pattern in software development.