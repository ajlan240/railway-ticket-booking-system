package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.CoachRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.CoachResponseDto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CoachService {

    CoachResponseDto addCoach(CoachRequestDto requestDto);

    CoachResponseDto updateCoach(
            Integer coachId,
            CoachRequestDto requestDto
    );

    void removeCoach(Integer coachId);

    List<CoachResponseDto> getCoachesOfATrain(Integer trainId);
}