package com.switchfully.codecoach.service;

import com.switchfully.codecoach.repository.TopicRepository;
import com.switchfully.codecoach.service.user.dto.TopicDto;
import com.switchfully.codecoach.service.user.dto.mapper.TopicMapper;
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

    public List<TopicDto> getAll() {
        return topicMapper.map(topicRepository.findAll());
    }

}
