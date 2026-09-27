package com.ajlan.railwayticketbookingbackend.dto.request;

import com.ajlan.railwayticketbookingbackend.enums.RoleName;
import jakarta.validation.constraints.NotNull;

public record RoleRequestDto (
        @NotNull(message = "Role name cannot be null")
        RoleName roleName
){

}
