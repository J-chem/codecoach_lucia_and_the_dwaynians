package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.Topic;
import com.switchfully.codecoach.service.coach.dto.TopicDTO;
import com.switchfully.codecoach.service.user.dto.TopicDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMapper {
    public TopicDto map(Topic topic) {
        return new TopicDto(topic.getTopicId(), topic.getTopicName());
    }
    public List<TopicDto> map(List<Topic> topics) {
        return topics.stream().map(topic -> new TopicDto(topic.getTopicId(), topic.getTopicName())).collect(Collectors.toList());
    }
}
