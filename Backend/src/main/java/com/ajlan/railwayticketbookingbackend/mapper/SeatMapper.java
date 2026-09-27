package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.SeatRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.SeatResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatMapper {

    public Seat toEntity(SeatRequestDto requestDto) {

        return Seat.builder()
                .seatNumber(requestDto.seatNumber())
                .seatType(requestDto.seatType())
                .seatStatus(
                        com.ajlan.railwayticketbookingbackend.enums.SeatStatus.AVAILABLE
                )
                .build();
    }

    public void updateEntity(
            Seat seat,
            SeatRequestDto requestDto
    ) {

        seat.setSeatNumber(requestDto.seatNumber());
        seat.setSeatType(requestDto.seatType());
    }

    public SeatResponseDto toResponseDto(Seat seat) {

        return new SeatResponseDto(
                seat.getSeatId(),
                seat.getSeatNumber(),
                seat.getSeatType(),
                seat.getSeatStatus(),
                seat.getCoach().getCoachId(),
                seat.getCoach().getCoachNumber(),
                seat.getCoach().getTrain().getTrainId(),
                seat.getCoach().getTrain().getTrainNumber(),
                seat.getCoach().getTrain().getTrainName()
        );
    }

    public List<SeatResponseDto> toResponseDtoList(
            List<Seat> seats
    ) {

        return seats.stream()
                .map(this::toResponseDto)
                .toList();
    }
}