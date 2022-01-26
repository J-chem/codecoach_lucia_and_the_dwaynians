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

    protected CoachInfoTopic() {
    }

    public CoachInfoTopic(CoachInfo coachInfoId, Topic topic, Expertise expertise) {
        this.coachInfo = coachInfoId;
        this.topic = topic;
        this.expertise = expertise;
    }

    public UUID getCoachInfoTopicId() {
        return coachInfoTopicId;
    }



    public CoachInfo getCoachInfo() {
        return coachInfo;
    }

    public Topic getTopic() {
        return topic;
    }

    public Expertise getExpertise() {
        return expertise;
    }

    public void setCoachInfoTopicId(UUID coachInfoTopicId) {
        this.coachInfoTopicId = coachInfoTopicId;
    }

    public void setCoachInfoId(CoachInfo coachInfoId) {
        this.coachInfo = coachInfoId;
    }

    public void setTopicId(Topic topicId) {
        this.topic = topicId;
    }

    public void setExpertise(Expertise expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "CoachInfoTopic{" +
                "coachInfoTopicId=" + coachInfoTopicId +
                ", coachInfoId=" + coachInfo +
                ", topicId=" + topic +
                ", expertise=" + expertise +
                '}';
    }
}
