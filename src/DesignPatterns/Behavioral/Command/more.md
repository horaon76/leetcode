Here are three industry-related examples of the **Command Pattern** with Java implementations:

### 1. **Text Editor Application**
In a text editor, you can use the Command Pattern to implement features like undo and redo actions for text manipulation.

#### Implementation

```java
// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void write(String newText) {
        text.append(newText);
        System.out.println("Current Text: " + text.toString());
    }

    public void undoWrite(String oldText) {
        text.delete(text.length() - oldText.length(), text.length());
        System.out.println("Current Text after undo: " + text.toString());
    }
}

// Concrete Command
class WriteCommand implements Command {
    private TextEditor editor;
    private String textToWrite;

    public WriteCommand(TextEditor editor, String textToWrite) {
        this.editor = editor;
        this.textToWrite = textToWrite;
    }

    @Override
    public void execute() {
        editor.write(textToWrite);
    }

    @Override
    public void undo() {
        editor.undoWrite(textToWrite);
    }
}

// Invoker
class CommandHistory {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
}

// Client code
public class CommandPatternTextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandHistory commandHistory = new CommandHistory();

        Command writeHello = new WriteCommand(editor, "Hello ");
        Command writeWorld = new WriteCommand(editor, "World!");

        commandHistory.executeCommand(writeHello);
        commandHistory.executeCommand(writeWorld);

        System.out.println("\nUndoing last command:");
        commandHistory.undoLastCommand();  // Undo "World!"
    }
}
```

### Explanation
1. **Receiver**: The `TextEditor` class manipulates text.
2. **Command**: The `WriteCommand` encapsulates the action of writing text.
3. **Invoker**: The `CommandHistory` class maintains a history of commands for undoing.
4. **Client Code**: Demonstrates the use of commands in a text editor.

---

### 2. **Home Automation System**
In a smart home application, commands can control various appliances like lights, fans, and security systems.

#### Implementation

```java
// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver Classes
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void turnOn() {
        System.out.println("Fan is ON");
    }

    public void turnOff() {
        System.out.println("Fan is OFF");
    }
}

// Concrete Command for Light
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// Concrete Command for Fan
class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }
}

// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        }
    }

    public void pressUndo() {
        if (command != null) {
            command.undo();
        }
    }
}

// Client code
public class CommandPatternHomeAutomationDemo {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(ceilingFan);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();  // Turns the light ON

        remote.setCommand(fanOn);
        remote.pressButton();  // Turns the fan ON

        remote.pressUndo();    // Turns the fan OFF
    }
}
```

### Explanation
1. **Receiver**: `Light` and `Fan` classes perform actions on devices.
2. **Command**: `LightOnCommand` and `FanOnCommand` encapsulate commands for turning devices on and off.
3. **Invoker**: `RemoteControl` holds and invokes commands.
4. **Client Code**: Demonstrates how to control devices using commands.

---

### 3. **Order Processing System in E-commerce**
In an e-commerce platform, the Command Pattern can encapsulate order processing actions like placing, canceling, and confirming orders.

#### Implementation

```java
// Command Interface
interface OrderCommand {
    void execute();
    void undo();
}

// Receiver
class Order {
    public void place() {
        System.out.println("Order placed.");
    }

    public void cancel() {
        System.out.println("Order canceled.");
    }
}

// Concrete Command for Place Order
class PlaceOrderCommand implements OrderCommand {
    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.place();
    }

    @Override
    public void undo() {
        order.cancel();
    }
}

// Invoker
class OrderInvoker {
    private OrderCommand command;

    public void setCommand(OrderCommand command) {
        this.command = command;
    }

    public void processOrder() {
        if (command != null) {
            command.execute();
        }
    }

    public void cancelOrder() {
        if (command != null) {
            command.undo();
        }
    }
}

// Client code
public class CommandPatternOrderDemo {
    public static void main(String[] args) {
        Order order = new Order();
        OrderCommand placeOrder = new PlaceOrderCommand(order);

        OrderInvoker orderInvoker = new OrderInvoker();
        orderInvoker.setCommand(placeOrder);
        
        orderInvoker.processOrder();  // Place the order
        orderInvoker.cancelOrder();    // Cancel the order
    }
}
```

