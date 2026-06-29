# DTO Package

## Purpose

Contains Data Transfer Objects.

DTOs are used to transfer data between client and server.

## Structure

dto
│
├── request
│
└── response

## Request DTOs

LoginRequest

RegisterRequest

BookingRequest

PassengerRequest

TrainRequest

## Response DTOs

LoginResponse

UserResponse

BookingResponse

TrainResponse

TicketResponse

ErrorResponse

## Rules

Never expose Entity classes directly to clients.

Always use DTOs.