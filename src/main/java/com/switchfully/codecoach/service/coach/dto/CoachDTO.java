package com.switchfully.codecoach.service.coach.dto;

import java.util.List;
import java.util.UUID;

public class CoachDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<TopicDTO> topics;

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

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public CoachDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CoachDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CoachDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CoachDTO setTopics(List<TopicDTO> topics) {
        this.topics = topics;
        return this;
    }
}
