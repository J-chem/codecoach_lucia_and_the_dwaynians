package com.switchfully.codecoach.api;


import com.switchfully.codecoach.service.TopicService;
import com.switchfully.codecoach.service.coach.dto.CoachDTO;
import com.switchfully.codecoach.service.coach.dto.TopicDTO;
import com.switchfully.codecoach.service.user.dto.TopicDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    public List<TopicDto> getAllTopics(){
        List<TopicDto> topics = topicService.getAll();
        return topics;
    }
}
