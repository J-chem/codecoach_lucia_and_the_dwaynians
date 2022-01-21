package com.switchfully.codecoach.domain.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEAM")
    private String team;

    @Column(name = "IS_COACH")
    private Boolean isCoach;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COACH_INFO_FK")
    private CoachInfo coachInfo;


    public User(String firstName, String lastName, String email, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.team = team;
        this.isCoach = false;
        this.coachInfo = null;
    }

    public User() {

    }

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

    public String getTeam() {
        return team;
    }

    public Boolean isCoach() {
        return isCoach;
    }

    public CoachInfo getCoachInfo() {
        return coachInfo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setIsCoach(Boolean coach) {
        isCoach = coach;
    }

    public void setCoachInfo(CoachInfo coachInfo) {
        this.coachInfo = coachInfo;
    }
}
