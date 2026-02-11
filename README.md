# KtorFit API 🏋️‍♂️⚡

**KtorFit** is a high-performance RESTful API designed for tracking gym workouts, loads, and repetitions. Built with **Kotlin** and **Ktor**, this project demonstrates modern backend development practices, focusing on idiomatic Kotlin syntax, asynchronous processing, and type-safe database interactions.

## 🚀 Tech Stack

*   **Language:** [Kotlin](https://kotlinlang.org/) (Latest Stable)
*   **Framework:** [Ktor Server](https://ktor.io/) (Netty Engine)
*   **Database:** [H2 Database](https://www.h2database.com/) (In-Memory for Dev / File-based)
*   **ORM:** [Exposed](https://github.com/JetBrains/Exposed) (Kotlin SQL Framework)
*   **Serialization:** Kotlinx Serialization (JSON)
*   **Build System:** Gradle Kotlin DSL

## 🏗️ Architecture & Features

*   **Type-Safe Routing:** Leveraging Ktor's DSL for clean and readable endpoint definitions.
*   **Async Database Access:** Non-blocking database operations using Exposed's coroutine support.
*   **Content Negotiation:** Automatic JSON serialization/deserialization.
*   **Clean Architecture:** Separation of concerns between Routes, Models, and Data Access Objects (DAOs).

## 🛠️ Getting Started

### Prerequisites
*   JDK 17+
*   IntelliJ IDEA (Recommended)

### Running the Application
1.  Clone the repository:
    ```bash
    https://github.com/jhonataswillian/kot-lift
    ```
2.  Open the project in IntelliJ IDEA.
3.  Run the `Application.kt` file or execute via Gradle:
    ```bash
    ./gradlew run
    ```

The API will start at `http://0.0.0.0:8080`.

## 📝 API Endpoints (Planned)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/workouts` | Create a new workout |
| `GET` | `/workouts` | List all workouts |
| `GET` | `/workouts/{id}` | Get specific workout details |
| `PUT` | `/workouts/{id}` | Update a workout |
| `DELETE` | `/workouts/{id}` | Remove a workout |

---
*Built with ❤️ and Kotlin.*