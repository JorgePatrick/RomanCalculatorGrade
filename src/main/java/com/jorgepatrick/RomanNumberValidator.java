package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;

public class RomanNumberValidator {
    public void validateRomanNumber(final RomanNumber romanNumber) {
        if (romanNumber.isNull()) {
            throw new IllegalArgumentException("Roman Number Cannot be Null");
        }

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            if (!isDigitRomanSymbol(romanNumber.digitAt(currentDigit))) {
                throw new IllegalArgumentException("Invalid Roman Digit");
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
                case X, L, C, D, M -> {
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

    private boolean isDigitRomanSymbol(String romanDigit) {
        try {
            RomanSymbols.valueOf(romanDigit);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
