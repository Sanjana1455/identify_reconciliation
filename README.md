# Identity Reconciliation Service
A powerful Spring Boot microservice that intelligently consolidates and manages contact identities across multiple touchpoints.
## Overview
This service handles contact information consolidation by linking related identities and maintaining hierarchical relationships between primary and secondary contacts.
## Core Features
- Smart contact linking and consolidation 
- Primary/Secondary contact relationship management
- Automatic duplicate detection
- Contact precedence tracking
- Comprehensive contact history

## API Reference

### Identify Contact
```http
POST /identify
Content-Type: application/json

Request Body:
{
    "email": "user@example.com",
    "phoneNumber": "1234567890"
}
Success Response:

{
    "primaryContactId": 1,
    "emails": ["user@example.com"],
    "phoneNumbers": ["1234567890"], 
    "secondaryContactIds": [],
    "linkPrecedence": "primary"
}

Technical Architecture
Key Components
ContactService: Core business logic for identity reconciliation
ContactRepository: Data access layer using Spring Data JPA
Contact: Entity model with relationship mapping
ContactRequest/Response: Data transfer objects

Database Schema

CREATE TABLE contact (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    phone_number VARCHAR(255),
    linked_id BIGINT,
    link_precedence VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);


Setup & Deployment
spring.datasource.url=jdbc:mysql://localhost:3306/identity_db
spring.datasource.username=root
spring.datasource.password=password

Build and run:
mvn clean install
java -jar target/identity-reconciliation-1.0.0.jar

Design Highlights
Efficient contact linking using LinkedHashSet for deduplication
Hierarchical contact relationships with primary/secondary pattern
Audit trail with timestamp tracking
Optimized database queries

Use Cases
New Contact Registration
Creates primary contact record
Returns consolidated contact details
Existing Contact Update
Links new information to primary contact
Creates secondary contact records
Returns merged contact information
Contact Consolidation
Identifies related contacts
Maintains contact hierarchy
Prevents duplicate entries

Performance Features
Optimized database queries
Efficient data structures
Smart caching capabilities
Minimal database operations
