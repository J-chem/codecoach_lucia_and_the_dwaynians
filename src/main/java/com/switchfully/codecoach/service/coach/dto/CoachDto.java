package com.switchfully.codecoach.service.coach.dto;

import com.switchfully.codecoach.service.topic.dto.TopicDto;

import java.util.List;
import java.util.UUID;

public class CoachDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<TopicDto> topics;

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<TopicDto> getTopics() {
        return topics;
    }

    public CoachDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CoachDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CoachDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CoachDto setTopics(List<TopicDto> topics) {
        this.topics = topics;
        return this;
    }
}
