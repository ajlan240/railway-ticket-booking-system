package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.enums.CoachType;

public record CoachResponseDto(

        Integer coachId,

        String coachNumber,

        CoachType coachType,

        Integer totalSeats,

        Integer trainId,

        String trainNumber,

        String trainName

) {
}