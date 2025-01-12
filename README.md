---
# Event Management System API

An **Event Management System API** built with Spring Boot that allows event organizers to manage events, guests, and invitations efficiently. This API supports features like RSVP status tracking, invitation templates, guest management, and more.

---

## 📖 Features

- **Event Management**: Create, update, and delete events.
- **Guest Management**: Add, update, and track guest details.
- **RSVP Tracking**: Update and view RSVP statuses (`Pending`, `Accepted`, `Declined`).
- **Customizable Invitations**:
  - Choose from predefined templates.
  - Personalize invitations with event details.
  - Preview customized templates.
- **Email Notifications**: Send invitations to guests via email.

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring MVC, JPA, Hibernate
- **Database**: MySQL/PostgreSQL
- **Testing**: JUnit, Mockito
- **Tools**: Maven, Postman (for API testing)
<!-- **Deployment**: Docker, AWS EC2 -->

---

## 🚀 Getting Started

### Prerequisites

- **Java**: Version 17 or above.
- **Maven**: Ensure Maven is installed.
- **Database**: MySQL or PostgreSQL setup.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/event-management-system.git
   cd event-management-system

```


## Folder Structure
```bash
PROJECT_FOLDER
│  README.md
│  pom.xml
|      
└──[src]      
│  └──[main]      
│     └──[java]
|     |   └──[com.example.demo]
|     |      |
|     |      |---- DemoApplication.java # Main class
|     |      └──[controller] # Contains rest controllers
|     |      └──[dto] # Contains classes for data transfer objects
|     |      └──[entity] # Contains database entities annotated Hibernate & JPA Annotations
|     |      └──[enumerated] # Contains enums used inside entities
|     |      └──[exception] # Contains application related custom exception
|     |      └──[repository] # Contains repository classes used to perform persistence operations
|     |      └──[service] # Contains service classes
|     |      └──[util] # Contains dto mapper methods
|     |
|     |
│     └──[resources]
│        │  application.properties #contains springboot cofigurations
│        └── templates.sql  # Contains DB Script to create templates        
│      
│
└──[target]              #Java build files, auto-created after running java build: mvn install
```

Important Dependencies :
```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.33</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

   <!--for json related validation-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.8.1</version>
		</dependency>


	</dependencies>
```

```
## Configure the database and smtp:
- Update the application.properties file with your database credentials

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/eventdb
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.debug=true
```
## Build and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

---
## 📚 API Documentation
- The API is documented using Swagger. After running the application, you can view the API docs at:
```bash
[mvn clean install
mvn spring-boot:run](http://localhost:8080/swagger-ui.html)
```
---
## 🔗ER DIAGRAM
![er_diagram](/src/main/resources/er_diagram.png)
---
## Sample Endpoints
---
1. **Endpoints for organizer:**


#### Create Organizer

```bach
POST /organizer
```
Body
```json
{
    "name": "jack",
    "email": "example@gmail.com",
    "phone": 9999999999
}
```
Response
```json
{
    "timestamp": "2025-01-12T13:11:01.6397861",
    "status": 200,
    "message": "Organizer saved with id:5",
    "data": {
        "id": 5,
        "name": "jack",
        "email": "example@gmail.com",
        "phone": 9999999999
    }
}
```
##
#### Read Organizer

```bach
GET /organizer/{id}
```
Response

```json
{
    "timestamp": "2025-01-12T13:11:39.7684142",
    "status": 200,
    "message": "Organizer found with id:5",
    "data": {
        "id": 5,
        "name": "jack",
        "email": "example@gmail.com",
        "phone": 9999999999
    }
}
```
##
#### Update Organizer

```bach
PATCH /organizer/{id}
```
Body
```json
{
    "email": "example123@gmail.com"
}
```
Response
```json
{
    "timestamp": "2025-01-12T13:16:57.4526972",
    "status": 200,
    "message": "Organizer details updated:5",
    "data": {
        "id": 5,
        "name": "jack",
        "email": "example123@gmail.com",
        "phone": 9999999999
    }
}
```
##
#### Delete Organizer

```bach
DELETE /organizer/{id}
```
Response
```json
{
    "timestamp": "2025-01-12T13:21:17.3294808",
    "status": 200,
    "message": "Organizer data deleted:3"
}
```
---
2. **Endpoints For Event:**

#### Create Event

```bach
POST /organizer/{organizer-id}/events
```
Body
```json
{
    "event_name": "public party", 
    "date": "2025-01-01", 
    "time": "05:14",
    "location": "xyz",
    "description": "new year party",
    "rsvp_date": "2024-12-01"
}
```
Response
```json
{
    "timestamp": "2025-01-12T14:25:52.9580353",
    "status": 200,
    "message": "Event saved with id:3",
    "data": {
        "id": 3,
        "event_name": "public party",
        "date": "2025-01-01",
        "time": "05:14",
        "location": "xyz",
        "rsvp_date": "2024-12-01",
        "description": "new year party"
    }
}
```
##
#### Get Event

```bach
POST /organizer/{organizer-id}/events/{event-id}
```
Response
```json
{
    "timestamp": "2025-01-12T14:29:40.8796245",
    "status": 200,
    "message": "Event found with eventId:3",
    "data": {
        "id": 3,
        "event_name": "public party",
        "date": "2025-01-01",
        "time": "05:14",
        "location": "xyz",
        "rsvp_date": "2024-12-01",
        "description": "new year party"
    }
}
```
##
#### Create Guest

```bach
POST /event/{eventId}/guests
```
Body
```json
{
    "name": "Cassie",
    "email": "example@gmail.com",
    "phone": 9999999999
}
```
Response
```json
{
    "timestamp": "2025-01-12T14:35:38.0287348",
    "status": 200,
    "message": "Guest details saved",
    "data": {
        "id": 6,
        "name": "Cassie",
        "email": "example@gmail.com",
        "phone": 9999999999,
        "status": "PENDING",
        "emailStatus": "PENDING"
    }
}
```
---
# <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Alien.png" alt="Alien" width="25" height="25" /> &nbsp; Keep Building &nbsp; <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Alien.png" alt="Alien" width="25" height="25" />
<img src="https://user-images.githubusercontent.com/74038190/225813708-98b745f2-7d22-48cf-9150-083f1b00d6c9.gif" width="500">
