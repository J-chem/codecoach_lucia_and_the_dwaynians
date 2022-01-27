package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.coachinfotopic.CoachInfoTopicService;
import com.switchfully.codecoach.service.coachinfotopic.dto.CoachInfoTopicDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/levels", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class LevelController {

    private final CoachInfoTopicService coachInfoTopicService;

    public LevelController(CoachInfoTopicService coachInfoTopicService) {
        this.coachInfoTopicService = coachInfoTopicService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    public List<CoachInfoTopicDto> findAll() {
        return coachInfoTopicService.findAll();
    }
}
