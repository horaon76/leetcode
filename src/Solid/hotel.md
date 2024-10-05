Let’s create a complete example of a **Hotel Management System** that demonstrates the SOLID principles. This example will cover various aspects of a hotel management system, including room management, booking, and notification services.

### Example: Hotel Management System

#### Step 1: Define the Domain Models

We’ll start with the core models: `Room`, `Booking`, and `Customer`.

```java
// Room class
class Room {
    private int roomNumber;
    private String roomType; // e.g., "Single", "Double", "Suite"
    private boolean isAvailable;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true; // By default, a room is available
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Customer class
class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
```

#### Step 2: Booking Class

The `Booking` class is responsible for managing room bookings.

```java
import java.util.Date;

class Booking {
    private Room room;
    private Customer customer;
    private Date startDate;
    private Date endDate;

    public Booking(Room room, Customer customer, Date startDate, Date endDate) {
        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
```

#### Step 3: Hotel Class

The `Hotel` class manages rooms and bookings and follows the Single Responsibility Principle (SRP).

```java
import java.util.ArrayList;
import java.util.List;

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void bookRoom(Room room, Customer customer, Date startDate, Date endDate) {
        if (room.isAvailable()) {
            room.setAvailable(false);
            Booking booking = new Booking(room, customer, startDate, endDate);
            bookings.add(booking);
        } else {
            System.out.println("Room is not available!");
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
```

#### Step 4: NotificationService Interface and Implementations

To adhere to the Open/Closed Principle (OCP), we will create an interface for notifications.

```java
interface NotificationService {
    void notifyCustomer(Customer customer, String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void notifyCustomer(Customer customer, String message) {
        System.out.println("Sending email to " + customer.getEmail() + ": " + message);
    }
}

class SMSNotificationService implements NotificationService {
    @Override
    public void notifyCustomer(Customer customer, String message) {
        System.out.println("Sending SMS to " + customer.getName() + ": " + message);
    }
}
```

#### Step 5: BookingManager Class

The `BookingManager` class handles bookings and notifications. It encapsulates the logic related to booking management and adheres to the SOLID principles.

```java
class BookingManager {
    private Hotel hotel;
    private NotificationService notificationService;

    public BookingManager(Hotel hotel, NotificationService notificationService) {
        this.hotel = hotel;
        this.notificationService = notificationService;
    }

    public void bookRoom(Room room, Customer customer, Date startDate, Date endDate) {
        hotel.bookRoom(room, customer, startDate, endDate);
        notificationService.notifyCustomer(customer, "Your booking for room " + room.getRoomNumber() + " is confirmed.");
    }
}
```

#### Step 6: Main Application

Now, let’s put everything together in a main application to demonstrate the functionality.

```java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelManagementApplication {
    public static void main(String[] args) throws ParseException {
        // Create hotel
        Hotel hotel = new Hotel();

        // Add rooms
        hotel.addRoom(new Room(101, "Single"));
        hotel.addRoom(new Room(102, "Double"));
        hotel.addRoom(new Room(103, "Suite"));

        // Create customers
        Customer alice = new Customer("Alice", "alice@example.com");
        Customer bob = new Customer("Bob", "bob@example.com");

        // Choose a notification service
        NotificationService notificationService = new EmailNotificationService(); // or new SMSNotificationService();

        // Create booking manager
        BookingManager bookingManager = new BookingManager(hotel, notificationService);

        // Simulate room booking
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2024-10-01");
        Date endDate = dateFormat.parse("2024-10-05");

        bookingManager.bookRoom(hotel.getAvailableRooms().get(0), alice, startDate, endDate); // Book room 101 for Alice
        bookingManager.bookRoom(hotel.getAvailableRooms().get(0), bob, startDate, endDate); // Attempt to book room 101 for Bob
    }
}
```

### Summary of SOLID Principles Applied

1. **Single Responsibility Principle (SRP)**:
    - `Room`, `Customer`, and `Booking` classes each have a single responsibility.
    - `Hotel` manages room and booking logic.
    - `BookingManager` handles the booking process and notifications.

2. **Open/Closed Principle (OCP)**:
    - The `NotificationService` interface allows for new notification methods (like push notifications) to be added without modifying existing code.

3. **Liskov Substitution Principle (LSP)**:
    - Any implementation of the `NotificationService` can be used interchangeably without affecting the behavior of the `BookingManager`. For example, you can switch from `EmailNotificationService` to `SMSNotificationService` seamlessly.

4. **Interface Segregation Principle (ISP)**:
    - The `NotificationService` interface is focused, ensuring that clients only implement the methods they need. For instance, if we later create a `PushNotificationService`, it will only implement the notification method without being burdened with unrelated methods.

5. **Dependency Inversion Principle (DIP)**:
    - `BookingManager` depends on the `NotificationService` abstraction, not on concrete implementations. This decoupling allows for easier testing and swapping of notification services.

### Conclusion

This complete example illustrates the application of all five SOLID principles in a hotel management system. By following these principles, the system is modular, maintainable, and extensible. Each component is responsible for a specific function, and the design allows for easy adaptation as new requirements emerge, such as adding different types of notifications or additional features related to room management and customer services.