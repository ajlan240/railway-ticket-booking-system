package com.ajlan.railwayticketbookingbackend.dto.response;

import java.time.LocalTime;

public  record RouteResponseDto(
        Integer routeId,

        Integer stopNumber,

        LocalTime arrivalTime,

        LocalTime departureTime,

        Integer distanceFromSource,

        Integer dayNumber,

        Integer trainId,

        String trainNumber,

        String trainName,

        Integer stationId,

        String stationCode,

        String stationName
) {
}
