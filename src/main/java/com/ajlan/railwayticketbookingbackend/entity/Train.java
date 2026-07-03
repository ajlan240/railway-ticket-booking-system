package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.TrainType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", nullable = false)
    private Integer trainId;

    @Column(name = "train_number", nullable = false, length = 10)
    @NotBlank
    private String trainNumber;

    @Column(name = "train_name", nullable = false, length = 100)
    @NotBlank
    private String trainName;

    @Enumerated(EnumType.STRING)
    @Column(name = "train_type", nullable = false, length = 100)
    @NotNull(message = "Train type cannot be null")
    private TrainType trainType;

    @Column(name = "departure_time", nullable = false)
    @NotNull
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    @NotNull
    private LocalTime arrivalTime;

    @Column(name = "total_distance")
    @NotNull
    private Integer totalDistance;

    @Column(name = "total_duration")
    @NotNull
    private Integer totalDuration;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean active = true;

    @OneToMany (
            mappedBy = "train",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("train-booking")
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(
            fetch = FetchType.LAZY

    )
    @JoinColumn(
            name = "source_station_id",
            nullable = false
    )
    @JsonBackReference("sourceStation")
    private Station sourceStation;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "destination_station_id",
            nullable = false
    )
    @JsonBackReference("destinationStation")
    private Station destinationStation;

    @OneToMany(
            mappedBy = "train",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("train-routes")
    @Builder.Default
    private List<Route> routes = new ArrayList<>();

    @OneToMany(
            mappedBy = "train",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("train-coaches")
    @Builder.Default
    private List<Coach>  coaches = new ArrayList<>();



}
