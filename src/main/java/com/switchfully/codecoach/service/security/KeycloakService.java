package com.switchfully.codecoach.service.security;

import com.switchfully.codecoach.service.security.dto.KeycloakUserDto;

import java.util.UUID;

public interface KeycloakService {

    String addUser(KeycloakUserDto keycloakUserDTO);
    void deleteUser(String userId);
    void updateUserRoleToCoach(UUID uuid);
}
