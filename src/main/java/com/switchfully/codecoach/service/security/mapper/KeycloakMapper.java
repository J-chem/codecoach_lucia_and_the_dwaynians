package com.switchfully.codecoach.service.security.mapper;

import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.security.dto.KeycloakUserDto;
import com.switchfully.codecoach.service.security.Role;
import org.springframework.stereotype.Component;

@Component
public class KeycloakMapper {

    public KeycloakUserDto map(CreateUserDto createUser, Role role) {
        return new KeycloakUserDto(
                createUser.email(),
                createUser.password(),
                role);
    }
}
