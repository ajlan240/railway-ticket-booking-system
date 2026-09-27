package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.entity.Route;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import com.ajlan.railwayticketbookingbackend.enums.RailwayZone;

import java.util.List;

public record StationResponseDto(
        Integer stationId,

        String stationCode,

        String stationName,

        String city,

        String state,

        RailwayZone zone,

        Integer platformCount,

        List<Train> sourceTrains,

        List<Train> destinationTrains,

        List<Route> routes
) {
}
