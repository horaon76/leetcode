Here's a table summarizing the differences between the access modifiers in Java: `public`, `protected`, `private`, and the default (package-private) access level:

| Access Modifier | Class Members | Inheritance | Same Package | Subclass in Different Package | Outside Package |
|------------------|---------------|-------------|--------------|------------------------------|-----------------|
| **public**       | Accessible from anywhere. | Accessible. | Accessible. | Accessible. | Accessible. |
| **protected**    | Accessible within the same package and subclasses (even in different packages). | Accessible. | Accessible. | Accessible. | Not accessible. |
| **private**      | Accessible only within the same class. | Not accessible. | Not accessible. | Not accessible. | Not accessible. |
| **default** (no modifier) | Accessible only within the same package. | Not accessible. | Accessible. | Not accessible. | Not accessible. |

### Detailed Explanation

1. **public**:
    - **Visibility**: Members declared as `public` can be accessed from any other class in any package.
    - **Use Case**: Use `public` for methods and variables that should be accessible by all classes.

   ```java
   public class PublicClass {
       public void publicMethod() {
           System.out.println("This is a public method.");
       }
   }
   ```

2. **protected**:
    - **Visibility**: Members declared as `protected` can be accessed within the same package and by subclasses, even if they are in different packages.
    - **Use Case**: Use `protected` when you want to allow access to subclasses and classes within the same package, but not to the outside world.

   ```java
   public class ProtectedClass {
       protected void protectedMethod() {
           System.out.println("This is a protected method.");
       }
   }
   ```

3. **private**:
    - **Visibility**: Members declared as `private` can only be accessed within the same class. They are not visible to any other class.
    - **Use Case**: Use `private` for encapsulation, to hide implementation details from other classes.

   ```java
   public class PrivateClass {
       private void privateMethod() {
           System.out.println("This is a private method.");
       }
   }
   ```

4. **default** (no modifier):
    - **Visibility**: Members declared without any access modifier (default) can be accessed only within classes in the same package. They are not accessible from subclasses in different packages.
    - **Use Case**: Use default access when you want to restrict access to classes in the same package only.

   ```java
   class DefaultClass {
       void defaultMethod() {
           System.out.println("This is a default method.");
       }
   }
   ```

### Summary

- **public**: Visible to all classes everywhere.
- **protected**: Visible to classes in the same package and subclasses.
- **private**: Visible only within the class.
- **default**: Visible only to classes in the same package.

Understanding these access modifiers is essential for controlling visibility and encapsulation in your Java applications.