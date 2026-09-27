package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.Gender;
import com.ajlan.railwayticketbookingbackend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenegr_id")
    private Integer passengerId;

    @Column(name = "first_name", length = 45)
    @NotBlank(message = "first name cnnot be null")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @NotBlank(message = "last name cnnot be null")
    private String lastName;

    @Column(name = "age", nullable = false)
    @NotNull(message = "Age cannot be null")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender cannot be null")
    @Column(name = "gender")
    private Gender gender;

    @NotBlank(message = "Nationality cannopt be null")
    @Column(name = "nationality", nullable = false, length = 45)
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "berth_preference")
    private SeatType berthPreference;

    @Column(name = "is_senior_citizen")
    @Builder.Default
    private Boolean isSeniorCitizen = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    @JsonBackReference("booking-passengers")
    private Booking booking;

    @OneToOne(mappedBy = "passenger")
    @JsonManagedReference("passenger-seatAllocation")
    private SeatAllocation seatAllocation;




}
