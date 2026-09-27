package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.TrainRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.TrainResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Booking;
import com.ajlan.railwayticketbookingbackend.entity.Station;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainMapper {
    public TrainResponseDto toTranResponseDto(Train train) {
        return new TrainResponseDto(
                train.getTrainId(),
                train.getTrainNumber(),
                train.getTrainName(),
                train.getTrainType(),
                train.getDepartureTime(),
                train.getArrivalTime(),
                train.getTotalDistance(),
                train.getTotalDuration(),
                train.getActive(),
                train.getSourceStation().getStationId(),
                train.getSourceStation().getStationName(),
                train.getDestinationStation().getStationId(),
                train.getDestinationStation().getStationName()

        );
    }

    public Train toTrain(TrainRequestDto dto, Station sourceStation, Station destinationStation) {
        return  Train.builder()
                .trainNumber(dto.trainNumber())
                .trainName(dto.trainName())
                .trainType(dto.trainType())
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .totalDistance(dto.totalDistance())
                .totalDuration(dto.totalDuration())
                .sourceStation(sourceStation)
                .destinationStation(destinationStation)
                .build();








    }
}
