package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.Gender;
import com.ajlan.railwayticketbookingbackend.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequestDto(

        @NotBlank(message = "First name cannot be blank")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        String lastName,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Please enter a valid email")
        String email,

        @NotBlank(message = "Phone number cannot be blank")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Enter a valid Indian mobile number"
        )
        String phoneNo,

        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "Address cannot be blank")
        String address,

        @NotBlank(message = "City cannot be blank")
        String city,

        @NotBlank(message = "State cannot be blank")
        String state,

        @NotBlank(message = "Pincode cannot be blank")
        @Pattern(
                regexp = "^[1-9][0-9]{5}$",
                message = "Enter a valid Indian pincode"
        )
        String pincode


) {
}
