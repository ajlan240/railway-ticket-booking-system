package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.CoachType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coach")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Integer coachId;

    @NotBlank(message = "Coach number cannot be blank")
    @Column(name = "coach_number", nullable = false, length = 10)
    private String coachNumber;

    @Column(name = "coach_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    @Column(name = "total_seats")
    @NotNull
    private Integer totalSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "train_id",
            nullable = false
    )
    @JsonBackReference("train-coaches")
    private Train train;

    @OneToMany(
            mappedBy = "coach",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference("coach-seats")
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();


}
