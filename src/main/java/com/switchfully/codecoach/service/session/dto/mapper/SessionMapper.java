package com.switchfully.codecoach.service.session.dto.mapper;

import com.switchfully.codecoach.domain.session.Session;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public Session map(CreateSessionDto createSessionDto, User coach, User coachee) {
        return new Session(
                createSessionDto.date(),
                createSessionDto.time(),
                createSessionDto.location(),
                createSessionDto.remarks(),
                createSessionDto.status(),
                coach,
                coachee
        );

    }

    public SessionDto map(Session session) {
        return new SessionDto(
                session.getId(),
                session.getCoach().getFullName(),
                session.getDate(),
                session.getTime(),
                session.getLocation(),
                session.getStatus()
        );
    }
}
