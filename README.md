
# Coffee Shop Management System

A Java-based Coffee Shop Management System that allows users to manage coffee orders, coffee menus, and user roles (Coffee Maker and Coffee Taker). The project uses Spring Framework, Hibernate ORM, and PostgreSQL for database management.

## Features

- **User Management**: Add new users, view all users, and assign roles (Coffee Maker or Coffee Taker).
- **Coffee Menu Management**: Add, update, delete, and view coffee menu items.
- **Order Management**: Place, update, and view coffee orders.
- **Role-Based Functionality**:
  - Coffee Takers can place orders and view their order history.
  - Coffee Makers can manage coffee orders and view shop order history.

## Technologies Used

- **Programming Language**: Java
- **Frameworks**: Spring Framework (Spring Context)
- **ORM**: Hibernate
- **Database**: PostgreSQL
- **Build Tool**: Maven

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- PostgreSQL database
- IDE (e.g., IntelliJ IDEA)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd coffee-shop
   ```

2. **Configure the Database**:
    - Update the database connection details in `src/main/resources/hibernate.cfg.xml`:
      ```xml
      <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/coffeeshop</property>
      <property name="hibernate.connection.username">postgres</property>
      <property name="hibernate.connection.password">your_password</property>
      ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
    - Use your IDE to run the `App` class located in `src/main/java/com/sasibhumaraju/App.java`.

5. **Database Schema**:
    - The database schema will be automatically created/updated based on the Hibernate configuration (`hibernate.hbm2ddl.auto` is set to `update`).

## Project Structure

- `src/main/java/com/sasibhumaraju`:
    - `App.java`: Entry point of the application.
    - `model`: Contains entity classes like `AppUser`, `Coffee`, `CoffeeOrder`, etc.
    - `service`: Contains service classes for business logic.
    - `DAO`: Contains data access objects for database operations.
    - `config`: Contains database configuration.

- `src/main/resources`:
    - `beans.xml`: Spring bean configuration file.
    - `hibernate.cfg.xml`: Hibernate configuration file.

## Usage

1. **Run the Application**:
    - Start the application and follow the console prompts.

2. **User Roles**:
    - **Coffee Maker**:
        - Manage coffee orders.
        - View shop order history.
        - Modify the coffee menu.
    - **Coffee Taker**:
        - Place coffee orders.
        - View active and historical orders.

3. **Coffee Menu**:
    - Add, update, delete, and view coffee items.

4. **Order Management**:
    - Place and update coffee orders.

## Dependencies

The project uses the following Maven dependencies:

- Spring Context (`org.springframework:spring-context:6.2.3`)
- Hibernate Core (`org.hibernate.orm:hibernate-core:6.6.3.Final`)
- PostgreSQL Driver (`org.postgresql:postgresql:42.7.3`)
- JUnit (`junit:junit:3.8.1`)

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Author

- **Sasibhumaraju**