### Explanation
1. **Receiver**: The `Order` class handles order operations.
2. **Command**: The `PlaceOrderCommand` encapsulates the action of placing an order.
3. **Invoker**: The `OrderInvoker` holds the command and invokes it.
4. **Client Code**: Demonstrates placing and canceling orders.

---

### Conclusion
The **Command Pattern** is useful across various industries, enabling flexible and decoupled command execution. These examples demonstrate how commands can be encapsulated, allowing for easy undo operations and clear separation between the command and its execution context. This pattern promotes better organization and maintainability in software design.

# More Examples

Here are more examples of the **Command Pattern** implemented in Java across different industries:

### 4. **Payment Processing System**
In an e-commerce application, payment processing can be implemented using the Command Pattern to encapsulate different payment actions (e.g., credit card payment, PayPal payment) for flexibility and extensibility.

#### Implementation

```java
// Command Interface
interface PaymentCommand {
    void execute();
    void undo();
}

// Receiver Class
class PaymentProcessor {
    public void payByCreditCard(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }

    public void payByPayPal(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }

    public void cancelPayment(double amount) {
        System.out.println("Payment of $" + amount + " canceled.");
    }
}

// Concrete Command for Credit Card Payment
class CreditCardPaymentCommand implements PaymentCommand {
    private PaymentProcessor processor;
    private double amount;

    public CreditCardPaymentCommand(PaymentProcessor processor, double amount) {
        this.processor = processor;
        this.amount = amount;
    }

    @Override
    public void execute() {
        processor.payByCreditCard(amount);
    }

    @Override
    public void undo() {
        processor.cancelPayment(amount);
    }
}

// Concrete Command for PayPal Payment
class PayPalPaymentCommand implements PaymentCommand {
    private PaymentProcessor processor;
    private double amount;

    public PayPalPaymentCommand(PaymentProcessor processor, double amount) {
        this.processor = processor;
        this.amount = amount;
    }

    @Override
    public void execute() {
        processor.payByPayPal(amount);
    }

    @Override
    public void undo() {
        processor.cancelPayment(amount);
    }
}

// Invoker
class PaymentInvoker {
    private PaymentCommand command;

    public void setCommand(PaymentCommand command) {
        this.command = command;
    }

    public void processPayment() {
        if (command != null) {
            command.execute();
        }
    }

    public void cancelPayment() {
        if (command != null) {
            command.undo();
        }
    }
}

// Client code
public class CommandPatternPaymentDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        PaymentCommand creditCardPayment = new CreditCardPaymentCommand(processor, 150.00);
        PaymentCommand payPalPayment = new PayPalPaymentCommand(processor, 100.00);

        PaymentInvoker paymentInvoker = new PaymentInvoker();

        // Process Credit Card Payment
        paymentInvoker.setCommand(creditCardPayment);
        paymentInvoker.processPayment();  // Output: Paid $150.0 using Credit Card.

        // Process PayPal Payment
        paymentInvoker.setCommand(payPalPayment);
        paymentInvoker.processPayment();  // Output: Paid $100.0 using PayPal.

        // Cancel last payment
        paymentInvoker.cancelPayment();    // Output: Payment of $100.0 canceled.
    }
}
```

### Explanation
- **Receiver**: The `PaymentProcessor` class handles various payment methods.
- **Command**: `CreditCardPaymentCommand` and `PayPalPaymentCommand` encapsulate payment actions.
- **Invoker**: `PaymentInvoker` holds the payment command and invokes it.
- **Client Code**: Demonstrates processing and canceling payments.

---

### 5. **Order Management System**
In a restaurant application, you can use the Command Pattern to handle orders from customers, allowing for flexible order processing and undo capabilities.

#### Implementation

