package com.switchfully.codecoach.domain.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "COACH_INFO_TOPIC")
public class CoachInfoTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COACH_INFO_TOPIC_ID")
    private UUID coachInfoTopicId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COACH_INFO_ID")
    private CoachInfo coachInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    @Column(name = "EXPERTISE")
    private Expertise expertise;

    public Topic getTopic() {
        return topic;
    }

    public Expertise getExpertise() {
        return expertise;
    }
}
