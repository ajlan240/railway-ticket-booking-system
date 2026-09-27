package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.AllocationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat_allocation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_id")
    private Integer allocationId;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "allocation_status",
            nullable = false
    )
    private AllocationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "booking_id",
            nullable = false
    )
    @JsonBackReference("booking-seatAllocations")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "seat_id",
            nullable = false
    )
    @JsonBackReference("seat-allocations")
    private Seat seat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "passenger_id",
            nullable = false,
            unique = true
    )
    @JsonBackReference("passenger-seatAllocation")
    private Passenger passenger;



}