```java
// Command Interface
interface OrderCommand {
    void execute();
    void undo();
}

// Receiver Class
class Kitchen {
    public void cookOrder(String dish) {
        System.out.println("Cooking: " + dish);
    }

    public void cancelOrder(String dish) {
        System.out.println("Canceled: " + dish);
    }
}

// Concrete Command for Cooking Order
class CookOrderCommand implements OrderCommand {
    private Kitchen kitchen;
    private String dish;

    public CookOrderCommand(Kitchen kitchen, String dish) {
        this.kitchen = kitchen;
        this.dish = dish;
    }

    @Override
    public void execute() {
        kitchen.cookOrder(dish);
    }

    @Override
    public void undo() {
        kitchen.cancelOrder(dish);
    }
}

// Invoker
class OrderInvoker {
    private OrderCommand command;

    public void setCommand(OrderCommand command) {
        this.command = command;
    }

    public void processOrder() {
        if (command != null) {
            command.execute();
        }
    }

    public void cancelOrder() {
        if (command != null) {
            command.undo();
        }
    }
}

// Client code
public class CommandPatternOrderManagementDemo {
    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();
        OrderCommand orderPasta = new CookOrderCommand(kitchen, "Pasta");
        OrderCommand orderPizza = new CookOrderCommand(kitchen, "Pizza");

        OrderInvoker orderInvoker = new OrderInvoker();

        // Process Pasta Order
        orderInvoker.setCommand(orderPasta);
        orderInvoker.processOrder();  // Output: Cooking: Pasta

        // Process Pizza Order
        orderInvoker.setCommand(orderPizza);
        orderInvoker.processOrder();  // Output: Cooking: Pizza

        // Cancel Pizza Order
        orderInvoker.cancelOrder();    // Output: Canceled: Pizza
    }
}
```

### Explanation
- **Receiver**: The `Kitchen` class performs actions related to cooking orders.
- **Command**: The `CookOrderCommand` encapsulates the action of cooking a specific dish.
- **Invoker**: The `OrderInvoker` holds the command and invokes it.
- **Client Code**: Demonstrates processing and canceling food orders.

---

### 6. **Game Action Management**
In a video game, the Command Pattern can be used to manage player actions such as moving, attacking, and using items.

#### Implementation

```java
// Command Interface
interface GameCommand {
    void execute();
    void undo();
}

// Receiver Class
class Player {
    public void move(String direction) {
        System.out.println("Player moved " + direction);
    }

    public void attack() {
        System.out.println("Player attacked!");
    }

    public void useItem(String item) {
        System.out.println("Player used " + item);
    }

    public void undoMove() {
        System.out.println("Player undo move.");
    }
}

// Concrete Command for Move Action
class MoveCommand implements GameCommand {
    private Player player;
    private String direction;

    public MoveCommand(Player player, String direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {
        player.move(direction);
    }

    @Override
    public void undo() {
        player.undoMove();
    }
}

// Concrete Command for Attack Action
class AttackCommand implements GameCommand {
    private Player player;

    public AttackCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.attack();
    }

    @Override
    public void undo() {
        System.out.println("Attack action cannot be undone.");
    }
}

// Invoker
class GameInvoker {
    private GameCommand command;

    public void setCommand(GameCommand command) {
        this.command = command;
    }

    public void performAction() {
        if (command != null) {
            command.execute();
        }
    }

    public void undoAction() {
        if (command != null) {
            command.undo();
        }
    }
}

// Client code
public class CommandPatternGameDemo {
    public static void main(String[] args) {
        Player player = new Player();
        GameCommand moveUp = new MoveCommand(player, "up");
        GameCommand attack = new AttackCommand(player);

        GameInvoker gameInvoker = new GameInvoker();

        // Move Player Up
        gameInvoker.setCommand(moveUp);
        gameInvoker.performAction();  // Output: Player moved up

        // Attack
        gameInvoker.setCommand(attack);
        gameInvoker.performAction();  // Output: Player attacked!

        // Undo Move
        gameInvoker.undoAction();      // Output: Player undo move.
    }
}
```

### Explanation
- **Receiver**: The `Player` class manages player actions.
- **Command**: `MoveCommand` and `AttackCommand` encapsulate specific player actions.
- **Invoker**: The `GameInvoker` holds the command and invokes it.
- **Client Code**: Demonstrates player actions and undo functionality.

---

### Conclusion
These additional examples showcase the versatility of the **Command Pattern** across different industries, such as e-commerce, hospitality, and gaming. By encapsulating requests as objects, the Command Pattern enhances the flexibility and maintainability of applications, making it easier to implement features like undo/redo and dynamic command processing.