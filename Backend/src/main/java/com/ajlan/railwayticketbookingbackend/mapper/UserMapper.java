package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.UserRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Role;
import com.ajlan.railwayticketbookingbackend.entity.User;
import com.ajlan.railwayticketbookingbackend.enums.RoleName;
import com.ajlan.railwayticketbookingbackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserMapper {
    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User mapToUser(UserRequestDto dto) {

        Role role = roleRepository.getRoleByRoleName(RoleName.CUSTOMER);
        User user = User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNo(dto.phoneNo())
                .password(dto.password())
                .gender(dto.gender())
                .dateOfBirth(dto.dateOfBirth())
                .address(dto.address())
                .city(dto.city())
                .state(dto.state())
                .pincode(dto.pincode())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();

        return user;

    }
    public User mapToUser(Long id, UserRequestDto dto, User userOld) {
        Role role = roleRepository.getRoleByRoleName(RoleName.CUSTOMER);
        User user = User.builder()
                .userId(id)
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNo(dto.phoneNo())
                .password(dto.password())
                .gender(dto.gender())
                .dateOfBirth(dto.dateOfBirth())
                .address(dto.address())
                .city(dto.city())
                .state(dto.state())
                .pincode(dto.pincode())
                .createdAt(userOld.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();

        return user;

    }

    public UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNo(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAddress(),
                user.getCity(),
                user.getState(),
                user.getPincode(),
                user.getIsVerified(),
                user.getAccountStatus(),
                user.getRole().getRoleName(),
                user.getCreatedAt(),
                user.getUpdatedAt()

        );

    }
}
