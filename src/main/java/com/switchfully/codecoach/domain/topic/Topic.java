package com.switchfully.codecoach.domain.topic;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @Column(name = "TOPIC_ID")
    private UUID topicId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TOPIC_NAME")
    private TopicName topicName;

    public UUID getTopicId() {
        return topicId;
    }

    public TopicName getTopicName() {
        return topicName;
    }
}
