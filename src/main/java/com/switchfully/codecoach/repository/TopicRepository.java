package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
