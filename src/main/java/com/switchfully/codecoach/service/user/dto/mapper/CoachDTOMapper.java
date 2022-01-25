package com.switchfully.codecoach.service.user.dto.mapper;

import com.switchfully.codecoach.domain.user.CoachInfo;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.service.coach.dto.CoachDTO;
import com.switchfully.codecoach.service.coach.dto.TopicDTO;

import java.util.ArrayList;
import java.util.List;

public class CoachDTOMapper {
    public static List<CoachDTO> map(List<User> coaches, List<CoachInfo> coachInfos) {
        var result = new ArrayList<CoachDTO>();
        for (var coach : coaches) {
            CoachDTO newCoach = new CoachDTO();
            newCoach.setId(coach.getId())
                    .setEmail(coach.getEmail())
                    .setFirstName(coach.getFirstName())
                    .setLastName(coach.getLastName());

            var topicList = new ArrayList<TopicDTO>();
            for (var coachInfo : coachInfos) {
                if (coach.getCoachInfo().getId().toString().equals(coachInfo.getId().toString())) {
                    for(var topic : coachInfo.getCoachInfoTopics()){
                        topicList.add(new TopicDTO(topic.getTopic().getTopicName().toString(),
                                topic.getExpertise().toString()));
                    }
                }
            }
            newCoach.setTopics(topicList);
            result.add(newCoach);
        }
        return result;
    }
}
