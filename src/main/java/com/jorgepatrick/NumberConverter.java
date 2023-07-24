package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {
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

    private static int[] splitNumberInReverse(int numberToSplit, final int length) {
        int[] numberInReverse = new int[length];
        int position = 0;

        while (numberToSplit > 0) {
            numberInReverse[position] =  ( numberToSplit % 10);
            numberToSplit = numberToSplit / 10;
            position++;
        }
        return numberInReverse;
    }

    private static String getRomanDigit(final int[] arabicDigits, final int position) {
        final String[] RomanOnes = {I.name(), X.name(), C.name(), M.name()};
        final String[] RomanTens = {X.name(), C.name(), M.name()};
        final String[] RomanFives = {V.name(), L.name(), D.name()};

        int arabicDigit = arabicDigits[position];

        switch (arabicDigit) {
            case 0 -> {
                return "";
            }
            case 9 -> {
                return RomanOnes[position] + RomanTens[position];
            }
            case 5 -> {
                return RomanFives[position];
            }
            case 4 -> {
                return RomanOnes[position] + RomanFives[position];
            }
        }

        String romanDigit = getRomanDigit(position, RomanOnes, RomanFives, arabicDigit);
        return romanDigit;
    }

    private static String getRomanDigit(int position, String[] RomanOnes, String[] RomanFives, int arabicDigit) {
        String romanDigit = "";
        int onesQuantity = 0;

        if (arabicDigit > 5) {
            romanDigit = RomanFives[position];
            onesQuantity = arabicDigit - 5;
        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[position];
        }
        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumberStr) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        romanNumber.validate();

        return romanNumber.arabicNumber();
    }
}
