Here are some industry-related examples of the **Decorator Pattern** implemented in Java:

### 1. **Notification System**

#### Scenario
In a notification system, you may have various types of notifications (like Email, SMS) that can be enhanced with additional features like logging, encryption, or delivery confirmation.

#### Implementation

```java
// Component
interface Notification {
    void send(String message);
}

// Concrete Component
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending email with message: " + message);
    }
}

// Concrete Component
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}

// Decorator
abstract class NotificationDecorator implements Notification {
    protected Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void send(String message) {
        notification.send(message);
    }
}

// Concrete Decorator for Logging
class LoggingDecorator extends NotificationDecorator {
    public LoggingDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        System.out.println("Logging message: " + message);
        super.send(message);
    }
}

// Concrete Decorator for Encryption
class EncryptionDecorator extends NotificationDecorator {
    public EncryptionDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        String encryptedMessage = "Encrypted[" + message + "]";
        super.send(encryptedMessage);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Notification emailNotification = new EmailNotification();
        Notification smsNotification = new SMSNotification();

        Notification loggingEmail = new LoggingDecorator(emailNotification);
        Notification encryptedSMS = new EncryptionDecorator(smsNotification);

        loggingEmail.send("Hello via Email!");
        encryptedSMS.send("Hello via SMS!");
    }
}
```

### Output
```
Logging message: Hello via Email!
Sending email with message: Hello via Email!
Sending SMS with message: Encrypted[Hello via SMS!]
```

### Explanation
1. **Notification**: This is the component interface that defines the `send` method.
2. **EmailNotification** and **SMSNotification**: These are concrete components that implement the `Notification` interface.
3. **NotificationDecorator**: This abstract class implements the `Notification` interface and holds a reference to a `Notification` object.
4. **LoggingDecorator**: This concrete decorator adds logging functionality.
5. **EncryptionDecorator**: This concrete decorator adds encryption functionality.
6. **Main**: The client code creates notifications and applies decorators to enhance their functionalities.

---

### 2. **Text Processing Application**

#### Scenario
In a text processing application, you can have a base text that can be modified with additional features such as uppercasing, trimming, or adding borders.

#### Implementation

```java
// Component
interface Text {
    String getContent();
}

// Concrete Component
class SimpleText implements Text {
    private String content;

    public SimpleText(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}

// Decorator
abstract class TextDecorator implements Text {
    protected Text text;

    public TextDecorator(Text text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text.getContent();
    }
}

// Concrete Decorator for Uppercase
class UppercaseDecorator extends TextDecorator {
    public UppercaseDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return text.getContent().toUpperCase();
    }
}

// Concrete Decorator for Trimming
class TrimDecorator extends TextDecorator {
    public TrimDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return text.getContent().trim();
    }
}

// Concrete Decorator for Adding Borders
class BorderDecorator extends TextDecorator {
    public BorderDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "**********\n" + text.getContent() + "\n**********";
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Text text = new SimpleText("  Hello, Decorator Pattern!  ");

        // Apply uppercasing and trimming
        Text uppercasedText = new UppercaseDecorator(new TrimDecorator(text));
        System.out.println(uppercasedText.getContent());

        // Add borders
        Text borderedText = new BorderDecorator(uppercasedText);
        System.out.println(borderedText.getContent());
    }
}
```

### Output
```
HELLO, DECORATOR PATTERN!
**********
HELLO, DECORATOR PATTERN!
**********
```

### Explanation
1. **Text**: This is the component interface for text processing.
2. **SimpleText**: This concrete component represents a basic text.
3. **TextDecorator**: This abstract class implements the `Text` interface and holds a reference to a `Text` object.
4. **UppercaseDecorator**, **TrimDecorator**, and **BorderDecorator**: These concrete decorators enhance the text with specific functionalities.
5. **Main**: The client code creates a simple text, applies decorators, and displays the modified content.

---

### 3. **Pizza Ordering System**

#### Scenario
In a pizza ordering system, you can have a base pizza and add various toppings (like cheese, olives, peppers) as decorators to enhance the pizza.

#### Implementation

```java
// Component
interface Pizza {
    String getDescription();
    double cost();
}

// Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double cost() {
        return 5.00; // Base price for a plain pizza
    }
}

// Decorator
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double cost() {
        return pizza.cost();
    }
}

// Concrete Decorator for Cheese
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.50; // Add the cost of extra cheese
    }
}

// Concrete Decorator for Olives
class OlivesDecorator extends PizzaDecorator {
    public OlivesDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.75; // Add the cost of olives
    }
}

// Concrete Decorator for Peppers
class PeppersDecorator extends PizzaDecorator {
    public PeppersDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Peppers";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.50; // Add the cost of peppers
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        System.out.println(pizza.getDescription() + " $" + pizza.cost());

        // Adding cheese
        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.getDescription() + " $" + pizza.cost());

        // Adding olives
        pizza = new OlivesDecorator(pizza);
        System.out.println(pizza.getDescription() + " $" + pizza.cost());

        // Adding peppers
        pizza = new PeppersDecorator(pizza);
        System.out.println(pizza.getDescription() + " $" + pizza.cost());
    }
}
```

### Output
```
Plain Pizza $5.0
Plain Pizza, Extra Cheese $6.5
Plain Pizza, Extra Cheese, Olives $7.25
Plain Pizza, Extra Cheese, Olives, Peppers $7.75
```

### Explanation
1. **Pizza**: This is the component interface for pizza.
2. **PlainPizza**: This concrete component represents a basic pizza.
3. **PizzaDecorator**: This abstract class implements the `Pizza` interface and holds a reference to a `Pizza` object.
4. **CheeseDecorator**, **OlivesDecorator**, and **PeppersDecorator**: These concrete decorators enhance the pizza with specific toppings.
5. **Main**: The client code creates a plain pizza, applies decorators for toppings, and displays the final description and cost.

---

### Summary
The **Decorator Pattern** is widely applicable across various industries, enabling dynamic extension of functionalities without modifying the original object. The examples provided illustrate how this pattern can be utilized in systems such as notification services, text processing applications, and food ordering systems, demonstrating its flexibility and effectiveness in real-world scenarios.