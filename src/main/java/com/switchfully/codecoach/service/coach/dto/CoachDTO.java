package com.switchfully.codecoach.service.coach.dto;

import java.util.List;
import java.util.UUID;

public record CoachDTO (UUID id, String firstName, String lastName, String email, List<TopicDTO> topics){

}
