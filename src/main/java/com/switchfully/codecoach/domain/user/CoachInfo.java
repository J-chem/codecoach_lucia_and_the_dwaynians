package com.switchfully.codecoach.domain.user;

import javax.persistence.*;
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

    public CoachInfo(String introduction, String availability) {
        this.introduction = introduction;
        this.availability = availability;
    }

    public CoachInfo() {

    }
}
