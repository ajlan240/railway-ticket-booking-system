package com.ajlan.railwayticketbookingbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "route")
public class Route {
    @Id
    @Column(name = "route_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @Column(name = "stop_number", nullable = false)
    private Integer stopNumber;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(
            name = "distance_from_source",
            nullable = false
    )
    @NotNull
    private Integer distanceFromSource;

    @Column(name = "day_number")
    @NotNull
    private Integer dayNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "train_id",
            nullable = false
    )
    @JsonBackReference("train-routes")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "station_id",
            nullable = false
    )
    @JsonBackReference("station-routes")
    private Station station;

}
