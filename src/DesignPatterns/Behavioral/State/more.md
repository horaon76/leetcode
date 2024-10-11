Here are three industry-related examples of the **State Pattern** with implementations in Java:

### Example 1: Traffic Light System

In a traffic light system, the light can be in one of several states: **Red**, **Green**, or **Yellow**. Each state has different behaviors for what happens when the light changes.

#### Implementation

```java
// State Interface
interface TrafficLightState {
    void changeLight(TrafficLight trafficLight);
}

// Context
class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        this.currentState = new RedLightState(); // Initial state
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void changeLight() {
        currentState.changeLight(this);
    }
}

// Concrete State: Red
class RedLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("Traffic light is red. Cars must stop.");
        trafficLight.setState(new GreenLightState()); // Change to green
    }
}

// Concrete State: Green
class GreenLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("Traffic light is green. Cars can go.");
        trafficLight.setState(new YellowLightState()); // Change to yellow
    }
}

// Concrete State: Yellow
class YellowLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("Traffic light is yellow. Cars should prepare to stop.");
        trafficLight.setState(new RedLightState()); // Change to red
    }
}

// Client code
public class TrafficLightDemo {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        
        for (int i = 0; i < 6; i++) {
            trafficLight.changeLight();
        }
    }
}
```

### Explanation

- **Context**: `TrafficLight` maintains the current state and delegates the behavior to that state.
- **State Interface**: `TrafficLightState` defines the `changeLight` method for the various states.
- **Concrete States**: `RedLightState`, `GreenLightState`, and `YellowLightState` implement state-specific behavior.

---

### Example 2: ATM State Management

In an ATM system, the ATM can be in various states like **Idle**, **CardInserted**, **PinEntered**, and **TransactionCompleted**. The operations allowed depend on the current state.

#### Implementation

```java
// State Interface
interface ATMState {
    void insertCard(ATM atm);
    void enterPin(ATM atm, String pin);
    void withdrawCash(ATM atm, double amount);
}

// Context
class ATM {
    private ATMState currentState;

    public ATM() {
        currentState = new IdleState(this); // Initial state
    }

    public void setState(ATMState state) {
        currentState = state;
    }

    public void insertCard() {
        currentState.insertCard(this);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void withdrawCash(double amount) {
        currentState.withdrawCash(this, amount);
    }
}

// Concrete State: Idle
class IdleState implements ATMState {
    @Override
    public void insertCard(ATM atm) {
        System.out.println("Card inserted.");
        atm.setState(new CardInsertedState());
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Insert card first.");
    }

    @Override
    public void withdrawCash(ATM atm, double amount) {
        System.out.println("Insert card first.");
    }
}

// Concrete State: Card Inserted
class CardInsertedState implements ATMState {
    @Override
    public void insertCard(ATM atm) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("PIN entered: " + pin);
        atm.setState(new PinEnteredState());
    }

    @Override
    public void withdrawCash(ATM atm, double amount) {
        System.out.println("Enter PIN first.");
    }
}

// Concrete State: PIN Entered
class PinEnteredState implements ATMState {
    @Override
    public void insertCard(ATM atm) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("PIN already entered.");
    }

    @Override
    public void withdrawCash(ATM atm, double amount) {
        System.out.println("Withdrawing cash: $" + amount);
        atm.setState(new TransactionCompletedState());
    }
}

// Concrete State: Transaction Completed
class TransactionCompletedState implements ATMState {
    @Override
    public void insertCard(ATM atm) {
        System.out.println("Please remove your card first.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Please remove your card first.");
    }

    @Override
    public void withdrawCash(ATM atm, double amount) {
        System.out.println("Transaction already completed.");
    }
}

// Client code
public class ATMDemo {
    public static void main(String[] args) {
        ATM atm = new ATM();
        
        atm.withdrawCash(100); // Insert card first
        atm.insertCard();      // Card inserted
        atm.withdrawCash(100); // Enter PIN first
        atm.enterPin("1234");  // PIN entered
        atm.withdrawCash(100);  // Withdrawing cash
    }
}
```

### Explanation

- **Context**: `ATM` manages the current state and delegates behavior to that state.
- **State Interface**: `ATMState` defines methods for the different actions on the ATM.
- **Concrete States**: `IdleState`, `CardInsertedState`, `PinEnteredState`, and `TransactionCompletedState` implement state-specific behavior.

---

### Example 3: Online Shopping Cart

In an online shopping application, a shopping cart can be in states like **Empty**, **ItemsAdded**, **CheckoutInitiated**, and **OrderPlaced**. The available actions depend on the current state of the cart.

#### Implementation

```java
// State Interface
interface ShoppingCartState {
    void addItem(ShoppingCart cart);
    void initiateCheckout(ShoppingCart cart);
    void placeOrder(ShoppingCart cart);
}

// Context
class ShoppingCart {
    private ShoppingCartState currentState;

    public ShoppingCart() {
        currentState = new EmptyCartState(); // Initial state
    }

    public void setState(ShoppingCartState state) {
        currentState = state;
    }

    public void addItem() {
        currentState.addItem(this);
    }

    public void initiateCheckout() {
        currentState.initiateCheckout(this);
    }

    public void placeOrder() {
        currentState.placeOrder(this);
    }
}

// Concrete State: Empty Cart
class EmptyCartState implements ShoppingCartState {
    @Override
    public void addItem(ShoppingCart cart) {
        System.out.println("Item added to cart.");
        cart.setState(new ItemsAddedState());
    }

    @Override
    public void initiateCheckout(ShoppingCart cart) {
        System.out.println("Cart is empty. Cannot initiate checkout.");
    }

    @Override
    public void placeOrder(ShoppingCart cart) {
        System.out.println("Cart is empty. Cannot place order.");
    }
}

// Concrete State: Items Added
class ItemsAddedState implements ShoppingCartState {
    @Override
    public void addItem(ShoppingCart cart) {
        System.out.println("Item added to cart.");
    }

    @Override
    public void initiateCheckout(ShoppingCart cart) {
        System.out.println("Initiating checkout...");
        cart.setState(new CheckoutInitiatedState());
    }

    @Override
    public void placeOrder(ShoppingCart cart) {
        System.out.println("Cannot place order. Please initiate checkout first.");
    }
}

// Concrete State: Checkout Initiated
class CheckoutInitiatedState implements ShoppingCartState {
    @Override
    public void addItem(ShoppingCart cart) {
        System.out.println("Cannot add items. Checkout in progress.");
    }

    @Override
    public void initiateCheckout(ShoppingCart cart) {
        System.out.println("Checkout already initiated.");
    }

    @Override
    public void placeOrder(ShoppingCart cart) {
        System.out.println("Order placed successfully!");
        cart.setState(new OrderPlacedState());
    }
}

// Concrete State: Order Placed
class OrderPlacedState implements ShoppingCartState {
    @Override
    public void addItem(ShoppingCart cart) {
        System.out.println("Cannot add items. Order already placed.");
    }

    @Override
    public void initiateCheckout(ShoppingCart cart) {
        System.out.println("Order already placed. Cannot initiate checkout.");
    }

    @Override
    public void placeOrder(ShoppingCart cart) {
        System.out.println("Order has already been placed.");
    }
}

// Client code
public class ShoppingCartDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        cart.addItem();             // Item added to cart
        cart.initiateCheckout();    // Initiating checkout
        cart.placeOrder();          // Order placed successfully!
        cart.addItem();             // Cannot add items. Order already placed.
    }
}
```

### Explanation

- **Context**: `ShoppingCart` manages the current state and delegates behavior to that state.
- **State Interface**: `ShoppingCartState` defines methods for the different actions on the