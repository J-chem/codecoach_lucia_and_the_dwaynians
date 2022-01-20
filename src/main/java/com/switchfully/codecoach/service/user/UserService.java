package com.switchfully.codecoach.service.user;

import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;
import com.switchfully.codecoach.service.user.dto.mapper.KeycloakMapper;
import com.switchfully.codecoach.service.user.dto.mapper.UserMapper;
import com.switchfully.codecoach.service.security.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final KeycloakMapper keycloakMapper;
    private final KeycloakService keycloakService;

    public UserService(UserMapper userMapper, UserRepository userRepository, KeycloakMapper keycloakMapper, KeycloakService keycloakService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.keycloakMapper = keycloakMapper;
        this.keycloakService = keycloakService;
    }

    public UserDto createUser(CreateUserDto createUserDto) {

        User user = userMapper.map(assertUserIsValid(createUserDto));
        userRepository.save(user);
        String keycloakId = addPersonToKeycloak(createUserDto);

        return userMapper.map(userRepository.getById(user.getId()), keycloakId);
    }

    private CreateUserDto assertUserIsValid(CreateUserDto createUserDto) {
        if(createUserDto.firstName() == null || createUserDto.lastName() == null || createUserDto.email() == null || createUserDto.password() == null || createUserDto.team() == null) {
            throw new IllegalArgumentException("Please provide input for all fields");
        } return createUserDto;
    }

    private String addPersonToKeycloak(CreateUserDto createUserDto) {
        KeycloakUserDTO keycloakUserDTO = keycloakMapper.map(createUserDto, Role.COACHEE);
        return keycloakService.addUser(keycloakUserDTO);
    }

}
