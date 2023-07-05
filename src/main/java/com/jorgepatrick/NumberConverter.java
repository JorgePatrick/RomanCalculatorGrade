package com.jorgepatrick;

import static com.jorgepatrick.Digits.*;
import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {
    static final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
    static final String[] RomanTens = {X.name(), C.name(), M.name()};
    static final String[] RomanFives = {V.name(), L.name(), D.name()};
    static final Digits[] RomanDigits = {UNIT, TEN, HUNDRED, THOUSAND};
    private final RomanNumber romanNumber;

    public NumberConverter(RomanNumber romanNumber) {
        this.romanNumber = romanNumber;
    }

    public String parseArabicToRoman(final int arabicNumberSum) {
        String romanNumber = "";
        String arabicNumberString = String.valueOf(arabicNumberSum);

        int[] arabicDigits = splitNumberInReverse(arabicNumberSum, arabicNumberString.length());

        for(int position = arabicNumberString.length() - 1; position >= 0; position--) {
            romanNumber += setRomanDigit(arabicDigits[position], RomanDigits[position]);
        }
        return romanNumber;
    }

    private int[] splitNumberInReverse(int numberToSplit, int length) {
        int[] numberInReverse = new int[length];
        int position = 0;

        while (numberToSplit > 0) {
            numberInReverse[position] =  ( numberToSplit % 10);
            numberToSplit = numberToSplit / 10;
            position++;
        }
        return numberInReverse;
    }

    private String setRomanDigit(int arabicDigit, Digits digit) {
        String romanDigit = "";
        int onesQuantity = 0;

        if (arabicDigit == 0) {
            return "";
        }

        if (arabicDigit == 9) {
            return RomanOnes[digit.ordinal()] + RomanTens[digit.ordinal()];
        }

        if (arabicDigit == 5) {
            return RomanFives[digit.ordinal()];
        }

        if (arabicDigit == 4) {
            return RomanOnes[digit.ordinal()] + RomanFives[digit.ordinal()];
        }

        if (arabicDigit > 5) {
            romanDigit = RomanFives[digit.ordinal()];
            onesQuantity = arabicDigit - 5;
        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[digit.ordinal()];
        }
        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumberStr) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        romanNumber.validate();

        return romanNumber.arabicNumber();
    }
}
