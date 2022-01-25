package com.switchfully.codecoach.service.session;

import com.switchfully.codecoach.domain.session.Session;
import com.switchfully.codecoach.domain.user.Topic;
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
import java.util.UUID;

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
        Session sessionToRegister = sessionMapper.map(createSessionDto,
                findTopicById(createSessionDto.topicId()),
                findUserById(createSessionDto.coachId()),
                findUserById(createSessionDto.coacheeId()));
        Session savedSession = sessionRepository.save(sessionToRegister);
        return sessionMapper.map(savedSession);
    }

    private User findUserById(UUID id) {
        return userService.getUserById(id);
    }

    private Topic findTopicById(UUID id) {
        return topicRepository.getById(id);
    }
}
