package com.switchfully.codecoach.service.coachinfo.mapper;

import com.switchfully.codecoach.domain.coachinfo.CoachInfo;
import com.switchfully.codecoach.service.coachinfotopic.mapper.CoachInfoTopicMapper;
import com.switchfully.codecoach.service.coachinfo.dto.CoachInfoDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CoachInfoMapper {

    private final CoachInfoTopicMapper coachInfoTopicMapper;

    public CoachInfoMapper(CoachInfoTopicMapper coachInfoTopicMapper) {
        this.coachInfoTopicMapper = coachInfoTopicMapper;
    }

    public CoachInfoDto map(CoachInfo coachInfo) {
        return new CoachInfoDto(coachInfo.getId(), coachInfo.getIntroduction(), coachInfo.getAvailability(), coachInfo.getCoachInfoTopics().stream()
                .map(coachInfoTopic -> coachInfoTopicMapper.map(coachInfoTopic)).collect(Collectors.toList()));
    }
}
