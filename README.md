# 🚖 Travel Application (Uber Clone Backend) – Spring Boot

A production-grade backend for a ride-hailing platform similar to **Uber**, built using **Spring Boot**, **Spring Security (JWT)**, **MySQL**, and a clean **LLD + Folder Structure**.  
This project follows a multi-day architecture build-up: Authentication → Riders → Drivers → Matching → Rides → Wallet → Admin Panel → JWT Refresh → Logout Token Blacklist.

---

## ✨ Features

### 🔐 **Authentication & Security**
- JWT-based Authentication
- Refresh Token Support
- Logout with Token Blacklisting
- Role-based Access: `RIDER`, `DRIVER`, `ADMIN`
- Password hashing using BCrypt

### 👤 **User Module**
- Register (Signup)
- Login
- Profile update
- View roles & permissions

### 🚘 **Driver Module**
- Driver onboarding
- Availability toggle
- Real-time driver status
- Driver rating system

### 🎒 **Rider Module**
- Request ride
- Cancel ride
- View ride history
- Rate drivers

### 🎯 **Ride Matching Engine**
Driver Matching Strategies:
- 🚗 **NearestDriverStrategy**
- ⭐ **HighestRatedDriverStrategy**

Plug-and-play Strategy Pattern used for matching.

### 💸 **Fare Calculation**
- Default fare calculation strategy
- Distance-based pricing
- Surge pricing (extensible)

### 🧾 **Wallet System**
- Add money
- Deduct on ride completion
- Transaction history (credit/debit/refund/ride payment)

### 🛠 **Admin Panel Features**
- Approve drivers
- Block/unblock users
- System logs
- Monitor rides
- Financial transactions audit

---





---

## 🧠 **Tech Stack**

| Layer | Tech |
|------|------|
| Language | Java 17–25 |
| Framework | Spring Boot 3.5+ |
| Security | Spring Security + JWT |
| Database | MySQL 8 |
| Build Tool | Maven |
| API Docs | Swagger (SpringDoc OpenAPI) |

---

## 🧪 API Documentation (Swagger)

After running the project:


---

## 🚀 Running the Project

### 1️⃣ Configure MySQL database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/travel_app
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


2️⃣ Maven build
mvn clean install

3️⃣ Run the Application
mvn spring-boot:run

🔑 Important Endpoints
Auth
Method	Endpoint	Description
POST	/auth/signup	Register user
POST	/auth/login	Get JWT token
POST	/auth/refresh	Refresh JWT
POST	/auth/logout	Blacklist token
Ride
Method	Endpoint
POST	/ride/request
POST	/ride/accept/{rideId}
POST	/ride/start/{rideId}
POST	/ride/complete/{rideId}
Wallet

| POST | /wallet/add
| GET | /wallet/balance/{id}
| GET | /wallet/transactions/{id}

🧱 Architecture

This project follows:

✔ Layered Architecture

Controller → Service → Strategy → Repository → DB

✔ Strategy Pattern

Driver Matching (Nearest, Highest Rated)

Fare Calculation (Default, Surge, Night Fare, etc.)

✔ DTO Pattern

Avoids exposing entities directly.

✔ JWT Authentication Flow

Access Token + Refresh Token
Token Blacklisting on logout.

