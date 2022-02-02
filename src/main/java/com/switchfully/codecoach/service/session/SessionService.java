package com.switchfully.codecoach.service.session;

import com.switchfully.codecoach.domain.session.Session;
import com.switchfully.codecoach.domain.topic.Topic;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.SessionRepository;
import com.switchfully.codecoach.repository.TopicRepository;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import com.switchfully.codecoach.service.session.dto.mapper.SessionMapper;
import com.switchfully.codecoach.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TopicRepository topicRepository;
    private final SessionMapper sessionMapper;
    private final UserService userService;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          TopicRepository topicRepository, SessionMapper sessionMapper,
                          UserService userService) {
        this.sessionRepository = sessionRepository;
        this.topicRepository = topicRepository;
        this.sessionMapper = sessionMapper;
        this.userService = userService;
    }

    public SessionDto addSession(CreateSessionDto createSessionDto) {
        assertCreateSessionDto(createSessionDto);
        Session sessionToRegister = sessionMapper.map(createSessionDto,
                findTopicById(createSessionDto.topicId()),
                findUserById(createSessionDto.coachId()),
                findUserById(createSessionDto.coacheeId()));
        Session savedSession = sessionRepository.save(sessionToRegister);
        return sessionMapper.map(savedSession);
    }

    public List<SessionDto> getSessionsForCoach(UUID coachId) {
        return sessionRepository.findAll()
                .stream()
                .filter(session -> session.getCoach().getId().equals(coachId))
                .map(sessionMapper::map)
                .collect(Collectors.toList());
    }

    public List<SessionDto> getSessionsForCoachee(UUID coacheeId) {
        return sessionRepository.findAll()
                .stream()
                .filter(session -> session.getCoachee().getId().equals(coacheeId))
                .map(sessionMapper::map)
                .collect(Collectors.toList());
    }

    private User findUserById(UUID id) {
        return userService.getUserById(id);
    }

    private Topic findTopicById(UUID id) {
        return topicRepository.getById(id);
    }

    private void assertCreateSessionDto(CreateSessionDto createSessionDto) {
        assertNullFields(createSessionDto);
        assertDateAndTime(createSessionDto.date(), createSessionDto.time());
        assertCoachIsCoach(createSessionDto.coachId());
    }

    private void assertNullFields(CreateSessionDto createSessionDto) {
        if (createSessionDto.topicId() == null || createSessionDto.date() == null || createSessionDto.time() == null
                || createSessionDto.location() == null || createSessionDto.coachId() == null || createSessionDto.coacheeId() == null) {
            throw new IllegalArgumentException("Please provide input for all fields");
        }
    }

    private void assertDateAndTime(LocalDate date, LocalTime time) {
        LocalDateTime sessionDateTime = LocalDateTime.of(date, time);
        if (sessionDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A session cannot be scheduled in the past.");
        }
    }

    private void assertCoachIsCoach(UUID coachId) {
        User coach = userService.getUserById(coachId);
        if (!coach.isCoach()) {
            throw new IllegalArgumentException("The requested coach is not a coach in the system.");
        }
    }
}
