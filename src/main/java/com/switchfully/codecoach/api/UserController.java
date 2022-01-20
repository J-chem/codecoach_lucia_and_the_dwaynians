package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.user.CoachInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final CoachInfoService coachInfoService;

    @Autowired
    public UserController(CoachInfoService coachInfoService) {
        this.coachInfoService = coachInfoService;
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('BECOME_A_COACH')")
    @ResponseStatus(HttpStatus.OK)
    public void becomeACoach(@PathVariable("id") UUID userId) {
        coachInfoService.becomeACoach(userId);
    }

}
