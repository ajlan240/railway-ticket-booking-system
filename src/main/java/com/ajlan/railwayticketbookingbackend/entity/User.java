package com.ajlan.railwayticketbookingbackend.entity;

import com.ajlan.railwayticketbookingbackend.enums.AccountStatus;
import com.ajlan.railwayticketbookingbackend.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @NotBlank(message = "first name cannot be null")
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @NotBlank(message = "email cannot be null")
    @Email(message = "please enter a valid email")
    @Column(name = "email", nullable = false, unique = true,length = 100)
    private String email;

    @NotBlank(message = "phone number cannot be null")
    @Pattern(regexp="^[0-9]{10}$",
            message = "Enter a valid Indian mobile number"
    )
    @Column(
            name = "phone",
            nullable = false,
            unique = true,
            length = 15
    )
    private String phoneNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password Cannot be blank")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "address",nullable = false, length = 255)
    @NotBlank
    private String address;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonBackReference("users-role")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Builder.Default
    @OneToMany (mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("user-bookings")
    private List<Booking> bookings = new ArrayList<>();

}
