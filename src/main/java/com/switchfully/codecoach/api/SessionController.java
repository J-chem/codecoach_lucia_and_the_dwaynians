package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.session.SessionService;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDto requestSession(@RequestBody CreateSessionDto createSessionDto) {
        return sessionService.addSession(createSessionDto);
    }

}
