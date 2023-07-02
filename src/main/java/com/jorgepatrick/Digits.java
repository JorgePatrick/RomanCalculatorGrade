package com.jorgepatrick;

public enum Digits {
    UNIT(0, 1),
    TEN(1, 10),
    HUNDRED(2, 100),
    THOUSAND(3, 1000);

    private final int position;
    private final int arabicValue;

    Digits(int position, int arabicValue) {
        this.position = position;
        this.arabicValue = arabicValue;
    }
    public int position() {
        return this.position;
    }
    public int arabicValue() { return this.arabicValue; }
}
