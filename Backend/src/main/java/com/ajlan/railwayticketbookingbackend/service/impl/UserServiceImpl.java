package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.UserRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.User;
import com.ajlan.railwayticketbookingbackend.mapper.UserMapper;
import com.ajlan.railwayticketbookingbackend.repository.UserRepository;
import com.ajlan.railwayticketbookingbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public UserResponseDto saveNewUser(UserRequestDto dto) {
        User user = mapper.mapToUser(dto);
        User savedUser = userRepository.save(user);
        return mapper.mapToUserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllTheUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(mapper ::mapToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateTheUser(Long id, UserRequestDto dto) {
        User user = userRepository.findUserByUserId(id);
        User updatedUser = mapper.mapToUser(id, dto, user);
        userRepository.save(updatedUser);
        return mapper.mapToUserResponseDto(updatedUser);

    }

    @Override
    public Boolean deleteTheUser(Long id) {
        try {
            userRepository.deleteById((int)(long)id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public UserResponseDto getAUser(Long id) {
        User user =  userRepository.findUserByUserId(id);
        return mapper.mapToUserResponseDto(user);
    }


}
