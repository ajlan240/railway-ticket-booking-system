package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.response.LoginResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserToLoginResponseDtoMapper {

    public LoginResponseDto toLoginResponseDto(User user) {

        return new LoginResponseDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getRoleName().name()
        );
    }
}