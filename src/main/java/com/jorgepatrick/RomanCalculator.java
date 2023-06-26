package com.jorgepatrick;

public class RomanCalculator {
    public String sumRomanNumbers(final String firstAddend, final String secondAddend) {

        int firstArabicNumber = 0;
        int secondArabicNumber = 0;
        firstArabicNumber = parseRomanToArabic(firstAddend);
        secondArabicNumber = parseRomanToArabic(secondAddend);

        int arabicNumberSum = firstArabicNumber + secondArabicNumber;

        return parseArabicToRoman(arabicNumberSum);
    }

    private String parseArabicToRoman(int arabicNumberSum) {
        String romanNumber = "";
        int unitQuantity = 0;

        if (arabicNumberSum == 5) {
            return "V";
        }

        if (arabicNumberSum == 4) {
            return "IV";
        }

        if (arabicNumberSum > 5) {
            romanNumber = "V";
            unitQuantity = arabicNumberSum - 5;

        } else {
            unitQuantity = arabicNumberSum;
        }

        for (int i = 0; i < unitQuantity; i++) {
            romanNumber += 'I';
        }

        return romanNumber;
    }

    private int parseRomanToArabic(final String romanNumber) {

        int unit = 0;

        for (int i = 0; i < romanNumber.length(); i++) {
            if (romanNumber.toUpperCase().charAt(i) == 'I') {
                unit += 1;
            }
        }
        return unit;
    }
}

