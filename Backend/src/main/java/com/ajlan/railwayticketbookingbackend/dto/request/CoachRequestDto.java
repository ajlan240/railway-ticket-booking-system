package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.CoachType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CoachRequestDto(

        @NotBlank(message = "Coach number cannot be blank")
        String coachNumber,

        @NotNull(message = "Coach type cannot be null")
        CoachType coachType,

        @NotNull(message = "Total seats cannot be null")
        Integer totalSeats,

        @NotNull(message = "Train ID cannot be null")
        Integer trainId

) {
}