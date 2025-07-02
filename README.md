# User Sector Selection Application

This is a simple web application created as a technical test assignment. The application allows a user to select a number of sectors from a hierarchical list, save their selection along with their name, and have this information persisted for the duration of their session.

---

## Technologies Used

* **Backend:** Java 17, Spring Boot 3
* **Database:** H2 In-Memory Database
* **Data Access:** Spring Data JPA / Hibernate
* **Frontend:** Thymeleaf
* **Build Tool:** Gradle
* **Utilities:** Lombok, Mapstruct

---

## How to Run the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/arvysodev/HelmesTest.git
    cd HelmesTest
    ```

2.  **Run the application:**
    * On Windows:
        ```bash
        .\gradlew bootRun
        ```
    * On macOS/Linux:
        ```bash
        ./gradlew bootRun
        ```

3.  The application will start on `http://localhost:8080`.

---

## Database

The application uses an H2 in-memory database for simplicity and ease of setup.

* The database schema is automatically created by Hibernate based on the JPA entities.
* The initial data for the sectors is loaded from the `import.sql` file located in `src/main/resources`.
* You can access the H2 database console while the application is running at: `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:testdb`
    * **User Name:** `sa`
    * **Password:** (leave empty)
* A full dump of the initial database (structure and sector data) can be found in the "dump.sql" file in the project root.

### Architectural Note on Schema Management

For the purposes of this test assignment, database schema creation is handled by Hibernate's `ddl-auto` feature for development speed. In a production environment, I would use a dedicated database migration tool like Liquibase to ensure version-controlled and stable schema management.
