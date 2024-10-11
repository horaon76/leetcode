Here’s another example of the **Composite Pattern**, this time focusing on a graphical user interface (GUI) representation where both simple components (like buttons) and complex components (like panels that contain multiple buttons) can be treated uniformly.

### Example: Graphical User Interface (GUI)

#### Scenario
In a GUI framework, you have different components such as buttons and panels. A panel can contain multiple buttons and other panels, allowing for a hierarchical structure of UI elements.

#### Key Components
- **Component**: `UIComponent`
- **Leaf**: `Button`
- **Composite**: `Panel`

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface UIComponent {
    void render();
}

// Leaf
class Button implements UIComponent {
    private String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public void render() {
        System.out.println("Rendering Button: " + label);
    }
}

// Composite
class Panel implements UIComponent {
    private String title;
    private List<UIComponent> components = new ArrayList<>();

    public Panel(String title) {
        this.title = title;
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void removeComponent(UIComponent component) {
        components.remove(component);
    }

    @Override
    public void render() {
        System.out.println("Rendering Panel: " + title);
        for (UIComponent component : components) {
            component.render();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create buttons
        Button button1 = new Button("OK");
        Button button2 = new Button("Cancel");
        Button button3 = new Button("Submit");

        // Create a panel and add buttons to it
        Panel mainPanel = new Panel("Main Panel");
        mainPanel.addComponent(button1);
        mainPanel.addComponent(button2);

        // Create another panel and add buttons and the main panel to it
        Panel secondaryPanel = new Panel("Secondary Panel");
        secondaryPanel.addComponent(mainPanel);
        secondaryPanel.addComponent(button3);

        // Render the entire UI structure
        secondaryPanel.render();
    }
}
```

### Output
```
Rendering Panel: Secondary Panel
Rendering Panel: Main Panel
Rendering Button: OK
Rendering Button: Cancel
Rendering Button: Submit
```

### Explanation

1. **UIComponent**: This is the common interface for all components in the GUI, defining the method `render()`.

2. **Button**: This class implements the `UIComponent` interface and represents a leaf node. Each button has a label and implements the `render` method to display its label.

3. **Panel**: This class also implements the `UIComponent` interface and acts as a composite node. It can contain multiple `UIComponent` objects (both buttons and other panels). The `addComponent` and `removeComponent` methods allow adding or removing child components.

4. **Main**: In the client code, we create several `Button` and `Panel` objects. We create a `mainPanel` that contains two buttons and a `secondaryPanel` that contains the `mainPanel` and an additional button. Finally, calling the `render` method on the `secondaryPanel` will recursively call `render` on all its components, demonstrating the composite structure.

### Summary
The Composite Pattern provides a flexible way to create complex structures where individual components and groups of components can be treated uniformly. In this GUI example, it simplifies the rendering process of various UI components, allowing for easy addition and management of components in a hierarchical manner. This pattern is commonly used in frameworks for creating user interfaces, graphical elements, and scene graphs in game development.