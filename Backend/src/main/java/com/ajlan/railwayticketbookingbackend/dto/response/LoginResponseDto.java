package com.ajlan.railwayticketbookingbackend.dto.response;

public record LoginResponseDto(

        Long userId,

        String firstName,

        String lastName,

        String email,

        String role

) {
}