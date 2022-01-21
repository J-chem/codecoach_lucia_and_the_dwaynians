package com.switchfully.codecoach.service.user.dto;

import com.switchfully.codecoach.service.security.Role;

public record KeycloakUserDTO (String userName, String password, Role role){
}
