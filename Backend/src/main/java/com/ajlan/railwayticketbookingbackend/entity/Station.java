package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.RailwayZone;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Integer stationId;

    @Column(name = "station_code", nullable = false, length = 10)
    @NotBlank(message = "Station code cannot be null")
    private String stationCode;

    @Column(name = "station_name", nullable = false, length = 50)
    @NotBlank(message = "Station name cannot be null")
    private String stationName;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank(message = "city cannot be null")
    private String city;

    @Column(name = "state", nullable = false, length = 50)
    @NotBlank(message = "state cannot be null")
    private String state;

    @Column(
            name = "zone",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Zone must not be null")
    private RailwayZone zone;

    @NotNull
    @Column(name = "platform_count", nullable = false)
    private Integer platformCount;

    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    @JsonManagedReference("sourceStation")
    @Builder.Default
    private List<Train> sourceTrains = new ArrayList<>();

    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    @JsonManagedReference("destinationStation")
    @Builder.Default
    private List<Train> destinationTrains = new ArrayList<>();

    @OneToMany(
            mappedBy = "station",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("station-routes")
    @Builder.Default
    private List<Route> routes = new ArrayList<>();


}
