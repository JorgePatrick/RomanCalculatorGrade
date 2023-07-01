package com.jorgepatrick;

import static com.jorgepatrick.Digits.*;
import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {

    static final RomanSymbols[] RomanOnes = {I, X, C, M};
    static final RomanSymbols[] RomanTens = {X, C, M};
    static final RomanSymbols[] RomanFives = {V, L, D};
    private final RomanNumberValidator romanNumberValidator;

    public NumberConverter(RomanNumberValidator romanNumberValidator) {
        this.romanNumberValidator = romanNumberValidator;
    }

    public String parseArabicToRoman(final int arabicNumberSum) {
        String romanNumber = "";

        int[] arabicDigits = new int[2];
        String arabicNumberString = String.valueOf(arabicNumberSum);
        for(int i = 0; i < arabicNumberString.length(); i++) {
            arabicDigits[i] = Character.digit(arabicNumberString.charAt(i), 10);
        }
        if (arabicNumberString.length() == 2){
            romanNumber += setRomanDigit(arabicDigits[0], TEN);
            romanNumber += setRomanDigit(arabicDigits[1], UNIT);
        } else {
            romanNumber += setRomanDigit(arabicDigits[0], UNIT);
        }

        return romanNumber;
    }

    private String setRomanDigit(int arabicDigit, Digits digit) {

        String romanDigit = "";
        int onesQuantity = 0;

        if (arabicDigit == 0) {
            return "";
        }

        if (arabicDigit == 9) {
            return RomanOnes[digit.value()].value() + RomanTens[digit.value()].value();
        }

        if (arabicDigit == 5) {
            return RomanFives[digit.value()].value();
        }

        if (arabicDigit == 4) {
            return RomanOnes[digit.value()].value() + RomanFives[digit.value()].value();
        }

        if (arabicDigit > 5) {
            romanDigit = RomanFives[digit.value()].value();
            onesQuantity = arabicDigit - 5;

        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[digit.value()].value();
        }

        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumber) {

        romanNumberValidator.validateRomanNumber(romanNumber);

        int unit = 0;

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            if (romanNumber.toUpperCase().charAt(currentDigit) == I.value().charAt(0)) {
                int nextDigit = currentDigit + 1;
                if (nextDigit == romanNumber.length() ||
                    romanNumber.toUpperCase().charAt(nextDigit) == I.value().charAt(0)) {
                    unit += 1;
                } else {
                    unit -= 1;
                }
            }

            if (romanNumber.toUpperCase().charAt(currentDigit) == V.value().charAt(0)) {
                unit += 5;
            }
        }

        return unit;
    }

}
