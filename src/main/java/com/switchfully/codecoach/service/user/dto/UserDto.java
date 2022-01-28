package com.switchfully.codecoach.service.user.dto;

import com.switchfully.codecoach.service.coachinfo.dto.CoachInfoDto;

import java.util.UUID;

public record UserDto(UUID id, String firstName, String lastName, String email, String team, boolean isCoach, CoachInfoDto coachInfo) {

}
