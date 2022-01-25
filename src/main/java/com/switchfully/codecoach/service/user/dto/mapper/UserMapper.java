package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    private final CoachInfoMapper coachInfoMapper;

    public UserMapper(CoachInfoMapper coachInfoMapper) {
        this.coachInfoMapper = coachInfoMapper;
    }

    public User map(CreateUserDto createUserDto, UUID id) {
        return new User(
                id,
                createUserDto.firstName(),
                createUserDto.lastName(),
                createUserDto.email(),
                createUserDto.team()
        );
    }

    public UserDto map(User user) {
        if(user.getCoachInfo() == null){
            return new UserDto(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getTeam(),
                    user.isCoach(),
                    null
            );
        }
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTeam(),
                user.isCoach(),
                coachInfoMapper.map(user.getCoachInfo())
        );
    }
}
