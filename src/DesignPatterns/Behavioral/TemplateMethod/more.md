Here are some industry-related examples of the **Template Method Pattern** with detailed implementations in Java.

### Example 1: Document Generation System

In a document generation system, different types of documents (like reports, invoices, and contracts) may have a common structure but differ in how they gather data and format the content. The Template Method Pattern allows us to define the common steps of document generation while allowing subclasses to implement specific details.

#### Implementation

```java
// Abstract Class
abstract class Document {
    // Template Method
    public final void generateDocument() {
        gatherData();
        formatData();
        createDocument();
        sendDocument();
    }

    protected abstract void gatherData();
    protected abstract void formatData();

    private void createDocument() {
        System.out.println("Creating document...");
    }

    private void sendDocument() {
        System.out.println("Sending document to the recipient...");
    }
}

// Concrete Class: Report Document
class ReportDocument extends Document {
    @Override
    protected void gatherData() {
        System.out.println("Gathering data for report document.");
    }

    @Override
    protected void formatData() {
        System.out.println("Formatting data for report.");
    }
}

// Concrete Class: Invoice Document
class InvoiceDocument extends Document {
    @Override
    protected void gatherData() {
        System.out.println("Gathering data for invoice document.");
    }

    @Override
    protected void formatData() {
        System.out.println("Formatting data for invoice.");
    }
}

// Client code
public class DocumentGenerationDemo {
    public static void main(String[] args) {
        Document report = new ReportDocument();
        report.generateDocument();

        System.out.println();

        Document invoice = new InvoiceDocument();
        invoice.generateDocument();
    }
}
```

### Explanation

- **Document**: The abstract class that defines the template method `generateDocument()` and the overall steps involved in document generation.
- **ReportDocument** and **InvoiceDocument**: Concrete classes that implement the `gatherData()` and `formatData()` methods, providing specific behavior for generating reports and invoices.

---

### Example 2: Online Order Processing

In an e-commerce application, the online order processing workflow may include steps like validating the order, charging the customer, processing shipping, and notifying the customer. Different types of orders (e.g., digital vs. physical products) may have different validation and charging mechanisms.

#### Implementation

```java
// Abstract Class
abstract class OrderProcessing {
    // Template Method
    public final void processOrder() {
        validateOrder();
        chargeCustomer();
        shipOrder();
        notifyCustomer();
    }

    protected abstract void validateOrder();
    protected abstract void chargeCustomer();

    private void shipOrder() {
        System.out.println("Shipping the order...");
    }

    private void notifyCustomer() {
        System.out.println("Notifying the customer...");
    }
}

// Concrete Class: Digital Order Processor
class DigitalOrderProcessor extends OrderProcessing {
    @Override
    protected void validateOrder() {
        System.out.println("Validating digital order.");
    }

    @Override
    protected void chargeCustomer() {
        System.out.println("Charging customer for digital product.");
    }
}

// Concrete Class: Physical Order Processor
class PhysicalOrderProcessor extends OrderProcessing {
    @Override
    protected void validateOrder() {
        System.out.println("Validating physical order.");
    }

    @Override
    protected void chargeCustomer() {
        System.out.println("Charging customer for physical product.");
    }
}

// Client code
public class OrderProcessingDemo {
    public static void main(String[] args) {
        OrderProcessing digitalOrder = new DigitalOrderProcessor();
        digitalOrder.processOrder();

        System.out.println();

        OrderProcessing physicalOrder = new PhysicalOrderProcessor();
        physicalOrder.processOrder();
    }
}
```

### Explanation

- **OrderProcessing**: The abstract class defining the template method `processOrder()` and outlining the steps involved in order processing.
- **DigitalOrderProcessor** and **PhysicalOrderProcessor**: Concrete classes that implement the `validateOrder()` and `chargeCustomer()` methods, providing specific behavior for different types of orders.

---

### Example 3: Game Development

In a game development scenario, different types of games (e.g., chess, checkers, and tic-tac-toe) may have a common game loop structure but differ in how they initialize the game, process player moves, and check for the winner. The Template Method Pattern allows defining the game loop while allowing subclasses to implement specific game rules.

#### Implementation

```java
// Abstract Class
abstract class Game {
    // Template Method
    public final void playGame() {
        initialize();
        while (!isGameFinished()) {
            processPlayerMove();
        }
        declareWinner();
    }

    protected abstract void initialize();
    protected abstract void processPlayerMove();
    protected abstract boolean isGameFinished();
    protected abstract void declareWinner();
}

// Concrete Class: Chess Game
class ChessGame extends Game {
    @Override
    protected void initialize() {
        System.out.println("Initializing chess game...");
    }

    @Override
    protected void processPlayerMove() {
        System.out.println("Processing chess player's move...");
    }

    @Override
    protected boolean isGameFinished() {
        // Placeholder for game finish logic
        return false; // In a real game, this would check for checkmate or stalemate
    }

    @Override
    protected void declareWinner() {
        System.out.println("Declaring winner of chess game.");
    }
}

// Concrete Class: Tic-Tac-Toe Game
class TicTacToeGame extends Game {
    @Override
    protected void initialize() {
        System.out.println("Initializing tic-tac-toe game...");
    }

    @Override
    protected void processPlayerMove() {
        System.out.println("Processing tic-tac-toe player's move...");
    }

    @Override
    protected boolean isGameFinished() {
        // Placeholder for game finish logic
        return false; // In a real game, this would check for a win or draw
    }

    @Override
    protected void declareWinner() {
        System.out.println("Declaring winner of tic-tac-toe game.");
    }
}

// Client code
public class GameDemo {
    public static void main(String[] args) {
        Game chessGame = new ChessGame();
        chessGame.playGame();

        System.out.println();

        Game ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.playGame();
    }
}
```

### Explanation

- **Game**: The abstract class defining the template method `playGame()` and the steps involved in playing a game.
- **ChessGame** and **TicTacToeGame**: Concrete classes that implement the specific game logic for chess and tic-tac-toe.

### When to Use Template Method Pattern

1. **Common Algorithm Structure**: When you have a common algorithm structure but need flexibility in some steps.
2. **Code Reuse**: When you want to reuse code across multiple subclasses while allowing specific behaviors to be defined in subclasses.
3. **Control Flow**: When you want to control the flow of execution while still allowing subclasses to define specific steps.

The Template Method Pattern is beneficial for promoting code reuse, maintainability, and separation of concerns in your applications.