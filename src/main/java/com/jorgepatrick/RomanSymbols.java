package com.jorgepatrick;

public enum RomanSymbols {
    I("I"),
    V("V"),
    X("X"),
    L("L"),
    C("C"),
    D("D"),
    M("M");

    private final String value;

    RomanSymbols(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
