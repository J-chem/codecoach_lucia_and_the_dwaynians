package com.switchfully.codecoach.service.coachinfotopic.dto;

import com.switchfully.codecoach.domain.coachinfotopic.Expertise;
import com.switchfully.codecoach.service.topic.mapper.dto.TopicDto;

import java.util.UUID;

public record CoachInfoTopicDto(UUID coachInfoTopicId, TopicDto topic, Expertise expertise) {

}
