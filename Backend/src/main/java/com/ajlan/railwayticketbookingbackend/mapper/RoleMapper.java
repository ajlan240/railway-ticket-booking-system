package com.ajlan.railwayticketbookingbackend.mapper;

import com.ajlan.railwayticketbookingbackend.dto.request.RoleRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.RoleResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleResponseDto toRoleResponseDto(Role role) {
        return new RoleResponseDto(
                role.getRoleId(),
                role.getRoleName()
        );
    }

    public Role toRole(RoleRequestDto dto) {

        return Role.builder()
                .roleName(dto.roleName())
                .build();
    }
}
