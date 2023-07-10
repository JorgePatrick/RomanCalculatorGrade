package com.jorgepatrick;

import java.util.Arrays;
import static com.jorgepatrick.RomanSymbols.*;

public class RomanNumber {
    private String romanNumberStr;
    static final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
    private final RomanNumberValidator romanNumberValidator;

    public RomanNumber(RomanNumberValidator romanNumberValidator) {
        this.romanNumberValidator = romanNumberValidator;
    }

    public void setRomanNumberStr(String romanNumberStr) {
        if (romanNumberStr == null) {
            this.romanNumberStr = romanNumberStr;
        } else {
            this.romanNumberStr = romanNumberStr.toUpperCase();
        }
    }

    public void validate() {
        romanNumberValidator.validateRomanNumber(this);
    }

    public boolean isDigitOnes(int currentDigit) {
        return Arrays.stream(RomanOnes).toList().contains(digitAt(currentDigit));
    }

    public int arabicNumber() {
        int arabicNumber = 0;

        for (int currentDigit = 0; currentDigit < length(); currentDigit++) {
            if (isDigitOnes(currentDigit)) {
                arabicNumber += getOnes(currentDigit);
            } else {
                arabicNumber += 5;
            }
        }
        return arabicNumber;
    }

    public int getOnes(int currentDigit) {
        int sum = 0;
        int nextDigit = currentDigit + 1;

        if (!isThereCharInPosition(nextDigit) || isNextDigitEqualOrLess(currentDigit)) {
            sum = 1;
        } else {
            sum = -1;
        }
        return sum * RomanSymbols.valueOf(digitAt(currentDigit)).arabicValue();
    }

    private boolean isNextDigitEqualOrLess(int currentDigit) {
        int nextDigit = currentDigit + 1;
        int currentArabicDigit = getArabicValue(digitAt(currentDigit));
        int nextArabicDigit = getArabicValue(digitAt(nextDigit));
        return nextArabicDigit <= currentArabicDigit;
    }

    private int getArabicValue (String romanDigit) {
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
}
