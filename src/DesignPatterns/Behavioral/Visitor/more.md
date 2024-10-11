Here are several industry examples of the **Visitor Pattern** with detailed implementations in Java, showcasing how it can be used in different scenarios.

### Example 1: E-commerce System (Shopping Cart)

In an e-commerce system, the Visitor Pattern can be used to handle different types of items in a shopping cart. This can help calculate total costs, apply discounts, or generate receipts without modifying the item classes.

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

// Client Code
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

- **Visitor Interface**: Defines methods for visiting each type of item.
- **Item Interface**: Defines an `accept` method for the visitor.
- **Concrete Elements (Book, Electronic)**: Implement the `Item` interface and define how they accept a visitor.
- **Concrete Visitor (ShoppingCart)**: Implements the `ShoppingCartVisitor` interface and performs operations on each item.

### Example 2: File System Structure

In a file management application, the Visitor Pattern can help perform operations like calculating sizes or extracting metadata from different types of files without modifying the file classes.

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

// Client Code
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

- **FileVisitor Interface**: Defines the methods for visiting different file types.
- **File Interface**: Defines the `accept` method for file types.
- **Concrete Elements (TextFile, ImageFile)**: Implement the `File` interface and define how they accept a visitor.
- **Concrete Visitor (FileAnalyzer)**: Implements the `FileVisitor` interface and defines operations for each file type.

### Example 3: Shape Drawing Application

In a graphics application, you can use the Visitor Pattern to perform operations on various shapes like circles and rectangles.

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

// Client Code
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

- **ShapeVisitor Interface**: Defines the methods for visiting different shape types.
- **Shape Interface**: Defines the `accept` method for shapes.
- **Concrete Elements (Circle, Rectangle)**: Implement the `Shape` interface and define how they accept a visitor.
- **Concrete Visitor (ShapeRenderer)**: Implements the `ShapeVisitor` interface and defines operations for rendering each shape.

### Summary

The **Visitor Pattern** is beneficial in scenarios where you need to perform multiple operations on complex object structures without altering the object classes. This makes it easier to manage changes and maintain the codebase, especially when dealing with various operations across different data types, such as in the examples above, which illustrate the pattern's applicability in an e-commerce system, file management system, and graphics application.