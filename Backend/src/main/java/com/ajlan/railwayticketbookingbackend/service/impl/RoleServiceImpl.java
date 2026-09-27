package com.ajlan.railwayticketbookingbackend.service.impl;

import com.ajlan.railwayticketbookingbackend.dto.request.RoleRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.RoleResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Role;
import com.ajlan.railwayticketbookingbackend.exception.RoleNotFoundException;
import com.ajlan.railwayticketbookingbackend.mapper.RoleMapper;
import com.ajlan.railwayticketbookingbackend.repository.RoleRepository;
import com.ajlan.railwayticketbookingbackend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDto findRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Couldn't find the role"));
        return roleMapper.toRoleResponseDto(role);
    }

    @Override
    public Boolean deleteRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElse(null);
        if(role == null) return false;
        roleRepository.delete(role);
        return true;
    }

    @Override
    public RoleResponseDto updateRoleById(Integer id, RoleRequestDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Couldn't find the role"));
        role.setRoleName(dto.roleName());
        return roleMapper.toRoleResponseDto(role);
    }

    @Override
    public RoleResponseDto addRole(RoleRequestDto dto) {
        Role role = roleMapper.toRole(dto);
        roleRepository.save(role);
        return roleMapper.toRoleResponseDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream()
                .map(roleMapper::toRoleResponseDto)
                .toList();
    }
}
