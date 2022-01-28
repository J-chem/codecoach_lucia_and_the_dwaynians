package com.switchfully.codecoach.domain.coachinfo;

import com.switchfully.codecoach.domain.coachinfotopic.CoachInfoTopic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "COACH_INFO")
public class CoachInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COACH_INFO_ID")
    private UUID id;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "AVAILABILITY")
    private String availability;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "COACH_INFO_ID")
    private List<CoachInfoTopic> coachInfoTopics = new ArrayList<>();

//     method get topics -> convert Coachinfotopics via stream, use dto

    public CoachInfo(String introduction, String availability) {
        this.introduction = introduction;
        this.availability = availability;
    }

    public CoachInfo() {

    }

    public UUID getId() {
        return id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getAvailability() {
        return availability;
    }

    public List<CoachInfoTopic> getCoachInfoTopics() {
        return coachInfoTopics;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setCoachInfoTopics(List<CoachInfoTopic> coachInfoTopics) {
        this.coachInfoTopics = coachInfoTopics;
    }
}
