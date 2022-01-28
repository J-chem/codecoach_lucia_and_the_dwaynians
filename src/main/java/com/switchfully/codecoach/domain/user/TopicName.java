package com.switchfully.codecoach.domain.user;

public enum TopicName {
    ANGULAR("ANGULAR"),
    MATHEMATICS("MATHEMATICS"),
    JAVA("JAVA"),
    BIOLOGY("BIOLOGY"),
    SPRING("SPRING");

    private String name;

    TopicName(String name) {
        this.name = name;
    }
}
