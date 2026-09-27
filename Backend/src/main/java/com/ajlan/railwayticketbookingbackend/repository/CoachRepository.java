package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.Coach;
import com.ajlan.railwayticketbookingbackend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
    List<Coach> getCoachByTrain(Train train);
}