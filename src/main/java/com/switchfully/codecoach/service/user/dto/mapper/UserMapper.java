package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(CreateUserDto createUserDto){
        return new User(
                createUserDto.firstName(),
                createUserDto.lastName(),
                createUserDto.email(),
                createUserDto.team()
        );
    }

    public UserDto map(User user, String keycloakId){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTeam(),
                user.isCoach(),
                keycloakId
        );
    }
}
