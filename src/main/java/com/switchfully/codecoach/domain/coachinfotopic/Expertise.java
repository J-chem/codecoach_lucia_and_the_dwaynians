package com.switchfully.codecoach.domain.coachinfotopic;

public enum Expertise {
    JUNIOR(1),
    MEDIOR(2),
    SENIOR(3),
    EXPERT(4);

    private final int id;

    Expertise(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
