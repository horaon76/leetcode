Here are industry examples of the **Flyweight Pattern** implemented in Java, focusing on various domains. Each example demonstrates how to optimize memory usage and manage a large number of similar objects.

### Example 1: **Online Game (Character Representation)**

#### Scenario
In an online game, many characters may share similar properties such as type (e.g., warrior, mage) but have different unique attributes (like name and health). The Flyweight pattern can be used to manage shared character types efficiently.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Character {
    void display(String name, int health);
}

// ConcreteFlyweight
class CharacterType implements Character {
    private final String type;

    public CharacterType(String type) {
        this.type = type;
    }

    @Override
    public void display(String name, int health) {
        System.out.println("Character Type: " + type + ", Name: " + name + ", Health: " + health);
    }
}

// FlyweightFactory
class CharacterFactory {
    private final Map<String, CharacterType> characterTypes = new HashMap<>();

    public CharacterType getCharacterType(String type) {
        if (!characterTypes.containsKey(type)) {
            characterTypes.put(type, new CharacterType(type));
        }
        return characterTypes.get(type);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        // Creating character types
        CharacterType warrior = factory.getCharacterType("Warrior");
        CharacterType mage = factory.getCharacterType("Mage");
        CharacterType warrior2 = factory.getCharacterType("Warrior"); // Shared

        // Displaying characters
        warrior.display("Thor", 100);
        mage.display("Gandalf", 80);
        warrior2.display("Loki", 90); // Uses shared instance
    }
}
```

### Output
```
Character Type: Warrior, Name: Thor, Health: 100
Character Type: Mage, Name: Gandalf, Health: 80
Character Type: Warrior, Name: Loki, Health: 90
```

### Explanation
- **Flyweight**: `Character` interface represents shared behaviors.
- **ConcreteFlyweight**: `CharacterType` class implements the interface and holds the intrinsic state (character type).
- **FlyweightFactory**: `CharacterFactory` manages the creation and sharing of `CharacterType` instances.
- **Client Code**: The client retrieves character types from the factory, reusing instances when possible.

---

### Example 2: **Web Application (Button States)**

#### Scenario
In a web application, many buttons might have the same visual representation (like color, size, and shape) but different states (like enabled or disabled). The Flyweight pattern can optimize memory by sharing button visuals.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Button {
    void render(String state);
}

// ConcreteFlyweight
class ButtonType implements Button {
    private final String color;
    private final String shape;

    public ButtonType(String color, String shape) {
        this.color = color;
        this.shape = shape;
    }

    @Override
    public void render(String state) {
        System.out.println("Rendering a " + state + " " + color + " " + shape + " button.");
    }
}

// FlyweightFactory
class ButtonFactory {
    private final Map<String, ButtonType> buttonTypes = new HashMap<>();

    public ButtonType getButtonType(String color, String shape) {
        String key = color + "-" + shape;
        if (!buttonTypes.containsKey(key)) {
            buttonTypes.put(key, new ButtonType(color, shape));
        }
        return buttonTypes.get(key);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ButtonFactory factory = new ButtonFactory();

        // Creating button types
        ButtonType redRoundButton = factory.getButtonType("Red", "Round");
        ButtonType greenSquareButton = factory.getButtonType("Green", "Square");
        ButtonType redRoundButton2 = factory.getButtonType("Red", "Round"); // Shared

        // Rendering buttons
        redRoundButton.render("enabled");
        greenSquareButton.render("disabled");
        redRoundButton2.render("hovered"); // Uses shared instance
    }
}
```

### Output
```
Rendering a enabled Red Round button.
Rendering a disabled Green Square button.
Rendering a hovered Red Round button.
```

### Explanation
- **Flyweight**: `Button` interface defines shared behaviors.
- **ConcreteFlyweight**: `ButtonType` class implements the interface and holds intrinsic state (color and shape).
- **FlyweightFactory**: `ButtonFactory` manages the creation and sharing of `ButtonType` instances.
- **Client Code**: The client retrieves button types from the factory, reusing instances when possible.

---

### Example 3: **Document Processing System**

#### Scenario
In a document processing system, many documents can have the same formatting attributes (like font type and size) but differ in their content. The Flyweight pattern can efficiently manage shared formatting information.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface DocumentFormat {
    void applyFormat(String content);
}

// ConcreteFlyweight
class TextFormat implements DocumentFormat {
    private final String font;
    private final int size;

    public TextFormat(String font, int size) {
        this.font = font;
        this.size = size;
    }

    @Override
    public void applyFormat(String content) {
        System.out.println("Formatting text: '" + content + "' with font: " + font + " and size: " + size);
    }
}

// FlyweightFactory
class FormatFactory {
    private final Map<String, TextFormat> formats = new HashMap<>();

    public TextFormat getFormat(String font, int size) {
        String key = font + "-" + size;
        if (!formats.containsKey(key)) {
            formats.put(key, new TextFormat(font, size));
        }
        return formats.get(key);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        FormatFactory factory = new FormatFactory();

        // Creating document formats
        TextFormat arial12 = factory.getFormat("Arial", 12);
        TextFormat arial14 = factory.getFormat("Arial", 14);
        TextFormat arial12Shared = factory.getFormat("Arial", 12); // Shared

        // Applying formats to content
        arial12.applyFormat("Hello World");
        arial14.applyFormat("Flyweight Pattern");
        arial12Shared.applyFormat("Shared Instance Example"); // Uses shared instance
    }
}
```

### Output
```
Formatting text: 'Hello World' with font: Arial and size: 12
Formatting text: 'Flyweight Pattern' with font: Arial and size: 14
Formatting text: 'Shared Instance Example' with font: Arial and size: 12
```

### Explanation
- **Flyweight**: `DocumentFormat` interface defines shared behaviors.
- **ConcreteFlyweight**: `TextFormat` class implements the interface and holds intrinsic state (font and size).
- **FlyweightFactory**: `FormatFactory` manages the creation and sharing of `TextFormat` instances.
- **Client Code**: The client retrieves formats from the factory, reusing instances when possible.

---

### Summary
These examples demonstrate the **Flyweight Pattern** in various industries, such as gaming, web applications, and document processing systems. By sharing common states and minimizing object creation, the Flyweight pattern effectively optimizes memory usage and improves performance, making it suitable for scenarios with numerous similar objects.