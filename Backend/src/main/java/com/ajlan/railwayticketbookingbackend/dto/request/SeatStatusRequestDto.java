package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import jakarta.validation.constraints.NotNull;

public record SeatStatusRequestDto(

        @NotNull(message = "Seat status cannot be null")
        SeatStatus seatStatus

) {
}