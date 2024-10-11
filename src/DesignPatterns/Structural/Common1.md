Here’s a comprehensive implementation of various design patterns (Adapter, Bridge, Composite, Decorator, Facade, Flyweight, and Proxy) using a **Library Management System** as a common industry-related example. This system will manage books, members, and borrowing functionality.

## Common Library Management System

### 1. **Adapter Pattern**
#### Purpose
The Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces.

#### Implementation

```java
// Target Interface
interface Book {
    void read();
}

// Adaptee
class OldBook {
    public void open() {
        System.out.println("Opening an old book...");
    }
}

// Adapter
class OldBookAdapter implements Book {
    private OldBook oldBook;

    public OldBookAdapter(OldBook oldBook) {
        this.oldBook = oldBook;
    }

    @Override
    public void read() {
        oldBook.open();
    }
}

// Client code
public class AdapterPatternDemo {
    public static void main(String[] args) {
        OldBook oldBook = new OldBook();
        Book book = new OldBookAdapter(oldBook);
        book.read();
    }
}
```

### Explanation
- **When to Use**: Use the Adapter pattern when you need to integrate classes that cannot work together due to incompatible interfaces.

---

### 2. **Bridge Pattern**
#### Purpose
The Bridge pattern separates abstraction from implementation, allowing the two to evolve independently.

#### Implementation

```java
// Abstraction
abstract class Library {
    protected BookStorage bookStorage;

    protected Library(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    abstract void showBooks();
}

// Refined Abstraction
class PublicLibrary extends Library {
    public PublicLibrary(BookStorage bookStorage) {
        super(bookStorage);
    }

    @Override
    void showBooks() {
        System.out.println("Public Library:");
        bookStorage.displayBooks();
    }
}

// Implementor
interface BookStorage {
    void displayBooks();
}

// Concrete Implementor
class DigitalStorage implements BookStorage {
    @Override
    public void displayBooks() {
        System.out.println("Displaying books from digital storage.");
    }
}

// Client code
public class BridgePatternDemo {
    public static void main(String[] args) {
        BookStorage digitalStorage = new DigitalStorage();
        Library library = new PublicLibrary(digitalStorage);
        library.showBooks();
    }
}
```

### Explanation
- **When to Use**: Use the Bridge pattern when you want to decouple an abstraction from its implementation so that both can vary independently.

---

### 3. **Composite Pattern**
#### Purpose
The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions uniformly.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface LibraryComponent {
    void showDetails();
}

// Leaf
class BookLeaf implements LibraryComponent {
    private String title;

    public BookLeaf(String title) {
        this.title = title;
    }

    @Override
    public void showDetails() {
        System.out.println("Book Title: " + title);
    }
}

// Composite
class LibraryComposite implements LibraryComponent {
    private List<LibraryComponent> components = new ArrayList<>();

    public void add(LibraryComponent component) {
        components.add(component);
    }

    @Override
    public void showDetails() {
        for (LibraryComponent component : components) {
            component.showDetails();
        }
    }
}

// Client code
public class CompositePatternDemo {
    public static void main(String[] args) {
        LibraryComponent book1 = new BookLeaf("Harry Potter");
        LibraryComponent book2 = new BookLeaf("The Lord of the Rings");

        LibraryComposite library = new LibraryComposite();
        library.add(book1);
        library.add(book2);

        library.showDetails();
    }
}
```

### Explanation
- **When to Use**: Use the Composite pattern when you need to treat individual objects and compositions of objects uniformly.

---

### 4. **Decorator Pattern**
#### Purpose
The Decorator pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class.

#### Implementation

```java
// Component
interface LibraryItem {
    String getDescription();
}

// Concrete Component
class BasicBook implements LibraryItem {
    @Override
    public String getDescription() {
        return "Basic Book";
    }
}

// Decorator
abstract class BookDecorator implements LibraryItem {
    protected LibraryItem libraryItem;

    public BookDecorator(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    @Override
    public String getDescription() {
        return libraryItem.getDescription();
    }
}

// Concrete Decorator
class FeaturedBookDecorator extends BookDecorator {
    public FeaturedBookDecorator(LibraryItem libraryItem) {
        super(libraryItem);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Featured Label";
    }
}

// Client code
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        LibraryItem book = new BasicBook();
        System.out.println(book.getDescription());

