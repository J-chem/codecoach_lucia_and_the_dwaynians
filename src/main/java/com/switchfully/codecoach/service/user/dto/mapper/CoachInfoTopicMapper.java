package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.CoachInfoTopic;
import com.switchfully.codecoach.service.user.dto.CoachInfoTopicDto;
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
