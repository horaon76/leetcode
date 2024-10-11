Here are several industry examples of the Memento Pattern implemented in Java, showcasing various applications where state management is essential.

### Example 1: Game Character State Management

In a gaming context, you might want to save a character's state (like health, level, and inventory) so that players can revert to a previous state.

#### Implementation

```java
// Memento
class CharacterMemento {
    private final String level;
    private final int health;
    private final String inventory;

    public CharacterMemento(String level, int health, String inventory) {
        this.level = level;
        this.health = health;
        this.inventory = inventory;
    }

    public String getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public String getInventory() {
        return inventory;
    }
}

// Originator
class GameCharacter {
    private String level;
    private int health;
    private String inventory;

    public void setState(String level, int health, String inventory) {
        this.level = level;
        this.health = health;
        this.inventory = inventory;
    }

    public CharacterMemento save() {
        return new CharacterMemento(level, health, inventory);
    }

    public void restore(CharacterMemento memento) {
        this.level = memento.getLevel();
        this.health = memento.getHealth();
        this.inventory = memento.getInventory();
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "level='" + level + '\'' +
                ", health=" + health +
                ", inventory='" + inventory + '\'' +
                '}';
    }
}

// Caretaker
class CharacterCaretaker {
    private final List<CharacterMemento> mementos = new ArrayList<>();
    private final GameCharacter character;

    public CharacterCaretaker(GameCharacter character) {
        this.character = character;
    }

    public void save() {
        mementos.add(character.save());
    }

    public void undo() {
        if (!mementos.isEmpty()) {
            CharacterMemento memento = mementos.remove(mementos.size() - 1);
            character.restore(memento);
        }
    }
}

// Client code
public class MementoPatternGameCharacterDemo {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter();
        CharacterCaretaker caretaker = new CharacterCaretaker(character);

        character.setState("Level 1", 100, "Sword");
        caretaker.save();

        character.setState("Level 2", 80, "Sword, Shield");
        caretaker.save();

        character.setState("Level 3", 60, "Sword, Shield, Potion");
        System.out.println("Current State: " + character);

        caretaker.undo();
        System.out.println("After Undo: " + character);

        caretaker.undo();
        System.out.println("After Undo: " + character);
    }
}
```

### Explanation
- **Memento**: `CharacterMemento` stores the character's state (level, health, inventory).
- **Originator**: `GameCharacter` allows saving and restoring its state.
- **Caretaker**: `CharacterCaretaker` manages the character's mementos, enabling undo operations.

---

### Example 2: Text Editor with Undo Functionality

In a text editor, users often need the ability to undo changes to their text.

#### Implementation

```java
// Memento
class TextEditorMemento {
    private final String text;

    public TextEditorMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Originator
class TextEditor {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TextEditorMemento save() {
        return new TextEditorMemento(text);
    }

    public void restore(TextEditorMemento memento) {
        text = memento.getText();
    }
}

// Caretaker
class TextEditorCaretaker {
    private final List<TextEditorMemento> mementos = new ArrayList<>();
    private final TextEditor editor;

    public TextEditorCaretaker(TextEditor editor) {
        this.editor = editor;
    }

    public void save() {
        mementos.add(editor.save());
    }

    public void undo() {
        if (!mementos.isEmpty()) {
            TextEditorMemento memento = mementos.remove(mementos.size() - 1);
            editor.restore(memento);
        }
    }
}

// Client code
public class MementoPatternTextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorCaretaker caretaker = new TextEditorCaretaker(editor);

        editor.setText("First version of the text.");
        caretaker.save();

        editor.setText("Second version of the text.");
        caretaker.save();

        editor.setText("Final version of the text.");
        System.out.println("Current Text: " + editor.getText());

        caretaker.undo();
        System.out.println("After Undo: " + editor.getText());

        caretaker.undo();
        System.out.println("After Undo: " + editor.getText());
    }
}
```

### Explanation
- **Memento**: `TextEditorMemento` captures the state of the text.
- **Originator**: `TextEditor` allows saving and restoring its content.
- **Caretaker**: `TextEditorCaretaker` manages mementos, facilitating undo operations.

---

### Example 3: Document Version Control

In a document management system, you may want to save versions of a document.

#### Implementation

```java
// Memento
class DocumentMemento {
    private final String content;

    public DocumentMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Originator
class Document {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public DocumentMemento save() {
        return new DocumentMemento(content);
    }

    public void restore(DocumentMemento memento) {
        content = memento.getContent();
    }
}

// Caretaker
class DocumentCaretaker {
    private final List<DocumentMemento> mementos = new ArrayList<>();
    private final Document document;

    public DocumentCaretaker(Document document) {
        this.document = document;
    }

    public void save() {
        mementos.add(document.save());
    }

    public void undo() {
        if (!mementos.isEmpty()) {
            DocumentMemento memento = mementos.remove(mementos.size() - 1);
            document.restore(memento);
        }
    }
}

// Client code
public class MementoPatternDocumentVersioningDemo {
    public static void main(String[] args) {
        Document document = new Document();
        DocumentCaretaker caretaker = new DocumentCaretaker(document);

        document.setContent("Version 1");
        caretaker.save();

        document.setContent("Version 2");
        caretaker.save();

        document.setContent("Version 3");
        System.out.println("Current Document: " + document.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + document.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + document.getContent());
    }
}
```

### Explanation
- **Memento**: `DocumentMemento` captures the content of the document.
- **Originator**: `Document` allows saving and restoring its content.
- **Caretaker**: `DocumentCaretaker` manages document states, enabling undo functionality.

---

### Conclusion

The Memento Pattern is widely applicable in scenarios that require state management and rollback functionality. Each of these examples demonstrates how to implement the pattern in different contexts, allowing developers to easily restore previous states while encapsulating the state details. This pattern is particularly useful in applications like text editors, games, and document management systems, making it an essential part of software design.