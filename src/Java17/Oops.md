Object-Oriented Programming (OOP) is a programming paradigm that uses "objects" to design software. It is based on several key concepts that help organize and structure code, making it more modular, reusable, and easier to understand. The four main principles of OOP are **Encapsulation**, **Inheritance**, **Polymorphism**, and **Abstraction**. Let’s explore these concepts in detail with examples.

### Key Principles of OOP

1. **Encapsulation**:
    - **Definition**: Encapsulation is the practice of bundling the data (attributes) and methods (functions) that operate on that data into a single unit, or class. It restricts direct access to some of the object's components, which helps prevent unintended interference and misuse of the methods and data.
    - **Example**:
   ```java
   public class BankAccount {
       private double balance; // Private variable

       public BankAccount(double initialBalance) {
           this.balance = initialBalance;
       }

       public void deposit(double amount) {
           if (amount > 0) {
               balance += amount; // Accessing the private variable
           }
       }

       public void withdraw(double amount) {
           if (amount > 0 && amount <= balance) {
               balance -= amount; // Accessing the private variable
           }
       }

       public double getBalance() {
           return balance; // Getter method to access the private variable
       }
   }
   ```

2. **Inheritance**:
    - **Definition**: Inheritance is a mechanism that allows one class to inherit the fields and methods of another class. The class that inherits is called the subclass (or derived class), and the class it inherits from is called the superclass (or base class). This promotes code reuse and establishes a relationship between classes.
    - **Example**:
   ```java
   public class Vehicle {
       protected String brand;

       public Vehicle(String brand) {
           this.brand = brand;
       }

       public void honk() {
           System.out.println("Vehicle honk!");
       }
   }

   public class Car extends Vehicle { // Inherits from Vehicle
       private int doors;

       public Car(String brand, int doors) {
           super(brand); // Call to the superclass constructor
           this.doors = doors;
       }

       public void displayInfo() {
           System.out.println("Brand: " + brand + ", Doors: " + doors);
       }
   }
   ```

3. **Polymorphism**:
    - **Definition**: Polymorphism allows methods to do different things based on the object it is acting upon. It enables a single interface to represent different underlying forms (data types). This can be achieved through method overloading (compile-time) and method overriding (runtime).
    - **Example**:
   ```java
   public class Animal {
       public void sound() {
           System.out.println("Animal makes a sound");
       }
   }

   public class Dog extends Animal {
       @Override
       public void sound() { // Method overriding
           System.out.println("Dog barks");
       }
   }

   public class Cat extends Animal {
       @Override
       public void sound() { // Method overriding
           System.out.println("Cat meows");
       }
   }

   public class Main {
       public static void main(String[] args) {
           Animal myDog = new Dog(); // Upcasting
           Animal myCat = new Cat(); // Upcasting
           myDog.sound(); // Output: Dog barks
           myCat.sound(); // Output: Cat meows
       }
   }
   ```

4. **Abstraction**:
    - **Definition**: Abstraction is the concept of hiding complex implementation details and exposing only the necessary parts of an object. This can be achieved using abstract classes and interfaces, allowing you to define methods that must be implemented by subclasses without providing the implementation in the abstract class itself.
    - **Example**:
   ```java
   public abstract class Shape { // Abstract class
       abstract void draw(); // Abstract method
   }

   public class Circle extends Shape {
       @Override
       void draw() {
           System.out.println("Drawing a Circle");
       }
   }

   public class Square extends Shape {
       @Override
       void draw() {
           System.out.println("Drawing a Square");
       }
   }

   public class Main {
       public static void main(String[] args) {
           Shape circle = new Circle();
           Shape square = new Square();
           circle.draw(); // Output: Drawing a Circle
           square.draw(); // Output: Drawing a Square
       }
   }
   ```

### Summary of OOP Concepts with Example

- **Encapsulation**: Protects data by restricting access through private variables and providing public methods (getters and setters).
- **Inheritance**: Allows a new class to inherit properties and methods from an existing class, promoting code reuse.
- **Polymorphism**: Enables objects to be treated as instances of their parent class, allowing for method overriding and dynamic method dispatch.
- **Abstraction**: Hides complex implementation details by exposing only necessary functionalities through abstract classes and interfaces.

These principles work together to create modular, maintainable, and scalable code, which is essential in modern software development. OOP is widely used in various applications, including web development, game development, and enterprise applications.

# More
Here are more examples demonstrating the key principles of Object-Oriented Programming (OOP) in Java. Each example illustrates how encapsulation, inheritance, polymorphism, and abstraction can be implemented in various scenarios.

### Example 1: Library Management System

**Concepts Covered**: Encapsulation, Inheritance, Polymorphism, and Abstraction

1. **Base Class (Abstraction)**:
   ```java
   public abstract class Item {
       private String title;

       public Item(String title) {
           this.title = title;
       }

       public String getTitle() {
           return title;
       }

       public abstract void displayInfo(); // Abstract method
   }
   ```

