package com.ajlan.railwayticketbookingbackend.dto.response;

import com.ajlan.railwayticketbookingbackend.enums.RoleName;

public record RoleResponseDto(
        Integer roleId,

        RoleName roleName
) {


}
