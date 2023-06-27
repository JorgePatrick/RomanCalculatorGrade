package com.jorgepatrick;
import static com.jorgepatrick.RomanSymbols.*;

public class RomanCalculator {
    public String sumRomanNumbers(final String firstAddend, final String secondAddend) {

        final int firstArabicNumber = parseRomanToArabic(firstAddend);
        final int secondArabicNumber = parseRomanToArabic(secondAddend);

        int arabicNumberSum = firstArabicNumber + secondArabicNumber;

        return parseArabicToRoman(arabicNumberSum);
    }

    private String parseArabicToRoman(final int arabicNumberSum) {
        String romanNumber = "";
        int unitQuantity = 0;

        if (arabicNumberSum == 5) {
            return V;
        }

        if (arabicNumberSum == 4) {
            return I + V;
        }

        if (arabicNumberSum > 5) {
            romanNumber = V;
            unitQuantity = arabicNumberSum - 5;

        } else {
            unitQuantity = arabicNumberSum;
        }

        for (int i = 0; i < unitQuantity; i++) {
            romanNumber += I;
        }

        return romanNumber;
    }

    private int parseRomanToArabic(final String romanNumber) {

        int unit = 0;

        for (int i = 0; i < romanNumber.length(); i++) {
            if (romanNumber.toUpperCase().charAt(i) == I.charAt(0)) {
                unit += 1;
            }
        }
        return unit;
    }
}

