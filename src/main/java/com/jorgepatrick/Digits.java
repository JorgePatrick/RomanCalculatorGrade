package com.jorgepatrick;

public enum Digits {
    UNIT(0),
    TEN(1),
    HUNDRED(2),
    THOUSAND(3);

    private final int value;

    Digits(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}
