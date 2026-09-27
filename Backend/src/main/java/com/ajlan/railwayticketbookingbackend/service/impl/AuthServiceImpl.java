package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.LoginRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.request.UserRegisterRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.LoginResponseDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.User;
import com.ajlan.railwayticketbookingbackend.exception.InvalidCredentialsException;
import com.ajlan.railwayticketbookingbackend.mapper.UserToLoginResponseDtoMapper;
import com.ajlan.railwayticketbookingbackend.repository.UserRepository;
import com.ajlan.railwayticketbookingbackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserToLoginResponseDtoMapper loginResponseDtoMapper;

    public AuthServiceImpl(UserRepository userRepository, UserToLoginResponseDtoMapper loginResponseDtoMapper) {
        this.userRepository = userRepository;
        this.loginResponseDtoMapper = loginResponseDtoMapper;
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto requestDto) {

        return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        User user = null;
        try {
            user = userRepository.findUserByEmail(requestDto.email());
        } catch (Exception e) {
            return null;
        }
        if(user.getPassword().equals(requestDto.password()))
            return loginResponseDtoMapper.toLoginResponseDto(user);

        return null;
    }
}
