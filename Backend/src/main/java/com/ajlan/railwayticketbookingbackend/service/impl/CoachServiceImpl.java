package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.CoachRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.CoachResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Coach;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import com.ajlan.railwayticketbookingbackend.exception.CoachNotFoundException;
import com.ajlan.railwayticketbookingbackend.exception.TrainNotFoundException;
import com.ajlan.railwayticketbookingbackend.mapper.CoachMapper;
import com.ajlan.railwayticketbookingbackend.repository.CoachRepository;
import com.ajlan.railwayticketbookingbackend.repository.TrainRepository;
import com.ajlan.railwayticketbookingbackend.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;
    private final CoachMapper coachMapper;

    @Override
    @Transactional
    public CoachResponseDto addCoach(CoachRequestDto requestDto) {

        Train train = trainRepository.findById(requestDto.trainId())
                .orElseThrow(() ->
                        new TrainNotFoundException(
                                "Train not found with id: "
                                        + requestDto.trainId()
                        )
                );

        Coach coach = coachMapper.toEntity(requestDto);

        coach.setTrain(train);

        Coach savedCoach = coachRepository.save(coach);

        return coachMapper.toResponseDto(savedCoach);
    }

    @Override
    @Transactional
    public CoachResponseDto updateCoach(
            Integer coachId,
            CoachRequestDto requestDto
    ) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new CoachNotFoundException(
                                "Coach not found with id: "
                                        + coachId
                        )
                );

        Train train = trainRepository.findById(requestDto.trainId())
                .orElseThrow(() ->
                        new TrainNotFoundException(
                                "Train not found with id: "
                                        + requestDto.trainId()
                        )
                );

        coachMapper.updateEntity(coach, requestDto);

        coach.setTrain(train);

        Coach updatedCoach = coachRepository.save(coach);

        return coachMapper.toResponseDto(updatedCoach);
    }

    @Override
    @Transactional
    public void removeCoach(Integer coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new CoachNotFoundException(
                                "Coach not found with id: "
                                        + coachId
                        )
                );

        coachRepository.delete(coach);
    }

    @Override
    public List<CoachResponseDto> getCoachesOfATrain(Integer trainId) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() ->
                    new TrainNotFoundException(
                            "Train not found with id: "
                                    + trainId
                    )
                );

        return coachMapper.toListOfResponseDto(train.getCoaches());

    }
}