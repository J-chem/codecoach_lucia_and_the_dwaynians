package com.switchfully.codecoach;

import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.security.dto.KeycloakUserDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
@Profile("test")
public class KeycloakServiceStub implements KeycloakService {
    @Override
    public String addUser(KeycloakUserDto keycloakUserDTO) {
        return UUID.randomUUID().toString();
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void updateUserRoleToCoach(UUID uuid) {

    }
}
