package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.BookingStatus;
import com.ajlan.railwayticketbookingbackend.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @NotBlank(message = "pnr feild cantbe null")
    @Column(name = "pnr_number", nullable = false, length = 20)
    private String pnrNumber;

    @Column(name = "journey_date")
    private LocalDate journeyDate;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "total_fare", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalFare;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "number_of_passengers")
    private Integer numberOfPassengers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference("user-bookings")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "train_id",
            nullable = false
    )
    @JsonBackReference("train-booking")
    private Train train;

    @OneToOne(
            mappedBy = "booking",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("ticket-booking")
    private Ticket ticket;

    @OneToOne(
            mappedBy = "booking",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("payement-booking")
    private Payment payment;

    @OneToMany(mappedBy = "booking")
    @JsonManagedReference("booking-passengers")
    @Builder.Default
    private List<Passenger> passengers = new ArrayList<>();

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("booking-seatAllocations")
    @Builder.Default
    private List<SeatAllocation> seatAllocations = new ArrayList<>();
}
