package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.SeatStatus;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(
            name = "seat_number",
            nullable = false
    )
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "seat_type",
            nullable = false
    )
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_status")
    @Builder.Default
    private SeatStatus seatStatus = SeatStatus.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "coach_id",
            nullable = false
    )
    @JsonBackReference("coach-seats")
    private Coach coach;

    @OneToMany(
            mappedBy = "seat",
    fetch = FetchType.LAZY)
    @JsonManagedReference("seat-allocations")
    @Builder.Default
    private List<SeatAllocation> allocations = new ArrayList<>();

}
