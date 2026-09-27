package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.TrainRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.TrainResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Station;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import com.ajlan.railwayticketbookingbackend.exception.DestinationNotFoundException;
import com.ajlan.railwayticketbookingbackend.exception.StationNotFoundException;
import com.ajlan.railwayticketbookingbackend.exception.TrainNotFoundException;
import com.ajlan.railwayticketbookingbackend.mapper.TrainMapper;
import com.ajlan.railwayticketbookingbackend.repository.StationRepoitory;
import com.ajlan.railwayticketbookingbackend.repository.TrainRepository;
import com.ajlan.railwayticketbookingbackend.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
   private final TrainRepository trainRepository;
   private final TrainMapper trainMapper;
   private final StationRepoitory stationRepoitory;

    public TrainServiceImpl(TrainRepository repository, TrainMapper trainMapper, StationRepoitory stationRepoitory) {
        this.trainRepository = repository;
        this.trainMapper = trainMapper;
        this.stationRepoitory = stationRepoitory;
    }

    @Override
    public List<TrainResponseDto> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream()
                .map(trainMapper::toTranResponseDto)
                .toList();
    }

    @Override
    public TrainResponseDto addTheTrain(TrainRequestDto dto) {
        Station sourceStation = stationRepoitory.findById(dto.sourceStationId())
                .orElseThrow(() -> new RuntimeException("Source Station not found"));

        Station destinationStation = stationRepoitory.findById(dto.destinationStationId())
                .orElseThrow(() -> new RuntimeException("Source Station not found"));
        Train train = trainMapper.toTrain(dto, sourceStation, destinationStation);
        trainRepository.save(train);
        return trainMapper.toTranResponseDto(train);
    }

    @Override
    public TrainResponseDto updateTrainById(Integer id, TrainRequestDto dto)
    {
        Train train = trainRepository.findById(id).orElse(null);
        if(train != null) {
            Station sourceStation = stationRepoitory.findById(dto.sourceStationId())
                    .orElseThrow(() -> new StationNotFoundException("Source Station not found"));

            Station destinationStation = stationRepoitory.findById(dto.destinationStationId())
                    .orElseThrow(() -> new DestinationNotFoundException("Source Station not found"));
            train.setTrainNumber(dto.trainNumber());
            train.setTrainName(dto.trainName());
            train.setTrainType(dto.trainType());
            train.setDepartureTime(dto.departureTime());
            train.setArrivalTime(dto.arrivalTime());
            train.setTotalDistance(dto.totalDistance());
            train.setTotalDuration(dto.totalDuration());
            train.setSourceStation(sourceStation);
            train.setDestinationStation(destinationStation);
            trainRepository.save(train);
        } else {
            return null;
        }

        return trainMapper.toTranResponseDto(train);
    }

    @Override
    public TrainResponseDto getTrainById(Integer id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException("Unable to find the train"));
        return trainMapper.toTranResponseDto(train);
    }

    @Override
    public Boolean deleteTrainById(Integer id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException("Unable to find the train"));
        trainRepository.delete(train);
        return true;
    }

    @Override
    public TrainResponseDto updateTrainById(Integer id, boolean active) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException("Train Not Found"));
        train.setActive(active);

        return trainMapper.toTranResponseDto(train);
    }
}
