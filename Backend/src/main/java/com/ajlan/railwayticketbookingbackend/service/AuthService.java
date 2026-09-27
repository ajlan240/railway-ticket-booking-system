package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.LoginRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.request.UserRegisterRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.LoginResponseDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;

public interface AuthService {

    UserResponseDto register(
            UserRegisterRequestDto requestDto
    );

    LoginResponseDto login(
            LoginRequestDto requestDto
    );
}
