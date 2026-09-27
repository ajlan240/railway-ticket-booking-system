package com.ajlan.railwayticketbookingbackend.service;

import com.ajlan.railwayticketbookingbackend.dto.request.RoleRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.RoleResponseDto;

import java.util.List;

public interface RoleService {

    RoleResponseDto  findRoleById(Integer id);

    Boolean deleteRoleById(Integer id);

    RoleResponseDto updateRoleById(Integer id, RoleRequestDto dto);

    RoleResponseDto addRole(RoleRequestDto dto);

    List<RoleResponseDto> getAllRoles();

}
