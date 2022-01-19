package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;
import com.switchfully.codecoach.service.security.Role;
import org.springframework.stereotype.Component;

@Component
public class KeycloakMapper {

    public KeycloakUserDTO map(CreateUserDto createUserDto, Role role) {
        return new KeycloakUserDTO(
                createUserDto.email(),
                createUserDto.password(),
                role);
    }
}
