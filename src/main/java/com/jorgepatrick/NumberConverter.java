package com.jorgepatrick;

import static com.jorgepatrick.RomanSymbols.*;

public class NumberConverter {
    public String parseArabicToRoman(final int arabicNumberSum) {
        String romanNumber = "";

        int[] arabicDigits = new int[2];
        String arabicNumberString = String.valueOf(arabicNumberSum);
        for(int i = 0; i < arabicNumberString.length(); i++) {
            arabicDigits[i] = Character.digit(arabicNumberString.charAt(i), 10);
        }
        if (arabicNumberString.length() == 2){
            romanNumber += setTenRomanDigit(arabicDigits[0]);
            romanNumber += setUnitRomanDigit(arabicDigits[1]);
        } else {
            romanNumber += setUnitRomanDigit(arabicDigits[0]);
        }

        return romanNumber;
    }

    private String setUnitRomanDigit(int arabicDigit) {

        String romanNumber = "";
        int unitQuantity = 0;

        if (arabicDigit == 0) {
            return "";
        }

        if (arabicDigit == 9) {
            return I + X;
        }

        if (arabicDigit == 5) {
            return V;
        }

        if (arabicDigit == 4) {
            return I + V;
        }

        if (arabicDigit > 5) {
            romanNumber = V;
            unitQuantity = arabicDigit - 5;

        } else {
            unitQuantity = arabicDigit;
        }

        for (int i = 0; i < unitQuantity; i++) {
            romanNumber += I;
        }

        return romanNumber;
    }

    private String setTenRomanDigit(int arabicDigit) {
        String romanNumber = "";
        int tenQuantity = 0;

        if (arabicDigit == 0) {
            return "";
        }

        if (arabicDigit == 9) {
            return X + C;
        }

        if (arabicDigit == 5) {
            return L;
        }

        if (arabicDigit == 4) {
            return X + L;
        }

        if (arabicDigit > 5) {
            romanNumber = L;
            tenQuantity = arabicDigit - 5;

        } else {
            tenQuantity = arabicDigit;
        }

        for (int i = 0; i < tenQuantity; i++) {
            romanNumber += X;
        }

        return romanNumber;
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
