package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.RailwayZone;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StationRequestDto(

        @NotBlank(message = "Station code cannot be null")
        String stationCode,

        @NotBlank(message = "Station name cannot be null")
        String stationName,

        @NotBlank(message = "city cannot be null")
        String city,

        @NotBlank(message = "State cannot be null")
        String state,


        @NotNull(message = "Zone must not be null")
        RailwayZone zone,

        @NotNull(message = "Platform count cannot be null")
        Integer platformCount,

        @NotNull(message = "Source Train id  cannot be null")
        List<Integer> sourceTrainIds,

        @NotNull(message = "Source Train id  cannot be null")
        List<Integer> destinationTrainIds,

        @NotNull(message = "Route Train id  cannot be null")
        List<Integer> routeIds




) {
}
