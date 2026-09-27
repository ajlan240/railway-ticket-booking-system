package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.StationRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.StationResponseDto;

import java.util.List;

public interface StationService {

    StationResponseDto addTheStation(StationRequestDto dto);
    List<StationResponseDto> getAllStations();
    StationResponseDto updateTheStation(Integer id, StationRequestDto dto);
    Boolean deleteTheStation(Integer id);

    StationResponseDto getStationById(Integer id);
}
