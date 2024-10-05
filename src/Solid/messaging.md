Let’s create a complete example of a **Chat-Based System** that demonstrates the SOLID principles. In this example, we’ll cover the components of a chat application, including user management, message handling, and notification services.

### Example: Chat-Based System

#### Step 1: Define the Domain Models

We’ll start with the core models: `User`, `Message`, and `ChatRoom`.

```java
// User class
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

// Message class
class Message {
    private User sender;
    private String content;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}
```

#### Step 2: ChatRoom Class

The `ChatRoom` class is responsible for managing users and messages. It follows the Single Responsibility Principle (SRP).

```java
import java.util.ArrayList;
import java.util.List;

class ChatRoom {
    private List<User> users;
    private List<Message> messages;

    public ChatRoom() {
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void sendMessage(Message message) {
        messages.add(message);
        // Notify users about the new message
        // This can be a separate notification service
    }

    public List<Message> getMessages() {
        return messages;
    }
}
```

#### Step 3: NotificationService Interface and Implementations

To adhere to the Open/Closed Principle (OCP), we will create an interface for notifications.

```java
interface NotificationService {
    void notifyUser(User user, Message message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, Message message) {
        System.out.println("Sending email to " + user.getUsername() + ": " + message.getContent());
    }
}

class SMSNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, Message message) {
        System.out.println("Sending SMS to " + user.getUsername() + ": " + message.getContent());
    }
}
```

#### Step 4: MessageHandler Class

The `MessageHandler` will handle sending messages and notifying users. It encapsulates the logic related to message processing.

```java
class MessageHandler {
    private ChatRoom chatRoom;
    private NotificationService notificationService;

    public MessageHandler(ChatRoom chatRoom, NotificationService notificationService) {
        this.chatRoom = chatRoom;
        this.notificationService = notificationService;
    }

    public void handleMessage(User user, String content) {
        Message message = new Message(user, content);
        chatRoom.sendMessage(message);

        // Notify all users about the new message
        for (User recipient : chatRoom.getUsers()) {
            if (!recipient.equals(user)) { // Don't notify the sender
                notificationService.notifyUser(recipient, message);
            }
        }
    }
}
```

#### Step 5: Main Application

Now, let’s put everything together in a main application to demonstrate the functionality.

```java
public class ChatApplication {
    public static void main(String[] args) {
        // Create chat room
        ChatRoom chatRoom = new ChatRoom();

        // Create users
        User alice = new User("Alice");
        User bob = new User("Bob");

        // Add users to chat room
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);

        // Choose a notification service
        NotificationService notificationService = new EmailNotificationService(); // or new SMSNotificationService();

        // Create message handler
        MessageHandler messageHandler = new MessageHandler(chatRoom, notificationService);

        // Simulate sending messages
        messageHandler.handleMessage(alice, "Hello, Bob!");
        messageHandler.handleMessage(bob, "Hi, Alice! How are you?");
    }
}
```

### Summary of SOLID Principles Applied

1. **Single Responsibility Principle (SRP)**:
    - `ChatRoom` manages users and messages.
    - `MessageHandler` is responsible for processing messages and notifying users.
    - Each class has a single responsibility.

2. **Open/Closed Principle (OCP)**:
    - The `NotificationService` interface allows for new notification methods to be added (like push notifications) without modifying existing code.

3. **Liskov Substitution Principle (LSP)**:
    - Any implementation of the `NotificationService` can be used interchangeably without affecting the behavior of the `MessageHandler`. For example, you can switch from `EmailNotificationService` to `SMSNotificationService` seamlessly.

4. **Interface Segregation Principle (ISP)**:
    - The `NotificationService` interface is focused, ensuring that clients only implement the methods they need. For instance, if we later create a `PushNotificationService`, it will only implement the notification method without being burdened with unrelated methods.

5. **Dependency Inversion Principle (DIP)**:
    - `MessageHandler` depends on the `NotificationService` abstraction, not on concrete implementations. This decoupling allows for easier testing and swapping of notification services.

### Conclusion

This complete example illustrates the application of all five SOLID principles in a chat-based system. By following these principles, the system is modular, maintainable, and extensible. Each component is responsible for a specific function, and the design allows for easy adaptation as new requirements emerge, such as adding different types of notifications or additional features to the chat functionality.