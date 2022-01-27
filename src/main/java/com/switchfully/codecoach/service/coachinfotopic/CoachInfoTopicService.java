package com.switchfully.codecoach.service.coachinfotopic;

import com.switchfully.codecoach.repository.CoachInfoTopicRepository;
import com.switchfully.codecoach.service.coachinfotopic.dto.CoachInfoTopicDto;
import com.switchfully.codecoach.service.coachinfotopic.mapper.CoachInfoTopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachInfoTopicService {

    private final CoachInfoTopicRepository coachInfoTopicRepository;


    public CoachInfoTopicService(CoachInfoTopicRepository coachInfoTopicRepository) {
        this.coachInfoTopicRepository = coachInfoTopicRepository;
   }


    public List<CoachInfoTopicDto> findAll() {
        return null;
    }
}
