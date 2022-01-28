package com.switchfully.codecoach.service.topic;

import com.switchfully.codecoach.domain.topic.TopicName;
import com.switchfully.codecoach.service.topic.dto.TempTopicDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    public List<TempTopicDto> getAllTopicNames() {
        return Arrays.stream(TopicName.values())
                .map(topic -> new TempTopicDto(topic.getId(), topic.name()))
                .collect(Collectors.toList());
    }

}
