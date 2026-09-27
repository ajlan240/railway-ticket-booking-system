package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.enums.AccountStatus;
import com.ajlan.railwayticketbookingbackend.enums.Gender;
import com.ajlan.railwayticketbookingbackend.enums.RoleName;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseDto(
        String firstName,

        String lastName,

        String email,

        String phoneNo,

        Gender gender,

        LocalDate dateOfBirth,

        String address,

        String city,

        String state,

        String pincode,

        Boolean isVerified,

        AccountStatus accountStatus,

        RoleName role,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
