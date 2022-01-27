package com.switchfully.codecoach.service.coachinfotopic.mapper;

import com.switchfully.codecoach.domain.coachinfotopic.CoachInfoTopic;
import com.switchfully.codecoach.service.coachinfotopic.dto.CoachInfoTopicDto;
import com.switchfully.codecoach.service.topic.mapper.TopicMapper;
import org.springframework.stereotype.Component;

@Component
public class CoachInfoTopicMapper {

    private final TopicMapper topicMapper;

    public CoachInfoTopicMapper(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public CoachInfoTopicDto map(CoachInfoTopic coachInfoTopic) {
        return new CoachInfoTopicDto(
                coachInfoTopic.getCoachInfoTopicId(),
                topicMapper.map(coachInfoTopic.getTopic()),
                coachInfoTopic.getExpertise()
        );
    }
}
