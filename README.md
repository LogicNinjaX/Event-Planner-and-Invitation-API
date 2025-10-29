# 🎉 Event Management System API
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-success)]()
[![OpenAPI](https://img.shields.io/badge/API%20Docs-Swagger-green?logo=swagger)]()

> A **Spring Boot-based Event Management API** for managing events, guests, invitations, and RSVP tracking — with built-in email notifications and secure JWT authentication.

---

## 🌟 Overview

The **Event Management System API** streamlines how event organizers handle events and invitations.  
It provides endpoints for event CRUD operations, guest management, customizable invitations, and automated email dispatch using **Spring Mail**.

---

## ✨ Features

🔹 **Event Management** — Create, update, and delete events.  
🔹 **Guest Management** — Add and track guests per event.  
🔹 **RSVP Tracking** — Manage guest responses: `Pending`, `Accepted`, `Declined`.  
🔹 **Email Notifications** — Send invitations via SMTP with templated emails.  
🔹 **Authentication** — JWT-secured endpoints with Spring Security.  
🔹 **API Documentation** — Interactive Swagger UI for testing endpoints.

---

## 🧰 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3 |
| **Database** | MySQL |
| **ORM** | Spring Data JPA |
| **Security** | Spring Security + JWT |
| **Templating** | Thymeleaf |
| **Email Service** | Spring Mail |
| **API Docs** | Springdoc OpenAPI (Swagger) |
| **Mapping Tool** | MapStruct |

---

## ⚙️ Maven Dependencies

<details>
<summary>Click to view dependencies</summary>

```xml
<!-- Core Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Database -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Email -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<!-- OpenAPI Docs -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.1</version>
</dependency>

<!-- Object Mapper -->
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.6.3</version>
</dependency>
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>1.6.3</version>
    <scope>provided</scope>
</dependency>

<!-- Security & JWT -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>

<!-- Thymeleaf Templates -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Testing -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

</details>

---

## ⚙️ Configuration (`application.yml`)

```yaml
spring:
  application:
    name: demo

  datasource:
    url: ${DB_URL}
    username: ${DB_UNAME}
    password: ${DB_PASS}
    driver-class-name: ${DRIVER_CLASS}

  jpa:
    hibernate.ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        highlight_sql: true

  mail:
    host: ${SMTP_HOST}
    port: ${SMTP_PORT}
    username: ${SMTP_UNAME}
    password: ${SMTP_PASS}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

logging:
  file:
    name: logs/app.log

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXP} # in milliseconds

app:
  ip: ${HOST_IP}
```

---

## 🔑 Environment Variables

| Variable | Description |
|-----------|--------------|
| `DB_URL` | Database connection URL |
| `DB_UNAME` | Database username |
| `DB_PASS` | Database password |
| `DRIVER_CLASS` | MySQL driver class |
| `SMTP_HOST` | SMTP host (e.g., smtp.gmail.com) |
| `SMTP_PORT` | SMTP port |
| `SMTP_UNAME` | SMTP username |
| `SMTP_PASS` | SMTP password |
| `JWT_SECRET` | Secret key for JWT token |
| `JWT_EXP` | JWT expiration time (ms) |
| `HOST_IP` | Application host IP |

---

## 🏃 How to Run

### Prerequisites
- ☕ Java 21+
- 🧱 Maven 3.9+
- 🗄️ MySQL Database
- 📧 SMTP credentials

### Steps
```bash
# Clone repository
git clone https://github.com/LogicNinjaX/Event-Planner-and-Invitation-API.git

# Navigate into folder
cd Event-Planner-and-Invitation-API

# Run the project
mvn spring-boot:run
```

📘 Swagger UI available at:  
👉 **http://localhost:8080/swagger-ui.html**

---

## 🧭 Sample API Endpoints

| Method | Endpoint                                           | Description                     |
|---------|----------------------------------------------------|---------------------------------|
| `POST` | `/api/v1/events`                                   | Create new event                |
| `GET` | `/api/v1/events`                                   | Fetch all events                |
| `PUT` | `/api/v1/events/{id}`                              | Update event details            |
| `DELETE` | `/api/v1/events/{id}`                              | Delete event                    |
| `POST` | `/api/v1/events/{id}/invitations/?email=guest-email` | Send invitation                 |
| `GET` | `/api/v1/events/{id}/invitations`                              | Get invitation list by event id |
| `GET` | `/api/rsvp/{eventId}`                              | Get RSVP summary                |

---

## 🏗️ Project Architecture

```plaintext
                          +---------------------+
                          |     Client / UI     |
                          +----------+----------+
                                     |
                                     v
                       +----------------------------+
                       |        REST Controller      |
                       +--------------+---------------+
                                      |
                                      v
                           +--------------------+
                           |   Service Layer    |
                           |  (Business Logic)  |
                           +--------------------+
                                      |
                                      v
                       +-----------------------------+
                       |        Repository Layer     |
                       | (Spring Data JPA Interface) |
                       +-----------------------------+
                                      |
                                      v
                             +------------------+
                             |    MySQL DB      |
                             +------------------+
                                      |
                +--------------------------------------------+
                | External Services:                         |
                | • Email (Spring Mail + Thymeleaf)          |
                | • JWT Auth (Token Validation & Security)   |
                +--------------------------------------------+
```

---

## 🔮 Future Enhancements
- 🗓️ Integration with Google Calendar / Outlook
- 📊 Admin dashboard (React or Angular)
- 📤 Bulk guest import (CSV/Excel)
- 🌐 Multi-language invitation templates
- 📨 Custom Invitations (Choose from predefined templates and personalize content)

---

## 👨‍💻 Author

**Nitish Kr Sahni**  
🎯 Java Backend Developer | Spring Boot | Hibernate | REST APIs  
📍 Passionate about clean, maintainable, and secure backend systems.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Nitish%20Sahni-blue?logo=linkedin)](https://www.linkedin.com/in/nitish-sahni/)  
[![GitHub](https://img.shields.io/badge/GitHub-Nitish%20Sahni-black?logo=github)](https://github.com/LogicNinjaX)

---
# <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Alien.png" alt="Alien" width="25" height="25" /> &nbsp; Keep Building &nbsp; <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Alien.png" alt="Alien" width="25" height="25" />
<img src="https://user-images.githubusercontent.com/74038190/225813708-98b745f2-7d22-48cf-9150-083f1b00d6c9.gif" width="500">
