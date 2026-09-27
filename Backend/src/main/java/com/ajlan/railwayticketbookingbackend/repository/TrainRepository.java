package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findTrainByTrainId (Integer id);
}
