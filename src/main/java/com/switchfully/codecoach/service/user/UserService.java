package com.switchfully.codecoach.service.user;

import com.switchfully.codecoach.domain.coachinfo.CoachInfo;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.exception.UserAlreadyExistsException;
import com.switchfully.codecoach.repository.CoachInfoRepository;
import com.switchfully.codecoach.repository.CoachInfoTopicRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.security.Role;
import com.switchfully.codecoach.service.security.dto.KeycloakUserDto;
import com.switchfully.codecoach.service.security.mapper.KeycloakMapper;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import com.switchfully.codecoach.service.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final KeycloakMapper keycloakMapper;
    private final KeycloakService keycloakService;
    private final CoachInfoRepository coachInfoRepository;


    public UserService(UserMapper userMapper, UserRepository userRepository, KeycloakMapper keycloakMapper,
                       KeycloakService keycloakService, CoachInfoRepository coachInfoRepository, CoachInfoTopicRepository coachInfoTopicRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.keycloakMapper = keycloakMapper;
        this.keycloakService = keycloakService;
        this.coachInfoRepository = coachInfoRepository;
    }

    public UserDto createUser(CreateUserDto createUser) {

        CreateUserDto validUser = assertUserIsValid(createUser);
        String keycloakId = addPersonToKeycloak(validUser);
        User user = userMapper.map(validUser, UUID.fromString(keycloakId));
        if (isUserEmailTaken(createUser.email())){
            throw new UserAlreadyExistsException("Email is already in use");
        }
        userRepository.save(user);

        return userMapper.map(userRepository.getById(user.getId()));
    }

    private CreateUserDto assertUserIsValid(CreateUserDto createUser) {
        if (createUser.firstName() == null || createUser.lastName() == null || createUser.email() == null || createUser.password() == null || createUser.team() == null) {
            throw new IllegalArgumentException("Please provide input for all fields");
        }
        return createUser;
    }

    private String addPersonToKeycloak(CreateUserDto createUser) {
        KeycloakUserDto keycloakUserDTO = keycloakMapper.map(createUser, Role.COACHEE);
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

    public List<UserDto> getByIsCoach(boolean isCoach) {
        List<User> coaches = userRepository.getByIsCoach(isCoach);
        return coaches.stream().map(userMapper::map).toList();
    }

    public User getUserById(UUID id) {
        return userRepository.getById(id);
    }

    public UserDto getUserDtoById(UUID id) {
        return userMapper.map(getUserById(id));
    }

    public boolean isUserEmailTaken(String userEmail) {
        return userRepository.findByEmail(userEmail) != null;
    }
}
