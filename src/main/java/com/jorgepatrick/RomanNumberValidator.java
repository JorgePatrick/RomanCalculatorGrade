package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;

public class RomanNumberValidator {
    public void validateRomanNumber(final String romanNumber) {

        if (romanNumber == null) {
            throw new IllegalArgumentException("Roman Number Cannot be Null");
        }

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            if (!charIsRomanSymbol(romanNumber.toUpperCase().charAt(currentDigit))) {
                throw new IllegalArgumentException("Invalid Roman Digit");
            }
        }

        for (int currentDigit = 0; currentDigit < romanNumber.length(); currentDigit++) {
            int nextDigit = currentDigit + 1;

            if (nextDigit == romanNumber.length()) {
                continue;
            }

            switch (romanNumber.toUpperCase().charAt(currentDigit)){
                case 'I' -> {
                    validateI(romanNumber, nextDigit);
                }
                case 'V' -> {
                    validateV(romanNumber, nextDigit);
                }
                case 'X', 'L', 'C', 'D', 'M' -> {
                    break;
                }
            }
        }
    }

    private void validateI(String romanNumber, int nextDigit) {

        int twoAfterCurrentDigit = nextDigit + 1;

        switch (romanNumber.toUpperCase().charAt(nextDigit)) {
            case 'I' -> {
                if (twoAfterCurrentDigit == romanNumber.length()) {
                    break;
                }
                if (romanNumber.toUpperCase().charAt(twoAfterCurrentDigit) != I.value().charAt(0)) {
                    throw new IllegalArgumentException("Invalid Roman Number - After two I there can only be another I");
                }

                int threeAfterCurrentDigit = nextDigit + 2;

                if (threeAfterCurrentDigit < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an III");
                }

            }
            case 'V' -> {
                if (twoAfterCurrentDigit < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after IV");
                }
            }
            case 'X' -> {
                if (twoAfterCurrentDigit < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - Unexpected char after IX");
                }
            }
            case 'L', 'C', 'D', 'M' ->
                    throw new IllegalArgumentException("Invalid Roman Number - Invalid Roman Symbol after an I");
        }
    }

    private void validateV(String romanNumber, int nextDigit) {

        for (int currentDigit = nextDigit; currentDigit < romanNumber.length(); currentDigit++) {
            if (romanNumber.toUpperCase().charAt(currentDigit) != I.value().charAt(0)) {
                throw new IllegalArgumentException("Invalid Roman Number - No char different I allowed after one V");
            }
        }
    }

    private boolean charIsRomanSymbol(char romanChar) {
        if (romanChar != I.value().charAt(0) &&
            romanChar != V.value().charAt(0) &&
            romanChar != X.value().charAt(0) &&
            romanChar != L.value().charAt(0) &&
            romanChar != D.value().charAt(0) &&
            romanChar != C.value().charAt(0) &&
            romanChar != M.value().charAt(0)) {
            return false;
        } else {
            return true;
        }
    }
}
