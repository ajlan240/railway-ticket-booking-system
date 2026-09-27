package com.ajlan.railwayticketbookingbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(
            name = "ticket_number",
            nullable = false,
            unique = true,
            length = 20
    )
    @NotBlank(message = "Ticket Number Cannot be blank")
    private String ticketNumber;

    @Column(
            name = "file_path",
            nullable = false,
            length = 255
    )
    @NotBlank(message = "File path cannot be blank")
    private String filePath;

    @CreationTimestamp
    @Column(
            name = "generated_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime generatedAt;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "booking_id",
            nullable = false,
            unique = true
    )
    @JsonBackReference("ticket-booking")
    private Booking booking;

}
