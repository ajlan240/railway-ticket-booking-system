package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.TrainType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record TrainRequestDto(

        @NotBlank(message = "Train number cannot be blank")
        String trainNumber,

        @NotBlank(message = "Train name cannot be blank")
        String trainName,

        @NotNull(message = "Train type cannot be null")
        TrainType trainType,

        @NotNull(message = "Departure time cannot be null")
        LocalTime departureTime,

        @NotNull(message = "Arrival time cannot be null")
        LocalTime arrivalTime,

        @NotNull(message = "Total distance cannot be null")
        Integer totalDistance,

        @NotNull(message = "Total duration cannot be null")
        Integer totalDuration,

        @NotNull(message = "Source station id cannot be null")
        Integer sourceStationId,

        @NotNull(message = "Destination station id cannot be null")
        Integer destinationStationId

) {
}