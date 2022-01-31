package com.switchfully.codecoach.service.topic.mapper.dto;

import com.switchfully.codecoach.domain.topic.TopicName;

import java.util.UUID;

public record TopicDto(UUID topicId, TopicName topicName) {
}
