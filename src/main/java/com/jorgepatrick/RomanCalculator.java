package com.jorgepatrick;

public class RomanCalculator {
    public String sumRomanNumbers(final String firstAddend, final String secondAddend) {

        NumberConverter coverter = new NumberConverter();
        final int firstArabicNumber = coverter.parseRomanToArabic(firstAddend);
        final int secondArabicNumber = coverter.parseRomanToArabic(secondAddend);

        int arabicNumberSum = firstArabicNumber + secondArabicNumber;

        return coverter.parseArabicToRoman(arabicNumberSum);
    }

}

