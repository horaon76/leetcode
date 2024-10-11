package DesignPatterns.Creational.Factory;

interface Notification {
    void notifyUser();
}

public class NotificationExample {
}
Got it! Let’s implement the **Factory Design Pattern** using a different example. We can create a **Notification Service** where the factory creates different types of notifications like **Email**, **SMS**, or **Push Notification** depending on the requirement.

### Example: Notification Service Factory

#### Step 1: Create a `Notification` Interface

```java
public interface Notification {
    void notifyUser();
}
```

#### Step 2: Create Concrete Classes Implementing the `Notification` Interface

```java
// SMS Notification class
public class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification.");
    }
}

// Email Notification class
public class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email notification.");
    }
}

// Push Notification class
public class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a Push notification.");
    }
}
```

#### Step 3: Create the Notification Factory

The factory class decides which notification object to create based on the input.

```java
public class NotificationFactory {
    // Method that returns a Notification object based on the type provided
    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel: " + channel);
        }
    }
}
```

#### Step 4: Use the Factory to Get the Desired Notification Object

```java
public class FactoryPatternDemo {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();

        // Get SMS Notification
        Notification sms = notificationFactory.createNotification("SMS");
        sms.notifyUser();  // Output: Sending an SMS notification.

        // Get Email Notification
        Notification email = notificationFactory.createNotification("EMAIL");
        email.notifyUser();  // Output: Sending an Email notification.

        // Get Push Notification
        Notification push = notificationFactory.createNotification("PUSH");
        push.notifyUser();  // Output: Sending a Push notification.
    }
}
```

### Key Points:
- The `NotificationFactory` class contains a method `createNotification`, which takes a string as an argument (the channel) and returns the appropriate `Notification` object.
- Concrete classes like `SMSNotification`, `EmailNotification`, and `PushNotification` implement the `Notification` interface.
- The factory method `createNotification` is responsible for instantiating the right notification based on the provided input.

This implementation makes the creation process more flexible, allowing the client code to be unaware of the exact type of notification being created, thus promoting loose coupling and scalability.

# Details
The **Factory Design Pattern** is a **creational design pattern** that provides a flexible way of creating objects. Instead of the client needing to know the specifics of how an object is created or instantiated, the factory pattern handles this by abstracting the instantiation logic.

### What is the Factory Pattern?

The **Factory Pattern** defines an interface or method for creating objects, but **lets the subclasses decide which class to instantiate**. It promotes **loose coupling** by delegating the responsibility of object creation to a factory class, so the client code doesn’t directly instantiate the object.

### Key Concepts:
1. **Encapsulation of Object Creation Logic**: The pattern encapsulates the object creation logic in a factory class, keeping it separate from the client code.

2. **Loose Coupling**: The client code only interacts with an abstract interface (or a parent class) and does not need to know the exact class name or creation process.

3. **Single Responsibility Principle**: The factory class is responsible for creating objects, while the client class handles object usage, following the single responsibility principle.

### How is the Factory Pattern Useful?

1. **Decoupling Object Creation from Usage**:
    - Clients do not need to understand or handle the complexity of creating objects. This leads to **cleaner code** and less coupling between classes.
    - In the notification example above, the client doesn’t need to know how to create an `EmailNotification`, `SMSNotification`, or `PushNotification`. It simply asks the factory to get the object.

2. **Code Flexibility and Maintenance**:
    - Since the factory class controls object creation, adding new classes (e.g., new types of notifications) or modifying existing ones doesn’t affect client code. You can modify the factory class to support new functionality without touching other parts of the system.
    - For example, if you add a `SlackNotification` in the notification service, you can simply modify the factory class to return this type of notification without changing any client code.

3. **Avoids Code Duplication**:
    - Object creation code (like initializing variables, handling complex instantiation logic) is often repeated when creating instances. The factory pattern consolidates this logic into a single place, eliminating redundant code.

4. **Promotes Open/Closed Principle**:
    - The system can be extended with new product classes (types of objects) without modifying existing code, meaning the factory pattern makes the system **open for extension but closed for modification**.

5. **Centralizes Control Over Object Creation**:
    - In large-scale systems, it’s beneficial to have a single location to control how specific types of objects are created. This makes the system more manageable.

6. **Enables Complex Object Creation Logic**:
    - Factories can handle more complex object creation logic like fetching configuration from external sources, applying business rules, or selecting strategies for instantiation based on various conditions.

### Example Scenario:
Let’s say you are building a notification service in a scalable system. Each user may want different types of notifications (e.g., SMS, Email, Push Notification). The logic to create these notifications can be encapsulated within a factory. The client (system) doesn’t need to know what specific notification it will get:

- The system just asks the **factory** for a notification.
- The factory decides the appropriate notification type, based on input.
- If you need to add a new notification type, you only modify the factory, not the entire system.

Without the factory pattern, the client code might need to manage all of the object creation logic itself. This can lead to:
- **Tight coupling**: The client needs to know about every specific notification class.
- **Duplication**: If object creation logic changes, you’d have to update it in every part of the client code that creates notifications.
- **Complexity**: In larger systems, managing object creation in many different places can lead to a higher likelihood of errors.

### Factory Pattern vs Direct Instantiation

Consider the two approaches:

1. **Without Factory Pattern**:
   ```java
   // Without a factory, the client is directly responsible for instantiation
   Notification notification = new SMSNotification();
   notification.notifyUser();
   ```

    - The client knows about `SMSNotification`, `EmailNotification`, and every other type of notification.
    - If a new type of notification is added, all parts of the code that instantiate notifications must be updated.

2. **With Factory Pattern**:
   ```java
   // Using a factory, the client is decoupled from object creation
   NotificationFactory factory = new NotificationFactory();
   Notification notification = factory.createNotification("SMS");
   notification.notifyUser();
   ```

    - The client doesn’t need to know the specific notification types (`SMSNotification`, `EmailNotification`, etc.). It simply asks the factory for the required notification.
    - If new types of notifications are added, only the factory is updated, not the client code.

### When to Use the Factory Pattern?

- **When the exact types or dependencies of objects are not known until runtime**.
- **When you need to decouple object creation from its use** to keep your code maintainable and scalable.
- **When the creation process involves complex logic**, such as setting up dependencies, configuration, or business rules.
- **When you need to add new types of objects to the system without modifying the existing codebase**.

In summary, the **Factory Design Pattern** is useful when you want to abstract and centralize the object creation process, reduce coupling, make code more flexible and easier to maintain, and follow principles like open/closed and single responsibility.