The **Visitor Pattern** is a behavioral design pattern that allows you to separate an algorithm from the objects on which it operates. This pattern enables you to add new operations to existing object structures without modifying those structures, making it easier to extend and maintain your code.

### Key Components

1. **Visitor Interface**: Defines the visiting operations for each type of element in the object structure.
2. **Concrete Visitor**: Implements the operations defined in the visitor interface.
3. **Element Interface**: Defines an `accept` method that takes a visitor as an argument.
4. **Concrete Elements**: Implement the element interface and provide an implementation of the `accept` method, which calls the visitor's operation.

### Use Cases

- When you want to perform operations on a set of objects with different interfaces without modifying their classes.
- When you need to add new operations to a class hierarchy without changing the existing code.
- When the object structure is stable but you expect to add new operations frequently.

### Industry Examples of Visitor Pattern

#### Example 1: E-commerce System

In an e-commerce application, you might have different types of items (like products, categories, and discounts) and want to perform operations like calculating total prices or applying discounts. The Visitor Pattern allows you to define these operations separately from the item classes.

#### Implementation

```java
// Visitor Interface
interface ShoppingCartVisitor {
    double visit(Book book);
    double visit(Electronic electronic);
}

// Element Interface
interface Item {
    double accept(ShoppingCartVisitor visitor);
}

// Concrete Element: Book
class Book implements Item {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Concrete Element: Electronic
class Electronic implements Item {
    private String name;
    private double price;

    public Electronic(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Concrete Visitor
class ShoppingCart implements ShoppingCartVisitor {
    @Override
    public double visit(Book book) {
        System.out.println("Book: " + book.getTitle() + ", Price: " + book.getPrice());
        return book.getPrice();
    }

    @Override
    public double visit(Electronic electronic) {
        System.out.println("Electronic: " + electronic.getName() + ", Price: " + electronic.getPrice());
        return electronic.getPrice();
    }
}

// Client code
public class VisitorPatternDemo {
    public static void main(String[] args) {
        Item[] items = new Item[] {
            new Book("Effective Java", 45.0),
            new Electronic("Smartphone", 799.99)
        };

        ShoppingCart shoppingCart = new ShoppingCart();
        double total = 0;
        for (Item item : items) {
            total += item.accept(shoppingCart);
        }

        System.out.println("Total Cost: " + total);
    }
}
```

### Explanation

- **ShoppingCartVisitor**: The visitor interface defining the operations that can be performed on the items.
- **Item**: The element interface that defines the `accept` method.
- **Book** and **Electronic**: Concrete element classes that implement the `Item` interface and define the `accept` method.
- **ShoppingCart**: The concrete visitor that implements the `ShoppingCartVisitor` interface and performs operations on the items.

---

#### Example 2: Shape Drawing Application

In a drawing application, you might have various shapes (like circles, rectangles, and triangles) and want to perform operations like rendering and calculating the area. The Visitor Pattern allows you to define these operations separately from the shape classes.

#### Implementation

```java
// Visitor Interface
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// Element Interface
interface Shape {
    void accept(ShapeVisitor visitor);
}

// Concrete Element: Circle
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Element: Rectangle
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor
class ShapeRenderer implements ShapeVisitor {
    @Override
    public void visit(Circle circle) {
        System.out.println("Rendering a circle with radius: " + circle.getRadius());
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Rendering a rectangle with width: " + rectangle.getWidth() + " and height: " + rectangle.getHeight());
    }
}

// Client code
public class VisitorPatternShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
            new Circle(5.0),
            new Rectangle(10.0, 20.0)
        };

        ShapeRenderer renderer = new ShapeRenderer();
        for (Shape shape : shapes) {
            shape.accept(renderer);
        }
    }
}
```

### Explanation

- **ShapeVisitor**: The visitor interface defining the operations that can be performed on the shapes.
- **Shape**: The element interface that defines the `accept` method.
- **Circle** and **Rectangle**: Concrete element classes that implement the `Shape` interface and define the `accept` method.
- **ShapeRenderer**: The concrete visitor that implements the `ShapeVisitor` interface and performs operations on the shapes.

---

### Example 3: File System Structure

In a file management application, you might have different types of files (like text files and image files) and want to perform operations like calculating size or extracting metadata. The Visitor Pattern allows you to define these operations separately from the file classes.

#### Implementation

```java
// Visitor Interface
interface FileVisitor {
    void visit(TextFile textFile);
    void visit(ImageFile imageFile);
}

// Element Interface
interface File {
    void accept(FileVisitor visitor);
}

// Concrete Element: TextFile
class TextFile implements File {
    private String name;
    private int size; // size in KB

    public TextFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Element: ImageFile
class ImageFile implements File {
    private String name;
    private int size; // size in KB

    public ImageFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor
class FileAnalyzer implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Analyzing text file: " + textFile.getName() + ", Size: " + textFile.getSize() + "KB");
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Analyzing image file: " + imageFile.getName() + ", Size: " + imageFile.getSize() + "KB");
    }
}

// Client code
public class VisitorPatternFileDemo {
    public static void main(String[] args) {
        File[] files = new File[] {
            new TextFile("Document.txt", 20),
            new ImageFile("Picture.jpg", 300)
        };

        FileAnalyzer analyzer = new FileAnalyzer();
        for (File file : files) {
            file.accept(analyzer);
        }
    }
}
```

### Explanation

- **FileVisitor**: The visitor interface defining the operations that can be performed on the files.
- **File**: The element interface that defines the `accept` method.
- **TextFile** and **ImageFile**: Concrete element classes that implement the `File` interface and define the `accept` method.
- **FileAnalyzer**: The concrete visitor that implements the `FileVisitor` interface and performs operations on the files.

### When to Use Visitor Pattern

1. **Adding New Operations**: Use the Visitor Pattern when you want to add new operations to a class hierarchy without modifying the classes.
2. **Complex Object Structures**: When the object structure is complex and you want to keep the operations that can be performed on the objects separate from the object structure itself.
3. **Multiple Operations**: When you need to perform multiple unrelated operations on the same object structure.

The Visitor Pattern helps in maintaining a clean separation of concerns, making your code easier to understand, extend, and maintain.