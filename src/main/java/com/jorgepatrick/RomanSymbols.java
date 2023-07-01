package com.jorgepatrick;

public enum RomanSymbols {
    I("I"),
    V("V"),
    X("X"),
    L("L"),
    C("C"),
    D("D"),
    M("M");

    private final String romanValue;

    RomanSymbols(String romanValue) {
        this.romanValue = romanValue;
    }
    public String value() {
        return this.romanValue;
    }
}
