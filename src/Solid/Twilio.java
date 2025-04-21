package Solid;

//  User Management: Managing users and their contact information.
// Messaging: Sending and receiving messages.
// Notification Services: Different methods to notify users.
// Message Types: Different types of messages (SMS, Email, etc.).

class User {
    private Integer id;
    private String name;
    private String email;
    private String mobileNumber;

    public User(Integer id, String name, String email, String mobileNumber){
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    //getter setter

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMobileNumber(){
        return this.name;
    }

    public void setMobileNumber(String name){
        this.name = name;
    }
    //others
}

//message
// If you want sender, recipient, and content to be part of the shared state across all message types, stick with the abstract class.
//If you want to define only the send behavior and allow classes to manage their own state and structure, use an interface.
abstract class Message{
    protected User sender;
    protected User recipient;
    protected String content;

    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public abstract void send();

}

//message Types

class SMSMessage extends Message{

    public SMSMessage(User sender, User recipient, String content) {
        super(sender, recipient, content);
    }

    @Override
    public void send() {
        // Logic to send SMS
        System.out.println("Sending SMS from " + sender.getName() +
                " to " + recipient.getMobileNumber() + ": " + content);
    }
}

class EmailMessage extends Message{

    public EmailMessage(User sender, User recipient, String content) {
        super(sender, recipient, content);
    }

    @Override
    public void send() {
        // Logic to send SMS
        System.out.println("Sending Email from " + sender.getName() +
                " to " + recipient.getMobileNumber() + ": " + content);
    }
}

//Message Service to send the message
class MessageService {
    public void sendMessage(Message message) {
        message.send(); // Delegate sending to the specific message type
    }
}

//
interface NotificationService {
    void notifyUser(User user, String message);
}

class SMSNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending SMS Notification to " + user.getMobileNumber() + ": " + message);
    }
}

class EmailNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String message) {
        System.out.println("Sending Email Notification to " + user.getMobileNumber() + ": " + message);
    }
}

//Manager Class

class CommunicationManager {
    private MessageService messageService;
    private NotificationService notificationService;

    public CommunicationManager(MessageService messageService, NotificationService notificationService) {
        this.messageService = messageService;
        this.notificationService = notificationService;
    }

    public void sendMessage(User sender, User recipient, String content, String messageType) {
        Message message;

        if ("SMS".equalsIgnoreCase(messageType)) {
            message = new SMSMessage(sender, recipient, content);
        } else if ("EMAIL".equalsIgnoreCase(messageType)) {
            message = new EmailMessage(sender, recipient, content);
        } else {
            throw new IllegalArgumentException("Unsupported message type");
        }

        messageService.sendMessage(message);
        notificationService.notifyUser(recipient, "You have a new message from " + sender.getName());
    }
}

public class Twilio {
    User alice = new User(1, "Alice", "1234567890", "alice@example.com");
    User bob = new User(2, "Bob", "0987654321", "bob@example.com");

    // Create services
    MessageService messageService = new MessageService();
    NotificationService smsNotificationService = new SMSNotificationService();
    NotificationService emailNotificationService = new EmailNotificationService();

    // Create communication managers
    CommunicationManager smsCommunicationManager = new CommunicationManager(messageService, smsNotificationService);
    CommunicationManager emailCommunicationManager = new CommunicationManager(messageService, emailNotificationService);

}
