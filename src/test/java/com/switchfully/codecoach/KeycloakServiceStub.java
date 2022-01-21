package com.switchfully.codecoach;

import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Override
    public void updateUserRoleToCoach(String userName) {

    }
}
