package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.session.SessionService;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    public SessionDto requestSession(@RequestBody CreateSessionDto createSessionDto) {
        return sessionService.addSession(createSessionDto);
    }

    @GetMapping(params = "coach")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ACCESS_COACH_SESSIONS')")
    public List<SessionDto> getCoachSessions(@RequestParam UUID coach) {
        return sessionService.getSessionsForCoach(coach);
    }

    @GetMapping(params = "coachee")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ACCESS_COACHEE_SESSIONS')")
    public List<SessionDto> getCoacheeSessions(@RequestParam UUID coachee) {
        return sessionService.getSessionsForCoachee(coachee);
    }

}
