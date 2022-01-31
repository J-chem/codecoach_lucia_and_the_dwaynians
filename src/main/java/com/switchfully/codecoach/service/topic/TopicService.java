package com.switchfully.codecoach.service.topic;

import com.switchfully.codecoach.repository.TopicRepository;
import com.switchfully.codecoach.service.topic.mapper.TopicMapper;
import com.switchfully.codecoach.service.topic.mapper.dto.TopicDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    public List<TopicDto> getAllTopicNames() {
        return topicMapper.map(topicRepository.findAll());
    }
}
