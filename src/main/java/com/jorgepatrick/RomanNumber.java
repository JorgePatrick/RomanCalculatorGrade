package com.jorgepatrick;

import java.util.Arrays;
import java.util.List;

import static com.jorgepatrick.Digits.*;
import static com.jorgepatrick.RomanSymbols.*;
import static com.jorgepatrick.RomanSymbols.M;
import static com.jorgepatrick.Utilities.enumValuesInList;

public class RomanNumber {
    private final String romanNumberStr;
    static final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
    static final Digits[] RomanDigits = {UNIT, TEN, HUNDRED, THOUSAND};

    private final RomanNumberValidator romanNumberValidator = new RomanNumberValidator();

    public RomanNumber(String romanNumberStr) {
        this.romanNumberStr = romanNumberStr;
    }

    public void validate() {
        romanNumberValidator.validateRomanNumber(romanNumberStr);
    }

    public boolean isDigitOnes(int currentDigit) {
        return Arrays.stream(RomanOnes).toList().contains(this.strAt(currentDigit));
    }

    public int getOnes(int currentDigit) {
        int sum = 0;
        int nextDigit = currentDigit + 1;
        Digits digit = RomanDigits[Arrays.stream(RomanOnes).toList().indexOf(this.strAt(currentDigit))];

        if (nextDigit == length() || isNextDigitEqualOrLess(currentDigit)) {
            sum = 1;
        } else {
            sum = -1;
        }
        return sum * (RomanDigits[digit.ordinal()]).arabicValue();
    }

    private boolean isNextDigitEqualOrLess(int currentDigit) {
        int nextDigit = currentDigit + 1;
        int currentArabicDigit = getArabicValue(strAt(currentDigit));
        int nextArabicDigit = getArabicValue(strAt(nextDigit));
        return nextArabicDigit <= currentArabicDigit;
    }

    private int getArabicValue (String romanDigit) {
        int arabicValue = 0;
        List<RomanSymbols> romanSymbols = enumValuesInList(RomanSymbols.class);
        for (RomanSymbols romanSymbol : romanSymbols) {
            if (romanSymbol.name().equals(romanDigit)) {
                arabicValue = romanSymbol.arabicValue();
                break;
            }
        }

        return arabicValue;
    }

    private String strAt(int currentDigit) {
        return romanNumberStr.substring(currentDigit, currentDigit + 1);
    }

    public int length() {
        return romanNumberStr.length();
    }
}
