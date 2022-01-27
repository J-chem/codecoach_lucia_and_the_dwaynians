package com.switchfully.codecoach.service.coach.mapper;

import com.switchfully.codecoach.domain.coachinfotopic.CoachInfoTopic;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.coach.dto.CoachDto;
import com.switchfully.codecoach.service.topic.dto.TopicDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoachMapper {
    public static List<CoachDto> map(List<User> coaches, List<CoachInfoTopic> coachInfoTopics) {
        var result = new ArrayList<CoachDto>();
        for (var coach : coaches) {
            CoachDto newCoach = new CoachDto();
            newCoach.setId(coach.getId())
                    .setEmail(coach.getEmail())
                    .setFirstName(coach.getFirstName())
                    .setLastName(coach.getLastName());

            var topicList = new ArrayList<TopicDto>();
            for (var coachInfoTopic : coachInfoTopics) {
                if (coach.getCoachInfo().getId().toString().equals(coachInfoTopic.getTopic().getTopicId().toString())) {
                    // TODO: NS + JC change this topicdto to a new one
                    topicList.add(new TopicDto(coachInfoTopic.getTopic().getTopicId(),
                            coachInfoTopic.getTopic().getTopicName()));
                }
            }
            newCoach.setTopics(topicList);
            result.add(newCoach);
        }
        return result;
    }
}
