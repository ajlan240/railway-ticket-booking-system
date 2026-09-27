# Controller Package

## Purpose

Contains REST Controllers that receive HTTP requests from JavaFX.

## Responsibilities

- Receive HTTP Requests
- Validate incoming data
- Call Service Layer
- Return ResponseEntity

## Classes

AuthController.java

UserController.java

TrainController.java

BookingController.java

TicketController.java

StationController.java

PaymentController.java

AdminController.java

## Rules

Controllers should NOT contain business logic.

Always delegate work to Service classes.