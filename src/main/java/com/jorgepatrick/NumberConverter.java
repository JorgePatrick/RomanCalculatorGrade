package com.jorgepatrick;

import static com.jorgepatrick.Digits.*;
import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {

    static final String[] RomanOnes = {I, X, C, M};
    static final String[] RomanTens = {X, C, M};
    static final String[] RomanFives = {V, L, D};


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
            return RomanOnes[digit.getValue()] + RomanTens[digit.getValue()];
        }

        if (arabicDigit == 5) {
            return RomanFives[digit.getValue()];
        }

        if (arabicDigit == 4) {
            return RomanOnes[digit.getValue()] + RomanFives[digit.getValue()];
        }

        if (arabicDigit > 5) {
            romanDigit = RomanFives[digit.getValue()];
            onesQuantity = arabicDigit - 5;

        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[digit.getValue()];
        }

        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumber) {

        int unit = 0;

        for (int i = 0; i < romanNumber.length(); i++) {
            if (romanNumber.toUpperCase().charAt(i) == I.charAt(0)) {
                int j = i + 1;
                if (j == romanNumber.length() ||
                    romanNumber.toUpperCase().charAt(j) == I.charAt(0)) {
                    unit += 1;
                } else {
                    unit -= 1;
                }
            }

            if (romanNumber.toUpperCase().charAt(i) == V.charAt(0)) {
                unit += 5;
            }
        }

        return unit;
    }

}
