package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;

public record SeatResponseDto(

        Integer seatId,

        Integer seatNumber,

        SeatType seatType,

        SeatStatus seatStatus,

        Integer coachId,

        String coachNumber,

        Integer trainId,

        String trainNumber,

        String trainName

) {
}