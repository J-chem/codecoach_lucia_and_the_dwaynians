package com.switchfully.codecoach.service.security;

import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;

public interface KeycloakService {

    String addUser(KeycloakUserDTO keycloakUserDTO);
    void deleteUser(String userId);
    void updateUserRoleToCoach(String userName);
}
