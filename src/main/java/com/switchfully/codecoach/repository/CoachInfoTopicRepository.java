package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.coachinfotopic.CoachInfoTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CoachInfoTopicRepository extends JpaRepository<CoachInfoTopic, UUID> {

}
