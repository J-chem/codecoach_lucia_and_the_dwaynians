package com.switchfully.codecoach.domain.session;

import com.switchfully.codecoach.domain.user.Topic;
import com.switchfully.codecoach.domain.user.User;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "SESSION")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SESSION_ID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "FK_TOPIC_ID")
    private Topic topic;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TIME")
    private LocalTime time;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "LOCATION")
    private Location location;

    @Column(name = "REMARKS")
    private String remarks;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "FK_COACH_ID")
    private User coach;

    @ManyToOne
    @JoinColumn(name = "FK_COACHEE_ID")
    private User coachee;

    public Session(Topic topic, LocalDate date, LocalTime time, Location location, String remarks, Status status, User coach, User coachee) {
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.location = location;
        this.remarks = remarks;
        this.status = status;
        this.coach = coach;
        this.coachee = coachee;
    }

    // Hibernate required
    public Session() {
    }

    public UUID getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    public Status getStatus() {
        return status;
    }

    public User getCoach() {
        return coach;
    }

    public User getCoachee() {
        return coachee;
    }

}
