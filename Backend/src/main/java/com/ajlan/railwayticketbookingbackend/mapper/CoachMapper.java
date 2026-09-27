package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.CoachRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.CoachResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Coach;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoachMapper {

    public Coach toEntity(CoachRequestDto requestDto) {

        return Coach.builder()
                .coachNumber(requestDto.coachNumber())
                .coachType(requestDto.coachType())
                .totalSeats(requestDto.totalSeats())
                .build();
    }

    public void updateEntity(
            Coach coach,
            CoachRequestDto requestDto
    ) {

        coach.setCoachNumber(requestDto.coachNumber());
        coach.setCoachType(requestDto.coachType());
        coach.setTotalSeats(requestDto.totalSeats());
    }

    public CoachResponseDto toResponseDto(Coach coach) {

        return new CoachResponseDto(
                coach.getCoachId(),
                coach.getCoachNumber(),
                coach.getCoachType(),
                coach.getTotalSeats(),
                coach.getTrain().getTrainId(),
                coach.getTrain().getTrainNumber(),
                coach.getTrain().getTrainName()
        );
    }

    public List<CoachResponseDto> toListOfResponseDto(List<Coach> coaches) {
        return coaches.stream()
                .map(this::toResponseDto)
                .toList();

    }
}