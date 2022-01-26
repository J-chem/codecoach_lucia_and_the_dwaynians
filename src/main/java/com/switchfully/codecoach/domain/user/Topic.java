package com.switchfully.codecoach.domain.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @Column(name = "TOPIC_ID")
    private UUID topicId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TOPIC_NAME")
    private TopicName topicName;

    public Topic(TopicName topicName) {
        this.topicId = UUID.randomUUID();
        this.topicName = topicName;
    }

    public Topic() {

    }

    public String getTopicName() {
        return topicName.toString();
    }

    public UUID getTopicId() {
        return topicId;
    }
}
