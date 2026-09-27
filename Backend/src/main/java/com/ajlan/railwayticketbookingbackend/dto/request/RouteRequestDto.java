package com.ajlan.railwayticketbookingbackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RouteRequestDto(
        @NotNull(message = "Stop number cannot be null")
        Integer stopNumber,

        @NotNull(message = "Arrival time cannot be null")
        LocalTime arrivalTime,

        @NotNull(message = "Departure time cannot be null")
        LocalTime departureTime,

        @NotNull(message = "Distance from source cannot be null")
        Integer distanceFromSource,

        @NotNull(message = "Day number cannot be null")
        Integer dayNumber,

        @NotNull(message = "Train ID cannot be null")
        Integer trainId,

        @NotNull(message = "Station ID cannot be null")
        Integer stationId
) {
}
