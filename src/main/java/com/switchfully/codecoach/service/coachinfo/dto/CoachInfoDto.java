package com.switchfully.codecoach.service.coachinfo.dto;

import com.switchfully.codecoach.service.coachinfotopic.dto.CoachInfoTopicDto;

import java.util.List;
import java.util.UUID;

public record CoachInfoDto(UUID coachInfoId, String introduction, String availability, List<CoachInfoTopicDto> coachInfoTopicDtoList) {

}
