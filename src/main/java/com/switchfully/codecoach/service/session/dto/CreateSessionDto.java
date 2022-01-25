package com.switchfully.codecoach.service.session.dto;

import com.switchfully.codecoach.domain.session.Location;
import com.switchfully.codecoach.domain.session.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateSessionDto(LocalDate date, LocalTime time, Location location, String remarks, Status status,
                               UUID coachId, UUID coacheeId) {

}
