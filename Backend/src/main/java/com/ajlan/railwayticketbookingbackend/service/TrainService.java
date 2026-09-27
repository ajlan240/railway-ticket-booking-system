package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.TrainRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.TrainResponseDto;

import java.util.List;

public interface TrainService {

   List< TrainResponseDto> getAllTrains();
    TrainResponseDto addTheTrain(TrainRequestDto dto);
    TrainResponseDto updateTrainById(Integer id, TrainRequestDto dto);
    TrainResponseDto  getTrainById(Integer id);
    Boolean deleteTrainById(Integer id);


    TrainResponseDto updateTrainById(Integer id, boolean active);
}