2. **Derived Classes (Inheritance)**:
   ```java
   public class Book extends Item {
       private String author;

       public Book(String title, String author) {
           super(title); // Call to the superclass constructor
           this.author = author;
       }

       @Override
       public void displayInfo() { // Method overriding
           System.out.println("Book Title: " + getTitle() + ", Author: " + author);
       }
   }

   public class Magazine extends Item {
       private String issue;

       public Magazine(String title, String issue) {
           super(title); // Call to the superclass constructor
           this.issue = issue;
       }

       @Override
       public void displayInfo() { // Method overriding
           System.out.println("Magazine Title: " + getTitle() + ", Issue: " + issue);
       }
   }
   ```

3. **Using the Classes**:
   ```java
   public class Library {
       public static void main(String[] args) {
           Item book = new Book("Effective Java", "Joshua Bloch");
           Item magazine = new Magazine("National Geographic", "January 2023");

           book.displayInfo(); // Output: Book Title: Effective Java, Author: Joshua Bloch
           magazine.displayInfo(); // Output: Magazine Title: National Geographic, Issue: January 2023
       }
   }
   ```

### Example 2: Employee Management System

**Concepts Covered**: Encapsulation, Inheritance, Polymorphism

1. **Base Class**:
   ```java
   public class Employee {
       private String name;
       private double salary;

       public Employee(String name, double salary) {
           this.name = name;
           this.salary = salary;
       }

       public double getSalary() {
           return salary;
       }

       public void displayInfo() {
           System.out.println("Employee Name: " + name + ", Salary: " + salary);
       }
   }
   ```

2. **Derived Classes**:
   ```java
   public class Manager extends Employee {
       private double bonus;

       public Manager(String name, double salary, double bonus) {
           super(name, salary);
           this.bonus = bonus;
       }

       @Override
       public void displayInfo() {
           super.displayInfo(); // Call to the superclass method
           System.out.println("Bonus: " + bonus);
       }
   }

   public class Developer extends Employee {
       private String programmingLanguage;

       public Developer(String name, double salary, String programmingLanguage) {
           super(name, salary);
           this.programmingLanguage = programmingLanguage;
       }

       @Override
       public void displayInfo() {
           super.displayInfo(); // Call to the superclass method
           System.out.println("Programming Language: " + programmingLanguage);
       }
   }
   ```

3. **Using the Classes**:
   ```java
   public class Company {
       public static void main(String[] args) {
           Employee emp1 = new Manager("Alice", 80000, 5000);
           Employee emp2 = new Developer("Bob", 60000, "Java");

           emp1.displayInfo();
           emp2.displayInfo();
       }
   }
   ```

### Example 3: Payment System

**Concepts Covered**: Polymorphism and Abstraction

1. **Base Class**:
   ```java
   public abstract class Payment {
       public abstract void processPayment(double amount); // Abstract method
   }
   ```

2. **Derived Classes**:
   ```java
   public class CreditCardPayment extends Payment {
       @Override
       public void processPayment(double amount) {
           System.out.println("Processing credit card payment of: $" + amount);
       }
   }

   public class PayPalPayment extends Payment {
       @Override
       public void processPayment(double amount) {
           System.out.println("Processing PayPal payment of: $" + amount);
       }
   }
   ```

3. **Using the Classes**:
   ```java
   public class PaymentGateway {
       public static void main(String[] args) {
           Payment payment1 = new CreditCardPayment();
           Payment payment2 = new PayPalPayment();

           payment1.processPayment(150.00); // Output: Processing credit card payment of: $150.0
           payment2.processPayment(75.50);   // Output: Processing PayPal payment of: $75.5
       }
   }
   ```

### Example 4: Online Shopping System

**Concepts Covered**: Encapsulation, Inheritance, Polymorphism, and Abstraction

1. **Base Class**:
   ```java
   public abstract class Product {
       private String name;
       private double price;

       public Product(String name, double price) {
           this.name = name;
           this.price = price;
       }

       public String getName() {
           return name;
       }

       public double getPrice() {
           return price;
       }

       public abstract void displayInfo(); // Abstract method
   }
   ```

2. **Derived Classes**:
   ```java
   public class Electronics extends Product {
       private String warranty;

       public Electronics(String name, double price, String warranty) {
           super(name, price);
           this.warranty = warranty;
       }

       @Override
       public void displayInfo() {
           System.out.println("Electronics Product: " + getName() + ", Price: $" + getPrice() + ", Warranty: " + warranty);
       }
   }

   public class Clothing extends Product {
       private String size;

       public Clothing(String name, double price, String size) {
           super(name, price);
           this.size = size;
       }

       @Override
       public void displayInfo() {
           System.out.println("Clothing Product: " + getName() + ", Price: $" + getPrice() + ", Size: " + size);
       }
   }
   ```

3. **Using the Classes**:
   ```java
   public class ShoppingCart {
       public static void main(String[] args) {
           Product product1 = new Electronics("Laptop", 1200.00, "2 years");
           Product product2 = new Clothing("T-Shirt", 25.00, "M");

           product1.displayInfo(); // Output: Electronics Product: Laptop, Price: $1200.0, Warranty: 2 years
           product2.displayInfo(); // Output: Clothing Product: T-Shirt, Price: $25.0, Size: M
       }
   }
   ```

### Summary

These examples illustrate how OOP principles can be applied in various scenarios, such as a library management system, an employee management system, a payment system, and an online shopping system. By using encapsulation, inheritance, polymorphism, and abstraction, you can create modular, reusable, and maintainable code. OOP is especially useful in large applications where complexity can grow, making these principles essential for software development.