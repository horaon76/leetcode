The Observer Pattern is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object (the subject) changes its state, all its dependents (observers) are notified and updated automatically. This pattern is commonly used to implement distributed event handling systems, such as in GUI frameworks, event-driven programming, and real-time data feeds.

### Key Components

1. **Subject**: The object being observed. It maintains a list of observers and provides methods to add, remove, and notify observers.

2. **Observer**: The interface or abstract class that defines the method to be called when the subject's state changes.

3. **Concrete Subject**: A concrete implementation of the Subject that holds the state and notifies observers of changes.

4. **Concrete Observer**: A concrete implementation of the Observer that updates its state based on changes in the subject.

### Example Scenarios

1. **Weather Monitoring System**
2. **Stock Price Tracking**
3. **User Interface Events**

---

### Example 1: Weather Monitoring System

In a weather monitoring application, various display elements (like current conditions, statistics, and forecast) need to update automatically when the weather data changes.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface WeatherData {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Concrete Subject
class WeatherStation implements WeatherData {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

// Concrete Observer
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
    }
}

// Client code
public class WeatherStationDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();

        weatherStation.registerObserver(currentDisplay);

        weatherStation.setMeasurements(30.4f, 65.0f, 1013.1f);
        weatherStation.setMeasurements(28.8f, 70.0f, 1010.0f);
    }
}
```

### Explanation

- **Subject**: `WeatherData` interface defines methods for managing observers.
- **Concrete Subject**: `WeatherStation` implements the subject and holds weather data. It notifies all registered observers whenever the data changes.
- **Observer**: `Observer` interface defines the update method.
- **Concrete Observer**: `CurrentConditionsDisplay` implements the observer and updates its display based on changes in weather data.

---

### Example 2: Stock Price Tracking

In a stock trading application, multiple components may need to display the current price of a stock. When the stock price changes, all interested parties should be notified.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface Stock {
    void registerObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers();
}

// Observer
interface StockObserver {
    void update(float price);
}

// Concrete Subject
class StockMarket implements Stock {
    private List<StockObserver> observers;
    private float price;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(price);
        }
    }

    public void setPrice(float price) {
        this.price = price;
        notifyObservers();
    }
}

// Concrete Observer
class StockPriceDisplay implements StockObserver {
    private float price;

    @Override
    public void update(float price) {
        this.price = price;
        display();
    }

    public void display() {
        System.out.println("Current Stock Price: " + price);
    }
}

// Client code
public class StockMarketDemo {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        StockPriceDisplay stockDisplay = new StockPriceDisplay();

        stockMarket.registerObserver(stockDisplay);

        stockMarket.setPrice(100.0f);
        stockMarket.setPrice(102.5f);
        stockMarket.setPrice(98.0f);
    }
}
```

### Explanation

- **Subject**: `Stock` interface allows observers to register and unregister.
- **Concrete Subject**: `StockMarket` maintains the stock price and notifies observers of price changes.
- **Observer**: `StockObserver` interface defines the update method for stock price changes.
- **Concrete Observer**: `StockPriceDisplay` implements the observer to display current stock prices.

---

### Example 3: User Interface Events

In a GUI application, components may need to respond to user actions such as button clicks. When a button is clicked, all registered listeners should be notified.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface Button {
    void addListener(ButtonListener listener);
    void removeListener(ButtonListener listener);
    void notifyListeners();
}

// Observer
interface ButtonListener {
    void onClick();
}

// Concrete Subject
class ConcreteButton implements Button {
    private List<ButtonListener> listeners = new ArrayList<>();

    @Override
    public void addListener(ButtonListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ButtonListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners() {
        for (ButtonListener listener : listeners) {
            listener.onClick();
        }
    }

    public void click() {
        System.out.println("Button clicked!");
        notifyListeners();
    }
}

// Concrete Observer
class ButtonClickListener implements ButtonListener {
    private String name;

    public ButtonClickListener(String name) {
        this.name = name;
    }

    @Override
    public void onClick() {
        System.out.println(name + " received button click event.");
    }
}

// Client code
public class ButtonDemo {
    public static void main(String[] args) {
        ConcreteButton button = new ConcreteButton();
        ButtonClickListener listener1 = new ButtonClickListener("Listener 1");
        ButtonClickListener listener2 = new ButtonClickListener("Listener 2");

        button.addListener(listener1);
        button.addListener(listener2);

        button.click(); // Simulate button click
    }
}
```

### Explanation

- **Subject**: `Button` interface manages listeners.
- **Concrete Subject**: `ConcreteButton` implements the subject, notifying listeners when the button is clicked.
- **Observer**: `ButtonListener` interface defines the onClick method.
- **Concrete Observer**: `ButtonClickListener` implements the observer to respond to button click events.

---

### Conclusion

The Observer Pattern is highly useful in scenarios where a change in one part of the system needs to be communicated to multiple dependent components. It promotes loose coupling and enhances flexibility, making it ideal for applications like weather monitoring systems, stock price tracking, and user interface events. By implementing this pattern, you can build scalable and maintainable systems that respond dynamically to changes in state.