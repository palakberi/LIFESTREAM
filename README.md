# 🩸 LifeStream – Blood Donation Platform

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-green?logo=mongodb)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](LICENSE)

**LifeStream** is a full-stack blood donation management system built with **Java Spring Boot**, **MongoDB**, and **Thymeleaf**.  
It streamlines the process of blood donation, requests, and inventory management for **donors, recipients, and admins**.

---

## ✨ Features

### 👤 Donors
- Register with username, password & blood type
- Schedule donation appointments
- Inventory automatically updates when a donor registers

### 🧍 Recipients
- Register and request blood
- Blood stock decreases when request is approved
- View current inventory

### 🛡️ Admin
- Login with `admin / admin123`
- View **all appointments**
- Mark appointments as completed
- Monitor and adjust **blood inventory**

---

## 🏗 Tech Stack

- **Backend**: Java 17, Spring Boot 3
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Database**: MongoDB (Spring Data MongoDB)
- **Build Tool**: Maven

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/lifestream.git
cd lifestream
```
2️⃣ Configure MongoDB
Ensure MongoDB is running locally (default: mongodb://localhost:27017).
 
Update src/main/resources/application.yml if needed:



```bash
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/lifestream
  thymeleaf:
    cache: false

server:
  port: 8080
```
3️⃣ Build & Run

```bash
mvn clean install
mvn spring-boot:run
```
4️⃣ Access the App
🌐 Home/Login → http://localhost:8080

👤 Donor / Recipient → register & login from homepage

🛡️ Admin Dashboard → login with admin / admin123

📂 Project Structure
```bash
src/main/java/com/lifestream
│
├── controller/        # Web + REST controllers
├── dto/               # Request/response DTOs
├── model/             # MongoDB models
├── repository/        # Spring Data repositories
└── service/           # Business logic services
```


🧪 Usage Workflow
Donor registers → blood type automatically added to inventory

Donor can schedule donation appointments

Recipient requests blood → reduces stock in inventory

Admin logs in (admin/admin123) → views & manages all appointments and inventory

📌 Future Roadmap
🔑 Replace hardcoded admin with secure authentication (JWT/OAuth2)

📩 Email/SMS notifications for appointments

📍 Location-based blood bank search

📊 Advanced analytics dashboard



### 👨‍💻 Author
Developed by Palak Beri ✨
Feel free to ⭐ this repo if you find it useful!