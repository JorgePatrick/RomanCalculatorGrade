package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;
import static com.jorgepatrick.RomanSymbols.I;

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
            if (romanNumber.toUpperCase().charAt(currentDigit) == I.value().charAt(0)) {

                int nextDigit = currentDigit + 1;

                if (nextDigit == romanNumber.length()) {
                    continue;
                }

                if (nextDigit + 2 < romanNumber.length()) {
                    throw new IllegalArgumentException("Invalid Roman Number - More than two chars after an I");
                }

                int twoAfterCurrentDigit = currentDigit + 2;

                switch (romanNumber.toUpperCase().charAt(nextDigit)) {
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
