The **Decorator Pattern** is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. This is accomplished by creating a set of decorator classes that are used to wrap concrete components. Decorators provide a flexible alternative to subclassing for extending functionality.

### Key Concepts
- **Component**: An interface or abstract class that defines the operations that can be performed.
- **ConcreteComponent**: A class that implements the Component interface.
- **Decorator**: An abstract class that implements the Component interface and has a reference to a Component object. It delegates calls to the wrapped component.
- **ConcreteDecorator**: Classes that extend the Decorator class and add additional behavior.

### Example: Coffee Shop

#### Scenario
In a coffee shop application, you have different types of coffee. You can add various condiments (like milk, sugar, etc.) to the coffee. Each condiment can enhance the coffee's functionality (like calculating the cost).

#### Implementation

```java
// Component
interface Coffee {
    String getDescription();
    double cost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double cost() {
        return 2.00; // Base price for a simple coffee
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double cost() {
        return coffee.cost();
    }
}

// Concrete Decorator for Milk
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.50; // Add the cost of milk
    }
}

// Concrete Decorator for Sugar
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.25; // Add the cost of sugar
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        // Adding milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        // Adding sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());
    }
}
```

### Output
```
Simple Coffee $2.0
Simple Coffee, Milk $2.5
Simple Coffee, Milk, Sugar $2.75
```

### Explanation
1. **Coffee**: This is the component interface that defines the methods `getDescription()` and `cost()`.
2. **SimpleCoffee**: This is the concrete component that implements the `Coffee` interface and represents a basic coffee.
3. **CoffeeDecorator**: This abstract class implements the `Coffee` interface and holds a reference to a `Coffee` object. It delegates the calls to the wrapped component.
4. **MilkDecorator**: This concrete decorator extends the `CoffeeDecorator` and adds the functionality for milk.
5. **SugarDecorator**: This concrete decorator extends the `CoffeeDecorator` and adds the functionality for sugar.
6. **Main**: In the client code, we create a simple coffee and then decorate it by adding milk and sugar. Each decoration modifies the description and cost accordingly.

---

### Industry-Related Examples

#### 1. **File Input/Output System**
In a file handling application, you can have different types of file readers (like text files, binary files) and additional features (like buffering, compression). You can use decorators to add these features to the base file reader.

```java
// Component
interface FileReader {
    void read();
}

// Concrete Component
class BasicFileReader implements FileReader {
    @Override
    public void read() {
        System.out.println("Reading file contents...");
    }
}

// Decorator
abstract class FileReaderDecorator implements FileReader {
    protected FileReader fileReader;

    public FileReaderDecorator(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void read() {
        fileReader.read();
    }
}

// Concrete Decorator for Buffering
class BufferedFileReader extends FileReaderDecorator {
    public BufferedFileReader(FileReader fileReader) {
        super(fileReader);
    }

    @Override
    public void read() {
        System.out.println("Buffering the file read...");
        super.read();
    }
}

// Concrete Decorator for Compression
class CompressedFileReader extends FileReaderDecorator {
    public CompressedFileReader(FileReader fileReader) {
        super(fileReader);
    }

    @Override
    public void read() {
        System.out.println("Decompressing the file...");
        super.read();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new BasicFileReader();
        fileReader.read();

        // Adding buffering
        FileReader bufferedReader = new BufferedFileReader(fileReader);
        bufferedReader.read();

        // Adding compression
        FileReader compressedReader = new CompressedFileReader(bufferedReader);
        compressedReader.read();
    }
}
```

### Output
```
Reading file contents...
Buffering the file read...
Reading file contents...
Decompressing the file...
Buffering the file read...
Reading file contents...
```

### Explanation
- **FileReader**: The component interface for reading files.
- **BasicFileReader**: A concrete component that reads files.
- **FileReaderDecorator**: An abstract class that implements the `FileReader` interface.
- **BufferedFileReader**: A concrete decorator that adds buffering functionality.
- **CompressedFileReader**: A concrete decorator that adds compression functionality.

---

#### 2. **Text Formatting**
In a text editor, you can have basic text formatting (like bold, italic, underline). You can use the Decorator Pattern to dynamically add these formatting features.

```java
// Component
interface Text {
    String getContent();
}

// Concrete Component
class PlainText implements Text {
    private String content;

    public PlainText(String content) {
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

// Concrete Decorator for Bold
class BoldTextDecorator extends TextDecorator {
    public BoldTextDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<b>" + text.getContent() + "</b>";
    }
}

// Concrete Decorator for Italic
class ItalicTextDecorator extends TextDecorator {
    public ItalicTextDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<i>" + text.getContent() + "</i>";
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Text text = new PlainText("Hello World!");

        // Applying bold formatting
        Text boldText = new BoldTextDecorator(text);
        System.out.println(boldText.getContent());

        // Applying italic formatting
        Text italicText = new ItalicTextDecorator(boldText);
        System.out.println(italicText.getContent());
    }
}
```

### Output
```
<b>Hello World!</b>
<i><b>Hello World!</b></i>
```

### Explanation
- **Text**: The component interface for text.
- **PlainText**: A concrete component representing plain text.
- **TextDecorator**: An abstract class implementing the `Text` interface.
- **BoldTextDecorator**: A concrete decorator for adding bold formatting.
- **ItalicTextDecorator**: A concrete decorator for adding italic formatting.

---

### Summary
The **Decorator Pattern** allows for dynamically adding behavior and responsibilities to individual objects without affecting other objects of the same class. The flexibility of decorators enables you to create complex functionalities by combining simple decorators in various ways, making this pattern widely used in applications like graphic design tools, text editors, and file handling systems.