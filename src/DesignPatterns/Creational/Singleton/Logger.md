Let’s explore another example of the **Singleton Pattern**, this time implementing a **Logger** class. This example will demonstrate how the Singleton pattern can effectively manage logging for an application, ensuring that there is only one instance of the logger throughout the application.

### Problem Example
In many applications, especially large ones, you often need a centralized logging mechanism to record messages, errors, and other significant events. The Singleton pattern ensures that there is only one instance of the Logger, which can be easily accessed throughout the application.

### Step 1: Define the **Logger** Class

The `Logger` class will handle logging messages to the console (or a file, in a more advanced implementation).

```java
import java.text.SimpleDateFormat;
import java.util.Date;

// Singleton class
public class Logger {
    private static Logger instance;
    private static final String LOG_FORMAT = "[%s] %s"; // Timestamp and Message

    // Private constructor
    private Logger() {}

    // Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); // Lazy initialization
        }
        return instance;
    }

    // Method to log an info message
    public void logInfo(String message) {
        System.out.println(String.format(LOG_FORMAT, getCurrentTimeStamp(), message));
    }

    // Method to log an error message
    public void logError(String message) {
        System.err.println(String.format(LOG_FORMAT, getCurrentTimeStamp(), message));
    }

    // Method to get the current timestamp
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
```

### Explanation:
1. **Private Constructor**: The constructor is private, preventing instantiation from other classes.
2. **Static Instance Variable**: `instance` holds the single instance of the `Logger`.
3. **Public Static Method**: `getInstance()` ensures lazy initialization and provides a global access point to the instance.
4. **Logging Methods**:
    - `logInfo(String message)`: Logs informational messages to the console.
    - `logError(String message)`: Logs error messages to the error stream.
5. **Timestamp Formatting**: The `getCurrentTimeStamp()` method formats the current timestamp to prepend to each log message.

### Step 2: Client Code Using the Singleton Logger

Here's how you can use the `Logger` class:

```java
public class LoggerDemo {
    public static void main(String[] args) {
        // Get the only instance of Logger
        Logger logger = Logger.getInstance();

        // Log messages
        logger.logInfo("Application is starting...");
        logger.logError("An error occurred while processing the request.");

        // Verify that the instance is the same
        Logger anotherLogger = Logger.getInstance();
        System.out.println("Are both logger instances equal? " + (logger == anotherLogger));
    }
}
```

### Output:
```
[2024-10-11 10:20:30] Application is starting...
[2024-10-11 10:20:30] An error occurred while processing the request.
Are both logger instances equal? true
```

### Explanation of Client Code:
1. **Get Logger Instance**: The client retrieves the single instance of the `Logger` using `getInstance()`.
2. **Log Info**: It calls `logInfo()` to log an informational message.
3. **Log Error**: It calls `logError()` to log an error message.
4. **Instance Comparison**: The client verifies that both references point to the same logger instance, confirming the Singleton behavior.

### Benefits of the Singleton Pattern in this Example:
- **Centralized Logging**: The `Logger` class acts as a centralized logging mechanism, preventing the need for multiple logger instances scattered throughout the application.
- **Consistency**: Since there’s only one instance of the logger, all log messages are consistent in format and management.
- **Lazy Initialization**: The logger is created only when needed, reducing memory usage until necessary.
- **Global Access**: The logger can be accessed from anywhere in the application, promoting ease of use.

### Thread-Safe Logger Implementation

If this logger is to be used in a multi-threaded environment, we can enhance it to be thread-safe. Here’s an example using synchronized methods:

```java
// Thread-safe Logger class
public class ThreadSafeLogger {
    private static ThreadSafeLogger instance;
    private static final String LOG_FORMAT = "[%s] %s"; // Timestamp and Message

    // Private constructor
    private ThreadSafeLogger() {}

    // Public method to provide access to the instance
    public static synchronized ThreadSafeLogger getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLogger(); // Lazy initialization
        }
        return instance;
    }

    // Method to log an info message
    public synchronized void logInfo(String message) {
        System.out.println(String.format(LOG_FORMAT, getCurrentTimeStamp(), message));
    }

    // Method to log an error message
    public synchronized void logError(String message) {
        System.err.println(String.format(LOG_FORMAT, getCurrentTimeStamp(), message));
    }

    // Method to get the current timestamp
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
```

### Conclusion

The **Singleton Pattern** is especially useful for managing shared resources, such as logging mechanisms. The `Logger` example illustrates how this pattern provides a centralized, consistent way to manage logging in an application. By implementing thread safety, the logger can be safely used in multi-threaded applications, ensuring that log messages remain coherent and organized, even when accessed from different threads.