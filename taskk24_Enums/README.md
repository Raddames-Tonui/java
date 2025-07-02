# 🧠 Mastering Enums in Java

## 📘 What Are Enums?

**Enums** (short for *enumerations*) are a special data type in Java used to define collections of constants. But they’re far more powerful than just lists — enums are full classes with fields, constructors, and methods.

Think of an enum like a class where each constant is a singleton instance, and you can give each constant its own behavior.

---

## ✅ Why Use Enums?

* Prevent invalid values (e.g., no "Funday" among weekdays)
* Type-safe and readable
* Group constant-related behavior
* Ideal for finite state machines, strategy patterns, and permission models

---

## 🧱 Enums Are Like Classes

Java enums:

* Extend `java.lang.Enum`
* Can have fields, constructors, methods
* Support method overriding using `@Override`
* Can be used in collections like `EnumMap`, `EnumSet`

### 🌐 Strategy Pattern Using Enums

Enums can hold behavior by overriding abstract methods, just like classes. This is great for strategy-style decisions like payments.

```java
public enum PaymentMethod {
    MPESA {
        @Override // Means we are customizing behavior for this specific constant
        public void process(double amount) {
            System.out.println("M-PESA payment: KES " + amount);
        }
    },
    PAYPAL {
        @Override
        public void process(double amount) {
            System.out.println("PayPal payment: KES " + amount);
        }
    };

    // Abstract method: Must be implemented by all enum constants
    public abstract void process(double amount);
}
```

---

## 🔐 Enum with Fields and Constructor

Enums can hold additional data like a regular class. You define a constructor, private fields, and a method to access them.

```java
public enum UserRole {
    ADMIN(3), MODERATOR(2), USER(1);

    private final int accessLevel; // Field to hold the access level

    // Constructor: Automatically called for each constant
    UserRole(int level) {
        this.accessLevel = level;
    }

    // Getter method to retrieve the field
    public int getAccessLevel() {
        return accessLevel;
    }
}
```

**Usage:**

```java
if (user.getRole().getAccessLevel() >= UserRole.MODERATOR.getAccessLevel()) {
    // Access check using access levels
    System.out.println("Access granted to moderator tools.");
}
```

---

## 🔁 Enum as Finite State Machine

Enums can transition between states using methods like `.next()`. This models systems like workflows or approvals.

```java
public enum DocumentState {
    DRAFT {
        @Override
        public DocumentState next() { return REVIEW; }
    },
    REVIEW {
        @Override
        public DocumentState next() { return APPROVED; }
    },
    APPROVED {
        @Override
        public DocumentState next() { return ARCHIVED; }
    },
    ARCHIVED {
        @Override
        public DocumentState next() { return ARCHIVED; } // Final state, no change
    };

    // Abstract method: forces each constant to define its transition logic
    public abstract DocumentState next();
}
```

---

## 📊 Business Logic in Switch

Use enums in `switch` statements for clear, rule-based logic. Great for decision engines or region-based operations.

```java
public enum Region {
    KENYA, UGANDA, TANZANIA;
}

public class TaxService {
    public static double calculateTax(Region region, double amount) {
        return switch (region) {
            case KENYA -> amount * 0.16;
            case UGANDA -> amount * 0.18;
            case TANZANIA -> amount * 0.15;
        };
    }
}
```

---

## 🌍 Enum for Code Mapping (e.g., DB or API)

Attach short codes or identifiers to enum constants for use in databases or JSON.

```java
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String code;

    // Constructor to assign string code
    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // Reverse lookup from string code to enum constant
    public static Gender fromCode(String code) {
        for (Gender g : values()) {
            if (g.code.equals(code)) return g;
        }
        throw new IllegalArgumentException("Unknown gender code: " + code);
    }
}
```

---

## 🚀 Collections: EnumMap & EnumSet

Use `EnumMap` and `EnumSet` for performance and memory efficiency.

```java
EnumMap<StateOfMatter, List<String>> map = new EnumMap<>(StateOfMatter.class);
EnumSet<UserRole> allowedRoles = EnumSet.of(UserRole.ADMIN, UserRole.MODERATOR);
```

* `EnumMap` is faster than HashMap for enum keys.
* `EnumSet` is memory-efficient for enum-based sets.

---

## 💡 Summary Table

| Feature                 | Supported? | Explanation                               |
| ----------------------- | ---------- | ----------------------------------------- |
| Fields & Constructors   | ✅          | You can define values per constant        |
| Method Overriding       | ✅          | Use `@Override` inside enum constants     |
| Implements Interfaces   | ✅          | Enums can implement interfaces            |
| Extending Other Classes | ❌          | All enums extend `java.lang.Enum` already |
| Abstract Methods        | ✅          | Forces each constant to implement it      |
| Singleton per Constant  | ✅          | Only one instance per constant exists     |

---

## 🛠️ Suggested Mini-Project

Build a **Workflow Engine**:

* Enum: `WorkflowStep`
* Methods: `next()`, `canSkip()`
* Use it to manage approvals or document flows

