# Identity Reconciliation Service

A robust Spring Boot service that intelligently processes and consolidates customer contact information across multiple entries. The service efficiently handles contact linking, primary/secondary relationships, and maintains data consistency.

## Overview

The Identity Reconciliation Service provides a RESTful API endpoint that processes customer contact information and automatically links related entries based on email addresses and phone numbers. It maintains a hierarchical relationship between primary and secondary contacts while ensuring data integrity.

## Technology Stack

- Java 17
- Spring Boot 3.x
- MySQL 8.0+
- Maven 3.6+
- JUnit (Testing)
- Postman (API Testing)

## Database Schema

### Contact Table
- `id` (INT, Primary Key)
- `email` (VARCHAR(100))
- `phoneNumber` (VARCHAR(15)) 
- `linkedId` (INT)
- `linkPrecedence` (ENUM: 'primary'/'secondary')
- `createdAt` (TIMESTAMP)
- `updatedAt` (TIMESTAMP)
- `deletedAt` (TIMESTAMP)

## Setup & Installation

1. Clone the repository:
```bash
git clone https://github.com/NikithRaj-S/Emotorad.git

1. 2.Configure MySQL database in application.properties:
spring.application.name=backendtask
spring.datasource.url=jdbc:mysql://localhost:3306/userlinkin?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=your_port

3. Build and run:
mvn spring-boot:run

API Documentation
Identify Contact
Endpoint: POST /api/identify
Purpose: Process and consolidate contact information
Request Body:
{
    "email": "san1@amazon.com",
    "phoneNumber": "9087659899"
}

Success Response:
{
    "primaryContactId": 4,
    "emails": [
        "san1@amazon.com"
    ],
    "phoneNumbers": [
        "9087657899",
        "9087659899"
    ],
    "secondaryContactIds": [
        5
    ],
    "linkPrecedence": "primary"
}

# Identity Reconciliation Service

A robust Spring Boot service that intelligently processes and consolidates customer contact information across multiple entries. The service efficiently handles contact linking, primary/secondary relationships, and maintains data consistency.

## Overview

The Identity Reconciliation Service provides a RESTful API endpoint that processes customer contact information and automatically links related entries based on email addresses and phone numbers. It maintains a hierarchical relationship between primary and secondary contacts while ensuring data integrity.

## Technology Stack

- Java 17
- Spring Boot 3.x
- MySQL 8.0+
- Maven 3.6+
- JUnit (Testing)
- Postman (API Testing)

## Database Schema

### Contact Table
- `id` (INT, Primary Key)
- `email` (VARCHAR(100))
- `phoneNumber` (VARCHAR(15)) 
- `linkedId` (INT)
- `linkPrecedence` (ENUM: 'primary'/'secondary')
- `createdAt` (TIMESTAMP)
- `updatedAt` (TIMESTAMP)
- `deletedAt` (TIMESTAMP)

## Setup & Installation

1. Clone the repository:
```bash
git clone https://github.com/NikithRaj-S/Emotorad.git

1. 2.Configure MySQL database in application.properties:
spring.application.name=backendtask
spring.datasource.url=jdbc:mysql://localhost:3306/userlinkin?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=your_port

3. Build and run:
mvn spring-boot:run

API Documentation
Identify Contact
Endpoint: POST /api/identify
Purpose: Process and consolidate contact information
Request Body:
{
    "email": "san1@amazon.com",
    "phoneNumber": "9087659899"
}

Success Response:
{
    "primaryContactId": 4,
    "emails": [
        "san1@amazon.com"
    ],
    "phoneNumbers": [
        "9087657899",
        "9087659899"
    ],
    "secondaryContactIds": [
        5
    ],
    "linkPrecedence": "primary"
}

Features
Contact information processing and consolidation
Primary/Secondary contact relationship management
Automatic contact linking based on matching criteria
Timestamp tracking for all operations
Soft delete support
Comprehensive error handling
Database optimization

Error Handling
The service implements robust error handling with appropriate HTTP status codes:

400 Bad Request: Invalid input validation
500 Internal Server Error: Server-side issues

Contributing
1.Fork the repository
2.Create your feature branch
3.Commit your changes
4.Push to the branch
5.Create a Pull Request

