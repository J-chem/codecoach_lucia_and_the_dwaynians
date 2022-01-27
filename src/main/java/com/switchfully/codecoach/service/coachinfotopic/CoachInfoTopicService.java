package com.switchfully.codecoach.service.coachinfotopic;

import com.switchfully.codecoach.domain.coachinfotopic.Expertise;
import com.switchfully.codecoach.service.coachinfotopic.dto.LevelDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachInfoTopicService {

    public List<LevelDto> findAllLevelNames() {
        return Arrays.stream(Expertise.values())
                .map(expertise -> new LevelDto(expertise.getId(), expertise.name()))
                .collect(Collectors.toList());
    }
}
