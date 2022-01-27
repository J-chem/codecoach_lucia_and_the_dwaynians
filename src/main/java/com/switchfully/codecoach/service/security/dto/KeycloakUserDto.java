package com.switchfully.codecoach.service.security.dto;

import com.switchfully.codecoach.service.security.Role;

public record KeycloakUserDto(String userName, String password, Role role){
}
