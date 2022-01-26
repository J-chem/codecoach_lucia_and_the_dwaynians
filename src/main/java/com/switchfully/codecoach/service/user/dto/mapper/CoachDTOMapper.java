package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.CoachInfoTopic;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.coach.dto.CoachDTO;
import com.switchfully.codecoach.service.coach.dto.TopicDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoachDTOMapper {
    public static List<CoachDTO> map(List<User> coaches, List<CoachInfoTopic> coachInfoTopics) {
        var result = new ArrayList<CoachDTO>();
        for (var coach : coaches) {
            CoachDTO newCoach = new CoachDTO();
            newCoach.setId(coach.getId())
                    .setEmail(coach.getEmail())
                    .setFirstName(coach.getFirstName())
                    .setLastName(coach.getLastName());

            var topicList = new ArrayList<TopicDTO>();
            for (var coachInfoTopic : coachInfoTopics) {
                if (coach.getCoachInfo().getId().toString().equals(coachInfoTopic.getTopic().getTopicId().toString())) {
                    topicList.add(new TopicDTO(coachInfoTopic.getTopic().getTopicName().toString(),
                            coachInfoTopic.getExpertise().toString()));
                }
            }
            newCoach.setTopics(topicList);
            result.add(newCoach);
        }
        return result;
    }
}
