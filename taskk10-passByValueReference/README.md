# **Pass By Value vs Pass By Reference**

## **📘 Overview**

This documentation provides a comprehensive understanding of how **Java handles argument passing** — specifically, how it treats **primitive types** and **object references** in method calls.

Java is strictly **pass-by-value**, but the behavior can be misunderstood, especially when dealing with object references. This task illustrates:

1. Pass-by-value for **primitives**
2. Pass-by-value for **object references** (modification of fields)
3. Pass-by-value with **reassignment** of object references

> 📌 Summary:
>
> In Java, "pass-by-value" means that when a method is called, the value of the variable used as an argument is copied and passed to the method.
> 1. For primitive types (like int, boolean, etc.), the method receives a copy of the actual value. 
>2.  For object references, the method receives a copy of the reference (the memory address) to the object, not a copy of the object itself. This means the method can modify the object that the reference points to, and those changes will be visible outside the method. However, if the method reassigns the reference to point to a different object, that change is not visible outside the method.

---

## **📦 File Structure**

```
task10-passbyvalue/
    ├── Product.java
    └── PassByValueReferenceDemo.java
```

---

## **🔍 Definitions & Concepts**

### **🧮 Primitive Types**

Primitive types are the most basic data types in Java. They hold actual values rather than references. Java provides 8 primitive types:

* `int`
* `double`
* `float`
* `long`
* `short`
* `byte`
* `char`
* `boolean`

> 📌 Example:

```
double price = 200.0;  // This directly stores the value 200.0
```

### **🧱 Object References**

In Java, an object reference is a variable that holds the **memory address** (or pointer) of an object, not the object itself. When you pass an object to a method, you're passing a **copy of the reference**.

> 📌 Example:

```
Product product = new Product("Phone", 500.0);
```

Here, `product` stores a reference (address) pointing to the actual `Product` object in memory.

### **📤 Pass-by-Value (Java's Approach)**

Java always uses **pass-by-value**, meaning:

* For primitives, the **actual value** is copied.
* For objects, the **reference (address)** is copied, not the object.

This means that inside a method:

* You can change the contents of the object (fields), and it reflects outside.
* But reassigning a new object to the parameter only changes the local copy of the reference. The original reference remains unchanged.

---

## **🧠 Concepts Illustrated**

### **1. Primitive Types Are Passed by Value**

When you pass a primitive (e.g., `int`, `double`) to a method, the value is copied. Changes inside the method **do not** affect the original.

```
void updatePrice(double price) {
    price += 50;
}
```

### **2. Object References Are Also Passed by Value**

The **reference (pointer)** to the object is copied. Thus, the method can modify the original object’s **fields**, and these changes will persist.

```
void updateProduct(Product p) {
    p.setProductName("Updated");
    p.setPrice(999.99);
}
```

### **3. Reassigning Object References Does Not Persist**

When you reassign a new object to the parameter inside the method, it only changes the **local copy** of the reference. The original reference remains unchanged.

```
void reassignProduct(Product p) {
    p = new Product("New", 100.0);
}
```

---

## **🧪 Use Case Output**

Command:

```
java -cp out  PassByValueReferenceDemo
```

### **✅ Output**

```
Before updatePrice(): price = 200.0
Inside updatePrice() before update: price = 200.0
Inside updatePrice() after update: price = 250.0
After updatePrice(): price = 200.0
------------------------------------------
Before updateProduct(): Product {name='Original Product', price=500.00}
Inside updateProduct() before update: Product {name='Original Product', price=500.00}
Inside updateProduct() after update: Product {name='Updated Product', price=600.00}
After updateProduct(): Product {name='Updated Product', price=600.00}
------------------------------------------
Before reassignProduct(): Product {name='Updated Product', price=600.00}
Inside reassignProduct() before reassignment: Product {name='Updated Product', price=600.00}
Inside reassignProduct() after reassignment: Product {name='New Product', price=999.99}
After reassignProduct(): Product {name='Updated Product', price=600.00}
------------------------------------------
```

---

## **🛠️ Use Cases & Practical Scenarios**

### **🔧 When working with primitives:**

* Safe to pass into methods that calculate values without affecting the original.
* Use when you want **pure functions** that do not mutate external state.

### **🧩 When working with object references:**

* Useful for **modifying** object fields, like setting user details, updating product prices, etc.
* Be cautious — unintentional modifications can lead to **side effects**.

### **🧨 Reassigning object references:**

* Cannot be used to change the caller's reference.
* Use this pattern to demonstrate **local-only effects** or prototype designs.

---

## **⚠️ Common Pitfalls**

* **Mistaking pass-by-reference**: Java doesn’t pass objects by reference; it passes **a copy of the reference by value**.
* **Unexpected mutations**: Modifying object fields in a method affects the caller’s object.

---

## **🧭 Conclusion**

Java always uses **pass-by-value** semantics:

* For primitives, it’s a copy of the value.
* For objects, it’s a copy of the reference — not the object itself.

Understanding this behavior is vital when debugging or architecting your Java applications to avoid surprises.

---

## **📌 Tips**

* Prefer **immutable objects** if you want to avoid side effects.
* Use clear naming and comments to indicate whether a method **mutates** or **just reads** an o
