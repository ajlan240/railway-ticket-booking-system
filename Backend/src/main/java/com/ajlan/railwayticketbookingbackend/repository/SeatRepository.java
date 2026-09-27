package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.Seat;
import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByCoachCoachIdOrderBySeatNumber(
            Integer coachId
    );

    Optional<Seat> findByCoachCoachIdAndSeatNumber(
            Integer coachId,
            Integer seatNumber
    );

    boolean existsByCoachCoachIdAndSeatNumber(
            Integer coachId,
            Integer seatNumber
    );

    List<Seat> findByCoachCoachIdAndSeatStatus(
            Integer coachId,
            SeatStatus seatStatus
    );

    List<Seat> findByCoachCoachIdAndSeatType(
            Integer coachId,
            SeatType seatType
    );

    long countByCoachCoachId(
            Integer coachId
    );

    long countByCoachCoachIdAndSeatStatus(
            Integer coachId,
            SeatStatus seatStatus
    );
}