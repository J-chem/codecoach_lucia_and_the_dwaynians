package com.switchfully.codecoach.service.user.dto;

import java.util.UUID;

public record UserDto(UUID id, String firstName, String lastName, String email, String team, boolean isCoach, String keycloakId) {

}
