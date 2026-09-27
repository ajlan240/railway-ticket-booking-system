package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.RoleRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.RoleResponseDto;
import com.ajlan.railwayticketbookingbackend.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/findRole/{id}")
    public RoleResponseDto findRoleById(
            @PathVariable Integer id
    ) {
        return roleService.findRoleById(id);
    }

    @GetMapping("/findRole")
    public List<RoleResponseDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/addRole")
    public RoleResponseDto addRole(
            @RequestBody RoleRequestDto dto
    ) {
        return roleService.addRole(dto);
    }

    @PutMapping("/updateRole/{id}")
    public RoleResponseDto updateRoleById(
            @PathVariable Integer id,
            @RequestBody RoleRequestDto dto
    ) {
        return roleService.updateRoleById(id,  dto);
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRoleById(
            @PathVariable Integer id
    ) {
         if(roleService.deleteRoleById(id)) {
             return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
         }
        return new ResponseEntity<>("Couldnt delete the role ", HttpStatus.BAD_REQUEST);
    }
}
