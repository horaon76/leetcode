The **Flyweight Pattern** is a structural design pattern that enables sharing of objects to support a large number of similar objects efficiently. This pattern is particularly useful when dealing with a large number of objects that share common state, allowing for significant memory savings and performance improvements.

### Key Benefits
- **Memory Efficiency**: Reduces memory usage by sharing common state across similar objects.
- **Performance Improvement**: Minimizes the overhead of object creation and management.
- **Separation of Concerns**: Helps in separating intrinsic state (shared) from extrinsic state (unique to each object).

### Components
1. **Flyweight**: An interface or abstract class that declares methods for using the intrinsic state.
2. **ConcreteFlyweight**: Implements the Flyweight interface and stores the intrinsic state.
3. **FlyweightFactory**: Responsible for managing the Flyweight objects and ensuring that they are shared correctly.

### Example 1: **Text Formatting in a Document Editor**

#### Scenario
In a text editor, many characters can share the same font and style attributes. The Flyweight pattern can be used to manage the common character attributes efficiently.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Character {
    void display(int fontSize);
}

// ConcreteFlyweight
class ConcreteCharacter implements Character {
    private final char symbol;

    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int fontSize) {
        System.out.println("Character: " + symbol + ", Font Size: " + fontSize);
    }
}

// FlyweightFactory
class CharacterFactory {
    private final Map<Character, ConcreteCharacter> characters = new HashMap<>();

    public ConcreteCharacter getCharacter(char symbol) {
        if (!characters.containsKey(symbol)) {
            characters.put(symbol, new ConcreteCharacter(symbol));
        }
        return characters.get(symbol);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        // Creating characters
        Character a = factory.getCharacter('a');
        Character b = factory.getCharacter('b');
        Character c = factory.getCharacter('c');
        Character a2 = factory.getCharacter('a'); // Shared

        // Displaying characters
        a.display(12);
        b.display(12);
        c.display(12);
        a2.display(12); // Uses shared instance
    }
}
```

### Output
```
Character: a, Font Size: 12
Character: b, Font Size: 12
Character: c, Font Size: 12
Character: a, Font Size: 12
```

### Explanation
- **Flyweight**: `Character` interface represents the shared state.
- **ConcreteFlyweight**: `ConcreteCharacter` class implements the interface and holds the intrinsic state (the character itself).
- **FlyweightFactory**: `CharacterFactory` manages the creation and sharing of `ConcreteCharacter` instances.
- **Client Code**: The client retrieves characters from the factory, reusing instances when possible.

---

### Example 2: **Game Development (Sprite Management)**

#### Scenario
In a game, multiple instances of similar sprites (like trees, rocks, or enemies) can share visual properties. The Flyweight pattern can optimize memory usage by sharing these properties.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Sprite {
    void draw(int x, int y);
}

// ConcreteFlyweight
class TreeSprite implements Sprite {
    private final String treeType;

    public TreeSprite(String treeType) {
        this.treeType = treeType;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing " + treeType + " at (" + x + ", " + y + ")");
    }
}

// FlyweightFactory
class SpriteFactory {
    private final Map<String, Sprite> spriteMap = new HashMap<>();

    public Sprite getTreeSprite(String treeType) {
        if (!spriteMap.containsKey(treeType)) {
            spriteMap.put(treeType, new TreeSprite(treeType));
        }
        return spriteMap.get(treeType);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        SpriteFactory spriteFactory = new SpriteFactory();

        // Creating and drawing tree sprites
        Sprite oak = spriteFactory.getTreeSprite("Oak");
        Sprite pine = spriteFactory.getTreeSprite("Pine");
        Sprite oak2 = spriteFactory.getTreeSprite("Oak"); // Shared

        oak.draw(10, 20);
        pine.draw(30, 40);
        oak2.draw(50, 60); // Uses shared instance
    }
}
```

### Output
```
Drawing Oak at (10, 20)
Drawing Pine at (30, 40)
Drawing Oak at (50, 60)
```

### Explanation
- **Flyweight**: `Sprite` interface represents the shared methods.
- **ConcreteFlyweight**: `TreeSprite` class implements the interface and holds the intrinsic state (tree type).
- **FlyweightFactory**: `SpriteFactory` manages the creation and sharing of `TreeSprite` instances.
- **Client Code**: The client retrieves sprites from the factory, reusing instances when possible.

---

### Example 3: **Social Media Application (User Profiles)**

#### Scenario
In a social media application, many users might share common profile attributes (e.g., profile pictures, default settings). The Flyweight pattern can help manage these shared attributes efficiently.

#### Implementation

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight
interface UserProfile {
    void displayProfile(String userName);
}

// ConcreteFlyweight
class ConcreteUserProfile implements UserProfile {
    private final String profilePicture;

    public ConcreteUserProfile(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public void displayProfile(String userName) {
        System.out.println("User: " + userName + ", Profile Picture: " + profilePicture);
    }
}

// FlyweightFactory
class UserProfileFactory {
    private final Map<String, UserProfile> profiles = new HashMap<>();

    public UserProfile getUserProfile(String profilePicture) {
        if (!profiles.containsKey(profilePicture)) {
            profiles.put(profilePicture, new ConcreteUserProfile(profilePicture));
        }
        return profiles.get(profilePicture);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        UserProfileFactory factory = new UserProfileFactory();

        // Creating user profiles
        UserProfile user1 = factory.getUserProfile("default.jpg");
        UserProfile user2 = factory.getUserProfile("avatar.png");
        UserProfile user3 = factory.getUserProfile("default.jpg"); // Shared

        // Displaying user profiles
        user1.displayProfile("Alice");
        user2.displayProfile("Bob");
        user3.displayProfile("Charlie"); // Uses shared instance
    }
}
```

### Output
```
User: Alice, Profile Picture: default.jpg
User: Bob, Profile Picture: avatar.png
User: Charlie, Profile Picture: default.jpg
```

### Explanation
- **Flyweight**: `UserProfile` interface represents the shared methods.
- **ConcreteFlyweight**: `ConcreteUserProfile` class implements the interface and holds the intrinsic state (profile picture).
- **FlyweightFactory**: `UserProfileFactory` manages the creation and sharing of `ConcreteUserProfile` instances.
- **Client Code**: The client retrieves user profiles from the factory, reusing instances when possible.

---

### Summary
The **Flyweight Pattern** is beneficial in scenarios where a large number of similar objects are needed, such as text formatting in document editors, sprite management in game development, and user profiles in social media applications. By sharing intrinsic states, the Flyweight pattern optimizes memory usage and improves performance, allowing applications to handle large data sets efficiently.