package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.coach.dto.CoachDto;
import com.switchfully.codecoach.service.user.UserService;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ACCESS_MY_COACH_PROFILE')")
    @ResponseStatus(HttpStatus.OK)
    public UserDto accessMyCoachProfile(@PathVariable("id") UUID uuid) {
        UserDto userDto = userService.getUserDtoById(uuid);
        return userDto;
    }

    @PostMapping(path = "/{id}/become-a-coach")
    @PreAuthorize("hasAuthority('BECOME_A_COACH')")
    @ResponseStatus(HttpStatus.CREATED)
    public void becomeACoach(@PathVariable("id") UUID uuid) {
        System.out.println(uuid);
        userService.becomeACoach(uuid);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        UserDto createdUser = userService.createUser(createUserDto);
        return createdUser;
    }

    @GetMapping(params = "coach")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    public List<UserDto> getByIsCoach(@RequestParam boolean coach){
        List<UserDto> coaches = userService.getByIsCoach(coach);
        return coaches;
    }
}