        LibraryItem featuredBook = new FeaturedBookDecorator(book);
        System.out.println(featuredBook.getDescription());
    }
}
```

### Explanation
- **When to Use**: Use the Decorator pattern when you want to add responsibilities to individual objects dynamically and extend their behavior without altering their structure.

---

### 5. **Facade Pattern**
#### Purpose
The Facade pattern provides a simplified interface to a complex subsystem, making it easier to interact with the subsystem.

#### Implementation

```java
// Subsystem 1
class BookService {
    public void addBook(String title) {
        System.out.println("Adding book: " + title);
    }
}

// Subsystem 2
class MemberService {
    public void addMember(String name) {
        System.out.println("Adding member: " + name);
    }
}

// Facade
class LibraryFacade {
    private BookService bookService;
    private MemberService memberService;

    public LibraryFacade() {
        this.bookService = new BookService();
        this.memberService = new MemberService();
    }

    public void addBookAndMember(String bookTitle, String memberName) {
        bookService.addBook(bookTitle);
        memberService.addMember(memberName);
    }
}

// Client code
public class FacadePatternDemo {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacade();
        libraryFacade.addBookAndMember("1984", "John Doe");
    }
}
```

### Explanation
- **When to Use**: Use the Facade pattern when you want to provide a simple interface to a complex system or set of interfaces.

---

### 6. **Flyweight Pattern**
#### Purpose
The Flyweight pattern is used to reduce the memory footprint by sharing common parts of state among multiple objects.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Book {
    void display();
}

// Concrete Flyweight
class ConcreteBook implements Book {
    private String title;

    public ConcreteBook(String title) {
        this.title = title;
    }

    @Override
    public void display() {
        System.out.println("Displaying book: " + title);
    }
}

// Flyweight Factory
class BookFactory {
    private Map<String, Book> bookMap = new HashMap<>();

    public Book getBook(String title) {
        Book book = bookMap.get(title);
        if (book == null) {
            book = new ConcreteBook(title);
            bookMap.put(title, book);
        }
        return book;
    }
}

// Client code
public class FlyweightPatternDemo {
    public static void main(String[] args) {
        BookFactory bookFactory = new BookFactory();

        Book book1 = bookFactory.getBook("The Catcher in the Rye");
        Book book2 = bookFactory.getBook("The Catcher in the Rye"); // Reuses the existing instance

        book1.display();
        book2.display(); // Same instance reused
    }
}
```

### Explanation
- **When to Use**: Use the Flyweight pattern when you have a large number of similar objects, and you want to minimize memory usage by sharing common states.

---

### 7. **Proxy Pattern**
#### Purpose
The Proxy pattern provides a surrogate or placeholder for another object to control access to it.

#### Implementation

```java
// Subject Interface
interface LibraryAccount {
    void borrowBook(String bookTitle);
}

// Real Subject
class RealLibraryAccount implements LibraryAccount {
    @Override
    public void borrowBook(String bookTitle) {
        System.out.println("Borrowing book: " + bookTitle);
    }
}

// Proxy
class ProxyLibraryAccount implements LibraryAccount {
    private RealLibraryAccount realAccount;

    public ProxyLibraryAccount() {
        this.realAccount = new RealLibraryAccount();
    }

    @Override
    public void borrowBook(String bookTitle) {
        System.out.println("Checking if book is available...");
        realAccount.borrowBook(bookTitle);
    }
}

// Client code
public class ProxyPatternDemo {
    public static void main(String[] args) {
        LibraryAccount libraryAccount = new ProxyLibraryAccount();
        libraryAccount.borrowBook("Moby Dick");
    }
}
```

### Explanation
- **When to Use**: Use the Proxy pattern when you need to control access to an object, such as for lazy initialization, access control, or logging.

---

## Summary of When to Use Each Pattern

- **Adapter Pattern**: When you want to integrate incompatible interfaces.
- **Bridge Pattern**: When you need to decouple abstraction from implementation, allowing both to evolve independently.
- **Composite Pattern**: When you want to treat individual objects and compositions of objects uniformly, representing part-wh