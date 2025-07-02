# Java Service Layer in Clean Architecture

In this section, we introduce the **Service Layer**, a crucial design component in Java backend applications. It helps organize business logic, separates concerns, and promotes clean, scalable architecture.

---

## 🧠 What is the Service Layer?

The **service layer** sits between the **Controller** and **DAO (Data Access Object)** layers. It encapsulates **business logic**, validations, data transformation, and coordinates interaction with the database.

### ✅ Responsibilities:

* Validate inputs before hitting the database
* Handle transformation or preprocessing
* Call DAO methods to fetch/modify data
* Apply business rules or enforce policies

---

## 🔁 Architecture Flows

### ❌ Current (2-tier):

```
Controller → DAO → DB
```

### ✅ Recommended (3-tier):

```
Controller → Service → DAO → DB
```

---

## 📦 Folder Structure

```
org/tonui/
├── controller/
│   └── PupilController.java
├── service/
│   └── PupilService.java        ← NEW
├── dao/
│   └── PupilDAO.java
├── model/
│   └── Pupil.java
├── db/
│   └── DBConnection.java
└── Server.java
```

---

## 🛠 Example: `PupilService.java`

```java
package org.tonui.service;

import org.tonui.dao.PupilDAO;
import org.tonui.model.Pupil;

import java.util.List;

public class PupilService {
    private final PupilDAO pupilDAO = new PupilDAO();

    public boolean createPupil(Pupil pupil) {
        // Business rule: Email must not be null
        if (pupil.getEmail() == null || pupil.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        return pupilDAO.insertPupil(pupil);
    }

    public List<Pupil> getAll() {
        return pupilDAO.getAllPupils();
    }

    public Pupil getById(Long id) {
        return pupilDAO.getPupilById(id);
    }

    public boolean update(Pupil pupil) {
        return pupilDAO.updatePupil(pupil);
    }

    public boolean delete(Long id) {
        return pupilDAO.deletePupil(id);
    }
}
```

Then in your controller:

```java
private final PupilService pupilService = new PupilService();

// Instead of pupilDAO.getAllPupils(), you now call:
pupilService.getAll();
```

---

## ✅ Benefits of Using Service Layer

| Feature                       | Description                                                           |
| ----------------------------- | --------------------------------------------------------------------- |
| 🔄 **Separation of Concerns** | Keeps controllers simple and decouples logic                          |
| 🧪 **Unit Testing**           | Business logic can be tested independently                            |
| 🔧 **Reusability**            | Multiple controllers or components can reuse the same service         |
| 📈 **Scalability**            | Easy to extend and refactor without impacting controller/DAO directly |

---

## 🧠 Summary

* Service Layer is **strongly recommended** in real-world applications.
* Start small and introduce it per feature/module.
* Aim for this flow: `Controller → Service → DAO → DB`

Would you like to refactor your current `PupilController` to use the service layer?
