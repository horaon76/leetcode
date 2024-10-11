The **Memento Pattern** is a behavioral design pattern that allows an object to capture its internal state and save it externally without violating encapsulation. This enables the object to be restored to this saved state later on. It's especially useful in scenarios where you want to implement undo/redo functionality.

### Key Components

1. **Memento**: A snapshot of the object's internal state.
2. **Originator**: The object whose state needs to be saved or restored.
3. **Caretaker**: Manages the memento and is responsible for saving and restoring the state.

### Use Cases
- Text editors (for undo/redo operations).
- Game state management (to save game progress).
- Version control systems (to track changes).

### Example 1: Text Editor

#### Implementation

```java
// Memento
class TextEditorMemento {
    private final String content;

    public TextEditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Originator
class TextEditor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public TextEditorMemento save() {
        return new TextEditorMemento(content);
    }

    public void restore(TextEditorMemento memento) {
        content = memento.getContent();
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
        if (mementos.size() > 0) {
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

        editor.setContent("Version 1");
        caretaker.save();

        editor.setContent("Version 2");
        caretaker.save();

        editor.setContent("Version 3");
        System.out.println("Current Content: " + editor.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + editor.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + editor.getContent());
    }
}
```

### Explanation
- **Memento**: The `TextEditorMemento` class stores the content of the text editor.
- **Originator**: The `TextEditor` class can save and restore its state using mementos.
- **Caretaker**: The `TextEditorCaretaker` class manages the saved states and allows for undo operations.

---

### Example 2: Game State Management

In a gaming application, you may want to save and restore player states, like positions, scores, and levels.

#### Implementation

```java
// Memento
class GameStateMemento {
    private final String level;
    private final int score;

    public GameStateMemento(String level, int score) {
        this.level = level;
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}

// Originator
class Game {
    private String level;
    private int score;

    public void setState(String level, int score) {
        this.level = level;
        this.score = score;
    }

    public GameStateMemento save() {
        return new GameStateMemento(level, score);
    }

    public void restore(GameStateMemento memento) {
        this.level = memento.getLevel();
        this.score = memento.getScore();
    }

    @Override
    public String toString() {
        return "Level: " + level + ", Score: " + score;
    }
}

// Caretaker
class GameCaretaker {
    private final List<GameStateMemento> mementos = new ArrayList<>();
    private final Game game;

    public GameCaretaker(Game game) {
        this.game = game;
    }

    public void save() {
        mementos.add(game.save());
    }

    public void undo() {
        if (mementos.size() > 0) {
            GameStateMemento memento = mementos.remove(mementos.size() - 1);
            game.restore(memento);
        }
    }
}

// Client code
public class MementoPatternGameDemo {
    public static void main(String[] args) {
        Game game = new Game();
        GameCaretaker caretaker = new GameCaretaker(game);

        game.setState("Level 1", 100);
        caretaker.save();

        game.setState("Level 2", 200);
        caretaker.save();

        game.setState("Level 3", 300);
        System.out.println("Current State: " + game);

        caretaker.undo();
        System.out.println("After Undo: " + game);

        caretaker.undo();
        System.out.println("After Undo: " + game);
    }
}
```

### Explanation
- **Memento**: The `GameStateMemento` class captures the level and score of the game.
- **Originator**: The `Game` class can save and restore its state through mementos.
- **Caretaker**: The `GameCaretaker` class manages the game states, allowing for undo operations.

---

### Example 3: Document Versioning in an Office Application

In an office application, users often want to save versions of their documents.

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
        if (mementos.size() > 0) {
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

        document.setContent("Draft 1");
        caretaker.save();

        document.setContent("Draft 2");
        caretaker.save();

        document.setContent("Final Draft");
        System.out.println("Current Document: " + document.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + document.getContent());

        caretaker.undo();
        System.out.println("After Undo: " + document.getContent());
    }
}
```

### Explanation
- **Memento**: The `DocumentMemento` class captures the content of the document.
- **Originator**: The `Document` class can save and restore its state through mementos.
- **Caretaker**: The `DocumentCaretaker` class manages the document's states, allowing for undo operations.

---

### Conclusion
The Memento Pattern is particularly useful when you need to implement undo/redo functionality in applications. It encapsulates the internal state of objects while allowing them to be restored to a previous state without exposing their internal structure. This pattern is widely used in text editors, games, and version control systems, making it valuable across various domains.