package com.jorgepatrick;

import java.util.Arrays;
import static com.jorgepatrick.RomanSymbols.*;
import static com.jorgepatrick.ComparisonResult.*;

public class RomanNumber {
    private String romanNumberStr;
    private final RomanNumberValidator romanNumberValidator;

    public RomanNumber(RomanNumberValidator romanNumberValidator) {
        this.romanNumberValidator = romanNumberValidator;
    }

    public void setRomanNumberStr(String romanNumberStr) {
        if (romanNumberStr != null) {
            this.romanNumberStr = romanNumberStr.toUpperCase();
        }
    }

    public void validate() {
        romanNumberValidator.validateRomanNumber(this);
    }

    public boolean isDigitOnes(int currentDigit) {
        final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
        return Arrays.stream(RomanOnes).toList().contains(digitAt(currentDigit));
    }

    public int arabicNumber() {
        int arabicNumber = 0;

        for (int currentDigit = 0; currentDigit < length(); currentDigit++) {
            if (isDigitOnes(currentDigit)) {
                arabicNumber += getValueOnes(currentDigit);
            } else {
                arabicNumber += getValueFives(currentDigit);
            }
        }
        return arabicNumber;
    }

    public int getValueOnes(int currentDigit) {
        int value = 0;
        int nextDigit = currentDigit + 1;

        if (isThereCharInPosition(nextDigit) &&
            isFirstDigit(digitAt(currentDigit), digitAt(nextDigit)).equals(LESS)) {
            value = -1;
        } else {
            value = 1;
        }
        return value * getArabicValueOfRomanSymbol(digitAt(currentDigit));
    }

    public int getValueFives(int currentDigit) {
        return getArabicValueOfRomanSymbol(digitAt(currentDigit));
    }

    private static int getArabicValueOfRomanSymbol(String romanDigit) {
        return RomanSymbols.valueOf(romanDigit).arabicValue();
    }

    public String digitAt(int position) {
        return romanNumberStr.substring(position, position + 1);
    }

    public int length() {
        return romanNumberStr.length();
    }

    public boolean isNull() {
        return romanNumberStr == null;
    }

    public boolean isThereCharInPosition(int position) {
        return (position < length());
    }

    public boolean isEmpty() {
        return romanNumberStr.isEmpty();
    }

    public ComparisonResult isFirstDigit (String firstDigit, String secondDigit) {
        int arabicFirstDigit = getArabicValueOfRomanSymbol(firstDigit);
        int arabicSecondDigit = getArabicValueOfRomanSymbol(secondDigit);
        if (arabicFirstDigit < arabicSecondDigit) {
            return LESS;
        }

        if (arabicFirstDigit == arabicSecondDigit) {
            return EQUAL;
        }

        return GREATER;
    }
}
