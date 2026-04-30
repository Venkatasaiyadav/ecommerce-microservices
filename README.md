# 🛒 E-Commerce Microservices System

A scalable **E-commerce backend system** built using **Spring Boot Microservices Architecture**.
This project demonstrates service discovery, API Gateway routing, and modular service design.

---

## 🚀 Tech Stack

* **Backend:** Spring Boot
* **Service Registry:** Eureka
* **API Gateway:** Spring Cloud Gateway
* **Build Tool:** Maven
* **Database:** (Add yours: MySQL / MongoDB)
* **Code Quality:** SonarCloud

---

## 📦 Microservices Overview

| Service Name     | Description                         | Port |
| ---------------- | ----------------------------------- | ---- |
| service-registry | Eureka server for service discovery | 8761 |
| api-gateway      | Entry point for all client requests | 8080 |
| user-service     | Handles user-related operations     | 8081 |
| product-service  | Handles product-related operations  | 8082 |

---

## 🧩 Architecture

* All services register with **Eureka Server**
* API Gateway routes requests dynamically
* Each service is independently deployable

---

## ⚙️ Setup & Run Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/ecommerce-microservices.git
cd ecommerce-microservices
```

---

### 2️⃣ Start Services (Order matters)

👉 Start in this order:

```bash
# 1. Eureka Server
cd service-registry
mvn spring-boot:run

# 2. Other services
cd ../user-service
mvn spring-boot:run

cd ../product-service
mvn spring-boot:run

cd ../api-gateway
mvn spring-boot:run
```

---

### 3️⃣ Access Eureka Dashboard

👉 http://localhost:8761

You should see:

* USER-SERVICE
* PRODUCT-SERVICE

---

## 🔍 API Gateway Example

All requests go through:

```
http://localhost:8080
```

Example:

```
http://localhost:8080/user-service/users
http://localhost:8080/product-service/products
```

---

## 🧪 Code Quality (SonarCloud)

Run analysis:

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=your_project_key \
  -Dsonar.organization=your_org \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=your_token
```

---

## 📌 Features

* Microservices architecture
* Service discovery with Eureka
* API Gateway routing
* Scalable and modular design
* Ready for CI/CD integration

---

## 🔐 Best Practices Followed

* Layered architecture
* Separation of concerns
* Externalized configuration
* Clean code structure

---

## 📈 Future Enhancements

* Add authentication (JWT / OAuth2)
* Implement Feign Client for service communication
* Add centralized logging (ELK)
* Add Docker & Kubernetes support
* CI/CD with GitHub Actions

---

## 👨‍💻 Author

**Udatha Venkatasai**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
