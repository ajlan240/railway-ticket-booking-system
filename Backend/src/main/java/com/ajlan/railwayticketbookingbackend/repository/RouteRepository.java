package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    Route findRouteByRouteId (Integer id);
}
