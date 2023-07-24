package com.jorgepatrick;

import java.util.List;

import static com.jorgepatrick.ComparisonResult.*;
import static com.jorgepatrick.RomanSymbols.*;
import static com.jorgepatrick.Utilities.enumValuesInList;

public class RomanNumberValidator {

    static final RomanSymbols[] romanSymbols = RomanSymbols.values();

    public void validateRomanNumber(final RomanNumber romanNumber) {
        if (romanNumber.isNull()) {
            throw new IllegalArgumentException("Roman Number Cannot be Null");
        }

        if (romanNumber.isEmpty()) {
            throw new IllegalArgumentException("Roman Number Cannot be Empty");
        }

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            if (!isDigitRomanSymbol(romanNumber.digitAt(currentDigit))) {
                throw new IllegalArgumentException("Invalid Roman Digit (" + romanNumber.digitAt(currentDigit) + ")");
            }
        }

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            int nextDigit = currentDigit + 1;

            if (!romanNumber.isThereCharInPosition(nextDigit)) {
                continue;
            }

            switch (RomanSymbols.valueOf(romanNumber.digitAt(currentDigit))){
                case I, X, C -> {
                    currentDigit = validateOnes(romanNumber, currentDigit);
                }
                case V, L, D -> {
                    validateFives(romanNumber, currentDigit);
                }
                case M -> {
                    validateM(romanNumber, currentDigit);
                }
            }
        }
    }

    private int validateOnes(RomanNumber romanNumber, int currentDigit) {
        int digitReturn = currentDigit;
        int nextDigit = currentDigit + 1;
        int twoAfterCurrentDigit = currentDigit + 2;
        int threeAfterCurrentDigit = currentDigit + 3;

        ComparisonResult firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(currentDigit), romanNumber.digitAt(nextDigit));
        if (firstDigitIs.equals(GREATER)) {
            return digitReturn;
        }

        int positionRomanSymbol =  RomanSymbols.valueOf(romanNumber.digitAt(currentDigit)).ordinal();
        RomanSymbols currentRomanSymbol = romanSymbols[positionRomanSymbol];
        RomanSymbols aboveRomanSymbol = romanSymbols[positionRomanSymbol + 1];
        RomanSymbols twoAboveRomanSymbol = romanSymbols[positionRomanSymbol + 2];

        if (RomanSymbols.valueOf(romanNumber.digitAt(nextDigit)).equals(currentRomanSymbol)){
            digitReturn = nextDigit;

            if (!romanNumber.isThereCharInPosition(twoAfterCurrentDigit)) {
                return digitReturn;
            }

            firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(nextDigit), romanNumber.digitAt(twoAfterCurrentDigit));

            if (firstDigitIs.equals(LESS)) {
                throw new IllegalArgumentException("Invalid Roman Number - After two " + currentRomanSymbol + " there can only be another " + currentRomanSymbol + " or a lesser symbol");
            }

            if (firstDigitIs.equals(EQUAL) &&
                romanNumber.isThereCharInPosition(threeAfterCurrentDigit)) {
                firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(twoAfterCurrentDigit), romanNumber.digitAt(threeAfterCurrentDigit));
                if (firstDigitIs.equals(LESS) ||
                    firstDigitIs.equals(EQUAL)) {
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after " + currentRomanSymbol + currentRomanSymbol + currentRomanSymbol);
                }
                digitReturn = twoAfterCurrentDigit;
            }

            return digitReturn;
        }

        if (RomanSymbols.valueOf(romanNumber.digitAt(nextDigit)).equals(aboveRomanSymbol) ||
            RomanSymbols.valueOf(romanNumber.digitAt(nextDigit)).equals(twoAboveRomanSymbol)) {
            if (romanNumber.isThereCharInPosition(twoAfterCurrentDigit)) {
                firstDigitIs = romanNumber.isFirstDigit(currentRomanSymbol.name(), romanNumber.digitAt(twoAfterCurrentDigit));
                if (!firstDigitIs.equals(GREATER)) {
                    throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after " + currentRomanSymbol + romanNumber.digitAt(nextDigit));
                }
            }
            return digitReturn;
        }

        throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after " + currentRomanSymbol);
    }

    private void validateFives(RomanNumber romanNumber, int currentDigit) {
        ComparisonResult firstDigitIs;
        int positionRomanSymbol =  RomanSymbols.valueOf(romanNumber.digitAt(currentDigit)).ordinal();
        RomanSymbols currentRomanSymbol = romanSymbols[positionRomanSymbol];

        for (int nextDigits = currentDigit + 1; nextDigits < romanNumber.length(); nextDigits++) {
            firstDigitIs = romanNumber.isFirstDigit(currentRomanSymbol.name(), romanNumber.digitAt(nextDigits));
            if (!firstDigitIs.equals(GREATER)) {
                throw new IllegalArgumentException("Invalid Roman Number - After a " + currentRomanSymbol + " there can only be lesser symbols");
            }
        }
    }

    private void validateM(RomanNumber romanNumber, int currentDigit) {
        int nextDigit = currentDigit + 1;
        int twoAfterCurrentDigit = currentDigit + 2;
        int threeAfterCurrentDigit = currentDigit + 3;

        if (!romanNumber.isThereCharInPosition(threeAfterCurrentDigit)) {
            return;
        }

        if (RomanSymbols.valueOf(romanNumber.digitAt(nextDigit)).equals(M) &&
                RomanSymbols.valueOf(romanNumber.digitAt(twoAfterCurrentDigit)).equals(M) &&
                RomanSymbols.valueOf(romanNumber.digitAt(threeAfterCurrentDigit)).equals(M)){
            throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after MMM");
        }
    }

    private boolean isDigitRomanSymbol(String romanDigit) {
        List<RomanSymbols> romanSymbols = enumValuesInList(RomanSymbols.class);

        return (romanSymbols.stream().anyMatch(e -> e.name().equals(romanDigit)));
    }
}
