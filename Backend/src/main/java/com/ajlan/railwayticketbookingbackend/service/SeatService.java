package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.SeatRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.request.SeatStatusRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.SeatResponseDto;
import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;

import java.util.List;

public interface SeatService {

    SeatResponseDto addSeat(
            SeatRequestDto requestDto
    );

    List<SeatResponseDto> generateSeats(
            Integer coachId
    );

    SeatResponseDto updateSeat(
            Integer seatId,
            SeatRequestDto requestDto
    );

    SeatResponseDto getSeatById(
            Integer seatId
    );

    List<SeatResponseDto> getAllSeats();

    List<SeatResponseDto> getSeatsByCoach(
            Integer coachId
    );

    SeatResponseDto updateSeatStatus(
            Integer seatId,
            SeatStatusRequestDto requestDto
    );

    List<SeatResponseDto> getSeatsByStatus(
            Integer coachId,
            SeatStatus status
    );

    List<SeatResponseDto> getSeatsByType(
            Integer coachId,
            SeatType type
    );

    void deleteSeat(
            Integer seatId
    );
}