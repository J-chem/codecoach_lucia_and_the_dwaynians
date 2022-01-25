package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.Topic;
import com.switchfully.codecoach.service.user.dto.TopicDto;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public TopicDto map(Topic topic) {
        return new TopicDto(topic.getTopicId(), topic.getTopicName());
    }
}
