package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.StationRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.StationResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Route;
import com.ajlan.railwayticketbookingbackend.entity.Station;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import com.ajlan.railwayticketbookingbackend.mapper.StationMapper;
import com.ajlan.railwayticketbookingbackend.repository.RouteRepository;
import com.ajlan.railwayticketbookingbackend.repository.StationRepoitory;
import com.ajlan.railwayticketbookingbackend.repository.TrainRepository;
import com.ajlan.railwayticketbookingbackend.service.StationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepoitory repository;
    private final StationMapper mapper;


    public StationServiceImpl(StationRepoitory repository, StationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;

    }


    @Override
    public StationResponseDto addTheStation(StationRequestDto dto) {
        Station savedStation = mapper.stationRequestDtoToStation(dto);
        repository.save(savedStation);
        return mapper.stationToStationResponseDto(savedStation);
    }

    @Override
    public List<StationResponseDto> getAllStations() {
        List<Station> stations = repository.findAll();
        List<StationResponseDto> stationResponseDtos = new ArrayList<>();
        for(Station station : stations) {
            stationResponseDtos.add(mapper.stationToStationResponseDto(station));
        }
        return stationResponseDtos;
    }

    @Override
    public StationResponseDto updateTheStation(Integer id, StationRequestDto dto) {
        Station station = repository.save(mapper.StationRequestDtoToStation(id, dto));
        return mapper.stationToStationResponseDto(station);
    }

    @Override
    public Boolean deleteTheStation(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public StationResponseDto getStationById(Integer id) {
        Station station = repository.findById(id).orElse(null);
        if(station != null) return mapper.stationToStationResponseDto(station);
        return null;
    }
}