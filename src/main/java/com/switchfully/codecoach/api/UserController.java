package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.user.CoachInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import com.switchfully.codecoach.service.user.UserService;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON)
@CrossOrigin
public class UserController {
    private final CoachInfoService coachInfoService;
    private final UserService userService;


    @Autowired
    public UserController(CoachInfoService coachInfoService, UserService userService) {
        this.coachInfoService = coachInfoService;
        this.userService = userService;
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('BECOME_A_COACH')")
    @ResponseStatus(HttpStatus.OK)
    public void becomeACoach(@PathVariable("id") UUID userId) {
        coachInfoService.becomeACoach(userId);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        UserDto createdUser = userService.createUser(createUserDto);
        return createdUser;

    }
}
