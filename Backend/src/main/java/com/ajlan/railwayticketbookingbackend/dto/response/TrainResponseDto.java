package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.enums.TrainType;

import java.time.LocalTime;

public record TrainResponseDto(

        Integer trainId,

        String trainNumber,

        String trainName,

        TrainType trainType,

        LocalTime departureTime,

        LocalTime arrivalTime,

        Integer totalDistance,

        Integer totalDuration,

        Boolean active,

        Integer sourceStationId,

        String sourceStationName,

        Integer destinationStationId,

        String destinationStationName

) {
}