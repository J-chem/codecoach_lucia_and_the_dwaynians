package com.switchfully.codecoach.service.user.dto;

import java.util.List;
import java.util.UUID;

public record CoachInfoDto(UUID coachInfoId, String introduction, String availability, List<CoachInfoTopicDto> coachInfoTopicDtoList) {

}
