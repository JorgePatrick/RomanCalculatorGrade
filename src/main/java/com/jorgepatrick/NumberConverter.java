package com.jorgepatrick;

import java.util.Arrays;

import static com.jorgepatrick.Digits.*;
import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {

    static final String[] RomanOnes = {I.value(), X.value(), C.value(), M.value()};
    static final String[] RomanTens = {X.value(), C.value(), M.value()};
    static final String[] RomanFives = {V.value(), L.value(), D.value()};
    static final Digits[] RomanDigits = {UNIT, TEN, HUNDRED, THOUSAND};
    private final RomanNumberValidator romanNumberValidator;

    public NumberConverter(RomanNumberValidator romanNumberValidator) {
        this.romanNumberValidator = romanNumberValidator;
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
            return RomanOnes[digit.position()] + RomanTens[digit.position()];
        }

        if (arabicDigit == 5) {
            return RomanFives[digit.position()];
        }

        if (arabicDigit == 4) {
            return RomanOnes[digit.position()] + RomanFives[digit.position()];
        }

        if (arabicDigit > 5) {
            romanDigit = RomanFives[digit.position()];
            onesQuantity = arabicDigit - 5;

        } else {
            onesQuantity = arabicDigit;
        }

        for (int i = 0; i < onesQuantity; i++) {
            romanDigit += RomanOnes[digit.position()];
        }

        return romanDigit;
    }

    public int parseRomanToArabic(final String romanNumber) {

        romanNumberValidator.validateRomanNumber(romanNumber);

        int arabicNumber = 0;

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {

            if (Arrays.stream(RomanOnes).toList().contains(romanNumber.substring(currentDigit, currentDigit + 1))) {
                int digit = Arrays.stream(RomanOnes).toList().indexOf(romanNumber.substring(currentDigit, currentDigit + 1));
                arabicNumber += getOnes(romanNumber, currentDigit, RomanDigits[digit]);
            }

            if (romanNumber.toUpperCase().charAt(currentDigit) == V.value().charAt(0)) {
                arabicNumber += 5;
            }
        }
        return arabicNumber;
    }

    private int getOnes(String romanNumber, int currentDigit, Digits digit) {

        int sum = 0;

        int nextDigit = currentDigit + 1;
        if (nextDigit == romanNumber.length() ||
            isNextDigitEqualOrLess(romanNumber.toUpperCase().charAt(currentDigit), romanNumber.toUpperCase().charAt(nextDigit))) {
            sum = 1;
        } else {
            sum = -1;
        }

        return sum * (RomanDigits[digit.position()]).arabicValue();
    }

    private boolean isNextDigitEqualOrLess(char currentDigit, char nextDigit) {
        int currentArabicDigit = parseRomanToArabic(currentDigit + "");
        int nextArabicDigit = parseRomanToArabic(nextDigit + "");
        return nextArabicDigit <= currentArabicDigit;
    }

}
