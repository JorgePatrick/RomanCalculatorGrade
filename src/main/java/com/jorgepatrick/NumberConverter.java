package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {
    static final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
    static final String[] RomanTens = {X.name(), C.name(), M.name()};
    static final String[] RomanFives = {V.name(), L.name(), D.name()};
    static final PowerOfTenNumbers[] PowerOfTenNumber = PowerOfTenNumbers.values();
    private final RomanNumber romanNumber;

    public NumberConverter(RomanNumber romanNumber) {
        this.romanNumber = romanNumber;
    }

    public String parseArabicToRoman(final int arabicNumberSum) {
        if (arabicNumberSum > 3999) {
            throw new IllegalArgumentException("Cannot convert number greater than 3999 to Roman");
        }

        String romanNumber = "";
        int arabicNumberLength = String.valueOf(arabicNumberSum).length();

        int[] arabicDigits = splitNumberInReverse(arabicNumberSum, arabicNumberLength);

        for(int position = arabicNumberLength - 1; position >= 0; position--) {
            romanNumber += getRomanDigit(arabicDigits, position);
        }
        return romanNumber;
    }

    private int[] splitNumberInReverse(int numberToSplit, final int length) {
        int[] numberInReverse = new int[length];
        int position = 0;

        while (numberToSplit > 0) {
            numberInReverse[position] =  ( numberToSplit % 10);
            numberToSplit = numberToSplit / 10;
            position++;
        }
        return numberInReverse;
    }

    private String getRomanDigit(final int[] arabicDigits, final int position) {
        int arabicDigit = arabicDigits[position];
        int powerOfTenPosition = PowerOfTenNumber[position].ordinal();

        String romanDigit = "";
        int onesQuantity = 0;

        if (arabicDigit == 0) {
            return "";
        }

        if (arabicDigit == 9) {
            return RomanOnes[powerOfTenPosition] + RomanTens[powerOfTenPosition];
        }

        if (arabicDigit == 5) {
            return RomanFives[powerOfTenPosition];
        }

        if (arabicDigit == 4) {
            return RomanOnes[powerOfTenPosition] + RomanFives[powerOfTenPosition];
        }

        if (arabicDigit > 5) {
            romanDigit = RomanFives[powerOfTenPosition];
            onesQuantity = arabicDigit - 5;
        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[powerOfTenPosition];
        }
        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumberStr) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        romanNumber.validate();

        return romanNumber.arabicNumber();
    }
}
