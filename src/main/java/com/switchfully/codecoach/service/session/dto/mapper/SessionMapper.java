package com.switchfully.codecoach.service.session.dto.mapper;

import com.switchfully.codecoach.domain.session.Session;
import com.switchfully.codecoach.domain.topic.Topic;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public Session map(CreateSessionDto createSessionDto, Topic topic, User coach, User coachee) {
        return new Session(
                topic,
                createSessionDto.date(),
                createSessionDto.time(),
                createSessionDto.location(),
                createSessionDto.remarks(),
                coach,
                coachee
        );

    }

    public SessionDto map(Session session) {
        return new SessionDto(
                session.getId(),
                session.getCoach().getFullName(),
                session.getTopic().getTopicName(),
                session.getDate(),
                session.getTime(),
                session.getLocation(),
                session.getStatus()
        );
    }
}
