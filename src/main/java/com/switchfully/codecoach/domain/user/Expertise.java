package com.switchfully.codecoach.domain.user;

public enum Expertise {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    private final int number;

    Expertise(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
