# Applications Manager

**Applications Manager** is a backend system built with **Spring Boot**, designed for precise application lifecycle management. The project implements a robust **State Machine** engine, ensuring data integrity by strictly enforcing business transition rules.

## 🚀 Key Features

* **State-Driven Lifecycle**: Full control over the application flow through states: `CREATED` → `VERIFIED` → `ACCEPTED` → `PUBLISHED`.
* **Data Integrity**: The system prevents invalid operations (e.g., accepting an application that hasn't been verified) by throwing dedicated business exceptions.
* **Layered Architecture**: Strict separation of concerns using DTOs (`AppDto`, `RejectReason`, `ContentChange`) and a clean service layer.
* **Pagination & Filtering**: Efficient data retrieval with support for the `Pageable` interface and state-based filtering.
* **Error Handling**: Centralized exception management, including handling for missing resources (`EntityNotFoundException`) and invalid state transitions (`StateNotFoundException`).

## 🛠️ Tech Stack

* **Language**: Java 17+ 
* **Framework**: Spring Boot (Web, Data JPA) 
* **Database**: PostgreSQL
* **Testing**: JUnit 5, Mockito, AssertJ 
* **Tools**: Lombok, REST API, JSON 

## 🧪 Testing Standards

The project is developed according to **unit testing standards**. The `AppServiceTest` suite includes:
* **Happy Paths**: Verification of successful state transitions and database persistence.
* **Negative Scenarios**: Resistance testing against unauthorized actions in prohibited states.
* **Mocking**: Full isolation of service logic from the database and mappers using Mockito  .
* **BDD Style**: Tests written following the *given-when-then* pattern for maximum readability.

## 📋 API Documentation (Endpoints)

The controller is mapped to base path `/apps`.

| Method  | Endpoint               | Request Body     | Description                                              |
| :------ | :--------------------- | :--------------- | :------------------------------------------------------- |
| `GET`   | `/apps`                | -                | Fetch list of applications (optional `state` filter & pagination). |
| `GET`   | `/apps/{id}`           | -                | Fetch details of a specific application.     |
| `POST`  | `/apps`                | `AppDto`         | Create a new application (initializes to `CREATED`).     |
| `PATCH` | `/apps/{id}/verify`    | -                | Transition to `VERIFIED` state.              |
| `PATCH` | `/apps/{id}/accept`    | -                | Transition to `ACCEPTED` state.              |
| `PATCH` | `/apps/{id}/publish`   | -                | Transition to `PUBLISHED` state.             |
| `PATCH` | `/apps/{id}/reject`    | `RejectReason`   | Reject the application with a specific reason. |
| `PATCH` | `/apps/{id}/delete`    | `RejectReason`   | Delete the application (set to `DELETED`) with a reason. |
| `PATCH` | `/apps/{id}/content`   | `ContentChange`  | Update application content (allowed in specific states). |