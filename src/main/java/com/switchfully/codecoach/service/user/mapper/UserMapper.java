package com.switchfully.codecoach.service.user.mapper;

import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.coachinfo.mapper.CoachInfoMapper;
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

    public User map(CreateUserDto createUser, UUID id) {
        return new User(
                id,
                createUser.firstName(),
                createUser.lastName(),
                createUser.email(),
                createUser.team()
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
