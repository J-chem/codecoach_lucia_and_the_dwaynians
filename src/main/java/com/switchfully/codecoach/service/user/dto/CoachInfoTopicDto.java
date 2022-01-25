package com.switchfully.codecoach.service.user.dto;

import com.switchfully.codecoach.domain.user.Expertise;

import java.util.UUID;

public record CoachInfoTopicDto(UUID coachInfoTopicId, TopicDto topicDto, Expertise expertise) {

}
