Let's consider another example of the **Prototype Pattern** in a scenario involving a **Document Management System**. In this example, we will create a document prototype that can be cloned, allowing users to create copies of various document types (e.g., `WordDocument` and `PDFDocument`) quickly.

### Problem Scenario

In a document management system, users often need to create multiple versions of similar documents. Instead of recreating each document from scratch, we can use the Prototype Pattern to clone existing documents. This way, we can maintain a consistent structure and save time.

### Step 1: Define the Prototype Interface

We start by defining a `Document` interface that includes a `clone()` method.

```java
// Prototype interface
public interface Document {
    Document clone();
    void displayInfo();
}
```

### Step 2: Implement Concrete Prototypes

Now, we implement two concrete prototypes: `WordDocument` and `PDFDocument`.

```java
// Concrete prototype: WordDocument
public class WordDocument implements Document {
    private String content;
    private String author;

    public WordDocument(String content, String author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public Document clone() {
        return new WordDocument(this.content, this.author);
    }

    @Override
    public void displayInfo() {
        System.out.println("Word Document - Author: " + author + ", Content: " + content);
    }
}

// Concrete prototype: PDFDocument
public class PDFDocument implements Document {
    private String content;
    private String author;

    public PDFDocument(String content, String author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public Document clone() {
        return new PDFDocument(this.content, this.author);
    }

    @Override
    public void displayInfo() {
        System.out.println("PDF Document - Author: " + author + ", Content: " + content);
    }
}
```

### Step 3: Client Code Using the Prototype Pattern

Let’s demonstrate how to use these document prototypes in a client.

```java
public class PrototypeDemo {
    public static void main(String[] args) {
        // Create original document prototypes
        Document originalWordDoc = new WordDocument("Hello World", "Alice");
        Document originalPDFDoc = new PDFDocument("Hello PDF World", "Bob");

        // Clone the original prototypes
        Document clonedWordDoc = originalWordDoc.clone();
        Document clonedPDFDoc = originalPDFDoc.clone();

        // Display info of original documents
        System.out.println("Original Documents:");
        originalWordDoc.displayInfo(); // Output: Word Document - Author: Alice, Content: Hello World
        originalPDFDoc.displayInfo();   // Output: PDF Document - Author: Bob, Content: Hello PDF World

        // Display info of cloned documents
        System.out.println("\nCloned Documents:");
        clonedWordDoc.displayInfo(); // Output: Word Document - Author: Alice, Content: Hello World
        clonedPDFDoc.displayInfo();   // Output: PDF Document - Author: Bob, Content: Hello PDF World
    }
}
```

### Output

```
Original Documents:
Word Document - Author: Alice, Content: Hello World
PDF Document - Author: Bob, Content: Hello PDF World

Cloned Documents:
Word Document - Author: Alice, Content: Hello World
PDF Document - Author: Bob, Content: Hello PDF World
```

### Explanation of the Example

1. **Prototype Interface**: The `Document` interface defines the `clone()` method for cloning and a `displayInfo()` method to display document details.
2. **Concrete Prototypes**:
    - **WordDocument**: Implements the `Document` interface and provides a method to clone itself while preserving its content and author.
    - **PDFDocument**: Similar to the `WordDocument`, it implements the `Document` interface and can also clone itself with its attributes.
3. **Client Code**:
    - The client creates original document prototypes: `WordDocument` and `PDFDocument`.
    - It clones these prototypes using the `clone()` method.
    - It displays the details of both the original and cloned documents, demonstrating that they maintain their properties.

### Conclusion

The **Prototype Pattern** allows for efficient object creation by cloning existing instances rather than constructing new ones from scratch. In this example, we illustrated how document prototypes can be utilized to quickly create various versions of documents in a document management system. This pattern is especially beneficial in scenarios where document creation is frequent and requires consistency in structure and content.