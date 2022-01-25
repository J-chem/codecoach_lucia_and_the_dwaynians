package com.switchfully.codecoach.domain.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @Column(name = "TOPIC_ID")
    private UUID topicId;

    @Enumerated
    @Column(name = "TOPIC_NAME")
    private TopicName topicName;

}
