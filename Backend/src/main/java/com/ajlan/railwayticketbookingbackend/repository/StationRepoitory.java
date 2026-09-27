package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepoitory extends JpaRepository<Station, Integer> {
}
