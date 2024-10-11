Here are more industry-related examples of the **Observer Pattern** along with explanations:

### Example 1: Notification System

In a notification system for an e-commerce platform, various services (like email, SMS, and push notifications) need to be informed whenever there is an event such as an order placed or an order shipped.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface OrderSubject {
    void registerObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers();
}

// Observer
interface OrderObserver {
    void update(String orderStatus);
}

// Concrete Subject
class OrderService implements OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();
    private String orderStatus;

    @Override
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(orderStatus);
        }
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        notifyObservers();
    }
}

// Concrete Observer
class EmailNotification implements OrderObserver {
    @Override
    public void update(String orderStatus) {
        System.out.println("Email Notification: Your order status is now " + orderStatus);
    }
}

// Another Concrete Observer
class SMSNotification implements OrderObserver {
    @Override
    public void update(String orderStatus) {
        System.out.println("SMS Notification: Your order status is now " + orderStatus);
    }
}

// Client code
public class NotificationSystemDemo {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();
        
        orderService.registerObserver(emailNotification);
        orderService.registerObserver(smsNotification);
        
        orderService.setOrderStatus("Order Placed");
        orderService.setOrderStatus("Order Shipped");
    }
}
```

### Explanation

- **Subject**: `OrderSubject` interface allows observers to register and unregister.
- **Concrete Subject**: `OrderService` maintains the order status and notifies registered observers when the status changes.
- **Observer**: `OrderObserver` interface defines the update method.
- **Concrete Observers**: `EmailNotification` and `SMSNotification` implement the observer to notify users about the order status.

---

### Example 2: Social Media Feed

In a social media application, when a user posts an update, all their followers should receive a notification. The followers act as observers of the user's posts.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface User {
    void registerFollower(Follower follower);
    void removeFollower(Follower follower);
    void notifyFollowers();
}

// Observer
interface Follower {
    void update(String post);
}

// Concrete Subject
class SocialMediaUser implements User {
    private List<Follower> followers = new ArrayList<>();
    private String post;

    @Override
    public void registerFollower(Follower follower) {
        followers.add(follower);
    }

    @Override
    public void removeFollower(Follower follower) {
        followers.remove(follower);
    }

    @Override
    public void notifyFollowers() {
        for (Follower follower : followers) {
            follower.update(post);
        }
    }

    public void createPost(String post) {
        this.post = post;
        notifyFollowers();
    }
}

// Concrete Observer
class SocialMediaFollower implements Follower {
    private String name;

    public SocialMediaFollower(String name) {
        this.name = name;
    }

    @Override
    public void update(String post) {
        System.out.println(name + " received a new post: " + post);
    }
}

// Client code
public class SocialMediaDemo {
    public static void main(String[] args) {
        SocialMediaUser user = new SocialMediaUser();
        
        SocialMediaFollower follower1 = new SocialMediaFollower("Alice");
        SocialMediaFollower follower2 = new SocialMediaFollower("Bob");
        
        user.registerFollower(follower1);
        user.registerFollower(follower2);
        
        user.createPost("Hello, World!");
        user.createPost("Observer Pattern in Action!");
    }
}
```

### Explanation

- **Subject**: `User` interface allows followers to register and unregister.
- **Concrete Subject**: `SocialMediaUser` maintains the post and notifies followers when a new post is created.
- **Observer**: `Follower` interface defines the update method.
- **Concrete Observer**: `SocialMediaFollower` implements the observer to receive updates about new posts.

---

### Example 3: Online Auction System

In an online auction platform, bidders should be notified about the changes in the status of the items they are interested in. When an item is bid on, all interested bidders should be informed.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface Auction {
    void registerBidder(Bidder bidder);
    void removeBidder(Bidder bidder);
    void notifyBidders();
}

// Observer
interface Bidder {
    void update(String item, double newBid);
}

// Concrete Subject
class AuctionItem implements Auction {
    private List<Bidder> bidders = new ArrayList<>();
    private String itemName;
    private double currentBid;

    public AuctionItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void registerBidder(Bidder bidder) {
        bidders.add(bidder);
    }

    @Override
    public void removeBidder(Bidder bidder) {
        bidders.remove(bidder);
    }

    @Override
    public void notifyBidders() {
        for (Bidder bidder : bidders) {
            bidder.update(itemName, currentBid);
        }
    }

    public void placeBid(double newBid) {
        this.currentBid = newBid;
        notifyBidders();
    }
}

// Concrete Observer
class AuctionBidder implements Bidder {
    private String name;

    public AuctionBidder(String name) {
        this.name = name;
    }

    @Override
    public void update(String item, double newBid) {
        System.out.println(name + " has been notified: New bid on " + item + " is " + newBid);
    }
}

// Client code
public class AuctionDemo {
    public static void main(String[] args) {
        AuctionItem item = new AuctionItem("Antique Vase");
        
        AuctionBidder bidder1 = new AuctionBidder("Charlie");
        AuctionBidder bidder2 = new AuctionBidder("Diana");
        
        item.registerBidder(bidder1);
        item.registerBidder(bidder2);
        
        item.placeBid(150.0);
        item.placeBid(200.0);
    }
}
```

### Explanation

- **Subject**: `Auction` interface allows bidders to register and unregister.
- **Concrete Subject**: `AuctionItem` maintains the current bid and notifies bidders when a new bid is placed.
- **Observer**: `Bidder` interface defines the update method.
- **Concrete Observer**: `AuctionBidder` implements the observer to receive notifications about new bids.

---

### Conclusion

The **Observer Pattern** is versatile and applicable in various domains, such as notifications in e-commerce systems, social media feeds, and auction systems. It enhances modularity and ensures that components can react to changes without tight coupling, making your application more maintainable and extensible.