package com.switchfully.codecoach.service.security;

import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;

import java.util.UUID;

public interface KeycloakService {

    String addUser(KeycloakUserDTO keycloakUserDTO);
    void deleteUser(String userId);
    void updateUserRoleToCoach(UUID uuid);
}
