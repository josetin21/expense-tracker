# 💰 Expense Tracker Backend

A RESTful backend application for tracking income and expenses, built with **Spring Boot**, **Spring Security**, and **PostgreSQL**.
This project supports user authentication, transaction management, category-based analytics, and monthly summaries.

---

## 🚀 Features

### 🔐 Authentication & Security

* User registration and login
* Password encryption using BCrypt
* Stateless authentication using **Spring Security**
* Role-based access control (USER)

### 💳 Transactions

* Create, update, soft-delete transactions
* Income & expense tracking
* Pagination support for transaction listing
* Each transaction belongs to a user and category

### 🏷 Categories

* Predefined categories
* Automatic creation of **custom categories** from transactions
* Category-wise expense analytics

### 📊 Analytics

* Total expense by user
* Category-wise expense summary
* Monthly expense breakdown
* Income vs Expense vs Balance summary

### 📄 API Documentation

* Interactive API documentation using **Swagger (SpringDoc OpenAPI)**

---

## 🛠 Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **Spring Security**
* **PostgreSQL**
* **Hibernate**
* **Swagger / OpenAPI**
* **Gradle**

---

## 📂 Project Structure

```
com.josetin.expense_tracker
├── controller
├── service
├── repo
├── entity
├── dto
│   ├── request
│   └── response
├── mapper
├── exception
├── config
└── security
```

---

## 🔑 Authentication Flow

1. Register a new user
   `POST /auth/register`

2. Login with credentials
   `POST /auth/login`

3. Receive authentication token

4. Use token in requests:

```
Authorization: Bearer <token>
```

---

## 📌 API Endpoints (Main)

### Auth

* `POST /auth/register`
* `POST /auth/login`

### Transactions

* `POST /transactions`
* `PUT /transactions/{id}`
* `DELETE /transactions/{id}`
* `GET /transactions/user/{userId}` (paginated)

### Analytics

* `GET /transactions/analytics/total/{userId}`
* `GET /transactions/analytics/category/{userId}`
* `GET /transactions/analytics/monthly/{userId}`
* `GET /transactions/analytics/summary/{userId}`

---

## 📘 Swagger UI

After running the application, open:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ Configuration

### Database (PostgreSQL)

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Running the Application

```bash
./gradlew bootRun
```

Or from IDE:

```
Run ExpenseTrackerApplication
```

---

## 🧪 Testing

* APIs tested using **Postman** and **Swagger UI**
* Authentication and secured endpoints verified
* Pagination and analytics validated

---

## 📌 Notes

* Soft delete is implemented for transactions
* Amounts use `BigDecimal` for financial accuracy
* Database constraints enforce data integrity
* Clean separation of Entity, DTO, Mapper, and Service layers

---

## 👤 Author

**Josetin**
Backend Developer (Spring Boot, Java)

---

## 📄 License

This project is for learning and portfolio purposes.
