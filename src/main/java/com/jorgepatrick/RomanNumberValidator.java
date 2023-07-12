package com.jorgepatrick;

import java.util.List;

import static com.jorgepatrick.ComparisonResult.*;
import static com.jorgepatrick.RomanSymbols.*;
import static com.jorgepatrick.Utilities.enumValuesInList;

public class RomanNumberValidator {
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
                case I -> {
                    validateI(romanNumber, nextDigit);
                }
                case V -> {
                    validateV(romanNumber, nextDigit);
                }
                case X -> {
                    currentDigit = validateX(romanNumber, currentDigit);
                }
                case L, C, D, M -> {
                    break;
                }
            }
        }
    }

    private void validateI(RomanNumber romanNumber, int nextDigit) {
        int twoAfterCurrentDigit = nextDigit + 1;

        switch (RomanSymbols.valueOf(romanNumber.digitAt(nextDigit))){
            case I -> {
                if (!romanNumber.isThereCharInPosition(twoAfterCurrentDigit)) {
                    break;
                }

                if (!romanNumber.digitAt(twoAfterCurrentDigit).equals(I.name())) {
                    throw new IllegalArgumentException("Invalid Roman Number - After two I there can only be another I");
                }

                int threeAfterCurrentDigit = nextDigit + 2;

                if (romanNumber.isThereCharInPosition(threeAfterCurrentDigit)) {
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an III");
                }
            }
            case V -> {
                if (twoAfterCurrentDigit < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after IV");
                }
            }
            case X -> {
                if (twoAfterCurrentDigit < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after IX");
                }
            }
            case L, C, D, M ->
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an I");
        }
    }

    private void validateV(RomanNumber romanNumber, int nextDigit) {
        for (int currentDigit = nextDigit; currentDigit < romanNumber.length(); currentDigit++) {
            if (!romanNumber.digitAt(currentDigit).equals(I.name())) {
                throw new IllegalArgumentException("Invalid Roman Number - No char different I allowed after one V");
            }
        }
    }

    private int validateX(RomanNumber romanNumber, int currentDigit) {
        int currentDigitReturn = currentDigit;
        int nextDigit = currentDigit + 1;
        int twoAfterCurrentDigit = currentDigit + 2;
        int threeAfterCurrentDigit = currentDigit + 3;

        ComparisonResult firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(currentDigit), romanNumber.digitAt(nextDigit));
        if (firstDigitIs.equals(GREATER)) {
            return currentDigit;
        }

        switch (RomanSymbols.valueOf(romanNumber.digitAt(nextDigit))){
            case X -> {
                if (!romanNumber.isThereCharInPosition(twoAfterCurrentDigit)) {
                    break;
                }

                firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(nextDigit), romanNumber.digitAt(twoAfterCurrentDigit));

                if (firstDigitIs.equals(LESS)) {
                    throw new IllegalArgumentException("Invalid Roman Number - After two X there can only be another X or a lesser symbol");
                }

                if (firstDigitIs.equals(GREATER)) {
                    currentDigitReturn = twoAfterCurrentDigit;
                    break;
                }

                if (romanNumber.isThereCharInPosition(threeAfterCurrentDigit)) {
                    firstDigitIs = romanNumber.isFirstDigit(romanNumber.digitAt(twoAfterCurrentDigit), romanNumber.digitAt(threeAfterCurrentDigit));
                    if (firstDigitIs.equals(LESS) ||
                        firstDigitIs.equals(EQUAL)) {
                        throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an XXX");
                    }
                    currentDigitReturn = threeAfterCurrentDigit;
                }
            }
            case L, C -> {
                if (romanNumber.isThereCharInPosition(twoAfterCurrentDigit)) {
                    firstDigitIs = romanNumber.isFirstDigit(X.name(), romanNumber.digitAt(twoAfterCurrentDigit));
                    if (!firstDigitIs.equals(GREATER)) {
                        throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after X" + romanNumber.digitAt(nextDigit));
                    }
                }
                currentDigitReturn = nextDigit;
            }
            case D, M ->
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an X");
        }

        return currentDigitReturn;
    }

    private boolean isDigitRomanSymbol(String romanDigit) {
        List<RomanSymbols> romanSymbols = enumValuesInList(RomanSymbols.class);

        for (RomanSymbols romanSymbol : romanSymbols) {
            if (romanSymbol.name().equals(romanDigit)) {
                return true;
            }
        }

        return false;
    }
}
