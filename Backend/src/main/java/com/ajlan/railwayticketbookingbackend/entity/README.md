# 🚆 Railway Ticket Booking System

A full-stack Railway Ticket Booking System developed using **Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, and JavaFX**. The application allows users to search trains, book tickets, make payments, generate tickets, and manage railway operations through a modular and scalable architecture.

---

# 📌 Project Objectives

- Build an enterprise-level railway reservation system.
- Implement layered architecture using Spring Boot.
- Follow RESTful API principles.
- Apply Object-Oriented Programming and SOLID principles.
- Use JPA/Hibernate for ORM.
- Generate tickets after successful booking.
- Support future integration with Spring Security and JWT.

---

# 🏗️ Technology Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- JavaFX (Desktop Client)
- REST API

---

# 📂 Project Structure

```
src
├── controller
├── service
├── service/impl
├── repository
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── mapper
├── config
├── util
└── resources
```

---

# 🗄 Database Entities

## 1. User

Purpose:
Stores user information.

Relationships

- Many Users → One Role
- One User → Many Bookings

Fields

- userId
- firstName
- lastName
- email
- phoneNo
- password
- gender
- dateOfBirth
- address
- city
- state
- pincode
- isVerified
- accountStatus
- createdAt
- updatedAt

---

## 2. Role

Purpose:
Stores user roles.

Relationships

- One Role → Many Users

Fields

- roleId
- roleName

---

## 3. Station

Purpose:
Stores railway station information.

Relationships

- One Station → Many Routes
- One Station → Many Source Trains
- One Station → Many Destination Trains

Fields

- stationId
- stationCode
- stationName
- city
- state
- railwayZone
- platformCount

---

## 4. Train

Purpose:
Stores train details.

Relationships

- Many Trains → One Source Station
- Many Trains → One Destination Station
- One Train → Many Routes
- One Train → Many Coaches
- One Train → Many Bookings

Fields

- trainId
- trainNumber
- trainName
- trainType
- departureTime
- arrivalTime
- totalDistance
- totalDuration
- active

---

## 5. Route

Purpose:
Stores all stations visited by a train.

Relationships

- Many Routes → One Train
- Many Routes → One Station

Fields

- routeId
- stopNumber
- arrivalTime
- departureTime
- distanceFromSource
- dayNumber

---

## 6. Coach

Purpose:
Stores coach information of a train.

Relationships

- Many Coaches → One Train
- One Coach → Many Seats

Fields

- coachId
- coachNumber
- coachType
- totalSeats

---

## 7. Seat

Purpose:
Stores seats available inside a coach.

Relationships

- Many Seats → One Coach
- One Seat → Many Seat Allocations

Fields

- seatId
- seatNumber
- seatType
- seatStatus

---

## 8. Booking

Purpose:
Stores booking information.

Relationships

- Many Bookings → One User
- Many Bookings → One Train
- One Booking → One Payment
- One Booking → One Ticket
- One Booking → Many Passengers
- One Booking → Many Seat Allocations

Fields

- bookingId
- pnrNumber
- journeyDate
- bookingStatus
- totalFare
- paymentStatus
- numberOfPassengers

---

## 9. Passenger

Purpose:
Stores passenger details.

Relationships

- Many Passengers → One Booking
- One Passenger → One Seat Allocation

Fields

- passengerId
- firstName
- lastName
- age
- gender
- nationality
- berthPreference
- isSeniorCitizen

---

## 10. Seat Allocation

Purpose:
Maps passengers to allocated seats.

Relationships

- Many Seat Allocations → One Booking
- Many Seat Allocations → One Seat
- One Seat Allocation → One Passenger

Fields

- allocationId
- allocationStatus

---

## 11. Payment

Purpose:
Stores payment information.

Relationships

- One Payment → One Booking

Fields

- paymentId
- paymentMethod
- amount
- paymentStatus
- paymentDate

---

## 12. Ticket

Purpose:
Stores generated ticket information.

Relationships

- One Ticket → One Booking

Fields

- ticketId
- ticketNumber
- filePath
- generatedAt

---

# 📌 Entity Relationship Summary

```
Role
 └── User
       └── Booking
             ├── Passenger
             │      └── SeatAllocation
             ├── Payment
             ├── Ticket
             └── Train
                    ├── Coach
                    │      └── Seat
                    │              └── SeatAllocation
                    └── Route
                            └── Station
```

---

# 📋 Modules

## User Management

- User Registration
- Update Profile
- Delete User
- View User
- User Validation

---

## Role Management

- Assign Role
- View Roles

---

## Station Management

- Add Station
- Update Station
- Delete Station
- View Station

---

## Train Management

- Add Train
- Update Train
- Delete Train
- Search Train

---

## Route Management

- Add Route
- Update Route
- Delete Route

---

## Coach Management

- Add Coach
- Update Coach
- Delete Coach

---

## Seat Management

- Generate Seats
- Update Seat Status

---

## Booking Management

- Book Ticket
- Cancel Booking
- Booking History
- PNR Generation

---

## Passenger Management

- Add Passenger
- Update Passenger
- Delete Passenger

---

## Seat Allocation

- Allocate Seats
- Berth Preference Handling

---

## Payment Management

- Record Payment
- Payment History
- Payment Status

---

## Ticket Management

- Generate Ticket
- Download Ticket
- Ticket History

---

# 🚀 Future Enhancements

- Spring Security
- JWT Authentication
- Email Verification
- OTP Login
- Waiting List
- RAC
- Dynamic Fare
- Refund Module
- Notifications
- Admin Dashboard
- Reports
- Docker Deployment
- Unit Testing
- Integration Testing

---

# 👨‍💻 Architecture

```
Client
    │
    ▼
Controller
    │
    ▼
Service
    │
    ▼
ServiceImpl
    │
    ▼
Repository
    │
    ▼
MySQL Database
```

DTO Flow

```
Client
   │
Request DTO
   │
Controller
   │
Service
   │
Entity
   │
Repository
   │
Database
   │
Entity
   │
Response DTO
   │
Client
```

---

# 📖 Design Principles

- Layered Architecture
- SOLID Principles
- RESTful API Design
- DTO Pattern
- Repository Pattern
- Service Layer Pattern
- JPA/Hibernate ORM
- Object-Oriented Programming

---

# 📅 Current Progress

- [x] Database Design
- [x] Entity Creation
- [ ] DTO Layer
- [ ] Repository Layer
- [ ] Service Layer
- [ ] REST Controllers
- [ ] Exception Handling
- [ ] Validation
- [ ] Booking Logic
- [ ] Payment Integration
- [ ] Ticket Generation
- [ ] JavaFX Client
- [ ] Spring Security