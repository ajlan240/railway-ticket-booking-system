package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.StationRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.StationResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Route;
import com.ajlan.railwayticketbookingbackend.entity.Station;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import com.ajlan.railwayticketbookingbackend.repository.RouteRepository;
import com.ajlan.railwayticketbookingbackend.repository.StationRepoitory;
import com.ajlan.railwayticketbookingbackend.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StationMapper {

    private final StationRepoitory stationRepoitory;

    public StationMapper(StationRepoitory stationRepoitory) {
        this.stationRepoitory = stationRepoitory;
    }

    public Station stationRequestDtoToStation(StationRequestDto stationRequestDto) {

        Station station = Station.builder()
                .stationCode(stationRequestDto.stationCode())
                .stationName(stationRequestDto.stationName())
                .city(stationRequestDto.city())
                .state(stationRequestDto.state())
                .zone(stationRequestDto.zone())
                .platformCount(stationRequestDto.platformCount())
                .build();
        return station;

    }

    public Station StationRequestDtoToStation(Integer id, StationRequestDto stationRequestDto) {
        Station station = stationRepoitory.findById(id).orElse(null);
        if(station != null) {
            station.setStationCode(stationRequestDto.stationCode());
            station.setStationName(stationRequestDto.stationName());
            station.setCity(stationRequestDto.city());
            station.setState(stationRequestDto.state());
            station.setZone(stationRequestDto.zone());
            station.setPlatformCount(stationRequestDto.platformCount());
        }
        return station;

    }

    public StationResponseDto stationToStationResponseDto(Station station) {
        return new StationResponseDto(
                station.getStationId(),
                station.getStationCode(),
                station.getStationName(),
                station.getCity(),
                station.getState(),
                station.getZone(),
                station.getPlatformCount(),
                station.getSourceTrains(),
                station.getDestinationTrains(),
                station.getRoutes()
        );
    }

}
