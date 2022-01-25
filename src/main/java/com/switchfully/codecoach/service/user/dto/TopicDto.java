package com.switchfully.codecoach.service.user.dto;

import com.switchfully.codecoach.domain.user.TopicName;

import java.util.UUID;

public record TopicDto(UUID topicId, TopicName topicName) {
}
