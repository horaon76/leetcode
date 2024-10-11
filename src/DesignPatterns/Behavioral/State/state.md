The **State Pattern** is a behavioral design pattern that allows an object to alter its behavior when its internal state changes. It is particularly useful for managing state transitions in an object, encapsulating the state-specific behavior, and separating the state management from the main logic of the class.

### Key Components of the State Pattern

1. **Context**: The class that has an instance of a Concrete State.
2. **State Interface**: An interface that defines the operations that can be performed in each state.
3. **Concrete States**: Implementations of the State interface, each representing a specific state and the associated behavior.

### When to Use the State Pattern

- When an object's behavior depends on its state and it must change its behavior at runtime based on that state.
- When you want to avoid large conditionals or complex state management logic within a class.
- When you have a lot of states and transitions that you want to encapsulate.

### Example 1: Vending Machine

In a vending machine, the machine has several states like **Idle**, **HasMoney**, **Dispensing**, and **OutOfOrder**. The behavior of the machine changes depending on its current state.

#### Implementation

```java
// State Interface
interface VendingMachineState {
    void insertMoney();
    void selectProduct();
    void dispenseProduct();
}

// Context
class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        currentState = new IdleState(this);
    }

    public void setState(VendingMachineState state) {
        currentState = state;
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void selectProduct() {
        currentState.selectProduct();
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }
}

// Concrete State: Idle
class IdleState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Money inserted.");
        vendingMachine.setState(new HasMoneyState(vendingMachine));
    }

    @Override
    public void selectProduct() {
        System.out.println("Insert money first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Insert money first.");
    }
}

// Concrete State: Has Money
class HasMoneyState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected.");
        vendingMachine.setState(new DispensingState(vendingMachine));
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Select a product first.");
    }
}

// Concrete State: Dispensing
class DispensingState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing product...");
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}

// Client code
public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.selectProduct(); // No money inserted
        vendingMachine.insertMoney();    // Money inserted
        vendingMachine.selectProduct();   // Product selected
        vendingMachine.dispenseProduct(); // Dispensing product
    }
}
```

### Explanation

- **Context**: `VendingMachine` manages the current state and delegates behavior to the current state.
- **State Interface**: `VendingMachineState` defines methods for the different actions.
- **Concrete States**: `IdleState`, `HasMoneyState`, and `DispensingState` implement state-specific behavior.

---

### Example 2: Document Workflow

In a document workflow system, a document can have states like **Draft**, **Review**, and **Published**. The actions that can be performed on a document depend on its current state.

#### Implementation

```java
// State Interface
interface DocumentState {
    void edit();
    void review();
    void publish();
}

// Context
class Document {
    private DocumentState currentState;

    public Document() {
        currentState = new DraftState(this);
    }

    public void setState(DocumentState state) {
        currentState = state;
    }

    public void edit() {
        currentState.edit();
    }

    public void review() {
        currentState.review();
    }

    public void publish() {
        currentState.publish();
    }
}

// Concrete State: Draft
class DraftState implements DocumentState {
    private Document document;

    public DraftState(Document document) {
        this.document = document;
    }

    @Override
    public void edit() {
        System.out.println("Editing the document...");
    }

    @Override
    public void review() {
        System.out.println("Cannot review, document is still in draft.");
    }

    @Override
    public void publish() {
        System.out.println("Document published.");
        document.setState(new PublishedState(document));
    }
}

// Concrete State: Review
class ReviewState implements DocumentState {
    private Document document;

    public ReviewState(Document document) {
        this.document = document;
    }

    @Override
    public void edit() {
        System.out.println("Editing the document...");
        document.setState(new DraftState(document));
    }

    @Override
    public void review() {
        System.out.println("Reviewing the document...");
    }

    @Override
    public void publish() {
        System.out.println("Document published.");
        document.setState(new PublishedState(document));
    }
}

// Concrete State: Published
class PublishedState implements DocumentState {
    private Document document;

    public PublishedState(Document document) {
        this.document = document;
    }

    @Override
    public void edit() {
        System.out.println("Document published. Cannot edit.");
    }

    @Override
    public void review() {
        System.out.println("Document published. Cannot review.");
    }

    @Override
    public void publish() {
        System.out.println("Document already published.");
    }
}

// Client code
public class DocumentWorkflowDemo {
    public static void main(String[] args) {
        Document document = new Document();

        document.edit();       // Editing
        document.publish();    // Publishing
        document.review();     // Cannot review
        document.publish();    // Already published
    }
}
```

### Explanation

- **Context**: `Document` manages the current state and delegates behavior to the current state.
- **State Interface**: `DocumentState` defines methods for the different actions on the document.
- **Concrete States**: `DraftState`, `ReviewState`, and `PublishedState` implement state-specific behavior.

---

### Conclusion

The **State Pattern** provides a clean way to manage state-specific behavior and transitions within an object. It helps avoid complicated conditional statements and makes it easier to add or change states without modifying the existing code. This pattern is widely applicable in scenarios involving state management, such as workflows, user interfaces, and various stateful systems.