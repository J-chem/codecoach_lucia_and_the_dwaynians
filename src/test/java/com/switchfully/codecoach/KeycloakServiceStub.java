package com.switchfully.codecoach;

import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class KeycloakServiceStub implements KeycloakService {
    @Override
    public String addUser(KeycloakUserDTO keycloakUserDTO) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
