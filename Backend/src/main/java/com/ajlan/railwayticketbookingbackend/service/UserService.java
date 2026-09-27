package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.UserRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserResponseDto saveNewUser(UserRequestDto dto);
    List<UserResponseDto> getAllTheUsers();
    UserResponseDto updateTheUser(Long id, UserRequestDto dto);
    Boolean deleteTheUser(Long id);

    UserResponseDto getAUser(Long id);
}
