Here are some industry-related examples of the **Composite Pattern** with Java implementations:

### 1. Organization Structure

#### Scenario
In an organization, there are different levels of employees: departments, teams, and individual employees. Each department can contain multiple teams, and each team can have multiple employees. This hierarchy can be represented using the Composite Pattern.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface Employee {
    void showDetails();
}

// Leaf
class IndividualEmployee implements Employee {
    private String name;
    private String role;

    public IndividualEmployee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void showDetails() {
        System.out.println("Employee: " + name + ", Role: " + role);
    }
}

// Composite
class Team implements Employee {
    private String teamName;
    private List<Employee> members = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addMember(Employee member) {
        members.add(member);
    }

    public void removeMember(Employee member) {
        members.remove(member);
    }

    @Override
    public void showDetails() {
        System.out.println("Team: " + teamName);
        for (Employee member : members) {
            member.showDetails();
        }
    }
}

// Composite
class Department implements Employee {
    private String departmentName;
    private List<Employee> teams = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addTeam(Employee team) {
        teams.add(team);
    }

    public void removeTeam(Employee team) {
        teams.remove(team);
    }

    @Override
    public void showDetails() {
        System.out.println("Department: " + departmentName);
        for (Employee team : teams) {
            team.showDetails();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create individual employees
        IndividualEmployee emp1 = new IndividualEmployee("Alice", "Developer");
        IndividualEmployee emp2 = new IndividualEmployee("Bob", "Tester");
        IndividualEmployee emp3 = new IndividualEmployee("Charlie", "Manager");

        // Create teams and add employees to them
        Team team1 = new Team("Development Team");
        team1.addMember(emp1);
        team1.addMember(emp2);

        Team team2 = new Team("Management Team");
        team2.addMember(emp3);

        // Create a department and add teams to it
        Department department = new Department("IT Department");
        department.addTeam(team1);
        department.addTeam(team2);

        // Show organization structure
        department.showDetails();
    }
}
```

### Output
```
Department: IT Department
Team: Development Team
Employee: Alice, Role: Developer
Employee: Bob, Role: Tester
Team: Management Team
Employee: Charlie, Role: Manager
```

### Explanation
In this example, we have an organization structure where:
- **Employee** is the common interface.
- **IndividualEmployee** represents individual employees (leaf nodes).
- **Team** is a composite that can contain multiple employees.
- **Department** is another composite that can contain multiple teams.

The `showDetails()` method displays the hierarchy of the organization.

---

### 2. Graphic Editor

#### Scenario
In a graphic editor application, you can have shapes (like circles, rectangles) and groups of shapes. Both individual shapes and groups can be treated uniformly.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface Shape {
    void draw();
}

// Leaf
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

// Leaf
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// Composite
class ShapeGroup implements Shape {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Shape Group:");
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create individual shapes
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        // Create a group and add shapes to it
        ShapeGroup group = new ShapeGroup();
        group.addShape(circle);
        group.addShape(rectangle);

        // Draw individual shapes
        System.out.println("Drawing individual shapes:");
        circle.draw();
        rectangle.draw();

        // Draw the group of shapes
        System.out.println("\nDrawing grouped shapes:");
        group.draw();
    }
}
```

### Output
```
Drawing individual shapes:
Drawing Circle
Drawing Rectangle

Drawing grouped shapes:
Drawing Shape Group:
Drawing Circle
Drawing Rectangle
```

### Explanation
In this example, we have a graphic editor where:
- **Shape** is the common interface.
- **Circle** and **Rectangle** are individual shapes (leaf nodes).
- **ShapeGroup** is a composite that can contain multiple shapes.

The `draw()` method allows for drawing both individual shapes and groups of shapes uniformly.

---

### 3. E-commerce Product Catalog

#### Scenario
In an e-commerce application, a product catalog can contain individual products and categories. Each category can have multiple products or subcategories.

#### Implementation

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface ProductComponent {
    void showDetails();
}

// Leaf
class Product implements ProductComponent {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showDetails() {
        System.out.println("Product: " + name + ", Price: $" + price);
    }
}

// Composite
class Category implements ProductComponent {
    private String categoryName;
    private List<ProductComponent> products = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProduct(ProductComponent product) {
        products.add(product);
    }

    public void removeProduct(ProductComponent product) {
        products.remove(product);
    }

    @Override
    public void showDetails() {
        System.out.println("Category: " + categoryName);
        for (ProductComponent product : products) {
            product.showDetails();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create products
        Product phone = new Product("Smartphone", 699.99);
        Product laptop = new Product("Laptop", 999.99);
        Product tshirt = new Product("T-Shirt", 19.99);

        // Create categories and add products to them
        Category electronics = new Category("Electronics");
        electronics.addProduct(phone);
        electronics.addProduct(laptop);

        Category clothing = new Category("Clothing");
        clothing.addProduct(tshirt);

        // Create a main category and add subcategories to it
        Category mainCatalog = new Category("Main Catalog");
        mainCatalog.addProduct(electronics);
        mainCatalog.addProduct(clothing);

        // Show product catalog
        mainCatalog.showDetails();
    }
}
```

### Output
```
Category: Main Catalog
Category: Electronics
Product: Smartphone, Price: $699.99
Product: Laptop, Price: $999.99
Category: Clothing
Product: T-Shirt, Price: $19.99
```

### Explanation
In this e-commerce example:
- **ProductComponent** is the common interface.
- **Product** represents individual products (leaf nodes).
- **Category** is a composite that can contain multiple products and subcategories.

The `showDetails()` method allows for displaying the entire product catalog, showing both categories and their respective products.

---

### Summary
These examples illustrate how the **Composite Pattern** can be applied in different industry scenarios, including organization structures, graphic editors, and e-commerce applications. This pattern enables the creation of tree-like structures where both individual objects and compositions can be treated uniformly, simplifying the management and interaction with complex hierarchies.