package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SeatRequestDto(

        @NotNull(message = "Seat number cannot be null")
        @Positive(message = "Seat number must be greater than zero")
        Integer seatNumber,

        @NotNull(message = "Seat type cannot be null")
        SeatType seatType,

        @NotNull(message = "Coach ID cannot be null")
        Integer coachId

) {
}