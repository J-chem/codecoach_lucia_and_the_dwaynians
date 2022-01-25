package com.switchfully.codecoach.service.user;

import com.switchfully.codecoach.domain.user.CoachInfo;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.exception.UserAlreadyExistsException;
import com.switchfully.codecoach.repository.CoachInfoRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.coach.dto.CoachDTO;
import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.security.Role;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.KeycloakUserDTO;
import com.switchfully.codecoach.service.user.dto.UserDto;
import com.switchfully.codecoach.service.user.dto.mapper.KeycloakMapper;
import com.switchfully.codecoach.service.user.dto.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final KeycloakMapper keycloakMapper;
    private final KeycloakService keycloakService;
    private final CoachInfoRepository coachInfoRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, KeycloakMapper keycloakMapper,
                       KeycloakService keycloakService, CoachInfoRepository coachInfoRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.keycloakMapper = keycloakMapper;
        this.keycloakService = keycloakService;
        this.coachInfoRepository = coachInfoRepository;
    }

    public UserDto createUser(CreateUserDto createUserDto) {

        CreateUserDto validUser = assertUserIsValid(createUserDto);
        String keycloakId = addPersonToKeycloak(validUser);
        User user = userMapper.map(validUser, UUID.fromString(keycloakId));
        if (userRepository.findByEmail(createUserDto.email()) != null){
            throw new UserAlreadyExistsException("Email is already in use");
        }
        userRepository.save(user);

        return userMapper.map(userRepository.getById(user.getId()));
    }

    private CreateUserDto assertUserIsValid(CreateUserDto createUserDto) {
        if (createUserDto.firstName() == null || createUserDto.lastName() == null || createUserDto.email() == null || createUserDto.password() == null || createUserDto.team() == null) {
            throw new IllegalArgumentException("Please provide input for all fields");
        }
        return createUserDto;
    }

    private String addPersonToKeycloak(CreateUserDto createUserDto) {
        KeycloakUserDTO keycloakUserDTO = keycloakMapper.map(createUserDto, Role.COACHEE);
        return keycloakService.addUser(keycloakUserDTO);
    }

    public void becomeACoach(UUID uuid) {
        User user = userRepository.getById(uuid);
        CoachInfo coachInfo = new CoachInfo(null, null);
        coachInfoRepository.save(coachInfo);
        user.setIsCoach(true);
        user.setCoachInfo(coachInfo);
        keycloakService.updateUserRoleToCoach(uuid);
    }

    public List<CoachDTO> getByCoachesStatus(boolean isCoach) {
        //List<User> userRepository.getByCoachesStatus(isCoach);
        return null;
    }
}
