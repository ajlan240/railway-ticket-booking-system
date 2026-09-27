package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.SeatRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.request.SeatStatusRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.SeatResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Coach;
import com.ajlan.railwayticketbookingbackend.entity.Seat;
import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import com.ajlan.railwayticketbookingbackend.exception.CoachNotFoundException;
import com.ajlan.railwayticketbookingbackend.exception.SeatAlreadyExistsException;
import com.ajlan.railwayticketbookingbackend.exception.SeatNotFoundException;
import com.ajlan.railwayticketbookingbackend.mapper.SeatMapper;
import com.ajlan.railwayticketbookingbackend.repository.CoachRepository;
import com.ajlan.railwayticketbookingbackend.repository.SeatRepository;
import com.ajlan.railwayticketbookingbackend.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final CoachRepository coachRepository;
    private final SeatMapper seatMapper;


    // ==========================================
    // ADD SEAT
    // ==========================================

    @Override
    @Transactional
    public SeatResponseDto addSeat(
            SeatRequestDto requestDto
    ) {

        Coach coach = getCoach(requestDto.coachId());

        boolean exists =
                seatRepository.existsByCoachCoachIdAndSeatNumber(
                        requestDto.coachId(),
                        requestDto.seatNumber()
                );

        if (exists) {

            throw new SeatAlreadyExistsException(
                    "Seat " + requestDto.seatNumber()
                            + " already exists in coach "
                            + requestDto.coachId()
            );
        }

        Seat seat = seatMapper.toEntity(requestDto);

        seat.setCoach(coach);

        Seat savedSeat =
                seatRepository.save(seat);

        return seatMapper.toResponseDto(savedSeat);
    }


    // ==========================================
    // GENERATE SEATS
    // ==========================================

    @Override
    @Transactional
    public List<SeatResponseDto> generateSeats(
            Integer coachId
    ) {

        Coach coach = getCoach(coachId);

        Integer totalSeats = coach.getTotalSeats();

        if (totalSeats == null || totalSeats <= 0) {

            throw new IllegalArgumentException(
                    "Coach must have a valid total seat count"
            );
        }

        List<Seat> seats = new ArrayList<>();

        for (
                int seatNumber = 1;
                seatNumber <= totalSeats;
                seatNumber++
        ) {

            boolean exists =
                    seatRepository
                            .existsByCoachCoachIdAndSeatNumber(
                                    coachId,
                                    seatNumber
                            );

            if (exists) {
                continue;
            }

            Seat seat = Seat.builder()
                    .seatNumber(seatNumber)

                    // Change this according to
                    // your actual seat layout.
                    .seatType(SeatType.LOWER)

                    .seatStatus(SeatStatus.AVAILABLE)
                    .coach(coach)
                    .build();

            seats.add(seat);
        }

        if (seats.isEmpty()) {
            return List.of();
        }

        List<Seat> savedSeats =
                seatRepository.saveAll(seats);

        return seatMapper.toResponseDtoList(
                savedSeats
        );
    }


    // ==========================================
    // UPDATE SEAT
    // ==========================================

    @Override
    @Transactional
    public SeatResponseDto updateSeat(
            Integer seatId,
            SeatRequestDto requestDto
    ) {

        Seat seat = getSeat(seatId);

        Coach coach =
                getCoach(requestDto.coachId());

        boolean seatNumberChanged =
                !seat.getSeatNumber()
                        .equals(requestDto.seatNumber());

        boolean coachChanged =
                !seat.getCoach()
                        .getCoachId()
                        .equals(requestDto.coachId());

        if (seatNumberChanged || coachChanged) {

            boolean exists =
                    seatRepository
                            .existsByCoachCoachIdAndSeatNumber(
                                    requestDto.coachId(),
                                    requestDto.seatNumber()
                            );

            if (exists) {

                throw new SeatAlreadyExistsException(
                        "Seat " + requestDto.seatNumber()
                                + " already exists in coach "
                                + requestDto.coachId()
                );
            }
        }

        seatMapper.updateEntity(
                seat,
                requestDto
        );

        seat.setCoach(coach);

        Seat updatedSeat =
                seatRepository.save(seat);

        return seatMapper.toResponseDto(
                updatedSeat
        );
    }


    // ==========================================
    // GET SEAT BY ID
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public SeatResponseDto getSeatById(
            Integer seatId
    ) {

        return seatMapper.toResponseDto(
                getSeat(seatId)
        );
    }


    // ==========================================
    // GET ALL SEATS
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getAllSeats() {

        return seatMapper.toResponseDtoList(
                seatRepository.findAll()
        );
    }


    // ==========================================
    // GET SEATS BY COACH
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getSeatsByCoach(
            Integer coachId
    ) {

        getCoach(coachId);

        return seatMapper.toResponseDtoList(
                seatRepository
                        .findByCoachCoachIdOrderBySeatNumber(
                                coachId
                        )
        );
    }


    // ==========================================
    // UPDATE SEAT STATUS
    // ==========================================

    @Override
    @Transactional
    public SeatResponseDto updateSeatStatus(
            Integer seatId,
            SeatStatusRequestDto requestDto
    ) {

        Seat seat = getSeat(seatId);

        seat.setSeatStatus(
                requestDto.seatStatus()
        );

        Seat updatedSeat =
                seatRepository.save(seat);

        return seatMapper.toResponseDto(
                updatedSeat
        );
    }


    // ==========================================
    // GET SEATS BY STATUS
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getSeatsByStatus(
            Integer coachId,
            SeatStatus status
    ) {

        getCoach(coachId);

        return seatMapper.toResponseDtoList(
                seatRepository
                        .findByCoachCoachIdAndSeatStatus(
                                coachId,
                                status
                        )
        );
    }


    // ==========================================
    // GET SEATS BY TYPE
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getSeatsByType(
            Integer coachId,
            SeatType type
    ) {

        getCoach(coachId);

        return seatMapper.toResponseDtoList(
                seatRepository
                        .findByCoachCoachIdAndSeatType(
                                coachId,
                                type
                        )
        );
    }


    // ==========================================
    // DELETE SEAT
    // ==========================================

    @Override
    @Transactional
    public void deleteSeat(
            Integer seatId
    ) {

        Seat seat = getSeat(seatId);

        seatRepository.delete(seat);
    }


    // ==========================================
    // HELPER METHODS
    // ==========================================

    private Seat getSeat(Integer seatId) {

        return seatRepository
                .findById(seatId)
                .orElseThrow(
                        () -> new SeatNotFoundException(
                                "Seat not found with id: "
                                        + seatId
                        )
                );
    }


    private Coach getCoach(Integer coachId) {

        return coachRepository
                .findById(coachId)
                .orElseThrow(
                        () -> new CoachNotFoundException(
                                "Coach not found with id: "
                                        + coachId
                        )
                );
    }
}