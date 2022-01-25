package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.user.CoachInfoTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CoachInfoTopicRepository extends JpaRepository<CoachInfoTopic, UUID> {

}
