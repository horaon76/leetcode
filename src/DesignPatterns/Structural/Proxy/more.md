Here are several **Proxy Pattern** examples with detailed Java implementations across different industries, showcasing various use cases like access control, lazy loading, and remote service handling.

### Example 1: **E-commerce System (Image Proxy)**

#### Scenario
In an e-commerce application, product images can be large and consume significant resources. Using a proxy allows lazy loading of images, loading them only when needed.

#### Implementation

```java
// Subject Interface
interface ProductImage {
    void display();
}

// Real Subject
class RealProductImage implements ProductImage {
    private String imagePath;

    public RealProductImage(String imagePath) {
        this.imagePath = imagePath;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image from " + imagePath);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + imagePath);
    }
}

// Proxy
class ProxyProductImage implements ProductImage {
    private RealProductImage realImage;
    private String imagePath;

    public ProxyProductImage(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealProductImage(imagePath);
        }
        realImage.display();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ProductImage image1 = new ProxyProductImage("product1.jpg");
        ProductImage image2 = new ProxyProductImage("product2.jpg");

        // Image is loaded only when displayed
        image1.display(); // Loads the image
        image1.display(); // Does not load again
        image2.display(); // Loads the image
    }
}
```

### Output
```
Loading image from product1.jpg
Displaying image: product1.jpg
Displaying image: product1.jpg
Loading image from product2.jpg
Displaying image: product2.jpg
```

### Explanation
- **Subject**: `ProductImage` interface defines the method for displaying images.
- **Real Subject**: `RealProductImage` handles loading and displaying the actual image.
- **Proxy**: `ProxyProductImage` defers loading the image until the `display()` method is called.
- **Client Code**: The client interacts with the proxy, which ensures efficient resource usage by loading images only when necessary.

---

### Example 2: **Banking System (Access Control)**

#### Scenario
In a banking application, certain operations like withdrawal should be restricted based on user roles. The proxy can enforce access control.

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
- **Subject**: `BankAccount` interface defines the operations.
- **Real Subject**: `RealBankAccount` implements actual deposit and withdrawal logic.
- **Proxy**: `ProxyBankAccount` controls access to the withdrawal operation based on user roles.
- **Client Code**: The client uses the proxy to perform banking operations while enforcing access control.

---

### Example 3: **Remote Service (Weather Service)**

#### Scenario
In a weather application, fetching weather data from a remote server can be slow and costly. A remote proxy can manage the requests and cache results to optimize performance.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Subject Interface
interface WeatherService {
    String getWeather(String city);
}

// Real Subject
class RealWeatherService implements WeatherService {
    @Override
    public String getWeather(String city) {
        // Simulate network delay
        try {
            Thread.sleep(2000); // Simulate delay in fetching weather data
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Weather in " + city + ": Sunny";
    }
}

// Proxy
class WeatherServiceProxy implements WeatherService {
    private RealWeatherService realService;
    private Map<String, String> cache;

    public WeatherServiceProxy() {
        this.realService = new RealWeatherService();
        this.cache = new HashMap<>();
    }

    @Override
    public String getWeather(String city) {
        if (cache.containsKey(city)) {
            System.out.println("Fetching from cache...");
            return cache.get(city);
        }
        String weather = realService.getWeather(city);
        cache.put(city, weather);
        return weather;
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherServiceProxy();

        // Fetching weather for the first time
        System.out.println(weatherService.getWeather("New York"));
        // Fetching weather again (from cache)
        System.out.println(weatherService.getWeather("New York"));
        // Fetching weather for a different city
        System.out.println(weatherService.getWeather("Los Angeles"));
    }
}
```

### Output
```
Weather in New York: Sunny
Fetching from cache...
Weather in New York: Sunny
Weather in Los Angeles: Sunny
```

### Explanation
- **Subject**: `WeatherService` interface defines the method to fetch weather data.
- **Real Subject**: `RealWeatherService` simulates fetching weather data with a delay.
- **Proxy**: `WeatherServiceProxy` caches results to avoid repeated requests for the same city.
- **Client Code**: The client interacts with the proxy, which manages caching and network calls.

---

### Example 4: **File System (File Access)**

#### Scenario
In a file management system, access to certain files might require authentication. A proxy can enforce authentication checks before allowing access.

#### Implementation

```java
// Subject Interface
interface File {
    void read();
}

// Real Subject
class RealFile implements File {
    private String fileName;

    public RealFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void read() {
        System.out.println("Reading file: " + fileName);
    }
}

// Proxy
class ProxyFile implements File {
    private RealFile realFile;
    private String userRole;

    public ProxyFile(String fileName, String userRole) {
        this.realFile = new RealFile(fileName);
        this.userRole = userRole;
    }

    @Override
    public void read() {
        if ("admin".equals(userRole)) {
            realFile.read();
        } else {
            System.out.println("Access denied: Only admin can read this file.");
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        File adminFile = new ProxyFile("confidential.txt", "admin");
        File userFile = new ProxyFile("confidential.txt", "user");

        adminFile.read(); // Allowed
        userFile.read(); // Denied
    }
}
```

### Output
```
Reading file: confidential.txt
Access denied: Only admin can read this file.
```

### Explanation
- **Subject**: `File` interface defines the method for reading files.
- **Real Subject**: `RealFile` implements the actual file reading logic.
- **Proxy**: `ProxyFile` checks the user role before allowing file access.
- **Client Code**: The client uses the proxy to perform file operations while enforcing access control.

---

### Summary
The **Proxy Pattern** can be applied in various industries, such as e-commerce (image loading), banking (access control), weather applications (remote service handling), and file management systems (file access). Each implementation showcases how proxies can enhance functionality, improve performance, and enforce access control, making them valuable in real-world applications.