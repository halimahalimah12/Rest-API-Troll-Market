package com.indocyber.RestAPITrollMarket.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectListRoleDto {
    private String roleName;
}
