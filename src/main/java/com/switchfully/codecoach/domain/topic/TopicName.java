package com.switchfully.codecoach.domain.topic;

public enum TopicName {
    ANGULAR(1),
    JAVA(2),
    TYPESCRIPT(3),
    SPRING(4);

    private final int id;

    TopicName(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
