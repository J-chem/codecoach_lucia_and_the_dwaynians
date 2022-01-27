package com.switchfully.codecoach.api;


import com.switchfully.codecoach.service.topic.TopicService;
import com.switchfully.codecoach.service.topic.dto.TempTopicDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    public List<TempTopicDto> getAllTopicNames(){

        return topicService.getAllTopicNames();
    }
}
