Here’s another example of the **Composite Pattern**, focusing on a document structure where both text and images can be treated uniformly as part of a document.

### Example: Document Structure

#### Scenario
In a word processing application, a document can contain various types of content, such as text paragraphs and images. Each of these can be represented as a component. A document can consist of multiple paragraphs and images, allowing for a hierarchical composition of content.

#### Key Components
- **Component**: `DocumentComponent`
- **Leaf**: `TextParagraph`
- **Leaf**: `Image`
- **Composite**: `Document`

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface DocumentComponent {
    void display();
}

// Leaf for text paragraphs
class TextParagraph implements DocumentComponent {
    private String text;

    public TextParagraph(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println("Text: " + text);
    }
}

// Leaf for images
class Image implements DocumentComponent {
    private String imageName;

    public Image(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void display() {
        System.out.println("Image: " + imageName);
    }
}

// Composite for documents
class Document implements DocumentComponent {
    private List<DocumentComponent> components = new ArrayList<>();

    public void addComponent(DocumentComponent component) {
        components.add(component);
    }

    public void removeComponent(DocumentComponent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        for (DocumentComponent component : components) {
            component.display();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create text paragraphs
        TextParagraph paragraph1 = new TextParagraph("This is the first paragraph.");
        TextParagraph paragraph2 = new TextParagraph("This is the second paragraph.");

        // Create images
        Image image1 = new Image("image1.png");
        Image image2 = new Image("image2.jpg");

        // Create a document and add paragraphs and images to it
        Document document = new Document();
        document.addComponent(paragraph1);
        document.addComponent(image1);
        document.addComponent(paragraph2);
        document.addComponent(image2);

        // Display the entire document structure
        document.display();
    }
}
```

### Output
```
Text: This is the first paragraph.
Image: image1.png
Text: This is the second paragraph.
Image: image2.jpg
```

### Explanation

1. **DocumentComponent**: This interface defines the common interface for all components of the document, with a method `display()` for rendering the content.

2. **TextParagraph**: This class implements the `DocumentComponent` interface and represents a leaf node for text content. It has a text property and implements the `display` method to show the text.

3. **Image**: This class also implements the `DocumentComponent` interface and represents a leaf node for image content. It has an image name property and implements the `display` method to show the image name.

4. **Document**: This class acts as a composite that can contain multiple `DocumentComponent` objects (both text paragraphs and images). It has methods to add or remove components, and its `display` method iterates over all components and calls their `display` methods.

5. **Main**: In the client code, we create several `TextParagraph` and `Image` objects. We then create a `Document` and add the paragraphs and images to it. Finally, calling the `display` method on the document will render the entire content structure, demonstrating the composite functionality.

### Summary
The Composite Pattern allows you to treat both simple and complex components uniformly, enabling you to build and manage hierarchical structures easily. In this document structure example, the pattern simplifies how content is managed and displayed in a document, allowing for easy addition and organization of different content types (text and images). This pattern is commonly used in text editors, rendering engines, and graphical applications where a mix of different content types needs to be managed as a unified whole.