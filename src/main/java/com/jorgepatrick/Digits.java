package com.jorgepatrick;

public enum Digits {
    UNIT(1),
    TEN(10),
    HUNDRED(100),
    THOUSAND(1000);

    private final int arabicValue;
    Digits(int arabicValue) {
        this.arabicValue = arabicValue;
    }
    public int arabicValue() { return this.arabicValue; }
}
