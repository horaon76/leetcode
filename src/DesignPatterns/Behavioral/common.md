Behavioral design patterns deal with how objects interact and communicate with one another. They help to manage complex control flows and provide better communication between objects. Below are some commonly used behavioral patterns, their use cases, and a common example to illustrate when to use each pattern.

### 1. Chain of Responsibility Pattern

**When to Use:**
- When multiple objects can handle a request but the handler isn't known in advance.
- When the sender of a request wants to delegate the responsibility for handling that request to multiple receivers without specifying the handler explicitly.

**Example:**
In a customer support system, a request may go through different levels of support (like Technical Support, Customer Service, and Management) until it is resolved.

```java
// Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}

// Concrete Handlers
class TechnicalSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Technical Issue")) {
            System.out.println("Technical Support handling the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class CustomerService extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Customer Inquiry")) {
            System.out.println("Customer Service handling the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class Management extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("Management handling the request.");
    }
}

// Client Code
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportHandler techSupport = new TechnicalSupport();
        SupportHandler customerService = new CustomerService();
        SupportHandler management = new Management();

        techSupport.setNextHandler(customerService);
        customerService.setNextHandler(management);

        techSupport.handleRequest("Technical Issue");
        techSupport.handleRequest("Customer Inquiry");
        techSupport.handleRequest("General Request");
    }
}
```

### 2. Command Pattern

**When to Use:**
- When you want to parameterize objects with operations, delay the execution of an operation, or queue a request.
- When you want to support undoable operations.

**Example:**
In a text editor, you can use the Command Pattern to implement commands like Undo, Redo, Copy, Paste, etc.

```java
// Command Interface
interface Command {
    void execute();
    void undo();
}

// Concrete Command
class TextEditorCommand implements Command {
    private String text;

    public TextEditorCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        System.out.println("Executing command: " + text);
    }

    @Override
    public void undo() {
        System.out.println("Undoing command: " + text);
    }
}

// Invoker
class CommandInvoker {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
        command.execute();
    }

    public void undoLastCommand() {
        if (!commands.isEmpty()) {
            Command lastCommand = commands.remove(commands.size() - 1);
            lastCommand.undo();
        }
    }
}

// Client Code
public class CommandPatternDemo {
    public static void main(String[] args) {
        CommandInvoker invoker = new CommandInvoker();
        
        Command command1 = new TextEditorCommand("Type 'Hello World'");
        Command command2 = new TextEditorCommand("Type 'Design Patterns'");

        invoker.addCommand(command1);
        invoker.addCommand(command2);
        invoker.undoLastCommand();  // Undo last command
    }
}
```

### 3. Iterator Pattern

**When to Use:**
- When you want to provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
- When you need to traverse different collections or data structures uniformly.

**Example:**
In a collection of books in a library, you might want to iterate through the collection without exposing the internal data structure.

```java
// Iterator Interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Aggregate Interface
interface BookCollection {
    Iterator<Book> createIterator();
}

// Concrete Aggregate
class Library implements BookCollection {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Iterator<Book> createIterator() {
        return new LibraryIterator(books);
    }
}

// Concrete Iterator
class LibraryIterator implements Iterator<Book> {
    private List<Book> books;
    private int position = 0;

    public LibraryIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public Book next() {
        return books.get(position++);
    }
}

// Element
class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

// Client Code
public class IteratorPatternDemo {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Design Patterns"));
        library.addBook(new Book("Clean Code"));

        Iterator<Book> iterator = library.createIterator();
        while (iterator.hasNext()) {
            System.out.println("Book: " + iterator.next().getTitle());
        }
    }
}
```

### 4. Observer Pattern

**When to Use:**
- When you need to maintain a one-to-many relationship between objects, where a change in one object (the subject) triggers updates in dependent objects (the observers).
- When you want to decouple the subject from its observers.

**Example:**
In a weather monitoring system, observers (like display elements) can update when there is a change in the weather data.

```java
// Observer Interface
interface Observer {
    void update(float temperature, float humidity);
}

// Subject Interface
interface WeatherData {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements WeatherData {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }
}

// Concrete Observer
class Display implements Observer {
    private String name;

    public Display(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature, float humidity) {
        System.out.println(name + " updated: Temperature = " + temperature + ", Humidity = " + humidity);
    }
}

// Client Code
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Display display1 = new Display("Display 1");
        Display display2 = new Display("Display 2");

        weatherStation.registerObserver(display1);
        weatherStation.registerObserver(display2);

        weatherStation.setMeasurements(25.0f, 60.0f);
        weatherStation.setMeasurements(30.0f, 70.0f);
    }
}
```

### Summary of Usage

- **Chain of Responsibility**: Use when you want to decouple the sender and receiver of a request and allow multiple handlers to process it.
- **Command**: Use when you need to encapsulate requests as objects, support undo/redo functionality, or queue operations.
- **Iterator**: Use when you want to provide a uniform way to traverse a collection without exposing its internal representation.
- **Observer**: Use when you want to establish a one-to-many dependency between objects, so that when one object changes state, all its dependents are notified and updated automatically.

### Common Example: E-commerce Application

Consider an e-commerce application where we have a shopping cart, product listings, and order processing. Here’s how we can apply the above behavioral patterns:

- **Chain of Responsibility**: Handle various stages of order processing (e.g., payment processing, inventory check, shipping).
- **Command**: Implement user actions (add to cart, remove from cart, checkout) as commands that can be executed, undone, or queued.
- **Iterator**: Traverse through product lists or items in the shopping cart.
- **Observer**: Notify users about changes (e.g., order status updates) or promotional offers.

Using these patterns will help structure your application better, making it more maintainable and scalable.